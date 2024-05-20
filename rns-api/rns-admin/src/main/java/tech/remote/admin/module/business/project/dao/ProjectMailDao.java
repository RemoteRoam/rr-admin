package tech.remote.admin.module.business.project.dao;

import java.util.List;
import tech.remote.admin.module.business.project.domain.entity.ProjectMailEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectMailQueryForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectMailVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 项目邮寄表 Dao
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface ProjectMailDao extends BaseMapper<ProjectMailEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectMailVO> queryPage(Page page, @Param("queryForm") ProjectMailQueryForm queryForm);


}
