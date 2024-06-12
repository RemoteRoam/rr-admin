package tech.remote.admin.module.business.goods.service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.goods.dao.SkusDao;
import tech.remote.admin.module.business.goods.domain.entity.SkusEntity;
import tech.remote.admin.module.business.goods.domain.form.*;
import tech.remote.admin.module.business.goods.domain.vo.SkusExcelVO;
import tech.remote.admin.module.business.goods.domain.vo.SkusVO;
import tech.remote.admin.module.business.goods.manager.SkusManager;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 型号规格 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:37:19
 * @Copyright Remote Nomad Studio
 */

@Service
public class SkusService {

    @Resource
    private SkusDao skusDao;

    @Resource
    private SkusManager skusManager;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SkusVO> queryPage(SkusQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SkusVO> list = skusDao.queryPage(page, queryForm);
        PageResult<SkusVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(SkusAddForm addForm) {
        SkusEntity skusEntity = SmartBeanUtil.copy(addForm, SkusEntity.class);
        skusDao.insert(skusEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(SkusUpdateForm updateForm) {
        SkusEntity skusEntity = SmartBeanUtil.copy(updateForm, SkusEntity.class);
        skusDao.updateById(skusEntity);
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

        skusDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long skuId) {
        if (null == skuId){
            return ResponseDTO.ok();
        }

        skusDao.updateDeleted(skuId,true);
        return ResponseDTO.ok();
    }

    public void insertSkus(Long goodsId, GoodsAddForm addForm) {
        if (CollectionUtils.isEmpty(addForm.getSkuList())) {
            return;
        }
        List<SkusEntity> skusList = addForm.getSkuList().stream().map(skusAddForm -> {
            SkusEntity skusEntity = SmartBeanUtil.copy(skusAddForm, SkusEntity.class);
            skusEntity.setGoodsId(goodsId);
            skusEntity.setCategoryId(addForm.getCategoryId());
            return skusEntity;
        }).collect(Collectors.toList());
        skusManager.saveBatch(skusList);
    }

    public void updateSkus(GoodsUpdateForm updateForm) {
        // 先根据goodsId删除所有型号规格数据，然后重新插入
        if (CollectionUtils.isEmpty(updateForm.getSkuList())) {
            return;
        }
        skusDao.delete(Wrappers.lambdaQuery(SkusEntity.class).eq(SkusEntity::getGoodsId, updateForm.getGoodsId()));
        this.insertSkus(updateForm.getGoodsId(), updateForm);
    }

    public List<SkusExcelVO> getExcelExportData(SkusQueryForm queryForm) {
        List<SkusExcelVO> excelList = skusDao.selectExcelList(queryForm);
        return excelList;
    }
}
