package tech.remote.admin.module.business.project.manager;

import tech.remote.admin.module.business.project.domain.entity.ProjectEntity;
import tech.remote.admin.module.business.project.dao.ProjectDao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 项目表  Manager
 *
 * @Author cbh
 * @Date 2024-05-15 13:54:05
 * @Copyright Remote Nomad Studio
 */
@Service
public class ProjectManager extends ServiceImpl<ProjectDao, ProjectEntity> {


}
