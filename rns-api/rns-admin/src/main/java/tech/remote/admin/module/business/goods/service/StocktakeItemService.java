package tech.remote.admin.module.business.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.dao.StocktakeItemDao;
import tech.remote.admin.module.business.goods.domain.entity.StocktakeItemEntity;
import tech.remote.admin.module.business.goods.domain.form.StocktakeItemAddForm;
import tech.remote.admin.module.business.goods.domain.form.StocktakeItemQueryForm;
import tech.remote.admin.module.business.goods.domain.form.StocktakeItemUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.StocktakeItemVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 盘点明细表 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Service
public class StocktakeItemService {

    @Resource
    private StocktakeItemDao stocktakeItemDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<StocktakeItemVO> queryPage(StocktakeItemQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<StocktakeItemVO> list = stocktakeItemDao.queryPage(page, queryForm);
        PageResult<StocktakeItemVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(StocktakeItemAddForm addForm) {
        StocktakeItemEntity stocktakeItemEntity = SmartBeanUtil.copy(addForm, StocktakeItemEntity.class);
        stocktakeItemDao.insert(stocktakeItemEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(StocktakeItemUpdateForm updateForm) {
        StocktakeItemEntity stocktakeItemEntity = SmartBeanUtil.copy(updateForm, StocktakeItemEntity.class);
        stocktakeItemDao.updateById(stocktakeItemEntity);
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

        stocktakeItemDao.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        stocktakeItemDao.deleteById(id);
        return ResponseDTO.ok();
    }

    public List<StocktakeItemVO> listByStocktakeId(Long id) {
        return stocktakeItemDao.listByStocktakeId(id);
    }
}
