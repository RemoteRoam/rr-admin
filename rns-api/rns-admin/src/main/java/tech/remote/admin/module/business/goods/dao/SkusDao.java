package tech.remote.admin.module.business.goods.dao;

import java.util.List;
import tech.remote.admin.module.business.goods.domain.entity.SkusEntity;
import tech.remote.admin.module.business.goods.domain.form.SkusQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.SkusExcelVO;
import tech.remote.admin.module.business.goods.domain.vo.SkusVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 型号规格 Dao
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface SkusDao extends BaseMapper<SkusEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<SkusVO> queryPage(Page page, @Param("queryForm") SkusQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("skuId")Long skuId,@Param("deletedFlag")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("deletedFlag")boolean deletedFlag);

    List<SkusExcelVO> selectExcelList(@Param("queryForm") SkusQueryForm queryForm);
}
