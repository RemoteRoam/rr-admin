package tech.remote.base.module.support.serialnumber.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.remote.base.module.support.serialnumber.domain.SerialNumberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 单据序列号
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Mapper
@Component
public interface SerialNumberDao extends BaseMapper<SerialNumberEntity> {

    /**
     * 排他锁查询
     *
     * @param serialNumberId
     * @return
     */
    SerialNumberEntity selectForUpdate(@Param("serialNumberId") Integer serialNumberId);

    /**
     * 更新上一次的 数值和时间
     *
     * @param serialNumberId
     * @param lastNumber
     * @param lastTime
     */
    void updateLastNumberAndTime(@Param("serialNumberId") Integer serialNumberId, @Param("lastNumber") Long lastNumber, @Param("lastTime") LocalDateTime lastTime);

}
