package tech.remote.admin.module.business.oa.invoice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.admin.module.business.oa.invoice.domain.InvoiceEntity;
import tech.remote.admin.module.business.oa.invoice.domain.InvoiceQueryForm;
import tech.remote.admin.module.business.oa.invoice.domain.InvoiceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * OA发票信息
 *
 * @Author 远游工作室: 善逸
 * @Date 2022-06-23 19:32:59
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Mapper
@Component
public interface InvoiceDao extends BaseMapper<InvoiceEntity> {

    /**
     * 根据账号查询
     * @param enterpriseId
     * @param accountNumber
     * @param excludeInvoiceId
     * @param deletedFlag
     * @return
     */
    InvoiceEntity queryByAccountNumber(@Param("enterpriseId") Long enterpriseId, @Param("accountNumber") String accountNumber, @Param("excludeInvoiceId") Long excludeInvoiceId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 删除发票信息
     *
     * @param invoiceId
     * @param deletedFlag
     */
    void deleteInvoice(@Param("invoiceId") Long invoiceId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 发票信息分页查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<InvoiceVO> queryPage(Page page, @Param("queryForm") InvoiceQueryForm queryForm);

    /**
     * 查询发票信息详情
     * @param invoiceId
     * @param deletedFlag
     * @return
     */
    InvoiceVO getDetail(@Param("invoiceId") Long invoiceId, @Param("deletedFlag") Boolean deletedFlag);
}