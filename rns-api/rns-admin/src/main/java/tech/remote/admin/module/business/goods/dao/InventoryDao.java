package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.InventoryEntity;
import tech.remote.admin.module.business.goods.domain.form.InventoryQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.InventoryVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 库存表 Dao
 *
 * @Author cbh
 * @Date 2024-06-06 13:42:51
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface InventoryDao extends BaseMapper<InventoryEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<InventoryVO> queryPage(Page page, @Param("queryForm") InventoryQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Integer id,@Param("${deletedFlag}")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Integer> idList,@Param("${deletedFlag}")boolean deletedFlag);

}
