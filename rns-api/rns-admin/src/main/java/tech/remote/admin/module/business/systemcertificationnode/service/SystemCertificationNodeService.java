package tech.remote.admin.module.business.systemcertificationnode.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.systemcertificationnode.dao.SystemCertificationNodeDao;
import tech.remote.admin.module.business.systemcertificationnode.domain.entity.SystemCertificationNodeEntity;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeAddForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeQueryForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeUpdateForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.vo.SystemCertificationNodeVO;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

/**
 * 体系认证项目流程节点表 Service
 *
 * @Author cbh
 * @Date 2024-05-04 16:20:54
 * @Copyright Remote Nomad Studio
 */

@Service
public class SystemCertificationNodeService extends ServiceImpl<SystemCertificationNodeDao, SystemCertificationNodeEntity> {

    @Resource
    private SystemCertificationNodeDao systemCertificationNodeDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SystemCertificationNodeVO> queryPage(SystemCertificationNodeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SystemCertificationNodeVO> list = systemCertificationNodeDao.queryPage(page, queryForm);
        PageResult<SystemCertificationNodeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(SystemCertificationNodeAddForm addForm) {
        SystemCertificationNodeEntity systemCertificationNodeEntity = SmartBeanUtil.copy(addForm, SystemCertificationNodeEntity.class);
        systemCertificationNodeDao.insert(systemCertificationNodeEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(SystemCertificationNodeUpdateForm updateForm) {
        SystemCertificationNodeEntity systemCertificationNodeEntity = SmartBeanUtil.copy(updateForm, SystemCertificationNodeEntity.class);
        systemCertificationNodeDao.updateById(systemCertificationNodeEntity);
        return ResponseDTO.ok();
    }

    /**
     * 获取体系列表下需要处理的节点列表
     * @param id
     * @return
     */
    public List<SystemCertificationNodeEntity> getOperateNodesByProjectId(Long id) {
        LambdaQueryWrapper<SystemCertificationNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SystemCertificationNodeEntity::getProjectId, id);
        wrapper.in(SystemCertificationNodeEntity::getStatus, NodeStatusEnum.INIT.getValue(), NodeStatusEnum.DOING.getValue());
        wrapper.orderByAsc(SystemCertificationNodeEntity::getNodeSort);

        return baseMapper.selectList(wrapper);
    }

    /**
     * 获取体系认证项目下所有节点列表
     * @param id
     * @return
     */
    public List<SystemCertificationNodeEntity> getAllNodesByProjectId(Long id) {
        LambdaQueryWrapper<SystemCertificationNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SystemCertificationNodeEntity::getProjectId, id);
        wrapper.orderByAsc(SystemCertificationNodeEntity::getNodeSort);

        return baseMapper.selectList(wrapper);
    }

    /**
     * 判断是否所有节点都已完成或者跳过
     * @param id
     * @return
     */
    public boolean isAllDone(Long id) {
        List<SystemCertificationNodeEntity> list = getAllNodesByProjectId(id);
        for (SystemCertificationNodeEntity entity : list) {
            if (entity.getStatus() != NodeStatusEnum.OK.getValue() && entity.getStatus() != NodeStatusEnum.SKIP.getValue()) {
                return false;
            }
        }
        return true;
    }
}
