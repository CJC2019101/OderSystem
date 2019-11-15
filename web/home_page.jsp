<%@ page import="java.util.List" %>
<%@ page import="com.cjf.domain.Product" %><%--
  Created by IntelliJ IDEA.
  User: 陈剑非
  Date: 2019/9/29
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js" ></script>
    <style>
        .daohang{
            width: 25%;
            height: 110px;
            float: left;
            background-color: mediumblue;

        }
        .menu{
            font-size: large;
            text-decoration: none;/*去除下滑线*/
            color: white;
            line-height: 110px;
        }
        .afood{
            text-decoration: none;
            color:darkslategrey;
        }
        .Img{
            float: left;
            width: 100px;
            height: 100px;
            margin-left: 10px;
        }
        .specefic_aim{
            float: left;
            text-align: center;
            margin-top: 10px;
            margin-left: 10px;
        }
    </style>
    <script>
        var sorts=["美食","特色菜系","甜品饮品","小吃夜宵","医药健康","滋身养颜","早餐","晚餐"];
        $(function(){		//文档加载完成事件

            // 商家分类的具体信息
            $.each(sorts, function(i,n) {
                $("#store_info").append("<a href='#' class='afood' >"+n+"</a> ");
            });
            //为每个分类添加识别    标志
            for (var i = 0; i < sorts.length; i++) {
                var $a =$("#store_info a")[i]
                $a.name=i;
            }

            //商家分类菜品信息的显示
            //确定事件		具体
            $(".afood").click(function(){
                if(this.name==0)
                {
                    $("#commodity0_info").show();
                    $("#commodity1_info").hide();
                    $("#commodity2_info").hide();
                } else if(this.name==1){
                    $("#commodity0_info").hide();
                    $("#commodity1_info").show();
                    $("#commodity2_info").hide();
                }else if(this.name==2){
                    $("#commodity0_info").hide();
                    $("#commodity1_info").hide();
                    $("#commodity2_info").show();
                }
            });

        });
    </script>
</head>
<body>
<!--导航栏-->
<div >
    <div class="daohang" ><img src="img/small_logo.png" width="92%"  height="110px"/></div>
    <div  class="daohang"><a href="#" class="menu">首页</a></div>
    <div  class="daohang"><a href="#" class="menu">我的订单</a></div>
    <div  class="daohang">
        <a href="/MyDemo_war_exploded/login.jsp#username" class="menu">登录</a>
        <font size="4" color="white">/</font>
        <a href="/MyDemo_war_exploded/register.jsp#username"  class="menu">注册</a>
    </div>

</div>
<!--清除浮动-->
<div style="clear: both;"></div>

<!--搜索栏-->
<div style="width: 100%; height: 30px;">
    <div style="float: right;"><input type="text" placeholder="搜索商家美食" /></div>
</div>

<!--推荐栏-->
<div >
    <!--推荐栏描述-->
    <div style="float: left; width: 25%; border: 3px solid darkgrey; height: 80px; ">
        <font size="4" color="darkgrey " style="padding-left: 80px; ">商家分类:</font>
    </div>
    <!--推荐栏详细信息-->
    <div style="float: right; width: 74%; height:80px; border: 3px solid darkgrey;">
        <div id="store_info" style="float: left; margin-left: 30px;" ></div>

    </div>
</div>

<!--商品推荐-->
    <form action="/MyDemo_war_exploded/CountServlet">
        <div style="width: 100%;">
        <%
            List<Product> list= (List) request.getAttribute("products");
            for (Product p:list
                 ) {
                out.write("<div style='float: left;'>");
                out.write(" <div style='border: 1px solid red; height: 120px; width: 220px;'>");
                out.write("<div><img src='"+p.getPimg()+"' style='height: 80%; width: 100%;'/></div>");
                out.write("<div style='height: 20%; width: 100%;'>"+p.getPprice()+"元/份  <input type='checkbox'  name='shop' value='"+p.getPname()+"_"+p.getPprice()+"'/>"+p.getPname()+"</div>");
                out.write(" </div>");
                out.write(" </div>");
            }
        %> <div style="float: left; width: 100%; text-align: center;"><input type="submit" value="结账"/></div>
        </div>

    </form>
<div style="clear: both;"></div>
<!--相关法律法规-->
<%--导入页脚--%>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
