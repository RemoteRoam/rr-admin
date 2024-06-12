package tech.remote.admin.module.business.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.dao.InventoryFlowDao;
import tech.remote.admin.module.business.goods.domain.entity.InventoryFlowEntity;
import tech.remote.admin.module.business.goods.domain.form.InventoryFlowAddForm;
import tech.remote.admin.module.business.goods.domain.form.InventoryFlowQueryForm;
import tech.remote.admin.module.business.goods.domain.form.InventoryFlowUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.InventoryFlowVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 库存流水表 Service
 *
 * @Author cbh
 * @Date 2024-06-11 13:40:40
 * @Copyright Remote Nomad Studio
 */

@Service
public class InventoryFlowService {

    @Resource
    private InventoryFlowDao inventoryFlowDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<InventoryFlowVO> queryPage(InventoryFlowQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<InventoryFlowVO> list = inventoryFlowDao.queryPage(page, queryForm);
        PageResult<InventoryFlowVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(InventoryFlowAddForm addForm) {
        InventoryFlowEntity inventoryFlowEntity = SmartBeanUtil.copy(addForm, InventoryFlowEntity.class);
        inventoryFlowDao.insert(inventoryFlowEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(InventoryFlowUpdateForm updateForm) {
        InventoryFlowEntity inventoryFlowEntity = SmartBeanUtil.copy(updateForm, InventoryFlowEntity.class);
        inventoryFlowDao.updateById(inventoryFlowEntity);
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

        inventoryFlowDao.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        inventoryFlowDao.deleteById(id);
        return ResponseDTO.ok();
    }
}
