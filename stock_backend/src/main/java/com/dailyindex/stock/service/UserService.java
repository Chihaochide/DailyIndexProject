package com.dailyindex.stock.service;

import com.daily.stock.pojo.entity.SysUser;
import com.dailyindex.stock.vo.req.LoginReqVo;
import com.dailyindex.stock.vo.resp.LoginRespVo;
import com.daily.stock.dtos.R;

import java.util.Map;

public interface UserService {

    SysUser findByUserName(String userName);

    R login(LoginReqVo loginReqVo);

    R<Map> getCaptchaCode();

}
