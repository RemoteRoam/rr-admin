package tech.remote.admin.module.business.oa.bank;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.admin.module.business.oa.bank.domain.BankEntity;
import tech.remote.admin.module.business.oa.bank.domain.BankQueryForm;
import tech.remote.admin.module.business.oa.bank.domain.BankVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * OA办公-OA银行信息
 *
 * @Author 远游工作室:善逸
 * @Date 2022/6/23 21:59:22
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Mapper
@Component
public interface BankDao extends BaseMapper<BankEntity> {

    /**
     * 根据账号查询
     */
    BankEntity queryByAccountNumber(@Param("enterpriseId") Long enterpriseId, @Param("accountNumber") String accountNumber, @Param("excludeBankId") Long excludeBankId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 删除银行信息
     *
     */
    void deleteBank(@Param("bankId") Long bankId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 银行信息分页查询
     *
     */
    List<BankVO> queryPage(Page page, @Param("queryForm") BankQueryForm queryForm);

    /**
     * 查询银行信息详情
     */
    BankVO getDetail(@Param("bankId") Long bankId, @Param("deletedFlag") Boolean deletedFlag);
}
