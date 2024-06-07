package tech.remote.admin.module.business.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.dao.InventoryDao;
import tech.remote.admin.module.business.goods.domain.entity.InventoryEntity;
import tech.remote.admin.module.business.goods.domain.form.InventoryAddForm;
import tech.remote.admin.module.business.goods.domain.form.InventoryQueryForm;
import tech.remote.admin.module.business.goods.domain.form.InventoryUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.InventoryVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 库存表 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:42:51
 * @Copyright Remote Nomad Studio
 */

@Service
public class InventoryService {

    @Resource
    private InventoryDao inventoryDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<InventoryVO> queryPage(InventoryQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<InventoryVO> list = inventoryDao.queryPage(page, queryForm);
        PageResult<InventoryVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(InventoryAddForm addForm) {
        InventoryEntity inventoryEntity = SmartBeanUtil.copy(addForm, InventoryEntity.class);
        inventoryDao.insert(inventoryEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(InventoryUpdateForm updateForm) {
        InventoryEntity inventoryEntity = SmartBeanUtil.copy(updateForm, InventoryEntity.class);
        inventoryDao.updateById(inventoryEntity);
        return ResponseDTO.ok();
    }

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    public ResponseDTO<String> batchDelete(List<Integer> idList) {
        if (CollectionUtils.isEmpty(idList)){
            return ResponseDTO.ok();
        }

        inventoryDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Integer id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        inventoryDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }
}
