package tech.remote.admin.module.business.measurement.dao;

import java.util.List;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementTaskEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementTaskQueryForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementTaskVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 仪器计量实验室任务表 Dao
 *
 * @Author cbh
 * @Date 2024-05-07 14:44:51
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface MeasurementTaskDao extends BaseMapper<MeasurementTaskEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<MeasurementTaskVO> queryPage(Page page, @Param("queryForm") MeasurementTaskQueryForm queryForm);


    MeasurementTaskVO getDetail(Long id);
}
