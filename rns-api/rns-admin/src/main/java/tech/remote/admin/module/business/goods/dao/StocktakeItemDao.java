package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.StocktakeItemEntity;
import tech.remote.admin.module.business.goods.domain.form.StocktakeItemQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.StocktakeItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 盘点明细表 Dao
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface StocktakeItemDao extends BaseMapper<StocktakeItemEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<StocktakeItemVO> queryPage(Page page, @Param("queryForm") StocktakeItemQueryForm queryForm);


}
