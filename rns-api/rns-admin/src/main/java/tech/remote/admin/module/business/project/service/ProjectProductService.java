package tech.remote.admin.module.business.project.service;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.remote.admin.module.business.project.dao.ProjectProductDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectEntity;
import tech.remote.admin.module.business.project.domain.entity.ProjectLabEntity;
import tech.remote.admin.module.business.project.domain.entity.ProjectProductEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectProductAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectProductQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectProductUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectLabVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductVO;
import tech.remote.admin.module.business.project.manager.ProjectManager;
import tech.remote.admin.module.business.projectnode.domain.entity.ProjectNodeEntity;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeQueryForm;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
import tech.remote.admin.module.business.projectnode.manager.ProjectNodeManager;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQuery;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.admin.module.business.typenode.service.TypeNodeService;
import tech.remote.base.common.code.BusinessErrorCode;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.enumeration.ProjectStatusEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import tech.remote.base.module.support.datatracer.constant.DataTracerTypeEnum;
import tech.remote.base.module.support.datatracer.service.DataTracerService;

import javax.annotation.Resource;

/**
 * 项目产品表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectProductService {

    @Resource
    private ProjectProductDao projectProductDao;

    @Autowired
    private TypeNodeService typeNodeService;

    @Resource
    private ProjectNodeManager projectNodeManager;

    @Resource
    private DataTracerService dataTracerService;

    @Resource
    private ProjectManager projectManager;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectProductVO> queryPage(ProjectProductQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectProductVO> list = projectProductDao.queryPage(page, queryForm);
        if(CollectionUtils.isNotEmpty(list) &&
                (queryForm.getCertificationFeeId() != null || queryForm.getArchiveId() != null || queryForm.getMailId() != null)){
            for(ProjectProductVO productVO : list){

                ProjectNodeQueryForm nodeQueryForm = new ProjectNodeQueryForm();
                nodeQueryForm.setProjectId(queryForm.getProjectId());
                nodeQueryForm.setProjectType(queryForm.getProjectType());
                nodeQueryForm.setTaskId(queryForm.getTaskId());
                nodeQueryForm.setProductId(productVO.getId());
                nodeQueryForm.setNodeLevel(3);
                productVO.setProjectNodeList(projectNodeManager.getOperateNodes(nodeQueryForm));
            }
        }
        PageResult<ProjectProductVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ProjectProductAddForm addForm) {
        ProjectProductEntity projectProductEntity = SmartBeanUtil.copy(addForm, ProjectProductEntity.class);
        projectProductEntity.setStatus(ProjectStatusEnum.DOING.getValue());
        projectProductDao.insert(projectProductEntity);

        // 获取该类型下的对应节点
        TypeNodeQuery query2 = new TypeNodeQuery();
        query2.setNodeLevel(3);
        query2.setProjectType(addForm.getProjectType());
        List<TypeNodeListVO> typeNodeList2 = typeNodeService.getTypeNodes(query2);
        List<ProjectNodeEntity> projectNodeList2 = SmartBeanUtil.copyList(typeNodeList2, ProjectNodeEntity.class);
        for(ProjectNodeEntity projectNode : projectNodeList2){
            projectNode.setId(null);
            projectNode.setProjectId(addForm.getProjectId());
//            projectNode.setProjectNo(projectNo);
            projectNode.setTaskId(addForm.getTaskId());
            projectNode.setProductId(projectProductEntity.getId());
            projectNode.setProjectType(addForm.getProjectType());
            projectNode.setStatus(NodeStatusEnum.INIT.getValue());
            projectNode.setCreateUserId(addForm.getCreateUserId());
            projectNode.setCreateUserName(addForm.getCreateUserName());
        }
        projectNodeManager.saveBatch(projectNodeList2);

        dataTracerService.insert(projectProductEntity.getId(), DataTracerTypeEnum.PROJECT_PRODUCT);

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ProjectProductUpdateForm updateForm) {
        ProjectProductEntity oldEntity = projectProductDao.selectById(updateForm.getId());
        ProjectProductEntity projectProductEntity = SmartBeanUtil.copy(updateForm, ProjectProductEntity.class);
        projectProductDao.updateById(projectProductEntity);

        // 节点操作更新
        if(updateForm.getProjectNodeId() != null && updateForm.getNodeStatus() != null) {
            // 根据projectId和nodeId为条件，更新systemCertificationNode的状态

            ProjectNodeEntity projectNodeEntity = projectNodeManager.getById(updateForm.getProjectNodeId());
            if(projectNodeEntity.getStatus() == NodeStatusEnum.INIT.getValue() || projectNodeEntity.getStatus() == NodeStatusEnum.DOING.getValue()){
                projectNodeEntity.setOperateTime(LocalDateTime.now());
                projectNodeEntity.setOperateUserId(updateForm.getUpdateUserId());
                projectNodeEntity.setOperateUserName(updateForm.getUpdateUserName());
                projectNodeEntity.setPassReason(updateForm.getPassReason());
                projectNodeEntity.setStatus(updateForm.getNodeStatus());

            } else {
                return ResponseDTO.error(BusinessErrorCode.PROJECT_NODE_ALREADY_PROCESSED);
            }

            // Execute the update
            projectNodeManager.updateById(projectNodeEntity);
            String content = "节点操作：【" + projectNodeEntity.getNodeName() + "】" + NodeStatusEnum.getDescByValue(updateForm.getNodeStatus());
            dataTracerService.addTrace(updateForm.getId(), DataTracerTypeEnum.PROJECT_PRODUCT, content);
            // 判断是否所有节点都已完成或者跳过，如果是，修改状态为已完成
            ProjectNodeQueryForm queryForm = new ProjectNodeQueryForm();
            queryForm.setProjectId(updateForm.getProjectId());
            queryForm.setTaskId(updateForm.getTaskId());
            queryForm.setProductId(updateForm.getId());
            queryForm.setNodeLevel(3);

            if (projectNodeManager.isAllDone(queryForm)) {
                projectProductEntity.setStatus(ProjectStatusEnum.DONE.getValue());
                projectProductDao.updateById(projectProductEntity);
                dataTracerService.addTrace(projectProductEntity.getId(), DataTracerTypeEnum.PROJECT_PRODUCT, "项目完成");
            }
        } else {

            //变更记录
            dataTracerService.update(projectProductEntity.getId(), DataTracerTypeEnum.PROJECT_PRODUCT, oldEntity, projectProductEntity);
        }
        return ResponseDTO.ok();
    }

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    public ResponseDTO<String> batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)){
            return ResponseDTO.ok();
        }

        projectProductDao.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        projectProductDao.deleteById(id);
        return ResponseDTO.ok();
    }

    public ProjectProductVO getDetail(Long id) {
        ProjectProductEntity entity = projectProductDao.selectById(id);
        ProjectProductVO vo = SmartBeanUtil.copy(entity, ProjectProductVO.class);

        ProjectEntity projectEntity = projectManager.getById(vo.getProjectId());
        ProjectNodeQueryForm queryForm = new ProjectNodeQueryForm();
        queryForm.setProjectId(vo.getProjectId());
        queryForm.setProjectType(projectEntity.getProjectType());
        queryForm.setTaskId(vo.getTaskId());
        queryForm.setProductId(vo.getId());
        queryForm.setNodeLevel(3);
        List<ProjectNodeVO> projectNodeList = projectNodeManager.getAllNodes(queryForm);
        vo.setProjectNodeList(projectNodeList);

        return vo;
    }
}
