package tech.remote.base.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 远游工作室-yy
 * @Date 2023/10/25 09:47:13
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>，Since 2012
 */

@Getter
@AllArgsConstructor
public enum NodeStatusEnum implements BaseEnum {

    /**
     * 未开始
     */
    INIT(0, "未开始"),

    /**
     * 处理中
     */
    DOING(1, "处理中"),

    /**
     * 完成
     */
    OK(2, "完成"),

    /**
     * 跳过
     */
    JUMP(3, "跳过"),
    ;
    private final Integer value;

    private final String desc;

}
