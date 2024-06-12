package tech.remote.admin.module.business.goods.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import tech.remote.admin.module.business.goods.domain.entity.SalesNodeEntity;
import tech.remote.admin.module.business.goods.dao.SalesNodeDao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.remote.base.common.enumeration.NodeStatusEnum;

import java.util.List;

/**
 * 销售流程节点表  Manager
 *
 * @Author cbh
 * @Date 2024-06-11 19:30:52
 * @Copyright Remote Nomad Studio
 */
@Service
public class SalesNodeManager extends ServiceImpl<SalesNodeDao, SalesNodeEntity> {

    /**
     * 获取体系列表下需要处理的节点列表
     * @param projectId
     * @return
     */
    public List<SalesNodeEntity> getOperateNodes(Long projectId) {
        LambdaQueryWrapper<SalesNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SalesNodeEntity::getProjectId, projectId);
        wrapper.in(SalesNodeEntity::getStatus, NodeStatusEnum.INIT.getValue(), NodeStatusEnum.DOING.getValue());
        wrapper.orderByAsc(SalesNodeEntity::getNodeSort);

        return baseMapper.selectList(wrapper);

    }

    /**
     * 获取体系认证项目下所有节点列表
     * @param projectId
     * @return
     */
    public List<SalesNodeEntity> getAllNodes(Long projectId) {
        LambdaQueryWrapper<SalesNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SalesNodeEntity::getProjectId, projectId);
        wrapper.orderByAsc(SalesNodeEntity::getNodeSort);

        return baseMapper.selectList(wrapper);
    }

    /**
     * 判断是否所有节点都已完成或者跳过
     * @param projectId
     * @return
     */
    public boolean isAllDone(Long projectId) {
        List<SalesNodeEntity> list = getAllNodes(projectId);
        for (SalesNodeEntity entity : list) {
            if (entity.getStatus() != NodeStatusEnum.OK.getValue() && entity.getStatus() != NodeStatusEnum.SKIP.getValue()) {
                return false;
            }
        }
        return true;
    }

}
