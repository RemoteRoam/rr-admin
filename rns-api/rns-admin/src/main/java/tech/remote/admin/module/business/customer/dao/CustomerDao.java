package tech.remote.admin.module.business.customer.dao;

import java.util.List;
import tech.remote.admin.module.business.customer.domain.entity.CustomerEntity;
import tech.remote.admin.module.business.customer.domain.form.CustomerQueryForm;
import tech.remote.admin.module.business.customer.domain.vo.CustomerVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tech.remote.admin.module.business.customer.domain.vo.GrandTotalResult;

/**
 * 客户表 Dao
 *
 * @Author cbh
 * @Date 2024-04-23 12:10:04
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface CustomerDao extends BaseMapper<CustomerEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<CustomerVO> queryPage(Page page, @Param("queryForm") CustomerQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("customerId")Long customerId,@Param("deletedFlag")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("deletedFlag")boolean deletedFlag);

    GrandTotalResult getGrandTotal(@Param("customerId") Long customerId);
}
