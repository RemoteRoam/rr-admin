package tech.remote.admin.module.business.typenode.service;

import java.util.List;
import tech.remote.admin.module.business.typenode.dao.TypeNodeDao;
import tech.remote.admin.module.business.typenode.domain.entity.TypeNodeEntity;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeAddForm;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQueryForm;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeUpdateForm;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeVO;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;

/**
 * 项目类型节点关系表 Service
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@Service
public class TypeNodeService {

    @Resource
    private TypeNodeDao typeNodeDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<TypeNodeVO> queryPage(TypeNodeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<TypeNodeVO> list = typeNodeDao.queryPage(page, queryForm);
        PageResult<TypeNodeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }


}
