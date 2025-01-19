package tech.remote.admin.config;

import tech.remote.base.module.support.operatelog.core.OperateLogAspect;
import tech.remote.base.module.support.operatelog.core.OperateLogConfig;
import org.springframework.context.annotation.Configuration;

/**
 * 操作日志切面 配置
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022-05-30 21:22:12
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Configuration
public class OperateLogAspectConfig extends OperateLogAspect{

    /**
     * 配置信息
     */
    @Override
    public OperateLogConfig getOperateLogConfig() {
        return OperateLogConfig.builder().corePoolSize(1).queueCapacity(10000).build();
    }


}