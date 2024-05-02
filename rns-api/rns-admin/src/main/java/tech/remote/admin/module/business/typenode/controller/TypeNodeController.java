package tech.remote.admin.module.business.typenode.controller;

import tech.remote.admin.module.business.typenode.domain.form.TypeNodeAddForm;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQueryForm;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeUpdateForm;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeVO;
import tech.remote.admin.module.business.typenode.service.TypeNodeService;
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
 * 项目类型节点关系表 Controller
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class TypeNodeController {

    @Resource
    private TypeNodeService typeNodeService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/typeNode/queryPage")
    public ResponseDTO<PageResult<TypeNodeVO>> queryPage(@RequestBody @Valid TypeNodeQueryForm queryForm) {
        return ResponseDTO.ok(typeNodeService.queryPage(queryForm));
    }


}
