package tech.remote.base.module.support.heartbeat.core;

/**
 * 心跳处理接口
 *
 * @Author 远游工作室-yy
 * @Date 2022-01-09 20:57:24
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public interface IHeartBeatRecordHandler {

    /**
     * 心跳日志处理方法
     *
     * @param heartBeatRecord
     */
    void handler(HeartBeatRecord heartBeatRecord);
}
