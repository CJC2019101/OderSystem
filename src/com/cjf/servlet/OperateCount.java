package com.cjf.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "OperateCount", urlPatterns = "/OperateCount")
public class OperateCount extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       request.setCharacterEncoding("UTF-8");
        ServletContext context=this.getServletContext();
       Map<String ,Double> map= (Map<String, Double>) context.getAttribute("Pname");
       String key= (String) request.getParameter("key");
//        System.out.println(key);
       map.remove(key);
       context.setAttribute("Pname",map);
       response.sendRedirect("/MyDemo_war_exploded/pay.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
