package tech.remote.admin.module.business.project.service;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.remote.admin.module.business.project.dao.ProjectDao;
import tech.remote.admin.module.business.project.domain.entity.*;
import tech.remote.admin.module.business.project.domain.form.*;
import tech.remote.admin.module.business.project.domain.vo.ProjectAlarmCountVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectExcelVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectVO;
import tech.remote.admin.module.business.project.manager.ProjectArchiveManager;
import tech.remote.admin.module.business.project.manager.ProjectCertificationFeeManager;
import tech.remote.admin.module.business.project.manager.ProjectMailManager;
import tech.remote.admin.module.business.project.manager.ProjectProductManager;
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

    @Resource
    private ProjectProductManager projectProductManager;

    @Resource
    private ProjectCertificationFeeManager projectCertificationFeeManager;

    @Resource
    private ProjectArchiveManager projectArchiveManager;

    @Resource
    private ProjectMailManager projectMailManager;

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
     * 待办列表分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectVO> queryToDoPage(ProjectToDoQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectVO> list = projectDao.queryToDoPage(page, queryForm);
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
        if( updateForm.getProjectNodeId() != null && updateForm.getNodeStatus() != null) {

            // 交认证费、归档、邮寄，三个节点要处理产品列表
            if(updateForm.getNodeStatus().equals(NodeStatusEnum.OK.getValue()) && CollectionUtils.isNotEmpty(updateForm.getProductIdList())){

                boolean isAllDone = isProductAllDone(updateForm);
                if(isAllDone){
                    updateForm.setNodeStatus(NodeStatusEnum.OK.getValue());
                } else {
                    updateForm.setNodeStatus(NodeStatusEnum.DOING.getValue());
                }
            }

            // 根据projectNodeId为条件，更新systemCertificationNode的状态
            ProjectNodeEntity projectNodeEntity = projectNodeManager.getById(updateForm.getProjectNodeId());
            if(projectNodeEntity.getStatus().equals(NodeStatusEnum.INIT.getValue()) || projectNodeEntity.getStatus().equals(NodeStatusEnum.DOING.getValue())){
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
            if(updateForm.getNodeStatus().equals(NodeStatusEnum.OK.getValue()) || updateForm.getNodeStatus().equals(NodeStatusEnum.SKIP.getValue())) {
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
            }
        } else {

            //变更记录
            dataTracerService.update(projectEntity.getId(), DataTracerTypeEnum.fromValue(updateForm.getProjectType()), oldEntity, projectEntity);
        }

        return ResponseDTO.ok();
    }

    private boolean isProductAllDone(ProjectUpdateForm updateForm) {

        if(updateForm.getNodeId() == 15){
            //交认证费
            ProjectCertificationFeeEntity projectCertificationFee = SmartBeanUtil.copy(updateForm, ProjectCertificationFeeEntity.class);
            projectCertificationFee.setId(null);
            projectCertificationFee.setProjectId(updateForm.getId());
            projectCertificationFeeManager.save(projectCertificationFee);

            for(Long productId : updateForm.getProductIdList()){
                ProjectProductEntity productEntity = new ProjectProductEntity();
                productEntity.setId(productId);
                productEntity.setCertificationFeeId(projectCertificationFee.getId());

                projectProductManager.updateById(productEntity);
            }

            List<ProjectProductVO> allProducts = getAllProduct(updateForm.getId());
            // 判断所有product中的CertificationFeeId都不为空
            for(ProjectProductVO product : allProducts){
                if(product.getCertificationFeeId() == null){
                    return false;
                }
            }
            return true;

        } else if(updateForm.getNodeId() == 19){
            // 归档
            ProjectArchiveEntity projectArchive = SmartBeanUtil.copy(updateForm, ProjectArchiveEntity.class);
            projectArchive.setId(null);
            projectArchive.setProjectId(updateForm.getId());
            projectArchiveManager.save(projectArchive);

            for(Long productId : updateForm.getProductIdList()){
                ProjectProductEntity productEntity = new ProjectProductEntity();
                productEntity.setId(productId);
                productEntity.setArchiveId(projectArchive.getId());

                projectProductManager.updateById(productEntity);
            }

            List<ProjectProductVO> allProducts = getAllProduct(updateForm.getId());
            // 判断所有product中的ArchiveId都不为空
            for(ProjectProductVO product : allProducts){
                if(product.getArchiveId() == null){
                    return false;
                }
            }
            return true;

        } else if(updateForm.getNodeId() == 20){
            // 邮寄
            ProjectMailEntity projectMail = SmartBeanUtil.copy(updateForm, ProjectMailEntity.class);
            projectMail.setId(null);
            projectMail.setProjectId(updateForm.getId());
            projectMailManager.save(projectMail);

            for(Long productId : updateForm.getProductIdList()){
                ProjectProductEntity productEntity = new ProjectProductEntity();
                productEntity.setId(productId);
                productEntity.setMailId(projectMail.getId());

                projectProductManager.updateById(productEntity);
            }

            List<ProjectProductVO> allProducts = getAllProduct(updateForm.getId());
            // 判断所有product中的MailId都不为空
            for(ProjectProductVO product : allProducts){
                if(product.getMailId() == null){
                    return false;
                }
            }
            return true;

        }
        return true;
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

    public List<ProjectProductVO> getAllProduct(Long projectId) {
        // 根据projectID获取产品列表
        LambdaQueryWrapper<ProjectProductEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ProjectProductEntity::getProjectId, projectId);
        List<ProjectProductEntity> productList = projectProductManager.list(wrapper);
        return SmartBeanUtil.copyList(productList, ProjectProductVO.class);
    }

    public List<ProjectProductVO> getPendingProduct(Long projectId, Integer nodeId) {
        // 根据projectID和nodeId获取待处理的产品列表
        LambdaQueryWrapper<ProjectProductEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ProjectProductEntity::getProjectId, projectId);
        if(nodeId == 15){
            wrapper.isNull(ProjectProductEntity::getCertificationFeeId);
        } else if(nodeId == 19){
            wrapper.isNull(ProjectProductEntity::getArchiveId);
        } else if(nodeId == 20){
            wrapper.isNull(ProjectProductEntity::getMailId);
        }

        List<ProjectProductEntity> productList = projectProductManager.list(wrapper);
        return SmartBeanUtil.copyList(productList, ProjectProductVO.class);
    }

    public List<ProjectExcelVO> getExcelExportData(ProjectQueryForm queryForm) {
        List<ProjectExcelVO> excelList = projectDao.selectExcelList(queryForm);
        return excelList;
    }

    /**
     * 预警分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectVO> queryAlarmPage(ProjectAlarmQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectVO> list = projectDao.queryAlarmPage(page, queryForm);
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

    public ProjectAlarmCountVO getAlarmCount(){

        ProjectAlarmCountVO vo = new ProjectAlarmCountVO();
        vo.setExpectedDateAlarm(projectDao.queryAlarmCount(1));
        vo.setLabExpectedDateAlarm(projectDao.queryAlarmCount(2));
        vo.setAuditDateAlarm(projectDao.queryAlarmCount(3));
        vo.setNonConformityCorrectionAlarm(projectDao.queryAlarmCount(4));

        return vo;
    }
}
