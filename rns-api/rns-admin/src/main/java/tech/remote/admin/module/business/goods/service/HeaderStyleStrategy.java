package tech.remote.admin.module.business.goods.service;

import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.IndexedColors;

public class HeaderStyleStrategy extends HorizontalCellStyleStrategy {
    private final WriteCellStyle headWriteCellStyle = new WriteCellStyle();
    @Override
    protected void setHeadCellStyle( CellWriteHandlerContext context) {
        // 获取字体实例
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("宋体");
        if (context.getRowIndex() == 0) {
            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            headWriteFont.setFontHeightInPoints((short) 14);
            headWriteFont.setBold(true);
        }else{
            headWriteCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headWriteFont.setFontHeightInPoints((short) 11);
            headWriteFont.setBold(false);
        }
        headWriteCellStyle.setWriteFont(headWriteFont);
        if (stopProcessing(context)) {
            return;
        }
        WriteCellData<?> cellData = context.getFirstCellData();
        WriteCellStyle.merge(headWriteCellStyle, cellData.getOrCreateStyle());
    }
}
