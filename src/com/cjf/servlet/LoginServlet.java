package com.cjf.servlet;

import com.cjf.domain.Administer;
import com.cjf.domain.User;
import com.cjf.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        *
        * 检验验证码
        * 不成功请求转发至login.jsp
        * 成功
        * 获取登录数据 username、password
        * 数据库查询
        *   存在：重定向至首页
        *   不存在：提示4秒重定向至注册页面
        * */
        //解决中文乱码
        response.setContentType("text/html;charset=UTF-8");
        //获取用户名和密码 初始化连接
        String username=request.getParameter("username");
        String password =request.getParameter("password");
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        String sql;
        Object[] params={username,password};

        //获取登录方式
        String judge_user=request.getParameter("login");
        System.out.println(request.getParameter("login"));
        if (judge_user!=null)
        if (judge_user.equals("管理食品")){
            Administer administer=null;
            sql="select * from administer where aname=? and apassword=?";
            try {
               administer= qr.query(sql,new BeanHandler<>(Administer.class),params);
               if (administer!=null){
                   response.sendRedirect("/MyDemo_war_exploded/OperateServlet");
               }else {
                   request.setAttribute("info","输入有误");
                   request.getRequestDispatcher("login.jsp").forward(request,response);
            }
            } catch (SQLException e) {
                e.printStackTrace();
                throw  new RuntimeException("管理员用户登录 连接数据库连接失败。");
            }
        }
        else{
            User user=null;
//        数据库查询
           sql="select * from user where username=? and password=?";
            try {
                user=qr.query(sql,new BeanHandler<User>(User.class),params);
                if (user!=null){//        存在：重定向至首页
                    response.sendRedirect(request.getContextPath()+"/ProductServlet");
                }else {//        不存在：提示4秒重定向至注册页面
                    response.getWriter().write("用户信息不存在，4秒后跳转到注册页面。");
                    response.setHeader("refresh","3;url="+request.getContextPath()+"/register.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw  new RuntimeException("普通用户登录 连接数据库连接失败。");
            }


        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
