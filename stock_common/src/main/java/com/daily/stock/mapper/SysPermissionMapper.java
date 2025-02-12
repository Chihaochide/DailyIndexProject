package com.daily.stock.mapper;

import com.daily.stock.pojo.entity.SysPermission;

/**
* @author 刘轩赫
* @description 针对表【sys_permission(权限表（菜单）)】的数据库操作Mapper
* @createDate 2025-01-27 01:37:21
* @Entity com.daily.sotck.pojo.entity.SysPermission
*/
public interface SysPermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

}
