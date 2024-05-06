package tech.remote.admin.module.business.node.dao;

import java.util.List;
import tech.remote.admin.module.business.node.domain.entity.NodeEntity;
import tech.remote.admin.module.business.node.domain.form.NodeQueryForm;
import tech.remote.admin.module.business.node.domain.vo.NodeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 节点表 Dao
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface NodeDao extends BaseMapper<NodeEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<NodeVO> queryPage(Page page, @Param("queryForm") NodeQueryForm queryForm);


}
