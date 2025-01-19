package tech.remote.admin.module.system.department.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.remote.admin.module.system.department.domain.entity.DepartmentEntity;
import tech.remote.admin.module.system.department.domain.vo.DepartmentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 部门
 *
 * @Author 远游工作室-yy
 * @Date 2022-01-12 20:37:48
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Component
@Mapper
public interface DepartmentDao extends BaseMapper<DepartmentEntity> {

    /**
     * 根据部门id，查询此部门直接子部门的数量
     *
     */
    Integer countSubDepartment(@Param("departmentId") Long departmentId);

    /**
     * 获取全部部门列表
     */
    List<DepartmentVO> listAll();

}
