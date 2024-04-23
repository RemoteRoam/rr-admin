package tech.remote.base.module.support.heartbeat;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.module.support.heartbeat.domain.HeartBeatRecordQueryForm;
import tech.remote.base.module.support.heartbeat.domain.HeartBeatRecordVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Slf4j
@Service
public class HeartBeatService {

    @Resource
    private HeartBeatRecordDao heartBeatRecordDao;

    public ResponseDTO<PageResult<HeartBeatRecordVO>> pageQuery(HeartBeatRecordQueryForm pageParam) {
        Page pageQueryInfo = SmartPageUtil.convert2PageQuery(pageParam);
        List<HeartBeatRecordVO> recordVOList = heartBeatRecordDao.pageQuery(pageQueryInfo,pageParam);
        PageResult<HeartBeatRecordVO> pageResult = SmartPageUtil.convert2PageResult(pageQueryInfo, recordVOList);
        return ResponseDTO.ok(pageResult);
    }
}
