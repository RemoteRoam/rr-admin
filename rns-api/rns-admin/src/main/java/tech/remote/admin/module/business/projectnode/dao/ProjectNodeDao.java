package tech.remote.admin.module.business.projectnode.dao;

import java.util.List;
import tech.remote.admin.module.business.projectnode.domain.entity.ProjectNodeEntity;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeQueryForm;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 项目流程节点表 Dao
 *
 * @Author cbh
 * @Date 2024-04-26 22:55:15
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface ProjectNodeDao extends BaseMapper<ProjectNodeEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectNodeVO> queryPage(Page page, @Param("queryForm") ProjectNodeQueryForm queryForm);


}
