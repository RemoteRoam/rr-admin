package tech.remote.base.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 未预期的错误码（即发生了不可能发生的事情，此类返回码应该高度重视）
 *
 * @Author 远游工作室-yy
 * @Date 2021/09/27 22:10:46
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Getter
@AllArgsConstructor
public enum UnexpectedErrorCode implements ErrorCode {

    /**
     * 业务错误
     */
    BUSINESS_HANDING(20001, "呃~ 业务繁忙，请稍后重试"),

    /**
     * id错误
     */
    PAY_ORDER_ID_ERROR(20002, "付款单id发生了异常，请联系技术人员排查"),

    ;

    private final int code;

    private final String msg;

    private final String level;

    UnexpectedErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.level = LEVEL_UNEXPECTED;
    }

}
