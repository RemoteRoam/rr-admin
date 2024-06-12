package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.SalesItemEntity;
import tech.remote.admin.module.business.goods.domain.form.SalesItemQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.SalesItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 销售明细表 Dao
 *
 * @Author cbh
 * @Date 2024-06-06 13:17:41
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface SalesItemDao extends BaseMapper<SalesItemEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<SalesItemVO> queryPage(Page page, @Param("queryForm") SalesItemQueryForm queryForm);


    /**
     * 根据销售单ID查询销售明细
     * @param salesId
     * @return
     */
    List<SalesItemVO> listBySalesId(@Param("salesId")Long salesId);

}
