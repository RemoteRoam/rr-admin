package tech.remote.admin.module.system.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.remote.admin.module.system.role.domain.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tech.remote.admin.module.system.employee.domain.vo.EmployeeVO;
import tech.remote.admin.module.system.role.domain.entity.RoleEmployeeEntity;
import tech.remote.admin.module.system.role.domain.form.RoleEmployeeQueryForm;
import tech.remote.admin.module.system.role.domain.vo.RoleEmployeeVO;

import java.util.List;


/**
 * 角色 员工 dao
 *
 * @Author 远游工作室: 罗伊
 * @Date 2022-03-07 18:54:42
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
@Mapper
@Component
public interface RoleEmployeeDao extends BaseMapper<RoleEmployeeEntity> {

    /**
     * 根据员工id 查询所有的角色
     */
    List<RoleVO> selectRoleByEmployeeId(@Param("employeeId") Long employeeId);

    /**
     * 根据员工id 查询所有的角色io集合
     */
    List<Long> selectRoleIdByEmployeeId(@Param("employeeId") Long employeeId);

    /**
     * 根据员工id 查询所有的角色
     */
    List<RoleEmployeeEntity> selectRoleIdByEmployeeIdList(@Param("employeeIdList") List<Long> employeeIdList);

    /**
     * 根据员工id 查询所有的角色
     */
    List<RoleEmployeeVO> selectRoleByEmployeeIdList(@Param("employeeIdList") List<Long> employeeIdList);

    /**
     * 查询角色下的人员id
     */
    List<Long> selectEmployeeIdByRoleIdList(@Param("roleIdList") List<Long> roleIdList);

    /**
     *
     */
    List<EmployeeVO> selectRoleEmployeeByName(Page page, @Param("queryForm") RoleEmployeeQueryForm roleEmployeeQueryForm);

    /**
     *
     */
    List<EmployeeVO> selectEmployeeByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据员工信息删除
     */
    void deleteByEmployeeId(@Param("employeeId") Long employeeId);

    /**
     * 删除某个角色的所有关系
     */
    void deleteByRoleId(@Param("roleId")Long roleId);

    /**
     * 根据员工和 角色删除关系
     */
    void deleteByEmployeeIdRoleId(@Param("employeeId") Long employeeId,@Param("roleId")Long roleId);

    /**
     * 批量删除某个角色下的某批用户的关联关系
     */
    void batchDeleteEmployeeRole(@Param("roleId") Long roleId,@Param("employeeIds")List<Long> employeeIds);
}
