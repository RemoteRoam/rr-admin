package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.StocktakeEntity;
import tech.remote.admin.module.business.goods.domain.form.StocktakeQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.StocktakeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 盘点表 Dao
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface StocktakeDao extends BaseMapper<StocktakeEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<StocktakeVO> queryPage(Page page, @Param("queryForm") StocktakeQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Long id,@Param("${deletedFlag}")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("${deletedFlag}")boolean deletedFlag);

}
