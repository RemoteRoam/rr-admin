package tech.remote.base.module.support.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.base.module.support.config.domain.ConfigEntity;
import tech.remote.base.module.support.config.domain.ConfigQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统参数配置 t_config Dao层
 *
 * @Author 远游工作室-yy
 * @Date 2022-03-14 20:46:27
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Component
@Mapper
public interface ConfigDao extends BaseMapper<ConfigEntity> {

    /**
     * 分页查询系统配置
     *
     */
    List<ConfigEntity> queryByPage(Page page, @Param("query") ConfigQueryForm queryForm);

    /**
     * 根据key查询获取数据
     *
     */
    ConfigEntity selectByKey(String key);
}
