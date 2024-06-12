package tech.remote.admin.module.business.project.dao;

import java.util.List;

import tech.remote.admin.module.business.measurement.domain.form.MeasurementQueryForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementExcelVO;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementVO;
import tech.remote.admin.module.business.project.domain.entity.ProjectEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectAlarmQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectToDoQueryForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectExcelVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 项目表 Dao
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface ProjectDao extends BaseMapper<ProjectEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectVO> queryPage(Page page, @Param("queryForm") ProjectQueryForm queryForm);

    /**
     * 待办列表分页查询
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectVO> queryToDoPage(Page page, @Param("queryForm") ProjectToDoQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Long id,@Param("deletedFlag")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("deletedFlag")boolean deletedFlag);

    ProjectVO getDetail(@Param("id")Long id);

    /**
     * 导出
     *
     * @param queryForm
     * @return
     */
    List<ProjectExcelVO> selectExcelList(@Param("queryForm") ProjectQueryForm queryForm);


    /**
     * 预警列表分页查询
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectVO> queryAlarmPage(Page page, @Param("queryForm") ProjectAlarmQueryForm queryForm);

    /**
     * 预警数量
     * @param alarmType
     * @return
     */
    int queryAlarmCount(@Param("alarmType")Integer alarmType);
}
