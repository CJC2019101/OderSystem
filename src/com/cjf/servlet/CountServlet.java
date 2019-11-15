package com.cjf.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CountServlet", urlPatterns = "/CountServlet")
public class CountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       /*
       解决中文乱码
       判断点餐是否存有数据
        有
            获取主页点餐的数据做结账运算。重定向至付款界面
        没有
            重定向至ProductServlet 重新点餐

        */
//        解决中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//                判断点餐是否存有数据
        String[] Worth=request.getParameterValues("shop");
        Map<String,Double> Pname = new HashMap<String,Double>();
        String[] data=null;
//        有
//        获取主页点餐的数据做结账运算。重定向至付款界面
        if (Worth!=null){

            for (int i=0;i<Worth.length;i++
            ) {
                data=Worth[i].split("_");
                Pname.put(data[0],new Double(data[1]));
            }
            this.getServletContext().setAttribute("Pname",Pname);//可以使用请求转发发送数据。
            response.sendRedirect("pay.jsp");
            return;
        }else {
            //                没有
//        重定向至ProductServlet 重新点餐
            response.getWriter().write("您没有点餐，无法提交。3秒后页面跳转");
            response.setHeader("refresh","3;url=/MyDemo_war_exploded/ProductServlet");
            return;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
