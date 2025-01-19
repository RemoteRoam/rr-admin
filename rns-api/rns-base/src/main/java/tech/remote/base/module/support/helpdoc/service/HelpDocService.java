package tech.remote.base.module.support.helpdoc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.module.support.helpdoc.dao.HelpDocDao;
import tech.remote.base.module.support.helpdoc.domain.entity.HelpDocEntity;
import tech.remote.base.module.support.helpdoc.domain.form.HelpDocAddForm;
import tech.remote.base.module.support.helpdoc.domain.form.HelpDocQueryForm;
import tech.remote.base.module.support.helpdoc.domain.form.HelpDocUpdateForm;
import tech.remote.base.module.support.helpdoc.domain.vo.HelpDocDetailVO;
import tech.remote.base.module.support.helpdoc.domain.vo.HelpDocVO;
import tech.remote.base.module.support.helpdoc.manager.HelpDocManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台管理业务
 *
 * @Author 远游工作室-yy
 * @Date 2022-08-20 23:11:42
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Service
public class HelpDocService {

    @Resource
    private HelpDocDao helpDocDao;

    @Resource
    private HelpDocManager helpDaoManager;


    /**
     * 查询 帮助文档
     *
     * @param queryForm
     * @return
     */
    public PageResult<HelpDocVO> query(HelpDocQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<HelpDocVO> list = helpDocDao.query(page, queryForm);
        return SmartPageUtil.convert2PageResult(page, list);
    }

    /**
     * 添加
     *
     * @param addForm
     * @return
     */
    public ResponseDTO<String> add(HelpDocAddForm addForm) {
        HelpDocEntity helpDaoEntity = SmartBeanUtil.copy(addForm, HelpDocEntity.class);
        helpDaoManager.save(helpDaoEntity, addForm.getRelationList());
        return ResponseDTO.ok();
    }


    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(HelpDocUpdateForm updateForm) {
        // 更新
        HelpDocEntity helpDaoEntity = SmartBeanUtil.copy(updateForm, HelpDocEntity.class);
        helpDaoManager.update(helpDaoEntity, updateForm.getRelationList());
        return ResponseDTO.ok();
    }


    /**
     * 删除
     *
     * @param helpDocId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long helpDocId) {
        HelpDocEntity helpDaoEntity = helpDocDao.selectById(helpDocId);
        if (helpDaoEntity != null) {
            helpDocDao.deleteById(helpDocId);
            helpDocDao.deleteRelation(helpDocId);
        }
        return ResponseDTO.ok();
    }

    /**
     * 获取详情
     *
     * @param helpDocId
     * @return
     */
    public HelpDocDetailVO getDetail(Long helpDocId) {
        HelpDocEntity helpDaoEntity = helpDocDao.selectById(helpDocId);
        HelpDocDetailVO detail = SmartBeanUtil.copy(helpDaoEntity, HelpDocDetailVO.class);
        if (detail != null) {
            detail.setRelationList(helpDocDao.queryRelationByHelpDoc(helpDocId));
        }
        return detail;
    }

    /**
     * 获取详情
     *
     * @param relationId
     * @return
     */
    public List<HelpDocVO> queryHelpDocByRelationId(Long relationId) {
        return helpDocDao.queryHelpDocByRelationId(relationId);
    }
}
