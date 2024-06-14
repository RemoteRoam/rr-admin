package tech.remote.admin.module.business.systemcertification.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.remote.admin.module.business.customer.service.CustomerService;
import tech.remote.admin.module.business.node.domain.entity.NodeEntity;
import tech.remote.admin.module.business.node.manager.NodeManager;
import tech.remote.admin.module.business.systemcertification.dao.SystemCertificationDao;
import tech.remote.admin.module.business.systemcertification.domain.entity.SystemCertificationEntity;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationAddForm;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationQueryForm;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationUpdateForm;
import tech.remote.admin.module.business.systemcertification.domain.vo.SystemCertificationExcelVO;
import tech.remote.admin.module.business.systemcertification.domain.vo.SystemCertificationVO;
import tech.remote.admin.module.business.systemcertificationnode.domain.entity.SystemCertificationNodeEntity;
import tech.remote.admin.module.business.systemcertificationnode.domain.vo.SystemCertificationNodeVO;
import tech.remote.admin.module.business.systemcertificationnode.service.SystemCertificationNodeService;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQuery;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.admin.module.business.typenode.service.TypeNodeService;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.enumeration.ProjectStatusEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import tech.remote.base.module.support.datatracer.constant.DataTracerTypeEnum;
import tech.remote.base.module.support.datatracer.domain.form.DataTracerForm;
import tech.remote.base.module.support.datatracer.service.DataTracerService;
import tech.remote.base.module.support.serialnumber.constant.SerialNumberIdEnum;
import tech.remote.base.module.support.serialnumber.service.SerialNumberService;

import javax.annotation.Resource;

/**
 * 体系认证表 Service
 *
 * @Author cbh
 * @Date 2024-04-25 14:53:11
 * @Copyright Remote Nomad Studio
 */

@Service
public class SystemCertificationService {

    @Resource
    private SystemCertificationDao systemCertificationDao;

    @Autowired
    private SerialNumberService serialNumberService;

    @Autowired
    private TypeNodeService typeNodeService;

    @Autowired
    private SystemCertificationNodeService systemCertificationNodeService;

    @Resource
    private DataTracerService dataTracerService;

    @Resource
    private NodeManager nodeManager;

    @Resource
    private CustomerService customerService;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SystemCertificationVO> queryPage(SystemCertificationQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SystemCertificationVO> list = systemCertificationDao.queryPage(page, queryForm);
        for(SystemCertificationVO systemCertificationVO : list) {
            List<SystemCertificationNodeEntity> systemCertificationNodeList = systemCertificationNodeService.getOperateNodesByProjectId(systemCertificationVO.getId());
            systemCertificationVO.setSystemCertificationNodeList(SmartBeanUtil.copyList(systemCertificationNodeList, SystemCertificationNodeVO.class));
        }
        PageResult<SystemCertificationVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 预警分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SystemCertificationVO> queryAlarmPage(SystemCertificationQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SystemCertificationVO> list = systemCertificationDao.queryAlarmPage(page, queryForm);
        for(SystemCertificationVO systemCertificationVO : list) {
            List<SystemCertificationNodeEntity> systemCertificationNodeList = systemCertificationNodeService.getOperateNodesByProjectId(systemCertificationVO.getId());
            systemCertificationVO.setSystemCertificationNodeList(SmartBeanUtil.copyList(systemCertificationNodeList, SystemCertificationNodeVO.class));
        }
        PageResult<SystemCertificationVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(SystemCertificationAddForm addForm) {
        SystemCertificationEntity systemCertificationEntity = SmartBeanUtil.copy(addForm, SystemCertificationEntity.class);

        String projectNo = serialNumberService.generate(SerialNumberIdEnum.SYSTEM_CERTIFICATION);
        systemCertificationEntity.setProjectNo(projectNo);
        systemCertificationEntity.setStatus(ProjectStatusEnum.DOING.getValue());
        systemCertificationDao.insert(systemCertificationEntity);

        // 获取该类型下的对应节点
        TypeNodeQuery query = new TypeNodeQuery();
//        query.setNodeLevel(1);
        query.setProjectType(addForm.getProjectType());
        List<TypeNodeListVO> typeNodeList = typeNodeService.getTypeNodes(query);
        // 把typeNodeList插入到SystemCertificationNode表中
        List<SystemCertificationNodeEntity> systemCertificationNodeList = new ArrayList<>();
        for (TypeNodeListVO typeNode : typeNodeList) {
            SystemCertificationNodeEntity systemCertificationNodeEntity = new SystemCertificationNodeEntity();
            systemCertificationNodeEntity.setProjectId(systemCertificationEntity.getId());
            systemCertificationNodeEntity.setNodeId(typeNode.getNodeId());
            systemCertificationNodeEntity.setNodeName(typeNode.getNodeName());
            systemCertificationNodeEntity.setNodeSort(typeNode.getNodeSort());
            systemCertificationNodeEntity.setStatus(NodeStatusEnum.INIT.getValue());
            systemCertificationNodeEntity.setCreateUserId(addForm.getCreateUserId());
            systemCertificationNodeEntity.setCreateUserName(addForm.getCreateUserName());

            systemCertificationNodeList.add(systemCertificationNodeEntity);
        }
        systemCertificationNodeService.saveBatch(systemCertificationNodeList);

        dataTracerService.insert(systemCertificationEntity.getId(), DataTracerTypeEnum.SYSTEM_CERTIFICATION);

        if(addForm.getContractAmount() != null && addForm.getCustomerId() != null){
            customerService.upgradeLevel(addForm.getCustomerId());
        }
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SystemCertificationUpdateForm updateForm) {

        SystemCertificationEntity oldEntity = systemCertificationDao.selectById(updateForm.getId());

        SystemCertificationEntity systemCertificationEntity = SmartBeanUtil.copy(updateForm, SystemCertificationEntity.class);
        systemCertificationDao.updateById(systemCertificationEntity);

        // 节点操作更新
        if(updateForm.getNodeId() != null && updateForm.getNodeStatus() != null) {
            // 根据projectId和nodeId为条件，更新systemCertificationNode的状态
            LambdaUpdateWrapper<SystemCertificationNodeEntity> updateWrapper = new LambdaUpdateWrapper<>();

            // Set the condition for the update
            updateWrapper.eq(SystemCertificationNodeEntity::getProjectId, updateForm.getId())
                    .eq(SystemCertificationNodeEntity::getNodeId, updateForm.getNodeId())
                    .in(SystemCertificationNodeEntity::getStatus, NodeStatusEnum.INIT.getValue(), NodeStatusEnum.DOING.getValue());

            // Set the field and its new value to update
            updateWrapper.set(SystemCertificationNodeEntity::getStatus, updateForm.getNodeStatus());
            updateWrapper.set(SystemCertificationNodeEntity::getOperateUserId, updateForm.getUpdateUserId());
            updateWrapper.set(SystemCertificationNodeEntity::getOperateUserName, updateForm.getUpdateUserName());
            updateWrapper.set(SystemCertificationNodeEntity::getOperateTime, LocalDateTime.now());
            updateWrapper.set(SystemCertificationNodeEntity::getPassReason, updateForm.getPassReason());

            // Execute the update
            systemCertificationNodeService.update(updateWrapper);
            NodeEntity nodeEntity = nodeManager.getById(updateForm.getNodeId());
            String content = "节点操作：【" + nodeEntity.getNodeName() + "】" + NodeStatusEnum.getDescByValue(updateForm.getNodeStatus());
            dataTracerService.addTrace(systemCertificationEntity.getId(), DataTracerTypeEnum.SYSTEM_CERTIFICATION, content);
            // 判断是否所有节点都已完成或者跳过，如果是，修改状态为已完成
            if (systemCertificationNodeService.isAllDone(updateForm.getId())) {
                systemCertificationEntity.setStatus(ProjectStatusEnum.DONE.getValue());
                systemCertificationDao.updateById(systemCertificationEntity);
                dataTracerService.addTrace(systemCertificationEntity.getId(), DataTracerTypeEnum.SYSTEM_CERTIFICATION, "项目完成");
            }
        } else {

            //变更记录
            dataTracerService.update(systemCertificationEntity.getId(), DataTracerTypeEnum.SYSTEM_CERTIFICATION, oldEntity, systemCertificationEntity);
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

        systemCertificationDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        systemCertificationDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    public SystemCertificationVO getDetail(Long id) {
        SystemCertificationVO vo = systemCertificationDao.getDetail(id);

        List<SystemCertificationNodeEntity> systemCertificationNodeList = systemCertificationNodeService.getAllNodesByProjectId(vo.getId());
        vo.setSystemCertificationNodeList(SmartBeanUtil.copyList(systemCertificationNodeList, SystemCertificationNodeVO.class));

        return vo;
    }

    public List<SystemCertificationExcelVO> getExcelExportData(SystemCertificationQueryForm queryForm) {
        List<SystemCertificationExcelVO> excelList = systemCertificationDao.selectExcelList(queryForm);
        return excelList;
    }
}
