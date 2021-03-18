package com.jiang.web;

import com.jiang.pojo.Book;
import com.jiang.pojo.Page;
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
 * @create 2021-03-15 0:18
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    /**
     *      获取图书列表每页图书信息的Servlet程序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数pageNo、pageSize
        int pageNo = WebUtils.parserInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parserInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 2 调用bookService.page，得到Page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        // 3 设置分页条的url地址
        page.setUrl("client/bookServlet?action=page");
        // 4 将Page对象保存到Request域中
        req.setAttribute("page",page);
        // 5 请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     *      获取价格区间内图书列表每页的图书信息的Servlet程序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数pageNo、pageSize、图书价格min、图书价格max
        int pageNo = WebUtils.parserInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parserInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int minPrice = WebUtils.parserInt(req.getParameter("min"), 0);
        int maxPrice = WebUtils.parserInt(req.getParameter("max"), Integer.MAX_VALUE);

        // 2 调用bookService.page，得到Page对象
        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,minPrice,maxPrice);
        // 3 设置分页条的url地址
        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");
        /*
            如果有价格参数，将价格参数追加到分页条请求地址的请求参数中
         */
        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        // 4 将Page对象保存到Request域中
        req.setAttribute("page",page);
        // 5 请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
