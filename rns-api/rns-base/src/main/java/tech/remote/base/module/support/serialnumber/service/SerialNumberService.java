package tech.remote.base.module.support.serialnumber.service;

import tech.remote.base.module.support.serialnumber.constant.SerialNumberIdEnum;

import java.util.List;

/**
 * 单据序列号
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-25 21:46:07
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public interface SerialNumberService {

    /**
     * 生成
     *
     * @param serialNumberIdEnum
     * @return
     */
    String generate(SerialNumberIdEnum serialNumberIdEnum);


    /**
     * 生成n个
     *
     * @param serialNumberIdEnum
     * @param count
     * @return
     */
    List<String> generate(SerialNumberIdEnum serialNumberIdEnum, int count);

}
