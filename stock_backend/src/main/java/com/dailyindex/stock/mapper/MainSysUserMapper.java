package com.dailyindex.stock.mapper;

import com.daily.stock.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MainSysUserMapper {
    @Select("select * from sys_user where username=#{userName}")
    SysUser findUserInfoByUserName(@Param("userName") String userName);
}
