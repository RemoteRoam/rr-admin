package tech.remote.admin.module.business.customer.manager;

import tech.remote.admin.module.business.customer.domain.entity.CustomerEntity;
import tech.remote.admin.module.business.customer.dao.CustomerDao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 客户表  Manager
 *
 * @Author cbh
 * @Date 2024-04-23 12:10:04
 * @Copyright Remote Nomad Studio
 */
@Service
public class CustomerManager extends ServiceImpl<CustomerDao, CustomerEntity> {


}
