package com.cjf.servlet;

import com.cjf.domain.User;
import com.cjf.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        * 解决中文乱码
        * 检验 验证码是否正确
        * 不正确
        *   请求转发回register.jsp页面   并提示验证码不正确。
        * 正确
        *   获取数据 账号，密码。省份，城市。
        *   判断用户是否重复。
        *       重复 请求转发至register.jsp
        *       不重复
        *           连接数据库，添加数据（账号、密码、省份、城市）
        *           重定向  到home_page.jsp
        * */
//        解决中文乱码
        request.setCharacterEncoding("UTF-8");
        String[] provinces ={"广东","湖南","福建","重庆"};
//        *  获取数据 账号，密码。省份，城市。
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String Province=provinces[Integer.parseInt(request.getParameter("Province"))];
        String City=request.getParameter("City");
        System.out.println(username+" "+password+"  "+Province+"    "+City);
        User user=Check(username);
        if (user!=null){
            request.setAttribute("register_info","用户已存在。");
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }else {
            QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
            String sql="INSERT into user VALUES(?,?,?,?,?); ";
//            INSERT into user VALUES('3','c','123','重庆','江津');
            Object[] params={UUID.randomUUID().toString(),username,password,Province,City};
            int rows;
            try {
                rows=qr.update(sql,params);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("数据库插入数据失败");
            }
            response.sendRedirect("/MyDemo_war_exploded/ProductServlet");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public User Check(String username){
        //        *   判断用户是否重复。
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from user where username=?";
        Object[] params={username};
        User user=null;
        try {
            user=qr.query(sql,new BeanHandler<User>(User.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库查询用户失败");
        }
        return user;
    }
}
