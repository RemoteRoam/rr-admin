package tech.remote.admin.module.business.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.dao.PurchaseDao;
import tech.remote.admin.module.business.goods.domain.entity.PurchaseEntity;
import tech.remote.admin.module.business.goods.domain.form.PurchaseAddForm;
import tech.remote.admin.module.business.goods.domain.form.PurchaseQueryForm;
import tech.remote.admin.module.business.goods.domain.form.PurchaseUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.PurchaseVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

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
    public ResponseDTO<String> add(PurchaseAddForm addForm) {
        PurchaseEntity purchaseEntity = SmartBeanUtil.copy(addForm, PurchaseEntity.class);
        purchaseDao.insert(purchaseEntity);
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
}
