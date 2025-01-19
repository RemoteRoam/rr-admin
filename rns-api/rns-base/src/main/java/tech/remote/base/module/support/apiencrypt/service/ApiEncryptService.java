package tech.remote.base.module.support.apiencrypt.service;

/**
 * 接口加密、解密 Service
 *
 * @Author 远游工作室-yy
 * @Date 2023/10/21 11:41:46
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */

public interface ApiEncryptService {

    /**
     * 解密
     * @param data
     * @return
     */
    String decrypt(String data);

    /**
     * 加密
     *
     * @param data
     * @return
     */
    String encrypt(String data);

}
