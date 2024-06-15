package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldBigDecimal;
import tech.remote.base.module.support.datatracer.annoation.DataTracerFieldLabel;

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
    @DataTracerFieldLabel("销售类型")
    private Integer salesType;

    /**
     * 销售公司
     */
    @DataTracerFieldLabel("销售公司")
    private Integer company;

    /**
     * 供货厂家
     */
    @DataTracerFieldLabel("供货厂家")
    private String supplier;

    /**
     * 客户ID
     */
    @DataTracerFieldLabel("客户")
    private Long customerId;

    /**
     * 销售经理ID
     */
    @DataTracerFieldLabel("销售经理")
    private Long managerId;

    /**
     * 合同号
     */
    @DataTracerFieldLabel("合同号")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String contractNo;

    /**
     * 合同日
     */
    @DataTracerFieldLabel("合同日")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate contractDate;

    /**
     * 合同金额
     */
    @DataTracerFieldLabel("合同金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal contractAmount;

    /**
     * 回款日期
     */
    @DataTracerFieldLabel("回款日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate receivedPaymentDate;

    /**
     * 回款金额
     */
    @DataTracerFieldLabel("回款金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal receivedPaymentAmount;

    /**
     * 发货日期
     */
    @DataTracerFieldLabel("发货日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate shippingDate;

    /**
     * 发货金额
     */
    @DataTracerFieldLabel("发货金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal shippingAmount;

    /**
     * 开票日期
     */
    @DataTracerFieldLabel("开票日期")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDate invoiceDate;

    /**
     * 发票金额
     */
    @DataTracerFieldLabel("发票金额")
    @DataTracerFieldBigDecimal
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal invoiceAmount;

    /**
     * 发票号
     */
    @DataTracerFieldLabel("发票号")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
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
    @DataTracerFieldLabel("备注")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
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