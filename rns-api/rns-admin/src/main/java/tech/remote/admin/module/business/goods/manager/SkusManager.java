package tech.remote.admin.module.business.goods.manager;

import tech.remote.admin.module.business.goods.domain.entity.InventoryFlowEntity;
import tech.remote.admin.module.business.goods.domain.entity.SkusEntity;
import tech.remote.admin.module.business.goods.dao.SkusDao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.domain.form.SkusStockUpdateForm;
import tech.remote.base.common.enumeration.RecordTypeEnum;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 型号规格  Manager
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */
@Service
public class SkusManager extends ServiceImpl<SkusDao, SkusEntity> {

    private final InventoryFlowManager inventoryFlowManager;

    public SkusManager(InventoryFlowManager inventoryFlowManager) {
        this.inventoryFlowManager = inventoryFlowManager;
    }

    /**
     * Updates the stock quantities for a list of SkusStockUpdateForm.
     *
     * @param  skusStockUpdateFormList    the list of SkusStockUpdateForm to update stock quantities
     * @return                            void
     */
    public void batchUpdateStock(List<SkusStockUpdateForm> skusStockUpdateFormList, RecordTypeEnum recordTypeEnum, Long recordId) {
        Map<Long, SkusEntity> skusMap = getSkusMap(skusStockUpdateFormList);

        // 插入库存流水记录表t_inventory_flow
        List<InventoryFlowEntity> inventoryFlowEntityList = skusStockUpdateFormList.stream()
                .map(form -> {
                    InventoryFlowEntity inventoryFlowEntity = new InventoryFlowEntity();
                    inventoryFlowEntity.setType(recordTypeEnum.getValue());
                    inventoryFlowEntity.setTaskId(recordId);
                    inventoryFlowEntity.setSkuId(form.getSkuId());
                    inventoryFlowEntity.setGoodsId(skusMap.get(form.getSkuId()).getGoodsId());
                    inventoryFlowEntity.setCategoryId(skusMap.get(form.getSkuId()).getCategoryId());
                    inventoryFlowEntity.setOldQuantity(skusMap.get(form.getSkuId()).getStockQuantity());
                    inventoryFlowEntity.setChangeQuantity(form.getQuantity());
                    return inventoryFlowEntity;
                })
                .collect(Collectors.toList());
        inventoryFlowManager.saveBatch(inventoryFlowEntityList);

        // 处理库存数量
        List<SkusEntity> skusList = skusStockUpdateFormList.stream()
                .map(form -> {
                    SkusEntity skusEntity = skusMap.get(form.getSkuId());
                    skusEntity.setStockQuantity(skusEntity.getStockQuantity() + form.getQuantity());
                    return skusEntity;
                })
                .collect(Collectors.toList());
        updateBatchById(skusList);

    }

    private Map<Long, SkusEntity> getSkusMap(List<SkusStockUpdateForm> skusStockUpdateFormList) {
        return listByIds(skusStockUpdateFormList.stream()
                .map(SkusStockUpdateForm::getSkuId)
                .collect(Collectors.toList()))
                .stream()
                .collect(Collectors.toMap(SkusEntity::getSkuId, Function.identity()));
    }
}
