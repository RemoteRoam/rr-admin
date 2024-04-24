package tech.remote.admin.module.business.third.dao;

import java.util.List;
import tech.remote.admin.module.business.third.domain.entity.ThirdPartyEntity;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyQueryForm;
import tech.remote.admin.module.business.third.domain.vo.ThirdPartyVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 三方机构 Dao
 *
 * @Author cbh
 * @Date 2024-04-24 09:19:13
 * @Copyright Remote Nomad Studio
 */

@Mapper
@Component
public interface ThirdPartyDao extends BaseMapper<ThirdPartyEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ThirdPartyVO> queryPage(Page page, @Param("queryForm") ThirdPartyQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Long id,@Param("${deletedFlag}")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("${deletedFlag}")boolean deletedFlag);

}
