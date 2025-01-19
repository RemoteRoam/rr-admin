package tech.remote.admin.module.business.node.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import tech.remote.admin.module.business.node.dao.NodeDao;
import tech.remote.admin.module.business.node.domain.form.NodeQueryForm;
import tech.remote.admin.module.business.node.domain.vo.NodeVO;
import tech.remote.base.common.domain.PageResult;
import tech.remote.base.common.util.SmartPageUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * 节点表 Service
 *
 * @Author cbh
 * @Date 2024-04-25 14:52:31
 * @Copyright Remote Nomad Studio
 */

@Service
public class NodeService {

    @Resource
    private NodeDao nodeDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<NodeVO> queryPage(NodeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<NodeVO> list = nodeDao.queryPage(page, queryForm);
        PageResult<NodeVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }


}
