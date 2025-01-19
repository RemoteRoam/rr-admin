package tech.remote.base.module.support.codegenerator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.remote.base.module.support.codegenerator.domain.entity.CodeGeneratorConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 表的 代码生成配置 Dao
 * @Author 远游工作室-yy
 * @Date 2022-09-23 20:15:38
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Mapper
@Component
public interface CodeGeneratorConfigDao extends BaseMapper<CodeGeneratorConfigEntity> {

}