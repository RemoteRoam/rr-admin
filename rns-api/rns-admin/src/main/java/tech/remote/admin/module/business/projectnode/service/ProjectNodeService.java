package tech.remote.admin.module.business.projectnode.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.projectnode.dao.ProjectNodeDao;
import tech.remote.admin.module.business.projectnode.domain.entity.ProjectNodeEntity;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeAddForm;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeQueryForm;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeUpdateForm;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 项目流程节点表 Service
 *
 * @Author cbh
 * @Date 2024-04-26 22:55:15
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectNodeService extends ServiceImpl<ProjectNodeDao, ProjectNodeEntity> {

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectNodeVO> queryPage(ProjectNodeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectNodeVO> list = baseMapper.queryPage(page, queryForm);
        PageResult<ProjectNodeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ProjectNodeAddForm addForm) {
        ProjectNodeEntity projectNodeEntity = SmartBeanUtil.copy(addForm, ProjectNodeEntity.class);
        baseMapper.insert(projectNodeEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ProjectNodeUpdateForm updateForm) {
        ProjectNodeEntity projectNodeEntity = SmartBeanUtil.copy(updateForm, ProjectNodeEntity.class);
        baseMapper.updateById(projectNodeEntity);
        return ResponseDTO.ok();
    }

    public List<ProjectNodeEntity> getOperateNodeByProjectId(Long id) {
        LambdaQueryWrapper<ProjectNodeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ProjectNodeEntity::getProjectId, id);
        wrapper.in(ProjectNodeEntity::getStatus, NodeStatusEnum.INIT.getValue(), NodeStatusEnum.DOING.getValue());
        wrapper.orderByAsc(ProjectNodeEntity::getNodeSort);

        return baseMapper.selectList(wrapper);
    }
}
