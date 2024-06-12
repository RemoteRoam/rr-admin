package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.InventoryFlowEntity;
import tech.remote.admin.module.business.goods.domain.form.InventoryFlowQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.InventoryFlowVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 库存流水表 Dao
 *
 * @Author cbh
 * @Date 2024-06-11 13:40:40
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface InventoryFlowDao extends BaseMapper<InventoryFlowEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<InventoryFlowVO> queryPage(Page page, @Param("queryForm") InventoryFlowQueryForm queryForm);


}
