package tech.remote.base.module.support.serialnumber.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.remote.base.common.constant.StringConst;
import tech.remote.base.common.enumeration.BaseEnum;

/**
 * 单据序列号 周期
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@AllArgsConstructor
@Getter
public enum SerialNumberRuleTypeEnum implements BaseEnum {
    /**
     * 没有周期
     */
    NONE(StringConst.EMPTY, "", "没有周期"),
    /**
     * 年周期
     */
    YEAR("[yyyy]", "\\[yyyy\\]", "年"),
    /**
     * 月周期
     */
    MONTH("[mm]", "\\[mm\\]", "年月"),
    /**
     * 日周期
     */
    DAY("[dd]", "\\[dd\\]", "年月日");

    private final String value;

    private final String regex;

    private final String desc;


}
