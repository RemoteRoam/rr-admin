package tech.remote.base.module.support.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import tech.remote.base.common.controller.SupportBaseController;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.constant.SwaggerTagConst;
import tech.remote.base.module.support.config.domain.ConfigVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 配置
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-14 20:46:27
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Tag(name = SwaggerTagConst.Support.CONFIG)
@RestController
public class ConfigController extends SupportBaseController {

    @Resource
    private ConfigService configService;

    @Operation(summary = "查询配置详情 @author 卓大")
    @GetMapping("/config/queryByKey")
    public ResponseDTO<ConfigVO> queryByKey(@RequestParam String configKey) {
        return ResponseDTO.ok(configService.getConfig(configKey));
    }

}
