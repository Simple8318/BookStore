package com.jiang.web;

import com.jiang.pojo.Cart;
import com.jiang.pojo.Order;
import com.jiang.pojo.User;
import com.jiang.service.OrderService;
import com.jiang.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 15:18
 */
public class OrderServlet extends BaseServlet {

    private OrderService orderService=new OrderServiceImpl();

    /**
     *      生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取购物车对象
        // 2 获取用户id
        // 3 调用orderService.createOrder(Cart,userId)，生成订单
        // 4 将生成的订单号保存到 Request域中
        // 5 请求重定向到结算生成订单页面

        // 1 获取购物车对象，购物车对象保存在Session域中
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        // 2 获取用户id，用户登录后，id保存在Session域中
        User loginUser= (User) req.getSession().getAttribute("user");
        // 如果此时用户并未登录，则跳转至登录页面，并停止后续代码的执行
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
        // 3 调用orderService.createOrder(Cart,userId)，生成订单
        String orderId = orderService.createOrder(cart, userId);
        // 4 将生成的订单号保存到Session域中，以便在订单页面显示订单号
        req.getSession().setAttribute("orderId",orderId);
        // 5 请求重定向到/pages/cart/checkout.jsp(避免重复提交)
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     *      显示全部订单信息的Servlet程序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取订单信息
        // 2 将订单信息保存到Request域中
        // 3 请求转发到订单页面

        // 1 调用orderService.queryOrders()，获取全部订单信息
        List<Order> orders = orderService.queryOrders();
        req.getSession().setAttribute("orders",orders);
        resp.sendRedirect(req.getContextPath()+"/pages/manager/order_manager.jsp");
        // // 2 将订单信息保存到Request域中，以便在页面显示
        // req.setAttribute("orders",orders);
        // // 3 请求转发到订单页面，查看全部订单
        // req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    /**
     *      显示当前用户订单信息的Servlet程序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = (String) req.getSession().getAttribute("userName");
        int userId = orderService.getUserId(userName);
        List<Order> ordersOfMy = orderService.queryOrderByUserId(userId);
        req.getSession().setAttribute("ordersOfMy",ordersOfMy);
        resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");
    }
}
