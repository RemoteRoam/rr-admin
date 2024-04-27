package tech.remote.admin.module.business.third.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.customer.domain.entity.CustomerEntity;
import tech.remote.admin.module.business.customer.domain.vo.CustomerVO;
import tech.remote.admin.module.business.third.dao.ThirdPartyDao;
import tech.remote.admin.module.business.third.domain.entity.ThirdPartyEntity;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyAddForm;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyQueryForm;
import tech.remote.admin.module.business.third.domain.form.ThirdPartyUpdateForm;
import tech.remote.admin.module.business.third.domain.vo.ThirdPartyVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 三方机构 Service
 *
 * @Author cbh
 * @Date 2024-04-24 09:19:13
 * @Copyright Remote Nomad Studio
 */

@Service
public class ThirdPartyService {

    @Resource
    private ThirdPartyDao thirdPartyDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ThirdPartyVO> queryPage(ThirdPartyQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ThirdPartyVO> list = thirdPartyDao.queryPage(page, queryForm);
        PageResult<ThirdPartyVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ThirdPartyAddForm addForm) {
        ThirdPartyEntity thirdPartyEntity = SmartBeanUtil.copy(addForm, ThirdPartyEntity.class);
        thirdPartyDao.insert(thirdPartyEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ThirdPartyUpdateForm updateForm) {
        ThirdPartyEntity thirdPartyEntity = SmartBeanUtil.copy(updateForm, ThirdPartyEntity.class);
        thirdPartyDao.updateById(thirdPartyEntity);
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

        thirdPartyDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        thirdPartyDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    public ThirdPartyVO getDetail(Long id) {
        ThirdPartyEntity entity = thirdPartyDao.selectById(id);
        return SmartBeanUtil.copy(entity, ThirdPartyVO.class);
    }

    public ResponseDTO<List<ThirdPartyVO>> queryList(String type) {
        LambdaQueryWrapper<ThirdPartyEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ThirdPartyEntity::getDeletedFlag, false);
        lambdaQueryWrapper.eq(ThirdPartyEntity::getDisabledFlag, false);
        lambdaQueryWrapper.eq(null != type , ThirdPartyEntity::getType, type);
        lambdaQueryWrapper.orderByAsc(ThirdPartyEntity::getId);

        List<ThirdPartyEntity> list = thirdPartyDao.selectList(lambdaQueryWrapper);
        return ResponseDTO.ok(SmartBeanUtil.copyList(list, ThirdPartyVO.class));
    }
}
