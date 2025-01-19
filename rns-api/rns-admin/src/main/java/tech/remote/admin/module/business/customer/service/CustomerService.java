package tech.remote.admin.module.business.customer.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.customer.dao.CustomerDao;
import tech.remote.admin.module.business.customer.domain.entity.CustomerEntity;
import tech.remote.admin.module.business.customer.domain.form.CustomerAddForm;
import tech.remote.admin.module.business.customer.domain.form.CustomerQueryForm;
import tech.remote.admin.module.business.customer.domain.form.CustomerUpdateForm;
import tech.remote.admin.module.business.customer.domain.vo.CustomerVO;
import tech.remote.admin.module.business.customer.domain.vo.GrandTotalResult;
import tech.remote.admin.module.business.third.domain.vo.ThirdPartyVO;
import tech.remote.base.common.code.BusinessErrorCode;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 客户表 Service
 *
 * @Author cbh
 * @Date 2024-04-23 12:10:04
 * @Copyright Remote Nomad Studio
 */

@Service
public class CustomerService {

    @Resource
    private CustomerDao customerDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<CustomerVO> queryPage(CustomerQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<CustomerVO> list = customerDao.queryPage(page, queryForm);
        PageResult<CustomerVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(CustomerAddForm addForm) {
        // 校验客户名称是否存在
        LambdaQueryWrapper<CustomerEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CustomerEntity::getCustomerName, addForm.getCustomerName());
        lambdaQueryWrapper.eq(CustomerEntity::getDeletedFlag, NumberUtils.INTEGER_ZERO);
        lambdaQueryWrapper.eq(CustomerEntity::getType, addForm.getType());
        List<CustomerEntity> list = customerDao.selectList(lambdaQueryWrapper);
        if(CollectionUtils.isNotEmpty(list)){
            return ResponseDTO.error(BusinessErrorCode.CUSTOMER_NAME_EXIST_ERROR);
        }
        CustomerEntity customerEntity = SmartBeanUtil.copy(addForm, CustomerEntity.class);
        customerDao.insert(customerEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(CustomerUpdateForm updateForm) {
        // 校验客户名称是否存在
        LambdaQueryWrapper<CustomerEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CustomerEntity::getCustomerName, updateForm.getCustomerName());
        lambdaQueryWrapper.eq(CustomerEntity::getDeletedFlag, NumberUtils.INTEGER_ZERO);
        lambdaQueryWrapper.eq(CustomerEntity::getType, updateForm.getType());
        lambdaQueryWrapper.ne(CustomerEntity::getCustomerId, updateForm.getCustomerId());
        List<CustomerEntity> list = customerDao.selectList(lambdaQueryWrapper);
        if(CollectionUtils.isNotEmpty(list)){
            return ResponseDTO.error(BusinessErrorCode.CUSTOMER_NAME_EXIST_ERROR);
        }

        CustomerEntity customerEntity = SmartBeanUtil.copy(updateForm, CustomerEntity.class);
        customerDao.updateById(customerEntity);
        return ResponseDTO.ok();
    }

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    public ResponseDTO<String> batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)){
            return ResponseDTO.ok();
        }

        customerDao.batchUpdateDeleted(idList, Boolean.TRUE);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long customerId) {
        if (null == customerId){
            return ResponseDTO.ok();
        }

        customerDao.updateDeleted(customerId,Boolean.TRUE);
        return ResponseDTO.ok();
    }

    public CustomerVO getDetail(Long customerId) {
        CustomerEntity entity = customerDao.selectById(customerId);
        return SmartBeanUtil.copy(entity, CustomerVO.class);
    }

    public ResponseDTO<List<CustomerVO>> queryList(Integer type) {
        LambdaQueryWrapper<CustomerEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CustomerEntity::getDeletedFlag, Boolean.FALSE);
        lambdaQueryWrapper.eq(CustomerEntity::getDisabledFlag, Boolean.FALSE);
        lambdaQueryWrapper.eq(CustomerEntity::getType, type);
        lambdaQueryWrapper.orderByDesc(CustomerEntity::getCreateTime);
        List<CustomerEntity> list = customerDao.selectList(lambdaQueryWrapper);
        return ResponseDTO.ok(SmartBeanUtil.copyList(list, CustomerVO.class));
    }

    public void updateLevel(Long customerId, String level) {
        CustomerEntity entity = customerDao.selectById(customerId);
        entity.setCustomerLevel(level);
        customerDao.updateById(entity);
    }


    public void upgradeLevel(Long customerId) {
        GrandTotalResult result = customerDao.getGrandTotal(customerId);
        if(result == null || result.getGrandTotalCount() == null || result.getGrandTotalAmount() == null){
            return;
        }
        // 判断合同金额（contractAmount + result.grandTotalAmount）满100万，并且项目数量(result.grandTotalCount)满10个，设置级别为A，否则合同金额满50万，或者项目数量满5个设置级别为B
        if (result.getGrandTotalCount() >= 10 && (result.getGrandTotalAmount()).compareTo(new BigDecimal(1000000)) >= 0) {
            updateLevel(customerId, "LEVEL_A");
        }   else if (result.getGrandTotalCount() >= 5 || (result.getGrandTotalAmount()).compareTo(new BigDecimal(500000)) >= 0) {
            updateLevel(customerId, "LEVEL_B");
        }

    }
}
