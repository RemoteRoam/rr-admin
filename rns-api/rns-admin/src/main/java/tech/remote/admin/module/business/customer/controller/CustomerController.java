package tech.remote.admin.module.business.customer.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import tech.remote.admin.constant.AdminSwaggerTagConst;
import tech.remote.admin.module.business.customer.domain.form.CustomerAddForm;
import tech.remote.admin.module.business.customer.domain.form.CustomerQueryForm;
import tech.remote.admin.module.business.customer.domain.form.CustomerUpdateForm;
import tech.remote.admin.module.business.customer.domain.vo.CustomerVO;
import tech.remote.admin.module.business.customer.service.CustomerService;
import tech.remote.admin.module.business.oa.enterprise.domain.vo.EnterpriseVO;
import tech.remote.admin.module.business.third.domain.vo.ThirdPartyVO;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ValidateList;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.base.common.util.SmartRequestUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 客户表 Controller
 *
 * @Author cbh
 * @Date 2024-04-23 12:10:04
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = AdminSwaggerTagConst.BaseManagement.CUSTOMER_MANAGEMENT)
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/customer/queryPage")
    public ResponseDTO<PageResult<CustomerVO>> queryPage(@RequestBody @Valid CustomerQueryForm queryForm) {
        return ResponseDTO.ok(customerService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/customer/add")
    public ResponseDTO<String> add(@RequestBody @Valid CustomerAddForm addForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        return customerService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/customer/update")
    public ResponseDTO<String> update(@RequestBody @Valid CustomerUpdateForm updateForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        updateForm.setUpdateUserId(requestUser.getUserId());
        updateForm.setUpdateUserName(requestUser.getUserName());
        return customerService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/customer/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return customerService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/customer/delete/{customerId}")
    public ResponseDTO<String> batchDelete(@PathVariable Long customerId) {
        return customerService.delete(customerId);
    }

    @Operation(summary = "查询详情 @author cbh")
    @GetMapping("/customer/get/{customerId}")
    public ResponseDTO<CustomerVO> getDetail(@PathVariable Long customerId) {
        return ResponseDTO.ok(customerService.getDetail(customerId));
    }

    @Operation(summary = "查询所有客户 @author cbh")
    @GetMapping("/customer/query/list")
    public ResponseDTO<List<CustomerVO>> queryList() {
        return customerService.queryList();
    }
}
