<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 陈剑非
  Date: 2019/9/30
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body background="img/regist_bg.jpg">

<div id="student" >
    <fieldset style="text-align: center; background-color: bisque;">点餐信息表
    <table id="ProductList"  border="1px" style="border-collapse: collapse; text-align: center; margin: 0px auto;
width: 80%;" >
        <tr >
            <th>名称</th>
            <th>价格</th>
            <th>操作</th>
        </tr>

        <%
            int money=0;
            Map<String,Double> map= (Map<String, Double>) application.getAttribute("Pname");
            for (Map.Entry<String,Double> entry:map.entrySet()
            ) {
                money+=entry.getValue();
        %>
        <tr>
            <td><%=entry.getKey()%></td>
            <td><%=entry.getValue() %></td>
<%--            <td>--%>
<%--                <a style="text-decoration: none;" href="OperateProduct?operate=delete&id=<%=product.getPid()%>">删除</a>--%>
<%--                <a style="text-decoration: none;" href="OperateProduct?operate=change&id=<%=product.getPid()%>">修改</a>--%>
<%--            </td>--%>
            <td><a style="text-decoration: none;" href="/MyDemo_war_exploded/OperateCount?key=<%=entry.getKey()%>">删除</a></td>
        </tr>
        <%
                }
        %>

    </table>
    </fieldset>

<h3><p>总价为：
<%
    out.write(""+money+"   微信扫码付款");
    out.write("</p></h3>");
%>
 <img style="width: 300px; height: 300px;" src="img/pay.JPG"/>
</body>
</html>
