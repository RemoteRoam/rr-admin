package tech.remote.admin.module.business.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.project.dao.ProjectProductDao;
import tech.remote.admin.module.business.project.domain.entity.ProjectProductEntity;
import tech.remote.admin.module.business.project.domain.form.ProjectProductAddForm;
import tech.remote.admin.module.business.project.domain.form.ProjectProductQueryForm;
import tech.remote.admin.module.business.project.domain.form.ProjectProductUpdateForm;
import tech.remote.admin.module.business.project.domain.vo.ProjectProductVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 项目产品表 Service
 *
 * @Author cbh
 * @Date 2024-05-15 13:40:52
 * @Copyright Remote Nomad Studio
 */

@Service
public class ProjectProductService {

    @Resource
    private ProjectProductDao projectProductDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ProjectProductVO> queryPage(ProjectProductQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ProjectProductVO> list = projectProductDao.queryPage(page, queryForm);
        PageResult<ProjectProductVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ProjectProductAddForm addForm) {
        ProjectProductEntity projectProductEntity = SmartBeanUtil.copy(addForm, ProjectProductEntity.class);
        projectProductDao.insert(projectProductEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ProjectProductUpdateForm updateForm) {
        ProjectProductEntity projectProductEntity = SmartBeanUtil.copy(updateForm, ProjectProductEntity.class);
        projectProductDao.updateById(projectProductEntity);
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

        projectProductDao.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        projectProductDao.deleteById(id);
        return ResponseDTO.ok();
    }
}
