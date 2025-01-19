package tech.remote.admin.module.business.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.project.dao.ProjectMailDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectMailEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectMailAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectMailQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectMailUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectMailVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 项目邮寄表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectMailService {

    @Resource
    private ProjectMailDao projectMailDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectMailVO> queryPage(ProjectMailQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectMailVO> list = projectMailDao.queryPage(page, queryForm);
        PageResult<ProjectMailVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ProjectMailAddForm addForm) {
        ProjectMailEntity projectMailEntity = SmartBeanUtil.copy(addForm, ProjectMailEntity.class);
        projectMailDao.insert(projectMailEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ProjectMailUpdateForm updateForm) {
        ProjectMailEntity projectMailEntity = SmartBeanUtil.copy(updateForm, ProjectMailEntity.class);
        projectMailDao.updateById(projectMailEntity);
        return ResponseDTO.ok();
    }

}
