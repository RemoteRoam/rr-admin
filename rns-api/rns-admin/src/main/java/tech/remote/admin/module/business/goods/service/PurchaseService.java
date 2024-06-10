package tech.remote.admin.module.business.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.remote.admin.module.business.goods.dao.PurchaseDao;
import tech.remote.admin.module.business.goods.domain.entity.PurchaseEntity;
import tech.remote.admin.module.business.goods.domain.entity.PurchaseItemEntity;
import tech.remote.admin.module.business.goods.domain.entity.SkusEntity;
import tech.remote.admin.module.business.goods.domain.form.*;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseItemVO;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseVO;
import tech.remote.admin.module.business.goods.manager.PurchaseItemManager;
import tech.remote.admin.module.business.goods.manager.SkusManager;
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
 * 采购入库表 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:47
 * @Copyright Remote Nomad Studio
 */

@Service
public class PurchaseService {

    @Resource
    private PurchaseDao purchaseDao;

    @Resource
    private PurchaseItemManager purchaseItemManager;

    @Resource
    private PurchaseItemService purchaseItemService;

    @Resource
    private SkusManager skusManager;

    @Autowired
    private SerialNumberService serialNumberService;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<PurchaseVO> queryPage(PurchaseQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<PurchaseVO> list = purchaseDao.queryPage(page, queryForm);
        PageResult<PurchaseVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(PurchaseAddForm addForm) {
        // 如果没有明细，直接返回
        if (CollectionUtils.isEmpty(addForm.getItemList())){
            return ResponseDTO.userErrorParam("明细不能为空");
        }
        // 明细不能超过300条
        if (addForm.getItemList().size() > 300){
            return ResponseDTO.userErrorParam("每次采购入库的商品规格型号不能超过300个");
        }
        PurchaseEntity purchaseEntity = SmartBeanUtil.copy(addForm, PurchaseEntity.class);

        String purchaseNo = serialNumberService.generate(SerialNumberIdEnum.PURCHASE_NO);
        purchaseEntity.setPurchaseNo(purchaseNo);
        purchaseDao.insert(purchaseEntity);

        // 插入明细
        List<PurchaseItemAddForm> itemList = addForm.getItemList();
        List<PurchaseItemEntity> entityList = SmartBeanUtil.copyList(itemList, PurchaseItemEntity.class);
        List<SkusStockUpdateForm> skusStockUpdateFormList = SmartBeanUtil.copyList(itemList, SkusStockUpdateForm.class);
        for (PurchaseItemEntity purchaseItemEntity : entityList) {

            purchaseItemEntity.setPurchaseId(purchaseEntity.getId());
        }
        purchaseItemManager.saveBatch(entityList);

        // 修改库存
        skusManager.batchUpdateStock(skusStockUpdateFormList);

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(PurchaseUpdateForm updateForm) {
        PurchaseEntity purchaseEntity = SmartBeanUtil.copy(updateForm, PurchaseEntity.class);
        purchaseDao.updateById(purchaseEntity);
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

        purchaseDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        purchaseDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    /**
     * 查询详情
     */
    public PurchaseVO getDetail(Long id) {
        PurchaseEntity purchaseEntity = purchaseDao.selectById(id);
        PurchaseVO purchaseVO = SmartBeanUtil.copy(purchaseEntity, PurchaseVO.class);
        // 调用PurchaseItemService的listByPurchaseId方法,查询明细
        List<PurchaseItemVO> purchaseItemVOList = purchaseItemService.listByPurchaseId(id);
        purchaseVO.setItemVOList(purchaseItemVOList);
        return purchaseVO;
    }
}
