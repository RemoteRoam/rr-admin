package tech.remote.admin.module.business.project.dao;

import java.util.List;
import tech.remote.admin.module.business.project.domain.entity.ProjectLabEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectLabListQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectLabQueryForm;
import tech.remote.admin.module.business.project.domain.vo.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 项目试验室任务表 Dao
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

    //    办公室待办、试验室任务列表、
    List<ProjectLabListVO> selectProjectLabs(Page page, @Param("queryForm") ProjectLabListQueryForm queryForm);

    List<ProjectLabExcelVO> selectExcelList(@Param("queryForm") ProjectLabListQueryForm queryForm);

    //    试验室待办
    List<ProjectProductListVO> selectLabsTodo(Page page, @Param("queryForm") ProjectLabListQueryForm queryForm);

    //    试验室待办总数
    Long selectLabsTodoCount(@Param("queryForm") ProjectLabListQueryForm queryForm);

    //    试验室预警
    List<ProjectProductListVO> selectLabsAlarm(Page page, @Param("queryForm") ProjectLabListQueryForm queryForm);

    List<ProjectLabProductExcelVO> selectLabProductExcelList(@Param("queryForm") ProjectLabListQueryForm queryForm);

}
