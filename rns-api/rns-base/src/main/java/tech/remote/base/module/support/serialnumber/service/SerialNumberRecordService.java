package tech.remote.base.module.support.serialnumber.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.module.support.serialnumber.dao.SerialNumberRecordDao;
import tech.remote.base.module.support.serialnumber.domain.SerialNumberRecordEntity;
import tech.remote.base.module.support.serialnumber.domain.SerialNumberRecordQueryForm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单据序列号 记录
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Service
public class SerialNumberRecordService {

    @Resource
    private SerialNumberRecordDao serialNumberRecordDao;

    public PageResult<SerialNumberRecordEntity> query(SerialNumberRecordQueryForm queryForm) {
        Page page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SerialNumberRecordEntity> recordList = serialNumberRecordDao.query(page, queryForm);
        return SmartPageUtil.convert2PageResult(page, recordList);
    }
}
