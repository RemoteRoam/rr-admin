package tech.remote.admin.module.business.third.controller;

import org.springframework.web.bind.annotation.*;
import tech.remote.admin.constant.AdminSwaggerTagConst;
import tech.remote.admin.module.business.oa.enterprise.domain.vo.EnterpriseListVO;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyAddForm;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyQueryForm;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyUpdateForm;
import tech.remote.admin.module.business.third.domain.vo.ThirdPartyVO;
import tech.remote.admin.module.business.third.service.ThirdPartyService;
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
 * 三方机构 Controller
 *
 * @Author cbh
 * @Date 2024-04-24 09:19:13
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = AdminSwaggerTagConst.BaseManagement.THIRD_PARTY)
public class ThirdPartyController {

    @Resource
    private ThirdPartyService thirdPartyService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/thirdParty/queryPage")
    public ResponseDTO<PageResult<ThirdPartyVO>> queryPage(@RequestBody @Valid ThirdPartyQueryForm queryForm) {
        return ResponseDTO.ok(thirdPartyService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/thirdParty/add")
    public ResponseDTO<String> add(@RequestBody @Valid ThirdPartyAddForm addForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        return thirdPartyService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/thirdParty/update")
    public ResponseDTO<String> update(@RequestBody @Valid ThirdPartyUpdateForm updateForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        updateForm.setUpdateUserId(requestUser.getUserId());
        updateForm.setUpdateUserName(requestUser.getUserName());
        return thirdPartyService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/thirdParty/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return thirdPartyService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/thirdParty/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return thirdPartyService.delete(id);
    }

    @Operation(summary = "查询详情 @author cbh")
    @GetMapping("/thirdParty/get/{id}")
    public ResponseDTO<ThirdPartyVO> getDetail(@PathVariable Long id) {
        return ResponseDTO.ok(thirdPartyService.getDetail(id));
    }

    @Operation(summary = "按照类型查询三方 @author cbh")
    @GetMapping("/thirdParty/query/list")
    public ResponseDTO<List<ThirdPartyVO>> queryList(@RequestParam(value = "type", required = false) String type) {
        return thirdPartyService.queryList(type);
    }
}
