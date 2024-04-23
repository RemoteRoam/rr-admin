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
public enum DataTypeEnum implements BaseEnum {

    /**
     *普通数据
     */
    NORMAL(1, "普通数据"),

    /**
     * 加密数据
     */
    ENCRYPT(10, "加密数据"),
    ;
    private final Integer value;

    private final String desc;

}
