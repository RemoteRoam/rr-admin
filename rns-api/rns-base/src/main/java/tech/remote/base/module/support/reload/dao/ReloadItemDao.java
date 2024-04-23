package tech.remote.base.module.support.reload.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.remote.base.module.support.reload.domain.ReloadItemEntity;
import tech.remote.base.module.support.reload.domain.ReloadItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * t_reload_item 数据表dao
 *
 * @Author 远游工作室-yy
 * @Date 2015-03-02 19:11:52
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Component
@Mapper
public interface ReloadItemDao extends BaseMapper<ReloadItemEntity> {

    List<ReloadItemVO> query();
}
