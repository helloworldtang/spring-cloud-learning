package com.learning.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * Created by tangcheng on 7/9/2017.
 */
//@Component
public class LoginFilter extends ZuulFilter {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    /**
     * 过滤类型
     * pre: 路由之间
     * routing: 路由之时
     * post: 路由之后
     * error: 发送错误调用
     */

    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 写判断逻辑，是否要过滤，true永远过滤
     * 可以写条件，让不需要过滤的return false
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        LOGGER.info("{}>>>{},{}", request.getMethod(), request.getRequestURL(), request.getRequestURI());

        if (!request.getRequestURI().contains("/login")) {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotBlank(authorization)) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (Objects.equals(HttpHeaders.AUTHORIZATION, cookie.getName())) {
                            authorization = cookie.getValue();
                            break;
                        }
                    }
                }

            }
            // 如果令牌为空, 再取QueryString中Authorization
            if (StringUtils.isBlank(authorization)) {
                authorization = request.getParameter(HttpHeaders.AUTHORIZATION);
            }

            if (StringUtils.isBlank(authorization)) {
                String msg = "token is empty";
                LOGGER.error(msg);
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);

                try {
                    context.getResponse().getWriter().write(msg);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                return null;
            } else {
                try {
                    Jws<Claims> claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(authorization);
                    //获取用户名
                    Claims claimsBody = claims.getBody();
                    String id = claimsBody.getId();
                    LOGGER.debug("username:{}", id);
                    //根据用户信息去获取相关信息
                    //校验逻辑
                    Date expiration = claimsBody.getExpiration();
                    LOGGER.debug("expTime:{}", DateFormatUtils.format(expiration, "yyyy-MM-dd HH:mm:ss"));
                    //校验是否过期的逻辑
                    return null;
                } catch (ExpiredJwtException e) {
                    e.printStackTrace();
                } catch (UnsupportedJwtException e) {
                    e.printStackTrace();
                } catch (MalformedJwtException e) {
                    e.printStackTrace();
                } catch (SignatureException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }

                // header中令牌不对, 可能被篡改
                String msg = "token is error";
                LOGGER.error(msg);
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
                try {
                    context.getResponse().getWriter().write(msg);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
                return null;
            }
        }
        LOGGER.info("token is ok");
        return null;
    }

}
