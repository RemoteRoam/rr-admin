package tech.remote.admin.module.business.customer.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.customer.dao.CustomerDao;
import tech.remote.admin.module.business.customer.domain.entity.CustomerEntity;
import tech.remote.admin.module.business.customer.domain.form.CustomerAddForm;
import tech.remote.admin.module.business.customer.domain.form.CustomerQueryForm;
import tech.remote.admin.module.business.customer.domain.form.CustomerUpdateForm;
import tech.remote.admin.module.business.customer.domain.vo.CustomerVO;
import tech.remote.admin.module.business.third.domain.vo.ThirdPartyVO;
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

    public ResponseDTO<List<CustomerVO>> queryList() {
        LambdaQueryWrapper<CustomerEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CustomerEntity::getDeletedFlag, Boolean.FALSE);
        lambdaQueryWrapper.eq(CustomerEntity::getDisabledFlag, Boolean.FALSE);
        lambdaQueryWrapper.orderByDesc(CustomerEntity::getCreateTime);
        List<CustomerEntity> list = customerDao.selectList(lambdaQueryWrapper);
        return ResponseDTO.ok(SmartBeanUtil.copyList(list, CustomerVO.class));
    }
}