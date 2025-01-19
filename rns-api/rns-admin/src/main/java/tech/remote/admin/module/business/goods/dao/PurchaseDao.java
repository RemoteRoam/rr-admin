package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.PurchaseEntity;
import tech.remote.admin.module.business.goods.domain.form.PurchaseQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 采购入库表 Dao
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:47
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<PurchaseVO> queryPage(Page page, @Param("queryForm") PurchaseQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Long id,@Param("${deletedFlag}")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("${deletedFlag}")boolean deletedFlag);

}
