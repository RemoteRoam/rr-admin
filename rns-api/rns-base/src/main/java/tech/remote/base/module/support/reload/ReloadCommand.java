package tech.remote.base.module.support.reload;

import tech.remote.base.common.util.SmartBeanUtil;
import tech.remote.base.module.support.reload.core.AbstractSmartReloadCommand;
import tech.remote.base.module.support.reload.core.domain.SmartReloadItem;
import tech.remote.base.module.support.reload.core.domain.SmartReloadResult;
import tech.remote.base.module.support.reload.dao.ReloadItemDao;
import tech.remote.base.module.support.reload.dao.ReloadResultDao;
import tech.remote.base.module.support.reload.domain.ReloadItemEntity;
import tech.remote.base.module.support.reload.domain.ReloadResultEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * reload 操作
 *
 * @Author 远游工作室-yy
 * @Date 2015-03-02 19:11:52
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Component
public class ReloadCommand extends AbstractSmartReloadCommand {

    @Resource
    private ReloadItemDao reloadItemDao;

    @Resource
    private ReloadResultDao reloadResultDao;

    /**
     * 读取数据库中SmartReload项
     *
     * @return List<ReloadItem>
     */
    @Override
    public List<SmartReloadItem> readReloadItem() {
        List<ReloadItemEntity> reloadItemEntityList = reloadItemDao.selectList(null);
        return SmartBeanUtil.copyList(reloadItemEntityList, SmartReloadItem.class);
    }


    /**
     * 保存reload结果
     *
     * @param smartReloadResult
     */
    @Override
    public void handleReloadResult(SmartReloadResult smartReloadResult) {
        ReloadResultEntity reloadResultEntity = SmartBeanUtil.copy(smartReloadResult, ReloadResultEntity.class);
        reloadResultDao.insert(reloadResultEntity);
    }
}
