package com.dailyindex.stock.controller;


// 用户web层

import com.daily.stock.pojo.entity.SysUser;
import com.dailyindex.stock.service.UserService;
import com.dailyindex.stock.vo.req.LoginReqVo;

import com.daily.stock.dtos.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(tags = "用户登录使用")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据用户名查询用户信息")
    @ApiImplicitParam(name = "name",value = "用户名",dataType = "String",required = true,type = "path")
    @GetMapping("/user/{userName}")
    public SysUser getUserByUserName(@PathVariable("userName") String userName){

        return userService.findByUserName(userName);
    }


    @ApiOperation("用户登录功能")
    @PostMapping("/login")
    public R login(@RequestBody LoginReqVo loginReqVo,HttpServletResponse response,HttpServletRequest request){

        return userService.login(loginReqVo,response,request);
    }

    /**
     * 生成图片验证码
     * @return
     */
    @GetMapping("/captcha")
    public R<Map> getCaptchaCode(){
        return userService.getCaptchaCode();
    }

    @GetMapping("/test")
    public void cookieTest(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("token","Axx");
        response.addCookie(cookie);
    }


}
