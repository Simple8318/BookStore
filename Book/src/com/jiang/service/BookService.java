package com.jiang.service;

import com.jiang.pojo.Book;
import com.jiang.pojo.Page;

import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-10 23:18
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int minPrice, int maxPrice);
}
