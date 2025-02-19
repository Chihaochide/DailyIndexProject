package com.dailyindex.stock.filter;

import com.daily.stock.dtos.ResponseCode;
import com.daily.stock.exception.DailyIndexException;
import com.daily.stock.utils.JwtUtils;
import com.daily.stock.utils.Payload;
import com.daily.stock.utils.RsaUtils;
import com.daily.stock.utils.ThreadLocalUtils;
import com.dailyindex.stock.vo.resp.LoginRespVo;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 用户信息过滤器
 * 参数一：过滤器名字（随便写）
 * 参数二：过滤器拦截啊的路径（/*代表全部拦截）
 * 有问题 前端发送过来的token只有一次，第二次为null
 */
//@Component
//@WebFilter(filterName = "DailyIndexFilter",urlPatterns = "/*")
public class DailyIndexFilter implements Filter {

    @Value("${DailyIndex.jwt.publicKeyPath}")
    private String publicKeyPath;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        if (requestURI.contains("login")||requestURI.contains("captcha")){
            System.out.println("登录请求，放行");
            filterChain.doFilter(request,response); // 执行Controller方法
        }else {
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute("token2");
            if (StringUtils.isEmpty(token)){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }

            try {
                System.out.println("token = " + token);
                PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
                Payload<LoginRespVo> info = JwtUtils.getInfoFromToken(token, publicKey, LoginRespVo.class);
                LoginRespVo loginRespVo = info.getInfo();
                System.out.println("loginRespVo = " + loginRespVo);
                ThreadLocalUtils.set(loginRespVo.getUsername());

            }catch (Exception e){
                System.err.println("不合法token！");
                e.printStackTrace();
            }
            finally {
                // 清空ThreadLocal
                ThreadLocalUtils.remove();
            }

        }




    }
    }
