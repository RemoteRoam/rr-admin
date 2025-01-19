package tech.remote.base.module.support.reload;

import tech.remote.base.common.code.UserErrorCode;
import tech.remote.base.common.domain.ResponseDTO;
import tech.remote.base.module.support.reload.dao.ReloadItemDao;
import tech.remote.base.module.support.reload.dao.ReloadResultDao;
import tech.remote.base.module.support.reload.domain.ReloadForm;
import tech.remote.base.module.support.reload.domain.ReloadItemEntity;
import tech.remote.base.module.support.reload.domain.ReloadItemVO;
import tech.remote.base.module.support.reload.domain.ReloadResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * reload (内存热加载、钩子等)
 *
 * @Author 远游工作室-yy
 * @Date 2015-03-02 19:11:52
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Service
public class ReloadService {

    @Resource
    private ReloadItemDao reloadItemDao;

    @Resource
    private ReloadResultDao reloadResultDao;

    /**
     * 查询
     *
     * @return
     */
    public ResponseDTO<List<ReloadItemVO>> query() {
        List<ReloadItemVO> list = reloadItemDao.query();
        return ResponseDTO.ok(list);
    }

    public ResponseDTO<List<ReloadResultVO>> queryReloadItemResult(String tag) {
        List<ReloadResultVO> reloadResultList = reloadResultDao.query(tag);
        return ResponseDTO.ok(reloadResultList);
    }


    /**
     * 通过标签更新标识符
     *
     * @param reloadForm
     * @return
     */
    public ResponseDTO<String> updateByTag(ReloadForm reloadForm) {
        ReloadItemEntity reloadItemEntity = reloadItemDao.selectById(reloadForm.getTag());
        if (null == reloadItemEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        reloadItemEntity.setIdentification(reloadForm.getIdentification());
        reloadItemEntity.setUpdateTime(LocalDateTime.now());
        reloadItemEntity.setArgs(reloadForm.getArgs());
        reloadItemDao.updateById(reloadItemEntity);
        return ResponseDTO.ok();
    }
}
