package tech.remote.admin.module.business.project.service;

import java.util.List;
import tech.remote.admin.module.business.project.dao.ProjectDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 项目表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectService {

    @Resource
    private ProjectDao projectDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectVO> queryPage(ProjectQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectVO> list = projectDao.queryPage(page, queryForm);
        PageResult<ProjectVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ProjectAddForm addForm) {
        ProjectEntity projectEntity = SmartBeanUtil.copy(addForm, ProjectEntity.class);
        projectDao.insert(projectEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ProjectUpdateForm updateForm) {
        ProjectEntity projectEntity = SmartBeanUtil.copy(updateForm, ProjectEntity.class);
        projectDao.updateById(projectEntity);
        return ResponseDTO.ok();
    }

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    public ResponseDTO<String> batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)){
            return ResponseDTO.ok();
        }

        projectDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        projectDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }
}
