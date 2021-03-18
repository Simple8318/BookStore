package com.jiang.filter;

import com.jiang.pojo.User;
import com.jiang.service.AdminService;
import com.jiang.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 16:31
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *      后台管理员权限内容对普通用户和未登录用户的拦截
     *      如果用户为管理员，则可以进行后台操作
     *      如果用户为普通用户或游客，则拦截
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        // 1 从Session域中获取用户登录信息
        User user= (User) httpServletRequest.getSession().getAttribute("user");
        // 2 如果用户未登录，则跳转至登录页面
        if (user==null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        } else {    // 3 如果用户已登录
            // 3.1 获取用户的Id
            Integer userId = user.getId();
            AdminService adminService=new AdminServiceImpl();
            // 3.2 调用AdminService.getAdminJurisdiction(userId),获取该用户的权限信息
            String adminJurisdiction = adminService.getAdminJurisdiction(userId);
            // 3.3 只有该用户为管理员权限时，才可通过检查
            if ("admin".equalsIgnoreCase(adminJurisdiction)){
                // 3.3.1 用户为管理员，则通过检查
                filterChain.doFilter(servletRequest,servletResponse);
            } else if (adminJurisdiction==null){
                // 3.3.2 用户不是管理员，则拦截
                httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
            } else {
                // 3.3.3 其他情况，也拦截
                httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
