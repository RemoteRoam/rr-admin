package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.PurchaseItemEntity;
import tech.remote.admin.module.business.goods.domain.form.PurchaseItemQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 采购入库明细表 Dao
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface PurchaseItemDao extends BaseMapper<PurchaseItemEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<PurchaseItemVO> queryPage(Page page, @Param("queryForm") PurchaseItemQueryForm queryForm);


}
