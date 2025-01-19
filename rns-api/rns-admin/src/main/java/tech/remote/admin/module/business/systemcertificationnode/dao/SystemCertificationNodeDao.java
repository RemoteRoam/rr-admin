package tech.remote.admin.module.business.systemcertificationnode.dao;

import java.util.List;
import tech.remote.admin.module.business.systemcertificationnode.domain.entity.SystemCertificationNodeEntity;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeQueryForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.vo.SystemCertificationNodeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 体系认证项目流程节点表 Dao
 *
 * @Author cbh
 * @Date 2024-05-04 16:20:54
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface SystemCertificationNodeDao extends BaseMapper<SystemCertificationNodeEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<SystemCertificationNodeVO> queryPage(Page page, @Param("queryForm") SystemCertificationNodeQueryForm queryForm);


}
