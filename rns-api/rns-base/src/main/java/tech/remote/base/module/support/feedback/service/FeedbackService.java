package tech.remote.base.module.support.feedback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.module.support.feedback.dao.FeedbackDao;
import tech.remote.base.module.support.feedback.domain.FeedbackAddForm;
import tech.remote.base.module.support.feedback.domain.FeedbackEntity;
import tech.remote.base.module.support.feedback.domain.FeedbackQueryForm;
import tech.remote.base.module.support.feedback.domain.FeedbackVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 意见反馈
 *
 * @Author 远游工作室: 开云
 * @Date 2022-08-11 20:48:09
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Service
public class FeedbackService {

    @Resource
    private FeedbackDao feedbackDao;

    /**
     * 分页查询
     *
     */
    public ResponseDTO<PageResult<FeedbackVO>> query(FeedbackQueryForm queryForm) {
        Page page = SmartPageUtil.convert2PageQuery(queryForm);
        List<FeedbackVO> list = feedbackDao.queryPage(page, queryForm);
        PageResult<FeedbackVO> pageResultDTO = SmartPageUtil.convert2PageResult(page, list);
        if (pageResultDTO.getEmptyFlag()) {
            return ResponseDTO.ok(pageResultDTO);
        }
        return ResponseDTO.ok(pageResultDTO);
    }

    /**
     * 新建
     */
    public ResponseDTO<String> add(FeedbackAddForm addForm, RequestUser requestUser) {
        FeedbackEntity feedback = SmartBeanUtil.copy(addForm, FeedbackEntity.class);
        feedback.setUserType(requestUser.getUserType().getValue());
        feedback.setUserId(requestUser.getUserId());
        feedback.setUserName(requestUser.getUserName());
        feedbackDao.insert(feedback);
        return ResponseDTO.ok();
    }
}
