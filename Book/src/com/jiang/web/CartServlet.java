package com.jiang.web;

import com.jiang.pojo.Book;
import com.jiang.pojo.Cart;
import com.jiang.pojo.CartItem;
import com.jiang.service.BookService;
import com.jiang.service.impl.BookServiceImpl;
import com.jiang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-15 15:05
 */
public class CartServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    /**
     *      加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1 获取请求的参数，商品id
        // 2 调用bookService.queryBookById(id)，得到要添加至购物车的图书信息
        // 3 把图书信息转为CartItem商品项
        // 4 调用Cart.addItem(CartItem)，添加商品项
        // 5 将最后一次添加的商品的名称保存到Session域中
        // 6 重定向回图书列表页面

        // 1 获取请求的参数，商品id
        int id = WebUtils.parserInt(req.getParameter("id"), 0);
        // 2 调用bookService.queryBookById(id)，得到要添加至购物车的图书信息
        Book book = bookService.queryBookById(id);
        // 3 把图书信息转为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 4 调用Cart.addItem(CartItem)，添加商品项
        // 4.1 从Session域中获取购物车对象
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        // 4.2 如果Session域中没有购物车对象，则创建一个新的购物车对象；否则使用Session域中的购物车对象
        // 避免每次添加商品项都新建一个购物车对象(无法将每个商品项添加至同一个购物车对象中)
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        // 5 将最后一次添加的商品的名称保存到Session域中，以便首先回显
        req.getSession().setAttribute("lastName",cartItem.getName());
        // 6 重定向回原来商品所在的页面
        // 请求头的Referer的值为当前地址栏的地址
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     *      删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取参数，商品项的编号
        // 2 获取购物车对象
        // 3 删除购物车中的商品项
        // 4 重定向回购物车菜单

        // 1 获取参数，商品项的编号
        int id = WebUtils.parserInt(req.getParameter("id"), 0);
        // 2 获取购物车对象
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        // 3 删除购物车中的商品项
        if (cart!=null){
            cart.deleteItem(id);
            // 4 重定向回购物车菜单
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     *      清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取购物车对象
        // 2 清空购物车
        // 3 重定向回购物车页面

        // 1 获取购物车对象
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        // 2 清空购物车
        if (cart!=null){
            cart.clear();
            // 3 重定向回购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     *      修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数，商品的编号、修改后的数量
        // 2 获取购物车对象
        // 3 修改购物车中商品的数量
        // 4 重定向回购物车页面

        // 1 获取请求的参数，商品的编号、修改后的数量
        int id = WebUtils.parserInt(req.getParameter("id"), 0);
        int newCount = WebUtils.parserInt(req.getParameter("newCount"), 1);
        // 2 获取购物车对象
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        // 3 修改购物车中商品的数量
        if (cart!=null){
            // 对指定商品编号的商品项修改数量
            cart.updateCount(id,newCount);
            // 4 重定向回购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

}
