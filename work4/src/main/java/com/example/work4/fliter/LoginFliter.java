package com.example.work4.fliter;
import com.alibaba.fastjson.JSONObject;
import com.example.work4.tool.Result;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@WebFilter(urlPatterns = {"/user/*"})
public class LoginFliter implements Filter {
    private FilterConfig filterConfig;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public void init(FilterConfig filterConfig)throws ServletException{
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse) resp;

        String token=request.getHeader("token");
        token=token==null? "":token;
        Long expire=redisTemplate.getExpire("token");
        if (expire >0) {
            redisTemplate.expire(token,30L, TimeUnit.MINUTES);
            filterChain.doFilter(req,resp);
        }else {
            String s= JSONObject.toJSONString(new Result(-1,"未登录",null));
            response.setContentType("json/text;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write(s);
        }
    }
    @Override
    public void destroy() {
        filterConfig=null;
    }

}