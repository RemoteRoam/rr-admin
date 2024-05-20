package tech.remote.admin.module.business.project.service;

import java.util.List;
import tech.remote.admin.module.business.project.dao.ProjectArchiveDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectArchiveEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectArchiveAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectArchiveQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectArchiveUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectArchiveVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 项目归档表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectArchiveService {

    @Resource
    private ProjectArchiveDao projectArchiveDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectArchiveVO> queryPage(ProjectArchiveQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectArchiveVO> list = projectArchiveDao.queryPage(page, queryForm);
        PageResult<ProjectArchiveVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ProjectArchiveAddForm addForm) {
        ProjectArchiveEntity projectArchiveEntity = SmartBeanUtil.copy(addForm, ProjectArchiveEntity.class);
        projectArchiveDao.insert(projectArchiveEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ProjectArchiveUpdateForm updateForm) {
        ProjectArchiveEntity projectArchiveEntity = SmartBeanUtil.copy(updateForm, ProjectArchiveEntity.class);
        projectArchiveDao.updateById(projectArchiveEntity);
        return ResponseDTO.ok();
    }

}
