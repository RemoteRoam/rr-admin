package tech.remote.admin.module.business.typenode.dao;

import java.util.List;
import tech.remote.admin.module.business.typenode.domain.entity.TypeNodeEntity;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQuery;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQueryForm;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 项目类型节点关系表 Dao
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface TypeNodeDao extends BaseMapper<TypeNodeEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<TypeNodeVO> queryPage(Page page, @Param("queryForm") TypeNodeQueryForm queryForm);

    /**
     * 根据项目类型和对应级别获取对应的节点
     * @param query
     * @return
     */
    List<TypeNodeListVO> list(@Param("query") TypeNodeQuery query);

}
