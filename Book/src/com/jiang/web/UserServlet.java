package com.jiang.web;

import com.jiang.pojo.User;
import com.jiang.service.UserService;
import com.jiang.service.impl.UserServiceImpl;
import com.jiang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-10 16:02
 */
public class UserServlet extends BaseServlet {

    private UserService userService=new UserServiceImpl();

    /**
     *      登录方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求参数
        // 2 调用userService.login
        // 3 根据login方法的返回值判断是否登录成功

        // 1 获取请求参数
        String username=req.getParameter("username");
        req.getSession().setAttribute("userName",username);
        String password=req.getParameter("password");
        // 2 调用userService.login()处理登录业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 3 根据login()方法的返回值判断是否登录成功
        if (loginUser==null){
            // 3.2 登录失败
            // 3.2.1 把错误信息和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            // 3.2.2 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            // 3.1 登录成功
            // 3.1.1 保存用户登录信息，以便显示
            req.getSession().setAttribute("user",loginUser);
            // 3.1.2 跳转到登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     *      注册方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取谷歌验证码生成并自动保存在Session域中的验证码
        // 2 删除Session域中的验证码
        // 3 获取注册表中的请求参数
        // 4 检查验证码是否正确
        // 5 检查用户名是否可用

        // 1 获取谷歌验证码生成并自动保存在Session域中的验证码
        String googleCode = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 2 删除Session域中的验证码，避免重复提交，验证码重复使用
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 3 获取注册表中的请求参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String code=req.getParameter("code");

        // 调用工具类，注入JavaBean属性
        User user = WebUtils.copyParameterToBean(req.getParameterMap(),new User());

        // 4 检查验证码是否正确
        // 4.1 验证码正确
        if (googleCode!=null && googleCode.equalsIgnoreCase(code)){
            // 5 检查用户名是否可用
            if (userService.existsUsername(username)){
                // 5.1 用户名不可用
                // 5.1.1 把需要回显的信息保存至Request域中
                req.setAttribute("msg","用户名已存在！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                // 5.1.2 跳转回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            } else {
                // 5.2 用户名可用
                // 5.2.1 调用Service将用户信息保存至数据库
                userService.registerUser(new User(null,username,password,email));
                // 5.2.2 跳转至用户注册成功页面regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        } else {
            // 4.2 验证码错误
            // 4.2.1 把需要回显的信息保存至Request域中
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            // 4.2.2 跳转回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    /**
     *      退出系统方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 销毁Session域中用户已经登录的信息
        // 2 重定向到首页

        // 1 销毁Session域中用户已经登录的信息
        req.getSession().invalidate();
        // 2 重定向到首页
        resp.sendRedirect(req.getContextPath());
    }
}
