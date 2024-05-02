package tech.remote.admin.module.business.projectnode.service;

import java.util.List;
import tech.remote.admin.module.business.projectnode.dao.ProjectNodeDao;
import tech.remote.admin.module.business.projectnode.domain.entity.ProjectNodeEntity;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeAddForm;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeQueryForm;
import tech.remote.admin.module.business.projectnode.domain.form.ProjectNodeUpdateForm;
import tech.remote.admin.module.business.projectnode.domain.vo.ProjectNodeVO;
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
public class ProjectNodeService {

    @Resource
    private ProjectNodeDao projectNodeDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectNodeVO> queryPage(ProjectNodeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectNodeVO> list = projectNodeDao.queryPage(page, queryForm);
        PageResult<ProjectNodeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ProjectNodeAddForm addForm) {
        ProjectNodeEntity projectNodeEntity = SmartBeanUtil.copy(addForm, ProjectNodeEntity.class);
        projectNodeDao.insert(projectNodeEntity);
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
        projectNodeDao.updateById(projectNodeEntity);
        return ResponseDTO.ok();
    }

}
