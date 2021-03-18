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
import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-11 0:27
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     *      添加图书的Servlet程序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
            获取页码的最后一页页码数，以便添加完数据时，直接跳转至所添加数据的页码
            对获取的页码+1，处理添加完图书信息，刚好页码总数增加一页的情况
            (在设置当前页码的Service业务方法时，已经设置如果当前页码大于总页码时，
                默认当前页码为最大页码数，避免了当前页码超出总页码的情况)
         */
        int pageNo = WebUtils.parserInt(req.getParameter("pageNo"), 0)+1;
        // 1 获取请求的参数，并将获取的请求参数封装成JavaBean对象(Book对象)
        Book book = WebUtils.copyParameterToBean(req.getParameterMap(), new Book());
        // 2 调用BookService.add方法，将图书信息保存至数据库
        bookService.addBook(book);
        // 3 跳转至图书列表页面/manager/bookServlet?action=list(刷新页面效果)
        /**
         * 因为请求转发是一次请求，可能出现表单重复提交的情况；请求重定向为两次请求
         * 所以使用请求重定向，避免变淡重复提交
         * 同时设置跳转至页码最后一页，以便显示所添加的数据
         */
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    /**
     *      删除图书的Servlet程序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求参数id，并转换成int类型数据
        int id = WebUtils.parserInt(req.getParameter("id"), 0);
        // 2 调用bookService.deleteById方法删除图书信息
        bookService.deleteBookById(id);
        // 3 重定向回图书列表信息页面 /book/manager/bookServlet?action=page
        // page="+req.getParameter("pageNo") 重定向到所删除图书原本所在页，以便检查图书是否已正常删除
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     *       获取图书信息的Servlet程序
     *       修改图书信息时，将图书信息显示在修改页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求参数，图书的编号
        int id = WebUtils.parserInt(req.getParameter("id"), 0);
        // 2 调用bookService.queryBookById，查询指定编号的图书信息
        Book book = bookService.queryBookById(id);
        // 3 将查询到的图书信息保存到Request域中，以便在book_edit.jsp页面显示
        req.setAttribute("book", book);
        // 4 请求转发到/pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     *      修改图书信息的Servlet程序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数，封装成JavaBean对象
        Book book = WebUtils.copyParameterToBean(req.getParameterMap(), new Book());
        // 2 调用bookService.update方法，修改图书信息
        bookService.updateBook(book);
        // 3 重定向回图书列表页面 /book/manager/bookServlet?action=page
        // pageNo="+req.getParameter("pageNo") 重定向到所修改图书原本所在页，以便检查图书是否已正常修改
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     *      获取全部图书信息的Servlet程序
     *      在图书管理页面显示全部图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 通过BookService查询全部图书信息
        List<Book> books = bookService.queryBooks();
        // 2 把全部图书信息保存到Request域中
        req.setAttribute("books", books);
        // 3 请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     *      列表显示图示信息的Servlet程序
     *      在图书列表页面，每页显示指定数量的图书信息
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
        page.setUrl("manager/bookServlet?action=page");
        // 4 将Page对象保存到Request域中
        req.setAttribute("page",page);
        // 5 请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}


