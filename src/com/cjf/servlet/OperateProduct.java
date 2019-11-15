package com.cjf.servlet;

import com.cjf.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OperateProduct", urlPatterns = "/OperateProduct")
public class OperateProduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String judge=request.getParameter("operate");
        String id=request.getParameter("id");
        System.out.println(judge);
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        int rows=0;
        String sql=null;
        if (judge!=null)
        if (judge.equals("delete")){
            sql="delete from product where pid=?";
            try {
                rows=qr.update(sql,id);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("管理员用户 数据库删除失败");
            }
            if (rows!=0){
                response.sendRedirect("/MyDemo_war_exploded/OperateServlet");
            }
        }
        response.getWriter().write("hahahaha");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
