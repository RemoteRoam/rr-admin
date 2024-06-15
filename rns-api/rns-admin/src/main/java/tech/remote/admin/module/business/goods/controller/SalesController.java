package tech.remote.admin.module.business.goods.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.remote.admin.module.business.goods.domain.entity.SalesNodeEntity;
import tech.remote.admin.module.business.goods.domain.form.SalesAddForm;
import tech.remote.admin.module.business.goods.domain.form.SalesQueryForm;
import tech.remote.admin.module.business.goods.domain.form.SalesUpdateForm;
import tech.remote.admin.module.business.goods.domain.form.SkusQueryForm;
import tech.remote.admin.module.business.goods.domain.vo.*;
import tech.remote.admin.module.business.goods.service.SalesService;
import tech.remote.base.common.domain.RequestUser;
import tech.remote.base.common.domain.ValidateList;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tech.remote.base.common.enumeration.SalesCompanyEnum;
import tech.remote.base.common.util.SmartRequestUtil;
import tech.remote.base.common.util.SmartResponseUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售单表 Controller
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@RestController
@Tag(name = "")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Operation(summary = "分页查询 @author cbh")
    @PostMapping("/sales/queryPage")
    public ResponseDTO<PageResult<SalesVO>> queryPage(@RequestBody @Valid SalesQueryForm queryForm) {
        return ResponseDTO.ok(salesService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author cbh")
    @PostMapping("/sales/add")
    public ResponseDTO<String> add(@RequestBody @Valid SalesAddForm addForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        return salesService.add(addForm);
    }

    @Operation(summary = "更新 @author cbh")
    @PostMapping("/sales/update")
    public ResponseDTO<String> update(@RequestBody @Valid SalesUpdateForm updateForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        updateForm.setUpdateUserId(requestUser.getUserId());
        updateForm.setUpdateUserName(requestUser.getUserName());
        return salesService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author cbh")
    @PostMapping("/sales/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return salesService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author cbh")
    @GetMapping("/sales/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return salesService.delete(id);
    }

    @Operation(summary = "查询详情")
    @GetMapping("/sales/detail/{id}")
    public ResponseDTO<SalesVO> detail(@PathVariable Long id) {
        return ResponseDTO.ok(salesService.getDetail(id));
    }

    @Operation(summary = "导出 @author cbh")
    @PostMapping("/sales/exportExcel")
    public void exportExcel(@RequestBody @Valid SalesQueryForm queryForm, HttpServletResponse response) throws IOException {
        List<SalesExcelVO> data = salesService.getExcelExportData(queryForm);
        if (CollectionUtils.isEmpty(data)) {
            SmartResponseUtil.write(response, ResponseDTO.userErrorParam("暂无数据"));
            return;
        }

        String fileName = "销售报表-" + DateUtil.today() + ".xls";

        // 设置下载消息头
        SmartResponseUtil.setDownloadFileHeader(response, fileName, null);

        // 下载
        EasyExcel.write(response.getOutputStream(), SalesExcelVO.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet("销售")
                .doWrite(data);
    }


    @Operation(summary = "打印 @author cbh")
    @PostMapping("/sales/printExcel/{id}")
    public void exportExcel(@PathVariable Long id, HttpServletResponse response) throws IOException {
        SalesVO data = salesService.getDetail(id);
        if (null == data) {
            SmartResponseUtil.write(response, ResponseDTO.userErrorParam("暂无数据"));
            return;
        }
        SalesTemplateVO templateVO = new SalesTemplateVO();
        List<SalesTemplateItemVO> items = new ArrayList<>();
        templateVO.setCompany(SalesCompanyEnum.getDescByValue(data.getCompany()));
        templateVO.setCustomerName(data.getCustomerName());
        templateVO.setContractNo(data.getContractNo());
        String shippingDate = data.getShippingDate() == null ? "" : data.getShippingDate().toString();
        templateVO.setShippingDate(shippingDate);

        for (int i = 0; i < data.getItemVOList().size(); i++) {
            SalesItemVO item = data.getItemVOList().get(i);
            SalesTemplateItemVO itemVO = new SalesTemplateItemVO();
            itemVO.setNo(i + 1);
            itemVO.setGoodsName(item.getGoodsName());
            itemVO.setCategoryName(item.getCategoryName());
            itemVO.setSkuName(item.getSkuName());
            itemVO.setWeight(item.getWeight());
            itemVO.setQuantity(item.getQuantity());
            itemVO.setUnitPrice(item.getUnitPrice());
            itemVO.setRemark(item.getRemark());
            items.add(itemVO);
            templateVO.setQuantityTotal(templateVO.getQuantityTotal() + item.getQuantity());
            templateVO.setWeightTotal(templateVO.getWeightTotal().add(item.getWeight()));
        }

        SalesTemplateItemVO itemEnd = new SalesTemplateItemVO();
        itemEnd.setGoodsName("以下空白");
        items.add(itemEnd);

        try {
            ClassPathResource classPathResource = new ClassPathResource("template/sales_template.xlsx");
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                    .withTemplate(classPathResource.getInputStream())
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();


            excelWriter.fill(items, writeSheet);
            excelWriter.fill(templateVO, writeSheet);

            // 设置下载消息头
            String fileName = "销售单-" + data.getSalesNo() + ".xls";
            SmartResponseUtil.setDownloadFileHeader(response, fileName, null);

            excelWriter.finish();

//            return FileUtil.buildResponse(new File(tempFileName), exportName); // 返回一个ResponseEntity<byte[]>
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("数据导出异常");
        }
    }
}
