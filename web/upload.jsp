<%@ page import="com.cjf.domain.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 陈剑非
  Date: 2019/10/3
  Time: 7:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传页面</title>
</head>
<body background="img/regist_bg.jpg">




<div id="student" >
    <table id="ProductList"  border="1px" style="border-collapse: collapse; text-align: center; margin: 0px auto;
width: 80%;" >
        <tr >
            <th>名称</th>
            <th>价格</th>
            <th>描述</th>
            <th>操作</th>
        </tr>

        <%
            List<Product> list= (List<Product>) request.getAttribute("products");
            if (list!=null)
            for(Product product:list){
        %>
        <tr>
            <td><%=product.getPname()%></td>
            <td><%=product.getPprice() %></td>
            <td><%=product.getPdesc() %></td>
            <td>
                <a style="text-decoration: none;" href="OperateProduct?operate=delete&id=<%=product.getPid()%>">删除</a>
                <a style="text-decoration: none;" href="OperateProduct?operate=change&id=<%=product.getPid()%>">修改</a>
            </td>
        </tr>
        <%
            }
        %>

    </table>
    <br/>






<!-- 上传文件是上传到服务器上，而保存到数据库是文件名 -->
<!-- 上传文件是以文件转换为二进制流的形式上传的 -->
<!-- enctype="multipart/form-data"需要设置在form里面，否则无法提交文件 -->

    <div style="background: antiquewhite; border: 5px solid darkgray; margin: 0px auto;
        width: 400px; height: 200px;">

        <form action="/MyDemo_war_exploded/upload" method="post" enctype="multipart/form-data">
            <fieldest>
                <legend style="text-align: center;">添加菜品</legend>
            <table>
                <tr>
                    <td><%= request.getAttribute("info")==null?"":request.getAttribute("info")%></td>
                </tr><tr>
                    <td>菜名：</td>
                    <td><input type="text" name="pname"/></td>
                </tr>
                <tr>
                     <td>价格：</td>
                     <td><input type="text" name="pprice"/></td>
                 </tr>
                <tr>
                    <td>食物描述：</td>
                    <td><input type="text" name="pdesc"/></td>
                </tr>
                <tr>
                    <td>美食图片：</td>
                    <td><input type="file" name="file"/></td>
                </tr>
                <tr>
                    <td><a href="/MyDemo_war_exploded/login.jsp"><input type="button" value="登录查看"/></a></td>
                    <td><input type="submit" value="上传图片"/></td>
                </tr>
            </table>
    </fieldest>

    </form>
    </div>

<%--<img src="/MyDemo_war_exploded\upload\815cf5b5-1899-425b-aefc-2e7ef245574e.jpeg" alt="">--%>
</body>
</html>
