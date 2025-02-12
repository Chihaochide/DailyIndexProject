package com.daily.stock.mapper;

import com.daily.stock.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
* @author 刘轩赫
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2025-01-27 01:37:21
* @Entity com.daily.sotck.pojo.entity.SysUser
*/
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


}
