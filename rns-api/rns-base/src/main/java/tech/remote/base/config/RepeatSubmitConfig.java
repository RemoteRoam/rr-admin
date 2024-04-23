package tech.remote.base.config;

import tech.remote.base.common.constant.StringConst;
import tech.remote.base.common.util.SmartRequestUtil;
import tech.remote.base.module.support.repeatsubmit.RepeatSubmitAspect;
import tech.remote.base.module.support.repeatsubmit.ticket.RepeatSubmitCaffeineTicket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 重复提交配置
 *
 * @Author 远游工作室: 罗伊
 * @Date 2021/10/9 18:47
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Configuration
public class RepeatSubmitConfig {

    @Bean
    public RepeatSubmitAspect repeatSubmitAspect() {
        RepeatSubmitCaffeineTicket caffeineTicket = new RepeatSubmitCaffeineTicket(this::ticket);
        return new RepeatSubmitAspect(caffeineTicket);
    }

    /**
     * 获取指明某个用户的凭证
     */
    private String ticket(String servletPath) {
        Long userId = SmartRequestUtil.getRequestUserId();
        if (null == userId) {
            return StringConst.EMPTY;
        }
        return servletPath + "_" + userId;
    }
}
