package tech.remote.base.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务级别的错误码（用户引起的错误返回码，可以不用关注）
 *
 * @Author 远游工作室-yy
 * @Date 2021/09/21 22:12:27
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorCode implements ErrorCode {

    PROJECT_NODE_STATUS_ERROR(40001, "项目节点状态异常"),
    PROJECT_NODE_ALREADY_PROCESSED(40002, "项目节点已经处理过了，无需操作"),
    CUSTOMER_NAME_EXIST_ERROR(41001, "客户名称已存在"),
;

    private final int code;

    private final String msg;

    private final String level;

    BusinessErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.level = LEVEL_USER;
    }
}
