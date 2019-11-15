<%--
  Created by IntelliJ IDEA.
  User: 陈剑非
  Date: 2019/9/29
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
    <script type="text/javascript" src="js/regutils.js" ></script>
    <script>
        var provinces =[
            ["深圳市","东菀市","惠州市","广州市"],
            ["长沙市","岳阳市","株洲市","湘潭市"],
            ["厦门市","福州市","漳州市","泉州市"],
            ["江津区","永川区","合川区","万州区"]
        ];


        $(function(){		//文档加载完成事件

            $("#province").change(function(){		//确定事件		change
                var cities=provinces[this.value];
                $("#city").empty();
                $.each(cities, function(i,n) {
                    $("<option>"+n+"</option>").appendTo("#city");
                });
            });



            /*检验账号长度以及组成*/
            $("#username").keyup(function(){
                if($("#username").val().length>6&&$("#username").val().length<13)		//长度大于6且小于13
                {

                    if(isNumberOrLetter($("#username").val()))
                    {
                        $("#showname").html("账号符合规范");
                    }else{
                        $("#showname").html("账号只能由英文字母和数字组成");
                    }

                }else{
                    $("#showname").html("账号长度必须大于6且小于13");
                }
            });

            /*检验密码长度以及组成只能是数字 、 字母、特殊字符 、下划线*/
            $("#password").keyup(function(){
                if($("#password").val().length>6&&$("#password").val().length<13)		//长度大于6且小于13
                {

                    if(isPassword($("#password").val()))
                    {
                        $("#showpassword").html("密码符合规范");
                    }else{
                        $("#showpassword").html("密码只能由英文字母、数字、少许特殊字符组成");
                    }

                }else{
                    $("#showpassword").html("密码长度必须大于6且小于13");
                }
            });
        });
    </script>
</head>
<body background="img/regist_bg.jpg">
<div style="  background-color: white; border: 5px solid darkgray; width: 500px; height: 400px;
				margin-top: 160px; margin-left: 450px; text-align: center;">
    <form action="RegisterServlet">
        <table  style="width: 100%; margin-top: 90px;/*border: 1px solid black;*/"  >
            <tr >
                <td style="/*border: 1px solid black;*/ width: 50%;"><%=request.getAttribute("register_info")==null?"":request.getAttribute("register_info")%></td>
                <td style="/*border: 1px solid black;*/ width: 50%;"></td>
            </tr> <tr >
                <td style="/*border: 1px solid black;*/ width: 50%;">账号：<input type="text"  placeholder="请输入账号" id="username" name="username" required/></td>
                <td style="/*border: 1px solid black;*/ width: 50%;"><div id="showname"></div></td>
            </tr>
            <tr >
                <td style="/*border: 1px solid black;*/ width: 50%;">密码：<input type="password" placeholder="请输入密码" id="password" name="password" required/></td>
                <td style="/*border: 1px solid black;*/ width: 50%;"><div id="showpassword"></div></td>
            </tr>
            <tr >
                <td style="/*border: 1px solid black;*/ width: 50%;">确认密码：<input type="password" placeholder="请再次输入密码" size="15" id="repassword" required/></td>
                <td style="/*border: 1px solid black;*/ width: 50%;"></td>
            </tr>

            <tr >
                <td colspan="2" style="/*border: 1px solid black;*/ width: 100%;">请选择城市信息：
                    <select id="province" name="Province" required>
                        <option value="-1">--- 请选择 ---</option>
                        <option value="0">--- 广东 ---</option>
                        <option value="1">--- 湖南 ---</option>
                        <option value="2">--- 福建 ---</option>
                        <option value="3">--- 重庆 ---</option>
                    </select>
                    <select id="city" name="City">
                    </select>
                </td>

            </tr>
            <tr>
                <td style="/*border: 1px solid black;*/ width: 50%; "><input  type="submit" value="注册"/></td>
                <td style="/*border: 1px solid black;*/ width: 50%;"></td>
            </tr>
        </table>
    </form>
</div>

<%--导入页脚--%>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
