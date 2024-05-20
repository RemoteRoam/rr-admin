package tech.remote.admin.module.business.projectnode.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import tech.remote.admin.module.business.projectnode.domain.entity.ProjectNodeEntity;
import tech.remote.admin.module.business.projectnode.dao.ProjectNodeDao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeQueryForm;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.util.SmartBeanUtil;

import java.util.List;

/**
 * 项目流程节点表  Manager
 *
 * @Author cbh
 * @Date 2024-04-26 22:55:15
 * @Copyright Remote Nomad Studio
 */
@Service
public class ProjectNodeManager extends ServiceImpl<ProjectNodeDao, ProjectNodeEntity> {


    /**
     * 获取体系列表下需要处理的节点列表
     * @param queryForm
     * @return
     */
    public List<ProjectNodeVO> getOperateNodes(ProjectNodeQueryForm queryForm) {
        LambdaQueryWrapper<ProjectNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ProjectNodeEntity::getProjectId, queryForm.getProjectId());
        wrapper.eq(ProjectNodeEntity::getProjectType, queryForm.getProjectType());
        wrapper.eq(ProjectNodeEntity::getNodeLevel, queryForm.getNodeLevel());
        wrapper.eq(queryForm.getTaskId() != null, ProjectNodeEntity::getTaskId, queryForm.getTaskId());
        wrapper.eq(queryForm.getProductId() != null, ProjectNodeEntity::getProductId, queryForm.getProductId());
        wrapper.in(ProjectNodeEntity::getStatus, NodeStatusEnum.INIT.getValue(), NodeStatusEnum.DOING.getValue());
        wrapper.orderByAsc(ProjectNodeEntity::getNodeSort);

        return SmartBeanUtil.copyList(baseMapper.selectList(wrapper), ProjectNodeVO.class);

    }

    /**
     * 获取体系认证项目下所有节点列表
     * @param queryForm
     * @return
     */
    public List<ProjectNodeVO> getAllNodes(ProjectNodeQueryForm queryForm) {
        LambdaQueryWrapper<ProjectNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ProjectNodeEntity::getProjectId, queryForm.getProjectId());
//        wrapper.eq(ProjectNodeEntity::getProjectType, queryForm.getProjectType());
        wrapper.eq(ProjectNodeEntity::getNodeLevel, queryForm.getNodeLevel());
        wrapper.eq(queryForm.getTaskId() != null, ProjectNodeEntity::getTaskId, queryForm.getTaskId());
        wrapper.eq(queryForm.getProductId() != null, ProjectNodeEntity::getProductId, queryForm.getProductId());
        wrapper.orderByAsc(ProjectNodeEntity::getNodeSort);

        return SmartBeanUtil.copyList(baseMapper.selectList(wrapper), ProjectNodeVO.class);
    }

    /**
     * 判断是否所有节点都已完成或者跳过
     * @param queryForm
     * @return
     */
    public boolean isAllDone(ProjectNodeQueryForm queryForm) {
        List<ProjectNodeVO> list = getAllNodes(queryForm);
        for (ProjectNodeVO entity : list) {
            if (entity.getStatus() != NodeStatusEnum.OK.getValue() && entity.getStatus() != NodeStatusEnum.SKIP.getValue()) {
                return false;
            }
        }
        return true;
    }

}
