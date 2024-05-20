package tech.remote.admin.module.business.project.service;

import java.util.List;
import tech.remote.admin.module.business.project.dao.ProjectLabDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectLabEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectLabAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectLabQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectLabUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectLabVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 项目实验室任务表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:19:26
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectLabService {

    @Resource
    private ProjectLabDao projectLabDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectLabVO> queryPage(ProjectLabQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectLabVO> list = projectLabDao.queryPage(page, queryForm);
        PageResult<ProjectLabVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ProjectLabAddForm addForm) {
        ProjectLabEntity projectLabEntity = SmartBeanUtil.copy(addForm, ProjectLabEntity.class);
        projectLabDao.insert(projectLabEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ProjectLabUpdateForm updateForm) {
        ProjectLabEntity projectLabEntity = SmartBeanUtil.copy(updateForm, ProjectLabEntity.class);
        projectLabDao.updateById(projectLabEntity);
        return ResponseDTO.ok();
    }

}
