package com.jiang.dao.impl;

import com.jiang.dao.BookDao;
import com.jiang.pojo.Book;

import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-10 22:51
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     *  添加图书
     * @param book  添加图书的信息
     * @return  返回影响的行数
     */
    @Override
    public int addBook(Book book) {
        //language=MySQL
        String sql="insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) value (?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    /**
     *      删除图书
     * @param id    要删除的图书的id
     * @return  返回影响的行数
     */
    @Override
    public int deleteBookById(Integer id) {
        //language=MySQL
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    /**
     *      修改图书的信息
     * @param book  需要修改的图书的信息
     * @return  返回影响的行数
     */
    @Override
    public int updateBook(Book book) {
        //language=MySQL
        String sql="update t_book set `name`=? , `author`=? , `price`=? , `sales`=? , `stock`=? , `img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    /**
     *      按id值查询图书信息
     * @param id    要查询的图书的id
     * @return  返回要查询的图书的信息
     */
    @Override
    public Book queryBookById(Integer id) {
        //language=MySQL
        String sql="select `id` ,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    /**
     *      查询全部图书的信息
     * @return      返回查询的图书的List集合
     */
    @Override
    public List<Book> queryBooks() {
        //language=MySQL
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    /**
     *      查询全部图书的数量
     * @return      返回全部图书的数量
     */
    @Override
    public Integer queryForPageTotalCount() {
        //language=MySQL
        String sql="select count(*) from t_book";
        Number count= (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    /**
     *      查询当前页的全部图书的信息的List集合
     * @param begin     当前页数据的开始索引
     * @param pageSize  每页要显示的图书数量
     * @return  返回查询到的当前页全部图书信息的List集合
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        //language=MySQL
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    /**
     *      搜索指定价格区间的全部图书数量
     * @param minPrice  最低价格
     * @param maxPrice  最高价格
     * @return      返回在minPrice和maxPrice价格区间内的所有图书的数量
     */
    @Override
    public Integer queryForPageTotalCountByPrice(int minPrice, int maxPrice) {
        //language=MySQL
        String sql="select count(*) from t_book where price between ? and ?";
        Number count= (Number) queryForSingleValue(sql,minPrice,maxPrice);
        return count.intValue();
    }

    /**
     *      搜索指定价格区间的从begin开始的pageSize个图书信息
     * @param begin     开始下标
     * @param pageSize  结束下标
     * @param minPrice  最低价格
     * @param maxPrice  最高价格
     * @return      返回价格在minPrice和maxPrice价格区间内的从begin开始的pageSize个图书信息
     */
    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int minPrice, int maxPrice) {
        //language=MySQL
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath " +
                "from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,minPrice,maxPrice,begin,pageSize);
    }
}
