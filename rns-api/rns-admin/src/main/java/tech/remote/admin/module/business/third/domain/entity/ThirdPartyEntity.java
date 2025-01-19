package tech.remote.admin.module.business.third.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 三方机构 实体类
 *
 * @Author cbh
 * @Date 2024-04-24 09:19:13
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_third_party")
public class ThirdPartyEntity {

    /**
     * 三方ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 三方类型
     */
    private String type;

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