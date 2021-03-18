package com.jiang.filter;

import com.jiang.utils.JDBCUtils;

import java.io.IOException;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 20:58
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *      对所有的操作都进行事务处理，同时进行错误页面情况的处理
     *      程序的异常全部抛到最底层，发生异常时，进行事务回滚，保证数据库安全；未发生异常，则手动提交事务
     *      将最终异常抛向服务器，使其进行相应的错误跳转页面操作
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();     // 没有异常情况，则提交事务，并关闭数据库连接对象
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();    // 存在异常情况，则回滚事务，并关闭数据库连接对象
            throw new RuntimeException(e);  // 将异常抛给服务器，以便服务器管理，展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
