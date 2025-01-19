package tech.remote.base.common.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 校验数据是否为空的包装类
 *
 * @Author 远游工作室: 胡克
 * @Date 2020/10/16 21:06:11
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Data
public class ValidateData<T> {

    @NotNull(message = "数据不能为空哦")
    private T data;
}