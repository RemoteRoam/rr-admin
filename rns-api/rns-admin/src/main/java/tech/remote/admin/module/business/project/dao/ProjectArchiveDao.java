package tech.remote.admin.module.business.project.dao;

import java.util.List;
import tech.remote.admin.module.business.project.domain.entity.ProjectArchiveEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectArchiveQueryForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectArchiveVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 项目归档表 Dao
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface ProjectArchiveDao extends BaseMapper<ProjectArchiveEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectArchiveVO> queryPage(Page page, @Param("queryForm") ProjectArchiveQueryForm queryForm);


}
