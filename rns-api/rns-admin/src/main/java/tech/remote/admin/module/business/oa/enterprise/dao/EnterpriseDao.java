package tech.remote.admin.module.business.oa.enterprise.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.admin.module.business.oa.enterprise.domain.entity.EnterpriseEntity;
import tech.remote.admin.module.business.oa.enterprise.domain.form.EnterpriseQueryForm;
import tech.remote.admin.module.business.oa.enterprise.domain.vo.EnterpriseExcelVO;
import tech.remote.admin.module.business.oa.enterprise.domain.vo.EnterpriseListVO;
import tech.remote.admin.module.business.oa.enterprise.domain.vo.EnterpriseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 企业
 *
 * @Author 远游工作室: 开云
 * @Date 2022/7/28 20:37:15
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Mapper
@Component
public interface EnterpriseDao extends BaseMapper<EnterpriseEntity> {

    /**
     * 根据企业名称查询
     *
     */
    EnterpriseEntity queryByEnterpriseName(@Param("enterpriseName") String enterpriseName, @Param("excludeEnterpriseId") Long excludeEnterpriseId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 删除企业
     */
    void deleteEnterprise(@Param("enterpriseId") Long enterpriseId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 企业分页查询
     *
     */
    List<EnterpriseVO> queryPage(Page page, @Param("queryForm") EnterpriseQueryForm queryForm);

    /**
     * 查询导出的数据
     *
     */
    List<EnterpriseExcelVO> selectExcelExportData(@Param("queryForm") EnterpriseQueryForm queryForm);

    /**
     * 查询企业详情
     *
     */
    EnterpriseVO getDetail(@Param("enterpriseId") Long enterpriseId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 查询列表
     *
     */
    List<EnterpriseListVO> queryList(@Param("type") Integer type, @Param("disabledFlag") Boolean disabledFlag, @Param("deletedFlag") Boolean deletedFlag);


}
