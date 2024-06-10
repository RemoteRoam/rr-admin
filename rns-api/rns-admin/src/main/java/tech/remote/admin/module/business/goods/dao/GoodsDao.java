package tech.remote.admin.module.business.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.admin.module.business.goods.domain.entity.GoodsEntity;
import tech.remote.admin.module.business.goods.domain.form.GoodsQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品
 *
 * @Author 远游工作室: 胡克
 * @Date 2021-10-25 20:26:54
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Mapper
@Component
public interface GoodsDao extends BaseMapper<GoodsEntity> {

    /**
     * 分页 查询商品
     *
     */
    List<GoodsVO> query(Page page, @Param("query") GoodsQueryForm query);

    /**
     * 批量更新删除状态
     */

    void batchUpdateDeleted(@Param("goodsIdList")List<Long> goodsIdList,@Param("deletedFlag")Boolean deletedFlag);

    GoodsVO getDetail(Long id);
}
