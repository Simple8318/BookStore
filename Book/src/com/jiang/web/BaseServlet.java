package com.jiang.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-10 20:19
 */
public abstract class BaseServlet extends HttpServlet {
    /**
     *  通过doGet方法调用doPost方法，避免连接标签的doGet请求无法完成
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将请求和响应以及服务器的编码集设置为utf-8，避免出现中文乱码的情况
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取不同业务请求的参数
        String action=req.getParameter("action");
        //通过反射，执行action参数相应的方法
        try {
            //根据参数获取相应方法的反射对象
            Method method = this.getClass().getDeclaredMethod(action,
                    HttpServletRequest.class,HttpServletResponse.class);
            //调用业务方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);  // 把异常抛给Filter过滤器
        }
    }
}
