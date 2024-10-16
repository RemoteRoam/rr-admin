package tech.remote.base.module.support.securityprotect.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.base.module.support.securityprotect.domain.LoginFailEntity;
import tech.remote.base.module.support.securityprotect.domain.LoginFailQueryForm;
import tech.remote.base.module.support.securityprotect.domain.LoginFailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 登录失败
 *
 * @Author 远游工作室-yy
 * @Date 2022/07/22 19:46:23
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Mapper
@Component
public interface LoginFailDao extends BaseMapper<LoginFailEntity> {

    /**
     * 根据用户id和类型查询
     *
     * @param userId
     * @param userType
     * @return
     */
    LoginFailEntity selectByUserIdAndUserType(@Param("userId") Long userId, @Param("userType") Integer userType);

    /**
     * 根据用户id和类型查询 进行删除
     *
     * @param userId
     * @param userType
     * @return
     */
    void deleteByUserIdAndUserType(@Param("userId") Long userId, @Param("userType") Integer userType);

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<LoginFailVO> queryPage(Page page, @Param("queryForm") LoginFailQueryForm queryForm);
}
