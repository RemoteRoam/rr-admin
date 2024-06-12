package tech.remote.admin.module.business.goods.dao;

import tech.remote.admin.module.business.goods.domain.entity.SalesNodeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 销售流程节点表 Dao
 *
 * @Author cbh
 * @Date 2024-06-11 19:30:52
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface SalesNodeDao extends BaseMapper<SalesNodeEntity> {

}
