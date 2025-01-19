package tech.remote.admin.module.business.project.dao;

import java.util.List;
import tech.remote.admin.module.business.project.domain.entity.ProjectCertificationFeeEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectCertificationFeeQueryForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectCertificationFeeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 项目认证费表 Dao
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface ProjectCertificationFeeDao extends BaseMapper<ProjectCertificationFeeEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ProjectCertificationFeeVO> queryPage(Page page, @Param("queryForm") ProjectCertificationFeeQueryForm queryForm);


}
