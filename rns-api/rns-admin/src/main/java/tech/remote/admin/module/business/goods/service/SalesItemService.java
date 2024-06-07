package tech.remote.admin.module.business.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.dao.SalesItemDao;
import tech.remote.admin.module.business.goods.domain.entity.SalesItemEntity;
import tech.remote.admin.module.business.goods.domain.form.SalesItemAddForm;
import tech.remote.admin.module.business.goods.domain.form.SalesItemQueryForm;
import tech.remote.admin.module.business.goods.domain.form.SalesItemUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.SalesItemVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 销售明细表 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:17:41
 * @Copyright Remote Nomad Studio
 */

@Service
public class SalesItemService {

    @Resource
    private SalesItemDao salesItemDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SalesItemVO> queryPage(SalesItemQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SalesItemVO> list = salesItemDao.queryPage(page, queryForm);
        PageResult<SalesItemVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(SalesItemAddForm addForm) {
        SalesItemEntity salesItemEntity = SmartBeanUtil.copy(addForm, SalesItemEntity.class);
        salesItemDao.insert(salesItemEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(SalesItemUpdateForm updateForm) {
        SalesItemEntity salesItemEntity = SmartBeanUtil.copy(updateForm, SalesItemEntity.class);
        salesItemDao.updateById(salesItemEntity);
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

        salesItemDao.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        salesItemDao.deleteById(id);
        return ResponseDTO.ok();
    }
}
