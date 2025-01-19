package tech.remote.admin.module.business.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.dao.PurchaseItemDao;
import tech.remote.admin.module.business.goods.domain.entity.PurchaseItemEntity;
import tech.remote.admin.module.business.goods.domain.form.PurchaseItemAddForm;
import tech.remote.admin.module.business.goods.domain.form.PurchaseItemQueryForm;
import tech.remote.admin.module.business.goods.domain.form.PurchaseItemUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseItemVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 采购入库明细表 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Service
public class PurchaseItemService {

    @Resource
    private PurchaseItemDao purchaseItemDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<PurchaseItemVO> queryPage(PurchaseItemQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<PurchaseItemVO> list = purchaseItemDao.queryPage(page, queryForm);
        PageResult<PurchaseItemVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(PurchaseItemAddForm addForm) {
        PurchaseItemEntity purchaseItemEntity = SmartBeanUtil.copy(addForm, PurchaseItemEntity.class);
        purchaseItemDao.insert(purchaseItemEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(PurchaseItemUpdateForm updateForm) {
        PurchaseItemEntity purchaseItemEntity = SmartBeanUtil.copy(updateForm, PurchaseItemEntity.class);
        purchaseItemDao.updateById(purchaseItemEntity);
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

        purchaseItemDao.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        purchaseItemDao.deleteById(id);
        return ResponseDTO.ok();
    }

    /**
     * 根据采购单ID查询明细
     */
    public List<PurchaseItemVO> listByPurchaseId(Long id) {
        return purchaseItemDao.listByPurchaseId(id);
    }
}
