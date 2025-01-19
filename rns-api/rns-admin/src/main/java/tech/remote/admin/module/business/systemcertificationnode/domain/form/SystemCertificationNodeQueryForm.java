package tech.remote.admin.module.business.systemcertificationnode.domain.form;

import tech.remote.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 体系认证项目流程节点表 分页查询表单
 *
 * @Author cbh
 * @Date 2024-05-04 16:20:54
 * @Copyright Remote Nomad Studio
 */

@Data
public class SystemCertificationNodeQueryForm extends PageParam{

    @Schema(description = "项目ID")
    private Long projectId;

}