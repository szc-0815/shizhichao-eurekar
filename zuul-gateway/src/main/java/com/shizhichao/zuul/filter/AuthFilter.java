package com.shizhichao.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends ZuulFilter {

    /**
       * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类
     型，具体如下：
       * pre：路由之前
       * routing：路由之时
       * post： 路由之后
       * error：发送错误调用
       */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
       * 过滤的顺序
       */
    @Override
    public int filterOrder() {
        return 0;
    }

    //这里可以写逻辑判断，是否要过滤，true,永远过滤。
    @Override
    public boolean shouldFilter() {
        return false;
    }

    //过滤器的具体逻辑。
    //可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
    @Override
    public Object run() throws ZuulException {

        //首先获取request对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.err.println("run:"+request.getRequestURI());
        //是否授权
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            //设置为false 就不会路由到后端的服务
            requestContext.setSendZuulResponse(false);
            //设置http响应的状态码 401代表未授权
            requestContext.setResponseStatusCode(401);
            //返回响应信息
            HttpServletResponse response = requestContext.getResponse();

            //response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            try{
                response.getWriter().write("{\"code\":401,\"msg:\":\"未授权\"}");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return null;





    }
}
