package tech.remote.base.module.support.serialnumber.service.impl;

import lombok.extern.slf4j.Slf4j;
import tech.remote.base.common.exception.BusinessException;
import tech.remote.base.module.support.serialnumber.domain.SerialNumberEntity;
import tech.remote.base.module.support.serialnumber.domain.SerialNumberGenerateResultBO;
import tech.remote.base.module.support.serialnumber.domain.SerialNumberInfoBO;
import tech.remote.base.module.support.serialnumber.domain.SerialNumberLastGenerateBO;
import tech.remote.base.module.support.serialnumber.service.SerialNumberBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单据序列号 基于mysql锁实现
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Slf4j
public class SerialNumberMysqlService extends SerialNumberBaseService {

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<String> generateSerialNumberList(SerialNumberInfoBO serialNumberInfo, int count) {
        // // 获取上次的生成结果
        SerialNumberEntity serialNumberEntity = serialNumberDao.selectForUpdate(serialNumberInfo.getSerialNumberId());
        if (serialNumberEntity == null) {
            throw new BusinessException("cannot found SerialNumberId 数据库不存在:" + serialNumberInfo.getSerialNumberId());
        }
        SerialNumberLastGenerateBO lastGenerateBO = SerialNumberLastGenerateBO
                .builder()
                .lastNumber(serialNumberEntity.getLastNumber())
                .lastTime(serialNumberEntity.getLastTime())
                .serialNumberId(serialNumberEntity.getSerialNumberId())
                .build();

        // 生成
        SerialNumberGenerateResultBO serialNumberGenerateResult = super.loopNumberList(lastGenerateBO, serialNumberInfo, count);

        // 将生成信息保存的内存和数据库
        lastGenerateBO.setLastNumber(serialNumberGenerateResult.getLastNumber());
        lastGenerateBO.setLastTime(serialNumberGenerateResult.getLastTime());
        serialNumberDao.updateLastNumberAndTime(serialNumberInfo.getSerialNumberId(),
                serialNumberGenerateResult.getLastNumber(),
                serialNumberGenerateResult.getLastTime());

        // 把生成过程保存到数据库里
        super.saveRecord(serialNumberGenerateResult);

        return formatNumberList(serialNumberGenerateResult, serialNumberInfo);
    }

    @Override
    public void initLastGenerateData(List<SerialNumberEntity> serialNumberEntityList) {

    }
}