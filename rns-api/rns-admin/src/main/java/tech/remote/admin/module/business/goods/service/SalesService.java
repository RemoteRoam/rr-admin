package tech.remote.admin.module.business.goods.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.remote.admin.module.business.customer.service.CustomerService;
import tech.remote.admin.module.business.goods.dao.SalesDao;
import tech.remote.admin.module.business.goods.domain.entity.SalesEntity;
import tech.remote.admin.module.business.goods.domain.entity.SalesItemEntity;
import tech.remote.admin.module.business.goods.domain.entity.SalesNodeEntity;
import tech.remote.admin.module.business.goods.domain.form.*;
import tech.remote.admin.module.business.goods.domain.vo.SalesExcelVO;
import tech.remote.admin.module.business.goods.domain.vo.SalesItemVO;
import tech.remote.admin.module.business.goods.domain.vo.SalesVO;
import tech.remote.admin.module.business.goods.manager.SalesItemManager;
import tech.remote.admin.module.business.goods.manager.SalesNodeManager;
import tech.remote.admin.module.business.goods.manager.SkusManager;
import tech.remote.admin.module.business.typenode.domain.form.TypeNodeQuery;
import tech.remote.admin.module.business.typenode.domain.vo.TypeNodeListVO;
import tech.remote.admin.module.business.typenode.service.TypeNodeService;
import tech.remote.base.common.code.BusinessErrorCode;
import tech.remote.base.common.enumeration.NodeStatusEnum;
import tech.remote.base.common.enumeration.ProjectStatusEnum;
import tech.remote.base.common.enumeration.ProjectTypeEnum;
import tech.remote.base.common.enumeration.RecordTypeEnum;
import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.common.util.SmartPageUtil;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import tech.remote.base.module.support.datatracer.constant.DataTracerTypeEnum;
import tech.remote.base.module.support.datatracer.service.DataTracerService;
import tech.remote.base.module.support.serialnumber.constant.SerialNumberIdEnum;
import tech.remote.base.module.support.serialnumber.service.SerialNumberService;

import javax.annotation.Resource;

/**
 * 销售单表 Service
 *
 * @Author cbh
 * @Date 2024-06-06 13:16:54
 * @Copyright Remote Nomad Studio
 */

@Service
public class SalesService {

    @Resource
    private SalesDao salesDao;

    @Autowired
    private SerialNumberService serialNumberService;
    @Autowired
    private SalesItemManager salesItemManager;
    @Autowired
    private SkusManager skusManager;

    @Autowired
    private SalesItemService salesItemService;

    @Autowired
    private TypeNodeService typeNodeService;
    @Autowired
    private SalesNodeManager salesNodeManager;

    @Resource
    private DataTracerService dataTracerService;

    @Resource
    private CustomerService customerService;
    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<SalesVO> queryPage(SalesQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SalesVO> list = salesDao.queryPage(page, queryForm);
        if(CollectionUtils.isNotEmpty(list)){
            for(SalesVO salesVO : list){
                salesVO.setNodeList(salesNodeManager.getOperateNodes(salesVO.getId()));
            }
        }
        PageResult<SalesVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(SalesAddForm addForm) {
        // 如果没有明细，直接返回
        if (CollectionUtils.isEmpty(addForm.getItemList())){
            return ResponseDTO.userErrorParam("明细不能为空");
        }
        // 明细不能超过300条
        if (addForm.getItemList().size() > 300){
            return ResponseDTO.userErrorParam("每次销售的商品规格型号不能超过300个");
        }
        SalesEntity salesEntity = SmartBeanUtil.copy(addForm, SalesEntity.class);

        String purchaseNo = serialNumberService.generate(SerialNumberIdEnum.SALES_NO);
        salesEntity.setSalesNo(purchaseNo);

        salesDao.insert(salesEntity);

        // 插入明细
        addForm.getItemList().forEach(item -> {
            item.setSalesId(salesEntity.getId());
        });
        salesItemManager.saveBatch(SmartBeanUtil.copyList(addForm.getItemList(), SalesItemEntity.class));

        // 如果salesType为0，更新库存
        if (salesEntity.getSalesType() == 0){

            List<SkusStockUpdateForm> skusStockUpdateFormList = new ArrayList<>();
            addForm.getItemList().forEach(item -> {
                SkusStockUpdateForm skusStockUpdateForm = new SkusStockUpdateForm();
                skusStockUpdateForm.setSkuId(item.getSkuId());
                // 减库存
                skusStockUpdateForm.setQuantity(-item.getQuantity());
                skusStockUpdateFormList.add(skusStockUpdateForm);
            });

            skusManager.batchUpdateStock(skusStockUpdateFormList, RecordTypeEnum.SALES_OUTBOUND, salesEntity.getId());
        }

        // 获取该类型下的对应节点
        TypeNodeQuery query = new TypeNodeQuery();
        query.setNodeLevel(1);
        query.setProjectType(ProjectTypeEnum.SALES.getValue());
        List<TypeNodeListVO> typeNodeList = typeNodeService.getTypeNodes(query);

        List<SalesNodeEntity> nodeList = SmartBeanUtil.copyList(typeNodeList, SalesNodeEntity.class);
        for(SalesNodeEntity node : nodeList){
            node.setId(null);
            node.setProjectId(salesEntity.getId());
            node.setStatus(NodeStatusEnum.INIT.getValue());
            node.setCreateUserId(addForm.getCreateUserId());
            node.setCreateUserName(addForm.getCreateUserName());
        }
        salesNodeManager.saveBatch(nodeList);

        dataTracerService.insert(salesEntity.getId(), DataTracerTypeEnum.SALES);

        if(addForm.getContractAmount() != null && addForm.getCustomerId() != null){
            customerService.upgradeLevel(addForm.getCustomerId());
        }

        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SalesUpdateForm updateForm) {
        SalesEntity oldEntity = salesDao.selectById(updateForm.getId());
        SalesEntity salesEntity = SmartBeanUtil.copy(updateForm, SalesEntity.class);
        salesDao.updateById(salesEntity);

        // 节点操作更新
        if(updateForm.getProjectNodeId() != null && updateForm.getNodeStatus() != null) {
            // 根据projectId和nodeId为条件，更新systemCertificationNode的状态

            SalesNodeEntity nodeEntity = salesNodeManager.getById(updateForm.getProjectNodeId());
            if(nodeEntity.getStatus() == NodeStatusEnum.INIT.getValue() || nodeEntity.getStatus() == NodeStatusEnum.DOING.getValue()){
                nodeEntity.setOperateTime(LocalDateTime.now());
                nodeEntity.setOperateUserId(updateForm.getUpdateUserId());
                nodeEntity.setOperateUserName(updateForm.getUpdateUserName());
                nodeEntity.setPassReason(updateForm.getPassReason());
                nodeEntity.setStatus(updateForm.getNodeStatus());

            } else {
                return ResponseDTO.error(BusinessErrorCode.PROJECT_NODE_ALREADY_PROCESSED);
            }

            // Execute the update
            salesNodeManager.updateById(nodeEntity);
            String content = "节点操作：【" + nodeEntity.getNodeName() + "】" + NodeStatusEnum.getDescByValue(updateForm.getNodeStatus());
            dataTracerService.addTrace(salesEntity.getId(), DataTracerTypeEnum.SALES, content);
            // 判断是否所有节点都已完成或者跳过，如果是，修改状态为已完成
            if (salesNodeManager.isAllDone(updateForm.getId())) {
                salesEntity.setStatus(ProjectStatusEnum.DONE.getValue());
                salesDao.updateById(salesEntity);
                dataTracerService.addTrace(salesEntity.getId(), DataTracerTypeEnum.SALES, "项目完成");
            }
        } else {

            //变更记录
            dataTracerService.update(salesEntity.getId(), DataTracerTypeEnum.SALES, oldEntity, salesEntity);
        }

        // 销售单明细更新
        if(CollectionUtils.isNotEmpty(updateForm.getItemList())){
            for (SalesItemUpdateForm item : updateForm.getItemList()){
                if (item.getId() == null){
                    return ResponseDTO.userErrorParam("明细ID不能为空");
                }
                SalesItemEntity oldItemEntity = salesItemManager.getById(item.getId());
                SalesItemEntity entity = SmartBeanUtil.copy(item, SalesItemEntity.class);
                salesItemManager.updateById(entity);

                //变更记录
                dataTracerService.update(salesEntity.getId(), DataTracerTypeEnum.SALES, oldItemEntity, entity);
            }
        }

        if(updateForm.getContractAmount() != null && oldEntity.getCustomerId() != null){
            customerService.upgradeLevel(oldEntity.getCustomerId());
        }
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

        salesDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        salesDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    /**
     * 查询详情
     */
    public SalesVO getDetail(Long id) {
        SalesVO salesVO = salesDao.detail(id);
        // 调用SalesItemService的listBySalesId方法,查询明细
        List<SalesItemVO> salesItemVOList = salesItemService.listBySalesId(id);
        salesVO.setItemVOList(salesItemVOList);
        List<SalesNodeEntity> nodeList = salesNodeManager.getAllNodes(id);
        salesVO.setNodeList(nodeList);
        return salesVO;
    }

    public List<SalesExcelVO> getExcelExportData(SalesQueryForm queryForm) {
        return salesDao.getExcelExportData(queryForm);
    }
}
