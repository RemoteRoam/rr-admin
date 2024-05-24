package tech.remote.admin.module.business.measurement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.measurement.dao.MeasurementTaskDao;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementNodeEntity;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementTaskEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementNodeVO;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementTaskVO;
import tech.remote.admin.module.business.measurement.manager.MeasurementNodeManager;
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
 * 仪器计量实验室任务表 Service
 *
 * @Author cbh
 * @Date 2024-05-07 14:44:51
 * @Copyright Remote Nomad Studio
 */

@Service
public class MeasurementTaskService {

    @Resource
    private MeasurementTaskDao measurementTaskDao;

    @Autowired
    private SerialNumberService serialNumberService;

    @Autowired
    private TypeNodeService typeNodeService;

    @Resource
    private MeasurementNodeManager measurementNodeManager;

    @Resource
    private DataTracerService dataTracerService;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<MeasurementTaskVO> queryPage(MeasurementTaskQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<MeasurementTaskVO> list = measurementTaskDao.queryPage(page, queryForm);
        if(CollectionUtils.isNotEmpty(list)){
            for(MeasurementTaskVO measurementTaskVO : list){
                MeasurementNodeQueryForm nodeQueryForm = new MeasurementNodeQueryForm();
                nodeQueryForm.setProjectId(measurementTaskVO.getProjectId());
                nodeQueryForm.setTaskId(measurementTaskVO.getId());
                nodeQueryForm.setNodeLevel(2);
                measurementTaskVO.setMeasurementNodeList(measurementNodeManager.getOperateNodes(nodeQueryForm));
            }
        }
        PageResult<MeasurementTaskVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(MeasurementTaskAddForm addForm) {
        MeasurementTaskEntity measurementTaskEntity = SmartBeanUtil.copy(addForm, MeasurementTaskEntity.class);
        String projectNo = serialNumberService.generate(SerialNumberIdEnum.MEASUREMENT_TASK);
        measurementTaskEntity.setTaskNo(projectNo);
        measurementTaskEntity.setStatus(ProjectStatusEnum.DOING.getValue());
        measurementTaskDao.insert(measurementTaskEntity);

        // 获取该类型下的对应节点
        TypeNodeQuery query = new TypeNodeQuery();
        query.setNodeLevel(2);
        query.setProjectType(ProjectTypeEnum.MEASUREMENT.getValue());
        List<TypeNodeListVO> typeNodeList = typeNodeService.getTypeNodes(query);

        List<MeasurementNodeEntity> projectNodeList = SmartBeanUtil.copyList(typeNodeList, MeasurementNodeEntity.class);
        for(MeasurementNodeEntity projectNode : projectNodeList){
            projectNode.setId(null);
            projectNode.setProjectId(addForm.getProjectId());
            projectNode.setTaskId(measurementTaskEntity.getId());
            projectNode.setStatus(NodeStatusEnum.INIT.getValue());
            projectNode.setCreateUserId(addForm.getCreateUserId());
            projectNode.setCreateUserName(addForm.getCreateUserName());
        }
        measurementNodeManager.saveBatch(projectNodeList);

        dataTracerService.insert(measurementTaskEntity.getId(), DataTracerTypeEnum.MEASUREMENT_TASK);

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(MeasurementTaskUpdateForm updateForm) {
        MeasurementTaskEntity oldEntity = measurementTaskDao.selectById(updateForm.getId());
        MeasurementTaskEntity measurementTaskEntity = SmartBeanUtil.copy(updateForm, MeasurementTaskEntity.class);
        measurementTaskDao.updateById(measurementTaskEntity);

        // 节点操作更新
        if(updateForm.getProjectNodeId() != null && updateForm.getNodeStatus() != null) {
            // 根据projectId和nodeId为条件，更新systemCertificationNode的状态

            MeasurementNodeEntity projectNodeEntity = measurementNodeManager.getById(updateForm.getProjectNodeId());
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
            measurementNodeManager.updateById(projectNodeEntity);
            String content = "节点操作：【" + projectNodeEntity.getNodeName() + "】" + NodeStatusEnum.getDescByValue(updateForm.getNodeStatus());
            dataTracerService.addTrace(measurementTaskEntity.getId(), DataTracerTypeEnum.MEASUREMENT_TASK, content);
            // 判断是否所有节点都已完成或者跳过，如果是，修改状态为已完成
            MeasurementNodeQueryForm queryForm = new MeasurementNodeQueryForm();
            queryForm.setProjectId(updateForm.getId());
            queryForm.setNodeLevel(2);

            if (measurementNodeManager.isAllDone(queryForm)) {
                measurementTaskEntity.setStatus(ProjectStatusEnum.DONE.getValue());
                measurementTaskDao.updateById(measurementTaskEntity);
                dataTracerService.addTrace(measurementTaskEntity.getId(), DataTracerTypeEnum.MEASUREMENT_TASK, "项目完成");
            }
        } else {

            //变更记录
            dataTracerService.update(measurementTaskEntity.getId(), DataTracerTypeEnum.MEASUREMENT_TASK, oldEntity, measurementTaskEntity);
        }
        return ResponseDTO.ok();
    }


    public MeasurementTaskVO getDetail(Long id) {
        MeasurementTaskVO vo = measurementTaskDao.getDetail(id);

        MeasurementNodeQueryForm queryForm = new MeasurementNodeQueryForm();
        queryForm.setProjectId(vo.getProjectId());
        queryForm.setTaskId(id);
        queryForm.setNodeLevel(2);
        List<MeasurementNodeVO> projectNodeList = measurementNodeManager.getAllNodes(queryForm);
        vo.setMeasurementNodeList(projectNodeList);

        return vo;
    }
}
