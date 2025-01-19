package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 采购入库表 实体类
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:47
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_purchase")
public class PurchaseEntity {

    /**
     * 采购入库ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 采购单号
     */
    private String purchaseNo;

    /**
     * 供货厂家
     */
    private String supplier;

    /**
     * 删除状态
     */
    private Integer deletedFlag;

    /**
     * 备注
     */
    private String remark;

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