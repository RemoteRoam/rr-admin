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
public enum SalesCompanyEnum implements BaseEnum {
    TCCTXNY(1, "天传诚通新能源科技（天津）有限公司"),
    TCCT(2, "天津天传诚通科技发展有限公司"),
    ;
    private final Integer value;

    private final String desc;

    // 根据value获取desc
    public static String getDescByValue(Integer value) {
        for (SalesCompanyEnum e : SalesCompanyEnum.values()) {
            if (e.value.equals(value)) {
                return e.desc;
            }
        }
        return null;
    }

}
