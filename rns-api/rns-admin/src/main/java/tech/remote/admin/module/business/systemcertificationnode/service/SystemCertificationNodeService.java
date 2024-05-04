package tech.remote.admin.module.business.systemcertificationnode.service;

import java.util.List;
import tech.remote.admin.module.business.systemcertificationnode.dao.SystemCertificationNodeDao;
import tech.remote.admin.module.business.systemcertificationnode.domain.entity.SystemCertificationNodeEntity;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeAddForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeQueryForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.form.SystemCertificationNodeUpdateForm;
import tech.remote.admin.module.business.systemcertificationnode.domain.vo.SystemCertificationNodeVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 体系认证项目流程节点表 Service
 *
 * @Author cbh
 * @Date 2024-05-04 16:20:54
 * @Copyright Remote Nomad Studio
 */

@Service
public class SystemCertificationNodeService {

    @Resource
    private SystemCertificationNodeDao systemCertificationNodeDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SystemCertificationNodeVO> queryPage(SystemCertificationNodeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SystemCertificationNodeVO> list = systemCertificationNodeDao.queryPage(page, queryForm);
        PageResult<SystemCertificationNodeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(SystemCertificationNodeAddForm addForm) {
        SystemCertificationNodeEntity systemCertificationNodeEntity = SmartBeanUtil.copy(addForm, SystemCertificationNodeEntity.class);
        systemCertificationNodeDao.insert(systemCertificationNodeEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(SystemCertificationNodeUpdateForm updateForm) {
        SystemCertificationNodeEntity systemCertificationNodeEntity = SmartBeanUtil.copy(updateForm, SystemCertificationNodeEntity.class);
        systemCertificationNodeDao.updateById(systemCertificationNodeEntity);
        return ResponseDTO.ok();
    }

}
