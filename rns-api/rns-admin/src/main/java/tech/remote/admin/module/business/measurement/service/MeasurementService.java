package tech.remote.admin.module.business.measurement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.measurement.dao.MeasurementDao;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementVO;
import tech.remote.admin.module.business.projectnode.domain.entity.ProjectNodeEntity;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeQueryForm;
import tech.remote.admin.module.business.projectnode.manager.ProjectNodeManager;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQuery;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.admin.module.business.typenode.service.TypeNodeService;
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
    private ProjectNodeManager projectNodeManager;

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
                ProjectNodeQueryForm nodeQueryForm = new ProjectNodeQueryForm();
                nodeQueryForm.setProjectId(measurementVO.getId());
                nodeQueryForm.setProjectType(ProjectTypeEnum.MEASUREMENT.getValue());
                nodeQueryForm.setNodeLevel(1);
                measurementVO.setProjectNodeList(projectNodeManager.getOperateNodes(nodeQueryForm));
            }
        }
        PageResult<MeasurementVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(MeasurementAddForm addForm) {
        MeasurementEntity measurementEntity = SmartBeanUtil.copy(addForm, MeasurementEntity.class);
        String projectNo = serialNumberService.generate(SerialNumberIdEnum.MEASUREMENT);
        measurementEntity.setProjectNo(projectNo);
        measurementEntity.setStatus(ProjectStatusEnum.DOING.getValue());
        measurementDao.insert(measurementEntity);

        // 获取该类型下的对应节点
        TypeNodeQuery query = new TypeNodeQuery();
        query.setNodeLevel(1);
        query.setProjectType(ProjectTypeEnum.MEASUREMENT.getValue());
        List<TypeNodeListVO> typeNodeList = typeNodeService.getTypeNodes(query);

        List<ProjectNodeEntity> projectNodeList = SmartBeanUtil.copyList(typeNodeList, ProjectNodeEntity.class);
        for(ProjectNodeEntity projectNode : projectNodeList){
            projectNode.setId(null);
            projectNode.setProjectId(measurementEntity.getId());
            projectNode.setProjectNo(projectNo);
            projectNode.setProjectType(ProjectTypeEnum.MEASUREMENT.getValue());
            projectNode.setStatus(NodeStatusEnum.INIT.getValue());
            projectNode.setCreateUserId(addForm.getCreateUserId());
            projectNode.setCreateUserName(addForm.getCreateUserName());
        }
        projectNodeManager.saveBatch(projectNodeList);

        dataTracerService.insert(measurementEntity.getId(), DataTracerTypeEnum.MEASUREMENT);

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(MeasurementUpdateForm updateForm) {
        MeasurementEntity measurementEntity = SmartBeanUtil.copy(updateForm, MeasurementEntity.class);
        measurementDao.updateById(measurementEntity);
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
}
