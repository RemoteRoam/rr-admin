package tech.remote.base.module.support.heartbeat;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.base.module.support.heartbeat.domain.HeartBeatRecordEntity;
import tech.remote.base.module.support.heartbeat.domain.HeartBeatRecordQueryForm;
import tech.remote.base.module.support.heartbeat.domain.HeartBeatRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 心跳记录
 *
 * @Author 远游工作室-yy
 * @Date 2022-01-09 20:57:24
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Component
@Mapper
public interface HeartBeatRecordDao extends BaseMapper<HeartBeatRecordEntity> {

    /**
     * 更新心跳日志
     *
     * @param id
     * @param heartBeatTime
     */
    void updateHeartBeatTimeById(@Param("id") Long id, @Param("heartBeatTime") LocalDateTime heartBeatTime);

    /**
     * 查询心跳日志
     *
     * @param heartBeatRecordEntity
     * @return
     */
    HeartBeatRecordEntity query(HeartBeatRecordEntity heartBeatRecordEntity);

    /**
     * 分页查询
     * @param heartBeatRecordQueryForm
     * @return
     */
    List<HeartBeatRecordVO> pageQuery(Page page, @Param("query") HeartBeatRecordQueryForm heartBeatRecordQueryForm);
}
