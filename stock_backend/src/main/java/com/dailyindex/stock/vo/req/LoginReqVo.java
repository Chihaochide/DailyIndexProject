package com.dailyindex.stock.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by itheima
 * @Date 2021/12/30
 * @Description 登录请求vo
 */
@Data
@ApiModel("用户登录前端传入的参数实体类")
public class LoginReqVo {
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty("用户登录输入的密码")
    private String password;
    /**
     * 验证码
     */
    @ApiModelProperty("用户输出的验证码")
    private String code;
    /**
     * sessionId
     */
    @ApiModelProperty("会话ID")
    private String sessionId;
}