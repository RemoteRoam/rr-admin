package tech.remote.admin.module.business.project.dao;

import java.util.List;
import tech.remote.admin.module.business.project.domain.entity.ProjectProductEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectProductQueryForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductProgressVO;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 项目产品表 Dao
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface ProjectProductDao extends BaseMapper<ProjectProductEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectProductVO> queryPage(Page page, @Param("queryForm") ProjectProductQueryForm queryForm);


    List<ProjectProductProgressVO> list(@Param("projectId")Long projectId, @Param("taskId")Long taskId);

    void updateCertificationFeeIdById(ProjectProductEntity productEntity);

    void updateArchiveIdById(ProjectProductEntity productEntity);

    void updateMailIdById(ProjectProductEntity productEntity);
}
