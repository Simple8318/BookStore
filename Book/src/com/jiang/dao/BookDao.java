package com.jiang.dao;

import com.jiang.pojo.Book;

import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-10 22:47
 */
public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int minPrice, int maxPrice);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int minPrice, int maxPrice);
}
