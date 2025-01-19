package tech.remote.admin.module.business.measurement.dao;

import java.util.List;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementNodeEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeQueryForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementNodeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 仪器计量流程节点表 Dao
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:44
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface MeasurementNodeDao extends BaseMapper<MeasurementNodeEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<MeasurementNodeVO> queryPage(Page page, @Param("queryForm") MeasurementNodeQueryForm queryForm);


}
