package tech.remote.admin.module.business.measurement.dao;

import java.util.List;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementEntity;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementQueryForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 仪器计量表 Dao
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:35
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface MeasurementDao extends BaseMapper<MeasurementEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<MeasurementVO> queryPage(Page page, @Param("queryForm") MeasurementQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Long id,@Param("${deletedFlag}")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("${deletedFlag}")boolean deletedFlag);

    MeasurementVO getDetail(@Param("id")Long id);
}
