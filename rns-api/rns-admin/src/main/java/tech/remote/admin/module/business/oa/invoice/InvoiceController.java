package tech.remote.admin.module.business.oa.invoice;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tech.remote.admin.constant.AdminSwaggerTagConst;
import tech.remote.admin.module.business.oa.invoice.domain.InvoiceAddForm;
import tech.remote.admin.module.business.oa.invoice.domain.InvoiceQueryForm;
import tech.remote.admin.module.business.oa.invoice.domain.InvoiceUpdateForm;
import tech.remote.admin.module.business.oa.invoice.domain.InvoiceVO;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.util.SmartRequestUtil;
import tech.remote.base.module.support.operatelog.annotation.OperateLog;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
@Slf4j
@RestController
@Tag(name = AdminSwaggerTagConst.Business.OA_INVOICE)
public class InvoiceController {

    @Resource
    private InvoiceService invoiceService;

    @Operation(summary = "分页查询发票信息 @author 善逸")
    @PostMapping("/oa/invoice/page/query")
    public ResponseDTO<PageResult<InvoiceVO>> queryByPage(@RequestBody @Valid InvoiceQueryForm queryForm) {
        return invoiceService.queryByPage(queryForm);
    }

    @Operation(summary = "查询发票信息详情 @author 善逸")
    @GetMapping("/oa/invoice/get/{invoiceId}")
    public ResponseDTO<InvoiceVO> getDetail(@PathVariable Long invoiceId) {
        return invoiceService.getDetail(invoiceId);
    }

    @Operation(summary = "新建发票信息 @author 善逸")
    @PostMapping("/oa/invoice/create")
    public ResponseDTO<String> createInvoice(@RequestBody @Valid InvoiceAddForm createVO) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        createVO.setCreateUserId(requestUser.getUserId());
        createVO.setCreateUserName(requestUser.getUserName());
        return invoiceService.createInvoice(createVO);
    }

    @Operation(summary = "编辑发票信息 @author 善逸")
    @PostMapping("/oa/invoice/update")
    @OperateLog
    public ResponseDTO<String> updateInvoice(@RequestBody @Valid InvoiceUpdateForm updateVO) {
        return invoiceService.updateInvoice(updateVO);
    }

    @Operation(summary = "删除发票信息 @author 善逸")
    @GetMapping("/invoice/delete/{invoiceId}")
    public ResponseDTO<String> deleteInvoice(@PathVariable Long invoiceId) {
        return invoiceService.deleteInvoice(invoiceId);
    }

    @Operation(summary = "查询列表 @author lidoudou")
    @GetMapping("/oa/invoice/query/list/{enterpriseId}")
    public ResponseDTO<List<InvoiceVO>> queryList(@PathVariable Long enterpriseId) {
        return invoiceService.queryList(enterpriseId);
    }


}
