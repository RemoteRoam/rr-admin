package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 销售单表 实体类
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_sales")
public class SalesEntity {

    /**
     * 销售单ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 销售单号
     */
    private String salesNo;

    /**
     * 销售类型（0:库存，1:厂家）
     */
    private Integer salesType;

    /**
     * 供货厂家
     */
    private String supplier;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 销售经理ID
     */
    private Long managerId;

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 合同日
     */
    private LocalDate contractDate;

    /**
     * 合同金额
     */
    private BigDecimal contractAmount;

    /**
     * 回款日期
     */
    private LocalDate receivedPaymentDate;

    /**
     * 回款金额
     */
    private BigDecimal receivedPaymentAmount;

    /**
     * 发货日期
     */
    private LocalDate shippingDate;

    /**
     * 发货金额
     */
    private BigDecimal shippingAmount;

    /**
     * 开票日期
     */
    private LocalDate invoiceDate;

    /**
     * 发票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 发票号
     */
    private String invoiceNumber;

    /**
     * 状态
     */
    private Integer status;

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