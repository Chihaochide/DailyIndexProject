package com.dailyindex.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import com.daily.stock.dtos.AppHttpCodeEnum;
import com.daily.stock.exception.DailyIndexException;
import com.daily.stock.pojo.entity.SysUser;
import com.daily.stock.utils.BCrypt;
import com.daily.stock.utils.IdWorker;
import com.daily.stock.utils.JwtUtils;
import com.daily.stock.utils.RsaUtils;
import com.dailyindex.stock.mapper.MainSysUserMapper;
import com.dailyindex.stock.service.UserService;
import com.dailyindex.stock.vo.req.LoginReqVo;
import com.dailyindex.stock.vo.resp.LoginRespVo;
import com.daily.stock.dtos.R;
import com.daily.stock.dtos.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private MainSysUserMapper mainSysUserMapper;

    @Value("${DailyIndex.jwt.privateKeyPath}")
    private String privateKeyPath; // 私钥路径

    @Value("${DailyIndex.jwt.publicKeyPath}")
    private String publicKeyPath; // 公钥路径

    @Value("${DailyIndex.jwt.expire}")
    private Integer expire; // 过期时间

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;

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
    public R login(LoginReqVo loginReqVo) {
        if (loginReqVo== null|| StringUtils.isBlank(loginReqVo.getUsername())||StringUtils.isBlank(loginReqVo.getPassword())||StringUtils.isBlank(loginReqVo.getCode())){
//            throw new DailyIndexException(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);
            throw new DailyIndexException(ResponseCode.DATA_ERROR);
        }
        System.out.println("======11===============");
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
        // 获取token
        try {
            PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);
            LoginRespVo respVo = new LoginRespVo();

//        respVo.setUsername(dbUser.getUsername());
//        respVo.setId(dbUser.getId());
//        respVo.setNickName(dbUser.getNickName());
//        respVo.setNickName(dbUser.getNickName());
            // LoginRespVo和dbUser对象属性和类型一样，直接用工具类进行赋值
            // 必须保证属性名称和类型一致
            BeanUtils.copyProperties(dbUser,respVo);
            String token = JwtUtils.generateTokenExpireInMinutes(respVo,privateKey,expire);
            Map map = new HashMap<>();
            map.put("accessToken",token);
            map.put("user",respVo);
            return R.ok(map);
        }catch (Exception e){
            throw new DailyIndexException(500,"系统错误");
        }

    }


    /**
     * 生成图片验证码 使用hutool工具类
     * @return
     */
    @Override
    public R<Map> getCaptchaCode() {
        // 生成图片验证码
        // 长*宽  验证码个数 干扰线条数量
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        // 设置背景颜色 可以不添加
        captcha.setBackground(Color.DARK_GRAY);
        // 自定义生成校验码的规则
//        captcha.setGenerator(new CodeGenerator() {
//            @Override
//            public String generate() {
//                // 自定义生成校验码的逻辑
//                return null;
//            }
//
//            @Override
//            public boolean verify(String s, String s1) {
//                return false;
//            }
//        });
        // 获取校验🐎
        String checkCode =  captcha.getCode();
        // 获取经过base64编码处理的图片数据
        String imageData = captcha.getImageBase64();
        // 生成sessionId 转换成String类型避免前端接收到精度丢失
        String sessionId = idWorker.nextId()+"";
        log.info("当前生成的图片校验码：{},会话ID；{}",checkCode,sessionId);
        // 将sessionId作为key 校验码作为value保存到reids中
        // key,value,过期时间，过期分钟
        redisTemplate.opsForValue().set("CK:"+sessionId,checkCode,10, TimeUnit.MINUTES);
        // 4.组装数据
        Map<String,String> map = new HashMap<>();
        map.put("imageData",imageData);
        map.put("sessionId",sessionId);
        return R.ok(map);
    }
}
