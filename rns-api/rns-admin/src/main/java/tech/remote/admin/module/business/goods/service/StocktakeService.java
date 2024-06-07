package tech.remote.admin.module.business.goods.service;

import java.util.List;
import tech.remote.admin.module.business.goods.dao.StocktakeDao;
import tech.remote.admin.module.business.goods.domain.entity.StocktakeEntity;
import tech.remote.admin.module.business.goods.domain.form.StocktakeAddForm;
import tech.remote.admin.module.business.goods.domain.form.StocktakeQueryForm;
import tech.remote.admin.module.business.goods.domain.form.StocktakeUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.StocktakeVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

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
        StocktakeEntity stocktakeEntity = SmartBeanUtil.copy(addForm, StocktakeEntity.class);
        stocktakeDao.insert(stocktakeEntity);
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
}
