package tech.remote.admin.module.business.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.project.dao.ProjectCertificationFeeDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectCertificationFeeEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectCertificationFeeAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectCertificationFeeQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectCertificationFeeUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectCertificationFeeVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 项目认证费表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:46:16
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectCertificationFeeService {

    @Resource
    private ProjectCertificationFeeDao projectCertificationFeeDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectCertificationFeeVO> queryPage(ProjectCertificationFeeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectCertificationFeeVO> list = projectCertificationFeeDao.queryPage(page, queryForm);
        PageResult<ProjectCertificationFeeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ProjectCertificationFeeAddForm addForm) {
        ProjectCertificationFeeEntity projectCertificationFeeEntity = SmartBeanUtil.copy(addForm, ProjectCertificationFeeEntity.class);
        projectCertificationFeeDao.insert(projectCertificationFeeEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ProjectCertificationFeeUpdateForm updateForm) {
        ProjectCertificationFeeEntity projectCertificationFeeEntity = SmartBeanUtil.copy(updateForm, ProjectCertificationFeeEntity.class);
        projectCertificationFeeDao.updateById(projectCertificationFeeEntity);
        return ResponseDTO.ok();
    }

}
