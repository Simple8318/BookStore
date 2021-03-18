package com.jiang.service.impl;

import com.jiang.dao.BookDao;
import com.jiang.dao.impl.BookDaoImpl;
import com.jiang.pojo.Book;
import com.jiang.pojo.Page;
import com.jiang.service.BookService;

import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-10 23:21
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    /**
     * 添加图书的业务方法
     * @param book 需要添加的图书信息
     */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    /**
     * 删除图书信息的业务方法
     * @param id 需要删除的图书的id
     */
    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    /**
     * 修改图书信息的业务方法
     * @param book 需要修改的图书的信息
     */
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    /**
     * 按id查询图书信息的业务方法
     * @param id 需要查询的图书的id
     * @return 返回查询到的图书的信息
     */
    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    /**
     * 查询所有图书的信息的业务方法
     * @return 返回查询到的图书的List集合
     */
    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    /**
     * 查询每页图书信息的业务方法
     * @param pageNo   当前页的页码
     * @param pageSize 每页要显示的图书数量
     * @return 返回每页要显示的图书的全部信息的List集合
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        /*
            检查页码的有效范围
            当页码小于最小页码时，默认为第一页
            当页码大于最大页码时，默认为最后一页
         */
        if (pageNo<1){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        // 设置当前页码
        page.setPageNo(pageNo);
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    /**
     *      查询价格在minPrice和maxPrice之间的当前页的pageSize个图书对象的信息的业务方法
     * @param pageNo        当前页页码数
     * @param pageSize      每页显示的数量
     * @param minPrice      最低价格
     * @param maxPrice      最高价格
     * @return      返回价格在minPrice和maxPrice之间的当前页的pageSize个图书对象的信息
     */
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int minPrice, int maxPrice) {
        Page<Book> page = new Page<>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(minPrice,maxPrice);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        /*
            检查页码的有效范围
            当页码小于最小页码时，默认为第一页
            当页码大于最大页码时，默认为最后一页
         */
        if (pageNo<1){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        // 设置当前页码
        page.setPageNo(pageNo);
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,minPrice,maxPrice);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }
}
