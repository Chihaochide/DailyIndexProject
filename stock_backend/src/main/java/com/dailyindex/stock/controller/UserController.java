package com.dailyindex.stock.controller;


// 用户web层

import com.daily.stock.pojo.entity.SysUser;
import com.dailyindex.stock.service.UserService;
import com.dailyindex.stock.vo.req.LoginReqVo;

import com.daily.stock.dtos.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/{userName}")
    public SysUser getUserByUserName(@PathVariable("userName") String userName){

        return userService.findByUserName(userName);
    }


    @PostMapping("/login")
    public R login(@RequestBody LoginReqVo loginReqVo){
        return userService.login(loginReqVo);
    }

    /**
     * 生成图片验证码
     * @return
     */
    @GetMapping("/captcha")
    public R<Map> getCaptchaCode(){
        return userService.getCaptchaCode();
    }

}
