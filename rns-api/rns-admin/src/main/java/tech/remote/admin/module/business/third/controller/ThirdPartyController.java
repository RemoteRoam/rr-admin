package tech.remote.admin.module.business.third.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyAddForm;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyQueryForm;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyUpdateForm;
import tech.remote.admin.module.business.third.domain.vo.ThirdPartyVO;
import tech.remote.admin.module.business.third.service.ThirdPartyService;
import tech.remote.base.common.domain.ValidateList;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 三方机构 Controller
 *
 * @Author cbh
 * @Date 2024-04-24 09:19:13
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
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
        return thirdPartyService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/thirdParty/update")
    public ResponseDTO<String> update(@RequestBody @Valid ThirdPartyUpdateForm updateForm) {
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
}
