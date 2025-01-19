package tech.remote.base.module.support.loginlog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.enumeration.UserTypeEnum;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.module.support.loginlog.domain.LoginLogEntity;
import tech.remote.base.module.support.loginlog.domain.LoginLogQueryForm;
import tech.remote.base.module.support.loginlog.domain.LoginLogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志
 *
 * @Author 远游工作室-yy
 * @Date 2022/07/22 19:46:23
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Service
@Slf4j
public class LoginLogService {

    @Resource
    private LoginLogDao loginLogDao;

    /**
     * @author 卓大
     * @description 分页查询
     */
    public ResponseDTO<PageResult<LoginLogVO>> queryByPage(LoginLogQueryForm queryForm) {
        Page page = SmartPageUtil.convert2PageQuery(queryForm);
        List<LoginLogVO> logList = loginLogDao.queryByPage(page, queryForm);
        PageResult<LoginLogVO> pageResult = SmartPageUtil.convert2PageResult(page, logList);
        return ResponseDTO.ok(pageResult);
    }

    /**
     * @author 卓大
     * @description 添加
     */
    public void log(LoginLogEntity loginLogEntity) {
        try {
            loginLogDao.insert(loginLogEntity);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 查询上一个登录记录
     *
     * @author 卓大
     * @description 查询上一个登录记录
     */
    public LoginLogVO queryLastByUserId(Long userId, UserTypeEnum userTypeEnum, LoginLogResultEnum loginLogResultEnum) {
        return loginLogDao.queryLastByUserId(userId,userTypeEnum.getValue(), loginLogResultEnum.getValue());
    }

}
