package tech.remote.admin.module.business.measurement.service;

import java.util.List;
import tech.remote.admin.module.business.measurement.dao.MeasurementDao;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementAddForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementQueryForm;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementUpdateForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

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

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<MeasurementVO> queryPage(MeasurementQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<MeasurementVO> list = measurementDao.queryPage(page, queryForm);
        PageResult<MeasurementVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(MeasurementAddForm addForm) {
        MeasurementEntity measurementEntity = SmartBeanUtil.copy(addForm, MeasurementEntity.class);
        measurementDao.insert(measurementEntity);
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
