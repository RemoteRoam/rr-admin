package tech.remote.admin.module.business.project.dao;

import java.util.List;
import tech.remote.admin.module.business.project.domain.entity.ProjectLabEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectLabQueryForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectLabProgressVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectLabVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tech.remote.admin.module.business.project.domain.vo.ProjectVO;

/**
 * 项目实验室任务表 Dao
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface ProjectLabDao extends BaseMapper<ProjectLabEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectLabVO> queryPage(Page page, @Param("queryForm") ProjectLabQueryForm queryForm);


    ProjectLabVO getDetail(@Param("id")Long id);

    ProjectLabProgressVO getByCode(@Param("progressCode")String progressCode);
}
