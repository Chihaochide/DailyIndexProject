package com.daily.stock.mapper;

import com.daily.stock.pojo.entity.SysUserRole;

/**
* @author 刘轩赫
* @description 针对表【sys_user_role(用户角色表)】的数据库操作Mapper
* @createDate 2025-01-27 01:37:21
* @Entity com.daily.sotck.pojo.entity.SysUserRole
*/
public interface SysUserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

}
