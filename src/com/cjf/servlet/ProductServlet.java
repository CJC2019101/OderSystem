package com.cjf.servlet;

import com.cjf.domain.Product;
import com.cjf.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        解决中文乱码
        连接数据库获取所有商品数据封装数据在request域中（由于数据并不是从其他页面获取，所以可以转发）
        请求转发至home_page.jsp页面，在home_page.jsp页面中动态的展示商品
              */
        //解决中文乱码
        request.setCharacterEncoding("UTF-8");
//        连接数据库获取所有商品数据封装数据在request域中
        List<Product> list=null;
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from product";
        try {
            list=qr.query(sql,new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("数据库获取所有商品信息失败");
        }
        if (list.size()==0){
            response.getWriter().write("商品已经售空");
            return;
        }else {
//        请求转发至home_page.jsp页面，在home_page.jsp页面中动态的展示商品
            request.setAttribute("products",list);
            request.getRequestDispatcher("home_page.jsp").forward(request,response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
