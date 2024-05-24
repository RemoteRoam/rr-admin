package tech.remote.admin.module.business.measurement.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import tech.remote.admin.module.business.measurement.dao.MeasurementNodeDao;
import tech.remote.admin.module.business.measurement.domain.entity.MeasurementNodeEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.measurement.domain.form.MeasurementNodeQueryForm;
import tech.remote.admin.module.business.measurement.domain.vo.MeasurementNodeVO;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.util.SmartBeanUtil;

import java.util.List;

/**
 * 仪器计量流程节点表  Manager
 *
 * @Author cbh
 * @Date 2024-05-07 14:42:44
 * @Copyright Remote Nomad Studio
 */
@Service
public class MeasurementNodeManager extends ServiceImpl<MeasurementNodeDao, MeasurementNodeEntity> {


    /**
     * 获取体系列表下需要处理的节点列表
     * @param queryForm
     * @return
     */
    public List<MeasurementNodeVO> getOperateNodes(MeasurementNodeQueryForm queryForm) {
        LambdaQueryWrapper<MeasurementNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(MeasurementNodeEntity::getProjectId, queryForm.getProjectId());
        wrapper.eq(MeasurementNodeEntity::getNodeLevel, queryForm.getNodeLevel());
        wrapper.eq(queryForm.getTaskId() != null, MeasurementNodeEntity::getTaskId, queryForm.getTaskId());
        wrapper.in(MeasurementNodeEntity::getStatus, NodeStatusEnum.INIT.getValue(), NodeStatusEnum.DOING.getValue());
        wrapper.orderByAsc(MeasurementNodeEntity::getNodeSort);

        return SmartBeanUtil.copyList(baseMapper.selectList(wrapper), MeasurementNodeVO.class);

    }

    /**
     * 获取体系认证项目下所有节点列表
     * @param queryForm
     * @return
     */
    public List<MeasurementNodeVO> getAllNodes(MeasurementNodeQueryForm queryForm) {
        LambdaQueryWrapper<MeasurementNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(MeasurementNodeEntity::getProjectId, queryForm.getProjectId());
        wrapper.eq(MeasurementNodeEntity::getNodeLevel, queryForm.getNodeLevel());
        wrapper.eq(queryForm.getTaskId() != null, MeasurementNodeEntity::getTaskId, queryForm.getTaskId());
        wrapper.orderByAsc(MeasurementNodeEntity::getNodeSort);

        return SmartBeanUtil.copyList(baseMapper.selectList(wrapper), MeasurementNodeVO.class);
    }

    /**
     * 判断是否所有节点都已完成或者跳过
     * @param queryForm
     * @return
     */
    public boolean isAllDone(MeasurementNodeQueryForm queryForm) {
        List<MeasurementNodeVO> list = getAllNodes(queryForm);
        for (MeasurementNodeVO entity : list) {
            if (entity.getStatus() != NodeStatusEnum.OK.getValue() && entity.getStatus() != NodeStatusEnum.SKIP.getValue()) {
                return false;
            }
        }
        return true;
    }

}
