package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.SalesEntity;
import tech.remote.admin.module.business.goods.domain.form.SalesQueryForm;
import tech.remote.admin.module.business.goods.domain.form.SkusQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.SalesExcelVO;
import tech.remote.admin.module.business.goods.domain.vo.SalesVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 销售单表 Dao
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface SalesDao extends BaseMapper<SalesEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<SalesVO> queryPage(Page page, @Param("queryForm") SalesQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Long id,@Param("${deletedFlag}")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("${deletedFlag}")boolean deletedFlag);

    SalesVO detail(Long id);

    List<SalesExcelVO> getExcelExportData(@Param("queryForm") SalesQueryForm queryForm);
}
