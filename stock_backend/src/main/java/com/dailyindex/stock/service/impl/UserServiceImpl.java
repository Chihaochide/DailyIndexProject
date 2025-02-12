package com.dailyindex.stock.service.impl;

import com.daily.stock.dtos.AppHttpCodeEnum;
import com.daily.stock.exception.DailyIndexException;
import com.daily.stock.pojo.entity.SysUser;
import com.daily.stock.utils.BCrypt;
import com.dailyindex.stock.mapper.MainSysUserMapper;
import com.dailyindex.stock.service.UserService;
import com.dailyindex.stock.vo.req.LoginReqVo;
import com.dailyindex.stock.vo.resp.LoginRespVo;
import com.daily.stock.dtos.R;
import com.daily.stock.dtos.ResponseCode;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private MainSysUserMapper mainSysUserMapper;


    @Override
    public SysUser findByUserName(String userName) {

        mainSysUserMapper.findUserInfoByUserName(userName);
        System.out.println(mainSysUserMapper.toString());

        return mainSysUserMapper.findUserInfoByUserName(userName);
    }

    /**
     * 用户登录功能
     */
    @Override
    public R<LoginRespVo> login(LoginReqVo loginReqVo) {
        if (loginReqVo== null|| StringUtils.isBlank(loginReqVo.getUsername())||StringUtils.isBlank(loginReqVo.getPassword())||StringUtils.isBlank(loginReqVo.getCode())){
//            throw new DailyIndexException(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);
            throw new DailyIndexException(ResponseCode.DATA_ERROR);
        }
        System.out.println("=====================");
        SysUser dbUser = mainSysUserMapper.findUserInfoByUserName(loginReqVo.getUsername());
        if (dbUser==null){
            throw new DailyIndexException(ResponseCode.ACCOUNT_NOT_EXISTS);
        }

        // 匹配密码
        String pwd = dbUser.getPassword();
        if (!BCrypt.checkpw(loginReqVo.getPassword(),pwd)) {
            throw new DailyIndexException(0,"密码错误");
        }

        // 都匹配了
        LoginRespVo respVo = new LoginRespVo();
//        respVo.setUsername(dbUser.getUsername());
//        respVo.setId(dbUser.getId());
//        respVo.setNickName(dbUser.getNickName());
//        respVo.setNickName(dbUser.getNickName());
        // LoginRespVo和dbUser对象属性和类型一样，直接用工具类进行赋值
        // 必须保证属性名称和类型一致
        BeanUtils.copyProperties(dbUser,respVo);
        return R.ok(respVo);
    }
}
