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
public enum ProjectStatusEnum implements BaseEnum {

    /**
     * 未开始
     */
    INIT(0, "未开始"),

    /**
     * 处理中
     */
    DOING(1, "进行中"),

    /**
     * 完成
     */
    DONE(2, "已完成"),
    ;
    private final Integer value;

    private final String desc;

    // 根据value获取desc
    public static String getDescByValue(Integer value) {
        for (ProjectStatusEnum e : ProjectStatusEnum.values()) {
            if (e.value.equals(value)) {
                return e.desc;
            }
        }
        return null;
    }

}
