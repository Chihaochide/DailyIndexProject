package com.daily.stock.mapper;

import com.daily.stock.pojo.entity.SysRole;

/**
* @author 刘轩赫
* @description 针对表【sys_role(角色表)】的数据库操作Mapper
* @createDate 2025-01-27 01:37:21
* @Entity com.daily.sotck.pojo.entity.SysRole
*/
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

}
