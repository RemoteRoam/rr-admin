package tech.remote.admin.module.business.systemcertification.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.systemcertification.dao.SystemCertificationDao;
import tech.remote.admin.module.business.systemcertification.domain.entity.SystemCertificationEntity;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationAddForm;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationQueryForm;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationUpdateForm;
import tech.remote.admin.module.business.systemcertification.domain.vo.SystemCertificationVO;
import tech.remote.admin.module.business.systemcertificationnode.domain.entity.SystemCertificationNodeEntity;
import tech.remote.admin.module.business.systemcertificationnode.domain.vo.SystemCertificationNodeVO;
import tech.remote.admin.module.business.systemcertificationnode.service.SystemCertificationNodeService;
import tech.remote.admin.module.business.typenode.domain.entity.TypeNodeEntity;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQuery;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.admin.module.business.typenode.service.TypeNodeService;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
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
            List<SystemCertificationNodeEntity> systemCertificationNodeList = systemCertificationNodeService.getOperateNodeByProjectId(systemCertificationVO.getId());
            systemCertificationVO.setSystemCertificationNodeList(SmartBeanUtil.copyList(systemCertificationNodeList, SystemCertificationNodeVO.class));
        }
        PageResult<SystemCertificationVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(SystemCertificationAddForm addForm) {
        SystemCertificationEntity systemCertificationEntity = SmartBeanUtil.copy(addForm, SystemCertificationEntity.class);

        String projectNo = serialNumberService.generate(SerialNumberIdEnum.PROJECT);
        systemCertificationEntity.setProjectNo(projectNo);
        systemCertificationDao.insert(systemCertificationEntity);

        // 获取该类型下的对应节点
        TypeNodeQuery query = new TypeNodeQuery();
        query.setNodeLevel(1);
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
            systemCertificationNodeEntity.setOperateUserId(addForm.getCreateUserId());
            systemCertificationNodeEntity.setOperateUserName(addForm.getCreateUserName());
            systemCertificationNodeEntity.setOperateTime(LocalDateTime.now());
            systemCertificationNodeEntity.setCreateUserId(addForm.getCreateUserId());
            systemCertificationNodeEntity.setCreateUserName(addForm.getCreateUserName());

            systemCertificationNodeList.add(systemCertificationNodeEntity);
        }
        systemCertificationNodeService.saveBatch(systemCertificationNodeList);

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(SystemCertificationUpdateForm updateForm) {
        SystemCertificationEntity systemCertificationEntity = SmartBeanUtil.copy(updateForm, SystemCertificationEntity.class);
        systemCertificationDao.updateById(systemCertificationEntity);

        // 根据projectId和nodeId为条件，更新systemCertificationNode的状态
        LambdaUpdateWrapper<SystemCertificationNodeEntity> updateWrapper = new LambdaUpdateWrapper<>();

        // Set the condition for the update
        updateWrapper.eq(SystemCertificationNodeEntity::getProjectId, updateForm.getId())
                .eq(SystemCertificationNodeEntity::getNodeId, updateForm.getNodeId())
                .in(SystemCertificationNodeEntity::getStatus, NodeStatusEnum.INIT.getValue(), NodeStatusEnum.DOING.getValue());

        // Set the field and its new value to update
        updateWrapper.set(SystemCertificationNodeEntity::getStatus, updateForm.getStatus());

        // Execute the update
        systemCertificationNodeService.update(updateWrapper);
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
        SystemCertificationEntity entity = systemCertificationDao.selectById(id);
        return SmartBeanUtil.copy(entity, SystemCertificationVO.class);
    }
}
