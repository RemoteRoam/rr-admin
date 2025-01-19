package tech.remote.base.module.support.datatracer.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import tech.remote.base.module.support.datatracer.dao.DataTracerDao;
import tech.remote.base.module.support.datatracer.domain.entity.DataTracerEntity;
import org.springframework.stereotype.Service;

/**
 * manager层
 *
 * @Author 远游工作室-yy
 * @Date 2022-07-23 19:38:52
 *  
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Service
public class DataTracerManger extends ServiceImpl<DataTracerDao, DataTracerEntity> {
}
