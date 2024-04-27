package tech.remote.admin.module.business.systemcertification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.systemcertification.dao.SystemCertificationDao;
import tech.remote.admin.module.business.systemcertification.domain.entity.SystemCertificationEntity;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationAddForm;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationQueryForm;
import tech.remote.admin.module.business.systemcertification.domain.form.SystemCertificationUpdateForm;
import tech.remote.admin.module.business.systemcertification.domain.vo.SystemCertificationVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import tech.remote.base.module.support.serialnumber.constant.SerialNumberIdEnum;
import tech.remote.base.module.support.serialnumber.service.SerialNumberService;

import javax.annotation.Resource;

/**
 * 体系认证表 Service
 *
 * @Author cbh
 * @Date 2024-04-25 14:53:11
 * @Copyright Remote Nomad Studio
 */

@Service
public class SystemCertificationService {

    @Resource
    private SystemCertificationDao systemCertificationDao;

    @Autowired
    private SerialNumberService serialNumberService;
    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SystemCertificationVO> queryPage(SystemCertificationQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SystemCertificationVO> list = systemCertificationDao.queryPage(page, queryForm);
        PageResult<SystemCertificationVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(SystemCertificationAddForm addForm) {
        SystemCertificationEntity systemCertificationEntity = SmartBeanUtil.copy(addForm, SystemCertificationEntity.class);

        String projectNo = serialNumberService.generate(SerialNumberIdEnum.PROJECT);
        systemCertificationEntity.setProjectNo(projectNo);
        systemCertificationDao.insert(systemCertificationEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(SystemCertificationUpdateForm updateForm) {
        SystemCertificationEntity systemCertificationEntity = SmartBeanUtil.copy(updateForm, SystemCertificationEntity.class);
        systemCertificationDao.updateById(systemCertificationEntity);
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

        systemCertificationDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        systemCertificationDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    public SystemCertificationVO getDetail(Long id) {
        SystemCertificationEntity entity = systemCertificationDao.selectById(id);
        return SmartBeanUtil.copy(entity, SystemCertificationVO.class);
    }
}
