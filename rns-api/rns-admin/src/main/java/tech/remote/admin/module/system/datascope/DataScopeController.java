package tech.remote.admin.module.system.datascope;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.admin.constant.AdminSwaggerTagConst;
import tech.remote.admin.module.system.datascope.domain.DataScopeAndViewTypeVO;
import tech.remote.admin.module.system.datascope.service.DataScopeService;
import tech.remote.base.common.domain.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 查询支持的数据范围类型
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022-03-18 20:59:17
 *
 *
 * @Copyright <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@RestController
@Tag(name = AdminSwaggerTagConst.System.SYSTEM_DATA_SCOPE)
public class DataScopeController {

    @Resource
    private DataScopeService dataScopeService;

    @Operation(summary = "获取当前系统所配置的所有数据范围 @author 罗伊")
    @GetMapping("/dataScope/list")
    public ResponseDTO<List<DataScopeAndViewTypeVO>> dataScopeList() {
        return dataScopeService.dataScopeList();
    }


}
