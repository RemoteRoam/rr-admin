package tech.remote.admin.module.business.goods.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 型号规格 列表VO
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@Data
@ColumnWidth(15)
public class SkusExcelVO {

    @ExcelProperty("商品分类")
    private String categoryName;

    @ExcelProperty("商品名称")
    private String goodsName;

    @ExcelProperty("型号规格")
    private String skuName;

    @ExcelProperty("长度")
    private String length;

    @ExcelProperty("库存数量")
    private Integer stockQuantity;

//    @ExcelProperty("重量")
//    private BigDecimal weight;

//    @ExcelProperty("备注")
//    private String remark;
//
//    @ExcelProperty("更新时间")
//    private LocalDateTime updateTime;
//
//    @ExcelProperty("创建时间")
//    @ColumnWidth(20)
//    private LocalDateTime createTime;

}