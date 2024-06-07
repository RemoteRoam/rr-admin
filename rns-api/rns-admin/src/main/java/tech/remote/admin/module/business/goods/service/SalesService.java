package tech.remote.admin.module.business.goods.service;

import java.util.List;
import tech.remote.admin.module.business.goods.dao.SalesDao;
import tech.remote.admin.module.business.goods.domain.entity.SalesEntity;
import tech.remote.admin.module.business.goods.domain.form.SalesAddForm;
import tech.remote.admin.module.business.goods.domain.form.SalesQueryForm;
import tech.remote.admin.module.business.goods.domain.form.SalesUpdateForm;
import tech.remote.admin.module.business.goods.domain.vo.SalesVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 销售单表 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Service
public class SalesService {

    @Resource
    private SalesDao salesDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SalesVO> queryPage(SalesQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SalesVO> list = salesDao.queryPage(page, queryForm);
        PageResult<SalesVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(SalesAddForm addForm) {
        SalesEntity salesEntity = SmartBeanUtil.copy(addForm, SalesEntity.class);
        salesDao.insert(salesEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(SalesUpdateForm updateForm) {
        SalesEntity salesEntity = SmartBeanUtil.copy(updateForm, SalesEntity.class);
        salesDao.updateById(salesEntity);
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

        salesDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        salesDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }
}
