package tech.remote.admin.module.business.customer.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 客户表 实体类
 *
 * @Author cbh
 * @Date 2024-04-23 12:10:04
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_customer")
public class CustomerEntity {

    /**
     * 客户ID
     */
    @TableId(type = IdType.AUTO)
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户级别
     */
    private String customerLevel;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 省份
     */
    private Integer province;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 市
     */
    private Integer city;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 区县
     */
    private Integer district;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 工厂省份
     */
    private Integer factoryProvince;

    /**
     * 工厂省份名称
     */
    private String factoryProvinceName;

    /**
     * 工厂市
     */
    private Integer factoryCity;

    /**
     * 工厂城市名称
     */
    private String factoryCityName;

    /**
     * 工厂区县
     */
    private Integer factoryDistrict;

    /**
     * 工厂区县名称
     */
    private String factoryDistrictName;

    /**
     * 工厂详细地址
     */
    private String factoryAddress;

    /**
     * 企业规模
     */
    private String size;

    /**
     * 主营业务
     */
    private String business;

    /**
     * 禁用状态
     */
    private Integer disabledFlag;

    /**
     * 删除状态
     */
    private Integer deletedFlag;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long updateUserId;

    /**
     * 更新人姓名
     */
    private String updateUserName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}