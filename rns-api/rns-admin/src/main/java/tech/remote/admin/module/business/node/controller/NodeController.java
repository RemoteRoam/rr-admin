package tech.remote.admin.module.business.node.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.remote.admin.module.business.node.domain.form.NodeQueryForm;
import tech.remote.admin.module.business.node.domain.vo.NodeVO;
import tech.remote.admin.module.business.node.service.NodeService;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 节点表 Controller
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class NodeController {

    @Resource
    private NodeService nodeService;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/node/queryPage")
    public ResponseDTO<PageResult<NodeVO>> queryPage(@RequestBody @Valid NodeQueryForm queryForm) {
        return ResponseDTO.ok(nodeService.queryPage(queryForm));
    }


}
