package tech.remote.admin.module.business.project.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.remote.admin.module.business.project.dao.ProjectDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectVO;
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
import tech.remote.base.common.enumeration.ProjectTypeEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import tech.remote.base.module.support.datatracer.constant.DataTracerTypeEnum;
import tech.remote.base.module.support.datatracer.service.DataTracerService;
import tech.remote.base.module.support.serialnumber.constant.SerialNumberIdEnum;
import tech.remote.base.module.support.serialnumber.service.SerialNumberService;

import javax.annotation.Resource;

/**
 * 项目表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectService {

    @Resource
    private ProjectDao projectDao;

    @Autowired
    private SerialNumberService serialNumberService;

    @Autowired
    private TypeNodeService typeNodeService;

    @Resource
    private ProjectNodeManager projectNodeManager;

    @Resource
    private DataTracerService dataTracerService;
    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectVO> queryPage(ProjectQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectVO> list = projectDao.queryPage(page, queryForm);
        if(CollectionUtils.isNotEmpty(list)){
            for(ProjectVO vo : list){
                ProjectNodeQueryForm nodeQueryForm = new ProjectNodeQueryForm();
                nodeQueryForm.setProjectId(vo.getId());
                nodeQueryForm.setProjectType(vo.getProjectType());
                nodeQueryForm.setNodeLevel(1);
                vo.setProjectNodeList(projectNodeManager.getOperateNodes(nodeQueryForm));
            }
        }
        PageResult<ProjectVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ProjectAddForm addForm) {
        ProjectEntity projectEntity = SmartBeanUtil.copy(addForm, ProjectEntity.class);

        String projectNo = serialNumberService.generate(SerialNumberIdEnum.PROJECT);
        projectEntity.setProjectNo(projectNo);
        projectEntity.setStatus(ProjectStatusEnum.DOING.getValue());

        projectDao.insert(projectEntity);

        // 获取该类型下的对应节点
        TypeNodeQuery query = new TypeNodeQuery();
        query.setNodeLevel(1);
        query.setProjectType(addForm.getProjectType());
        List<TypeNodeListVO> typeNodeList = typeNodeService.getTypeNodes(query);

        List<ProjectNodeEntity> projectNodeList = SmartBeanUtil.copyList(typeNodeList, ProjectNodeEntity.class);
        for(ProjectNodeEntity projectNode : projectNodeList){
            projectNode.setId(null);
            projectNode.setProjectId(projectEntity.getId());
            projectNode.setProjectNo(projectNo);
            projectNode.setProjectType(addForm.getProjectType());
            projectNode.setStatus(NodeStatusEnum.INIT.getValue());
            projectNode.setCreateUserId(addForm.getCreateUserId());
            projectNode.setCreateUserName(addForm.getCreateUserName());
        }
        projectNodeManager.saveBatch(projectNodeList);

        dataTracerService.insert(projectEntity.getId(), DataTracerTypeEnum.fromValue(addForm.getProjectType()));
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ProjectUpdateForm updateForm) {
        ProjectEntity oldEntity = projectDao.selectById(updateForm.getId());
        ProjectEntity projectEntity = SmartBeanUtil.copy(updateForm, ProjectEntity.class);
        projectDao.updateById(projectEntity);

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
            dataTracerService.addTrace(updateForm.getId(), DataTracerTypeEnum.fromValue(updateForm.getProjectType()), content);
            // 判断是否所有节点都已完成或者跳过，如果是，修改状态为已完成
            ProjectNodeQueryForm queryForm = new ProjectNodeQueryForm();
            queryForm.setProjectId(updateForm.getId());
            queryForm.setProjectType(updateForm.getProjectType());
            queryForm.setNodeLevel(1);

            if (projectNodeManager.isAllDone(queryForm)) {
                projectEntity.setStatus(ProjectStatusEnum.DONE.getValue());
                projectDao.updateById(projectEntity);
                dataTracerService.addTrace(projectEntity.getId(), DataTracerTypeEnum.fromValue(updateForm.getProjectType()), "项目完成");
            }
        } else {

            //变更记录
            dataTracerService.update(projectEntity.getId(), DataTracerTypeEnum.fromValue(updateForm.getProjectType()), oldEntity, projectEntity);
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

        projectDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        projectDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    public ProjectVO getDetail(Long id) {
        ProjectVO vo = projectDao.getDetail(id);

        ProjectNodeQueryForm queryForm = new ProjectNodeQueryForm();
        queryForm.setProjectId(id);
        queryForm.setProjectType(vo.getProjectType());
        queryForm.setNodeLevel(1);
        List<ProjectNodeVO> projectNodeList = projectNodeManager.getAllNodes(queryForm);
        vo.setProjectNodeList(projectNodeList);
        return vo;
    }
}
