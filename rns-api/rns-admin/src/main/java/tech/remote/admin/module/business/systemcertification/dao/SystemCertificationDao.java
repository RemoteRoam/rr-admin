package tech.remote.admin.module.business.systemcertification.dao;

import java.util.List;
import tech.remote.admin.module.business.systemcertification.domain.entity.SystemCertificationEntity;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationQueryForm;
import tech.remote.admin.module.business.systemcertification.domain.vo.SystemCertificationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 体系认证表 Dao
 *
 * @Author cbh
 * @Date 2024-04-25 14:53:11
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface SystemCertificationDao extends BaseMapper<SystemCertificationEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<SystemCertificationVO> queryPage(Page page, @Param("queryForm") SystemCertificationQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Long id,@Param("deletedFlag")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("deletedFlag")boolean deletedFlag);

    /**
     * 获取详情
     * @param id
     * @return
     */
    SystemCertificationVO getDetail(@Param("id")Long id);
}
