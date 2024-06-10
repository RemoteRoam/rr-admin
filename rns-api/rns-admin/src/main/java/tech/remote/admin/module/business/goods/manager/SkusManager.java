package tech.remote.admin.module.business.goods.manager;

import tech.remote.admin.module.business.goods.domain.entity.SkusEntity;
import tech.remote.admin.module.business.goods.dao.SkusDao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.domain.form.SkusStockUpdateForm;

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

    /**
     * Updates the stock quantities for a list of SkusStockUpdateForm.
     *
     * @param  skusStockUpdateFormList    the list of SkusStockUpdateForm to update stock quantities
     * @return                            void
     */
    public void batchUpdateStock(List<SkusStockUpdateForm> skusStockUpdateFormList) {
        Map<Long, SkusEntity> skusMap = getSkusMap(skusStockUpdateFormList);
        List<SkusEntity> skusList = skusStockUpdateFormList.stream()
                .map(form -> {
                    SkusEntity skusEntity = skusMap.get(form.getSkuId());
                    skusEntity.setQuantity(skusEntity.getQuantity() + form.getQuantity());
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
