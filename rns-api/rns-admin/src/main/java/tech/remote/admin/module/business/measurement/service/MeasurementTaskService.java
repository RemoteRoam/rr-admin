package tech.remote.admin.module.business.measurement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.measurement.dao.MeasurementTaskDao;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementTaskEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementTaskVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

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

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<MeasurementTaskVO> queryPage(MeasurementTaskQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<MeasurementTaskVO> list = measurementTaskDao.queryPage(page, queryForm);
        PageResult<MeasurementTaskVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(MeasurementTaskAddForm addForm) {
        MeasurementTaskEntity measurementTaskEntity = SmartBeanUtil.copy(addForm, MeasurementTaskEntity.class);
        measurementTaskDao.insert(measurementTaskEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(MeasurementTaskUpdateForm updateForm) {
        MeasurementTaskEntity measurementTaskEntity = SmartBeanUtil.copy(updateForm, MeasurementTaskEntity.class);
        measurementTaskDao.updateById(measurementTaskEntity);
        return ResponseDTO.ok();
    }

}
