package tech.remote.admin.module.business.measurement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.remote.admin.module.business.measurement.dao.MeasurementDao;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementEntity;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementNodeEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementExcelVO;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementNodeVO;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementVO;
import tech.remote.admin.module.business.measurement.manager.MeasurementNodeManager;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQuery;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.admin.module.business.typenode.service.TypeNodeService;
import tech.remote.base.common.code.BusinessErrorCode;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.enumeration.ProjectStatusEnum;
import tech.remote.base.common.enumeration.ProjectTypeEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.module.support.datatracer.constant.DataTracerTypeEnum;
import tech.remote.base.module.support.datatracer.service.DataTracerService;
import tech.remote.base.module.support.serialnumber.constant.SerialNumberIdEnum;
import tech.remote.base.module.support.serialnumber.service.SerialNumberService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仪器计量表 Service
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@Service
public class MeasurementService {

    @Resource
    private MeasurementDao measurementDao;

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
    public PageResult<MeasurementVO> queryPage(MeasurementQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<MeasurementVO> list = measurementDao.queryPage(page, queryForm);
        if(CollectionUtils.isNotEmpty(list)){
            for(MeasurementVO measurementVO : list){
                MeasurementNodeQueryForm nodeQueryForm = new MeasurementNodeQueryForm();
                nodeQueryForm.setProjectId(measurementVO.getId());
                nodeQueryForm.setNodeLevel(1);
                measurementVO.setMeasurementNodeList(measurementNodeManager.getOperateNodes(nodeQueryForm));
            }
        }
        PageResult<MeasurementVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 预警分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<MeasurementVO> queryAlarmPage(MeasurementQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<MeasurementVO> list = measurementDao.queryAlarmPage(page, queryForm);
        if(CollectionUtils.isNotEmpty(list)){
            for(MeasurementVO measurementVO : list){
                MeasurementNodeQueryForm nodeQueryForm = new MeasurementNodeQueryForm();
                nodeQueryForm.setProjectId(measurementVO.getId());
                nodeQueryForm.setNodeLevel(1);
                measurementVO.setMeasurementNodeList(measurementNodeManager.getOperateNodes(nodeQueryForm));
            }
        }
        PageResult<MeasurementVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(MeasurementAddForm addForm) {
        MeasurementEntity measurementEntity = SmartBeanUtil.copy(addForm, MeasurementEntity.class);
        String projectNo = serialNumberService.generate(SerialNumberIdEnum.MEASUREMENT);
        measurementEntity.setProjectNo(projectNo);
        measurementEntity.setProjectType(ProjectTypeEnum.MEASUREMENT.getValue());
        measurementEntity.setStatus(ProjectStatusEnum.DOING.getValue());
        measurementDao.insert(measurementEntity);

        // 获取该类型下的对应节点
        TypeNodeQuery query = new TypeNodeQuery();
        query.setNodeLevel(1);
        query.setProjectType(ProjectTypeEnum.MEASUREMENT.getValue());
        List<TypeNodeListVO> typeNodeList = typeNodeService.getTypeNodes(query);

        List<MeasurementNodeEntity> nodeList = SmartBeanUtil.copyList(typeNodeList, MeasurementNodeEntity.class);
        for(MeasurementNodeEntity node : nodeList){
            node.setId(null);
            node.setProjectId(measurementEntity.getId());
            node.setStatus(NodeStatusEnum.INIT.getValue());
            node.setCreateUserId(addForm.getCreateUserId());
            node.setCreateUserName(addForm.getCreateUserName());
        }
        measurementNodeManager.saveBatch(nodeList);

        dataTracerService.insert(measurementEntity.getId(), DataTracerTypeEnum.MEASUREMENT);

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(MeasurementUpdateForm updateForm) {
        MeasurementEntity oldEntity = measurementDao.selectById(updateForm.getId());
        MeasurementEntity measurementEntity = SmartBeanUtil.copy(updateForm, MeasurementEntity.class);
        measurementDao.updateById(measurementEntity);

        // 节点操作更新
        if(updateForm.getProjectNodeId() != null && updateForm.getNodeStatus() != null) {
            // 根据projectId和nodeId为条件，更新systemCertificationNode的状态

            MeasurementNodeEntity nodeEntity = measurementNodeManager.getById(updateForm.getProjectNodeId());
            if(nodeEntity.getStatus() == NodeStatusEnum.INIT.getValue() || nodeEntity.getStatus() == NodeStatusEnum.DOING.getValue()){
                nodeEntity.setOperateTime(LocalDateTime.now());
                nodeEntity.setOperateUserId(updateForm.getUpdateUserId());
                nodeEntity.setOperateUserName(updateForm.getUpdateUserName());
                nodeEntity.setPassReason(updateForm.getPassReason());
                nodeEntity.setStatus(updateForm.getNodeStatus());

            } else {
                return ResponseDTO.error(BusinessErrorCode.PROJECT_NODE_ALREADY_PROCESSED);
            }

            // Execute the update
            measurementNodeManager.updateById(nodeEntity);
            String content = "节点操作：【" + nodeEntity.getNodeName() + "】" + NodeStatusEnum.getDescByValue(updateForm.getNodeStatus());
            dataTracerService.addTrace(measurementEntity.getId(), DataTracerTypeEnum.MEASUREMENT, content);
            // 判断是否所有节点都已完成或者跳过，如果是，修改状态为已完成
            MeasurementNodeQueryForm queryForm = new MeasurementNodeQueryForm();
            queryForm.setProjectId(updateForm.getId());
            queryForm.setNodeLevel(1);

            if (measurementNodeManager.isAllDone(queryForm)) {
                measurementEntity.setStatus(ProjectStatusEnum.DONE.getValue());
                measurementDao.updateById(measurementEntity);
                dataTracerService.addTrace(measurementEntity.getId(), DataTracerTypeEnum.MEASUREMENT, "项目完成");
            }
        } else {

            //变更记录
            dataTracerService.update(measurementEntity.getId(), DataTracerTypeEnum.MEASUREMENT, oldEntity, measurementEntity);
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

        measurementDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        measurementDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    public MeasurementVO getDetail(Long id) {
        MeasurementVO vo = measurementDao.getDetail(id);

        MeasurementNodeQueryForm queryForm = new MeasurementNodeQueryForm();
        queryForm.setProjectId(id);
        queryForm.setNodeLevel(1);
        List<MeasurementNodeVO> nodeList = measurementNodeManager.getAllNodes(queryForm);
        vo.setMeasurementNodeList(nodeList);

        return vo;
    }

    public List<MeasurementExcelVO> getExcelExportData(MeasurementQueryForm queryForm) {
        List<MeasurementExcelVO> excelList = measurementDao.selectExcelList(queryForm);
        return excelList;
    }
}
