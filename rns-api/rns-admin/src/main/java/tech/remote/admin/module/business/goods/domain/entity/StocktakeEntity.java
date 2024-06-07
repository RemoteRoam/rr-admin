package tech.remote.admin.module.business.goods.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 盘点表 实体类
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Data
@TableName("t_stocktake")
public class StocktakeEntity {

    /**
     * 盘点ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 盘点单号
     */
    private String stocktakeNo;

    /**
     * 商品类目ID
     */
    private Integer categoryId;

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