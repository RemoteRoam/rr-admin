package tech.remote.admin.module.business.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.dao.StocktakeDao;
import tech.remote.admin.module.business.goods.domain.entity.StocktakeItemEntity;
import tech.remote.admin.module.business.goods.domain.entity.StocktakeEntity;
import tech.remote.admin.module.business.goods.domain.form.*;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseItemVO;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseVO;
import tech.remote.admin.module.business.goods.domain.vo.StocktakeItemVO;
import tech.remote.admin.module.business.goods.domain.vo.StocktakeVO;
import tech.remote.admin.module.business.goods.manager.SkusManager;
import tech.remote.admin.module.business.goods.manager.StocktakeItemManager;
import tech.remote.base.common.enumeration.RecordTypeEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import tech.remote.base.module.support.serialnumber.constant.SerialNumberIdEnum;
import tech.remote.base.module.support.serialnumber.service.SerialNumberService;

import javax.annotation.Resource;

/**
 * 盘点表 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Service
public class StocktakeService {

    @Resource
    private StocktakeDao stocktakeDao;

    @Resource
    private SerialNumberService serialNumberService;

    @Resource
    private SkusManager skusManager;

    @Resource
    private StocktakeItemManager stocktakeItemManager;

    @Resource
    private StocktakeItemService stocktakeItemService;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<StocktakeVO> queryPage(StocktakeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<StocktakeVO> list = stocktakeDao.queryPage(page, queryForm);
        PageResult<StocktakeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(StocktakeAddForm addForm) {
        // 如果没有明细，直接返回
        if (CollectionUtils.isEmpty(addForm.getItemList())){
            return ResponseDTO.userErrorParam("明细不能为空");
        }

        StocktakeEntity stocktakeEntity = SmartBeanUtil.copy(addForm, StocktakeEntity.class);

        String purchaseNo = serialNumberService.generate(SerialNumberIdEnum.STOCKTAKE_NO);
        stocktakeEntity.setStocktakeNo(purchaseNo);
        
        stocktakeDao.insert(stocktakeEntity);

        // 插入明细
        List<StocktakeItemAddForm> itemList = addForm.getItemList();
        List<StocktakeItemEntity> entityList = SmartBeanUtil.copyList(itemList, StocktakeItemEntity.class);
        List<SkusStockUpdateForm> skusStockUpdateFormList = SmartBeanUtil.copyList(itemList, SkusStockUpdateForm.class);
        for (StocktakeItemEntity purchaseItemEntity : entityList) {

            purchaseItemEntity.setStocktakeId(stocktakeEntity.getId());
        }
        stocktakeItemManager.saveBatch(entityList);

        // 修改库存
        skusManager.batchUpdateStock(skusStockUpdateFormList, RecordTypeEnum.INVENTORY_CHECK, stocktakeEntity.getId());

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(StocktakeUpdateForm updateForm) {
        StocktakeEntity stocktakeEntity = SmartBeanUtil.copy(updateForm, StocktakeEntity.class);
        stocktakeDao.updateById(stocktakeEntity);
        return ResponseDTO.ok();
    }

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    public ResponseDTO<String> batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)){
            return ResponseDTO.ok();
        }

        stocktakeDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        stocktakeDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    public StocktakeVO getDetail(Long id) {
        StocktakeEntity stocktakeEntity = stocktakeDao.selectById(id);
        StocktakeVO stocktakeVO = SmartBeanUtil.copy(stocktakeEntity, StocktakeVO.class);

        List<StocktakeItemVO> purchaseItemVOList = stocktakeItemService.listByStocktakeId(id);
        stocktakeVO.setItemList(purchaseItemVOList);
        return stocktakeVO;
    }
}
