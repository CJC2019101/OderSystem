<%--
  Created by IntelliJ IDEA.
  User: 陈剑非
  Date: 2019/9/29
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script type="text/javascript" src="js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="js/regutils.js"></script>
    <script>


        $(function(){		//文档加载完成事件

            /*检验账号长度以及组成*/
            $("#login_username").keyup(function(){
                if($("#login_username").val().length>6&&$("#login_username").val().length<13)		//长度大于6且小于13
                {

                    if(isNumberOrLetter($("#login_username").val()))
                    {
                        $("#username_message").html("账号符合规范");
                    }else{
                        $("#username_message").html("账号只能由英文字母和数字组成");
                    }

                }else{
                    $("#username_message").html("账号长度必须大于6且小于13");
                }
            });

            /*检验密码长度以及组成只能是数字 、 字母、特殊字符 、下划线*/
            $("#login_password").keyup(function(){
                if($("#login_password").val().length>6&&$("#login_password").val().length<13)		//长度大于6且小于13
                {

                    if(isPassword($("#login_password").val()))
                    {
                        $("#password_message").html("密码符合规范");
                    }else{
                        $("#password_message").html("密码只能由英文字母、数字、少许特殊字符组成");
                    }

                }else{
                    $("#password_message").html("密码长度必须大于6且小于13");
                }
            });


        });

    </script>
</head>
<body background="img/regist_bg.jpg">

<div style="background: antiquewhite; border: 5px solid darkgray; margin-top: 200px; margin-left: 400px;
        width: 600px; height: 200px;">
    <%--
    一个块元素装表单，提交表单指向LoginServlet类。
    --%>
<%--    <div style="text-align: center;">--%>
        <form action="/MyDemo_war_exploded/login">
            <table  style="width: 100%; /*border: 1px solid black; */ margin-top: 48px; text-align: center;"  >
                <tr >
                    <td style="/*border: 1px solid black; */width: 50%; "><%=request.getAttribute("info")==null?"":request.getAttribute("info")%></td>
                    <td style="/*border: 1px solid black; */width: 50%;"></td>
                </tr>
                <tr >
                    <td style="/*border: 1px solid black;*/ width: 50%;">账号：<input type="text" placeholder="请输入用户名" id="login_username" name="username" required/></td>
                    <td style="/*border: 1px solid black;*/ width: 50%;"><div id="username_message"></div></td>
                </tr>
                <tr >
                    <td style="/*border: 1px solid black;*/ width: 50%;">密码：<input type="password" placeholder="请输入密码" id="login_password" name="password" required/></td>
                    <td style="/*border: 1px solid black;*/ width: 50%;"><div id="password_message"></div></td>
                </tr>
                <tr>
                    <td style="/*border: 1px solid black;*/ width: 50%;"><input style="margin-right: 40px;"  type="submit" name="login" value="登录"/><input type="submit" name="login" value="管理食品"/></td>
                    <td style="/*border: 1px solid black;*/ width: 50%;"></td>
                </tr>
            </table>
        </form>

 <%--   </div>--%>

</div>
<%--添加页脚--%>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
