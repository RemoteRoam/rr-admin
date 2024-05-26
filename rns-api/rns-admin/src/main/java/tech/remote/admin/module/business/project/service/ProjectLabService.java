package tech.remote.admin.module.business.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.remote.admin.module.business.project.dao.ProjectLabDao;
import tech.remote.admin.module.business.project.dao.ProjectProductDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectEntity;
import tech.remote.admin.module.business.project.domain.entity.ProjectLabEntity;
import tech.remote.admin.module.business.project.domain.entity.ProjectProductEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectLabAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectLabQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectLabUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectLabProgressVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectLabVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductProgressVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductVO;
import tech.remote.admin.module.business.project.manager.ProjectManager;
import tech.remote.admin.module.business.project.manager.ProjectProductManager;
import tech.remote.admin.module.business.projectnode.domain.entity.ProjectNodeEntity;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeQueryForm;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
import tech.remote.admin.module.business.projectnode.manager.ProjectNodeManager;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQuery;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.admin.module.business.typenode.service.TypeNodeService;
import tech.remote.base.common.code.BusinessErrorCode;
import tech.remote.base.common.constant.StringConst;
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
import tech.remote.base.module.support.serialnumber.constant.SerialNumberIdEnum;
import tech.remote.base.module.support.serialnumber.service.SerialNumberService;

import javax.annotation.Resource;

/**
 * 项目实验室任务表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectLabService {

    @Resource
    private ProjectLabDao projectLabDao;

    @Autowired
    private SerialNumberService serialNumberService;

    @Autowired
    private TypeNodeService typeNodeService;

    @Resource
    private ProjectNodeManager projectNodeManager;

    @Resource
    private ProjectProductManager projectProductManager;

    @Resource
    private ProjectProductDao projectProductDao;

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
    public PageResult<ProjectLabVO> queryPage(ProjectLabQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectLabVO> list = projectLabDao.queryPage(page, queryForm);
        if(CollectionUtils.isNotEmpty(list)){
            for(ProjectLabVO vo : list){
                ProjectNodeQueryForm nodeQueryForm = new ProjectNodeQueryForm();
                nodeQueryForm.setProjectId(vo.getProjectId());
                nodeQueryForm.setProjectType(queryForm.getProjectType());
                nodeQueryForm.setTaskId(vo.getId());
                nodeQueryForm.setNodeLevel(2);
                vo.setProjectNodeList(projectNodeManager.getOperateNodes(nodeQueryForm));
            }
        }
        PageResult<ProjectLabVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ProjectLabAddForm addForm) {
        ProjectLabEntity projectLabEntity = SmartBeanUtil.copy(addForm, ProjectLabEntity.class);

        String projectNo = serialNumberService.generate(SerialNumberIdEnum.PROJECT_TASK);
        projectLabEntity.setTaskNo(projectNo);
        projectLabEntity.setStatus(ProjectStatusEnum.DOING.getValue());

        String uuid = UUID.randomUUID().toString().replace("-", StringConst.EMPTY);
        projectLabEntity.setProgressCode(uuid);

        projectLabDao.insert(projectLabEntity);

        // 获取该类型下的对应节点
        TypeNodeQuery query = new TypeNodeQuery();
        query.setNodeLevel(2);
        query.setProjectType(addForm.getProjectType());
        List<TypeNodeListVO> typeNodeList = typeNodeService.getTypeNodes(query);

        List<ProjectNodeEntity> projectNodeList = SmartBeanUtil.copyList(typeNodeList, ProjectNodeEntity.class);
        for(ProjectNodeEntity projectNode : projectNodeList){
            projectNode.setId(null);
            projectNode.setProjectId(addForm.getProjectId());
            projectNode.setProjectNo(projectNo);
            projectNode.setTaskId(projectLabEntity.getId());
            projectNode.setProjectType(addForm.getProjectType());
            projectNode.setStatus(NodeStatusEnum.INIT.getValue());
            projectNode.setCreateUserId(addForm.getCreateUserId());
            projectNode.setCreateUserName(addForm.getCreateUserName());
        }
        projectNodeManager.saveBatch(projectNodeList);

        // 保存产品列表
        List<ProjectProductEntity> projectProductList = SmartBeanUtil.copyList(addForm.getProducts(), ProjectProductEntity.class);
        for(ProjectProductEntity projectProduct : projectProductList){
            projectProduct.setId(null);
            projectProduct.setTaskId(projectLabEntity.getId());
            projectProduct.setProjectId(projectLabEntity.getProjectId());
            projectProduct.setStatus(ProjectStatusEnum.DOING.getValue());
            projectProduct.setCreateUserId(addForm.getCreateUserId());
            projectProduct.setCreateUserName(addForm.getCreateUserName());
        }
        projectProductManager.saveBatch(projectProductList);

        // 获取该类型下的对应节点
        TypeNodeQuery query2 = new TypeNodeQuery();
        query2.setNodeLevel(3);
        query2.setProjectType(addForm.getProjectType());
        List<TypeNodeListVO> typeNodeList2 = typeNodeService.getTypeNodes(query2);

        for(ProjectProductEntity projectProduct : projectProductList){
            List<ProjectNodeEntity> projectNodeList2 = SmartBeanUtil.copyList(typeNodeList2, ProjectNodeEntity.class);
            for(ProjectNodeEntity projectNode : projectNodeList2){
                projectNode.setId(null);
                projectNode.setProjectId(addForm.getProjectId());
                projectNode.setProjectNo(projectNo);
                projectNode.setTaskId(projectLabEntity.getId());
                projectNode.setProductId(projectProduct.getId());
                projectNode.setProjectType(addForm.getProjectType());
                projectNode.setStatus(NodeStatusEnum.INIT.getValue());
                projectNode.setCreateUserId(addForm.getCreateUserId());
                projectNode.setCreateUserName(addForm.getCreateUserName());
            }
            projectNodeManager.saveBatch(projectNodeList2);
        }

        dataTracerService.insert(projectLabEntity.getId(), DataTracerTypeEnum.PROJECT_LAB_TASK);

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ProjectLabUpdateForm updateForm) {
        ProjectLabEntity oldEntity = projectLabDao.selectById(updateForm.getId());
        ProjectLabEntity projectLabEntity = SmartBeanUtil.copy(updateForm, ProjectLabEntity.class);
        projectLabDao.updateById(projectLabEntity);

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
            dataTracerService.addTrace(updateForm.getId(), DataTracerTypeEnum.PROJECT_LAB_TASK, content);
            // 判断是否所有节点都已完成或者跳过，如果是，修改状态为已完成
            ProjectNodeQueryForm queryForm = new ProjectNodeQueryForm();
            queryForm.setProjectId(updateForm.getProjectId());
            queryForm.setProjectType(updateForm.getProjectType());
            queryForm.setTaskId(updateForm.getId());
            queryForm.setNodeLevel(2);

            if (projectNodeManager.isAllDone(queryForm)) {
                projectLabEntity.setStatus(ProjectStatusEnum.DONE.getValue());
                projectLabDao.updateById(projectLabEntity);
                dataTracerService.addTrace(projectLabEntity.getId(), DataTracerTypeEnum.PROJECT_LAB_TASK, "项目完成");
            }
        } else {

            //变更记录
            dataTracerService.update(projectLabEntity.getId(), DataTracerTypeEnum.PROJECT_LAB_TASK, oldEntity, projectLabEntity);
        }

        return ResponseDTO.ok();
    }

    public ProjectLabVO getDetail(Long id) {
        ProjectLabVO vo = projectLabDao.getDetail(id);

        ProjectEntity projectEntity = projectManager.getById(vo.getProjectId());
        ProjectNodeQueryForm queryForm = new ProjectNodeQueryForm();
        queryForm.setProjectId(vo.getProjectId());
        queryForm.setProjectType(projectEntity.getProjectType());
        queryForm.setTaskId(vo.getId());
        queryForm.setNodeLevel(2);
        List<ProjectNodeVO> projectNodeList = projectNodeManager.getAllNodes(queryForm);
        vo.setProjectNodeList(projectNodeList);

        // 构建一个lambdaQuery，根据projectId和taskId获取产品列表
        LambdaQueryWrapper<ProjectProductEntity> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ProjectProductEntity::getProjectId, vo.getProjectId());
        queryWrapper.eq(ProjectProductEntity::getTaskId, id);
        List<ProjectProductEntity> projectProductList = projectProductManager.list(queryWrapper);

        List<ProjectProductVO> projectProductVOList = SmartBeanUtil.copyList(projectProductList, ProjectProductVO.class);
//        for(ProjectProductVO productVO : projectProductVOList){
//
//            ProjectNodeQueryForm nodeQueryForm = new ProjectNodeQueryForm();
//            nodeQueryForm.setProjectId(vo.getProjectId());
//            nodeQueryForm.setProjectType(queryForm.getProjectType());
//            nodeQueryForm.setTaskId(vo.getId());
//            nodeQueryForm.setProductId(productVO.getId());
//            nodeQueryForm.setNodeLevel(3);
//            productVO.setProjectNodeList(projectNodeManager.getOperateNodes(nodeQueryForm));
//        }
        vo.setProjectProductList(projectProductVOList);

        return vo;
    }

    public ProjectLabProgressVO getProgress(String progressCode) {
        if(StringUtils.isEmpty(progressCode)){
            return null;
        }
        ProjectLabProgressVO vo = projectLabDao.getByCode(progressCode);
        List<ProjectProductProgressVO> projectProductList = projectProductDao.list(vo.getProjectId(), vo.getId());

        vo.setProjectProductList(projectProductList);
        return vo;
    }
}
