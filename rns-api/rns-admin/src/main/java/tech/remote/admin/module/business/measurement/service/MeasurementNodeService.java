package tech.remote.admin.module.business.measurement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.measurement.dao.MeasurementNodeDao;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementNodeEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementNodeVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 仪器计量流程节点表 Service
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:44
 * @Copyright Remote Nomad Studio
 */

@Service
public class MeasurementNodeService {

    @Resource
    private MeasurementNodeDao measurementNodeDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<MeasurementNodeVO> queryPage(MeasurementNodeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<MeasurementNodeVO> list = measurementNodeDao.queryPage(page, queryForm);
        PageResult<MeasurementNodeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(MeasurementNodeAddForm addForm) {
        MeasurementNodeEntity measurementNodeEntity = SmartBeanUtil.copy(addForm, MeasurementNodeEntity.class);
        measurementNodeDao.insert(measurementNodeEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(MeasurementNodeUpdateForm updateForm) {
        MeasurementNodeEntity measurementNodeEntity = SmartBeanUtil.copy(updateForm, MeasurementNodeEntity.class);
        measurementNodeDao.updateById(measurementNodeEntity);
        return ResponseDTO.ok();
    }

}
