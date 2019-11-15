package com.cjf.servlet;

import com.cjf.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 注意：上传文件必须添加@MultipartConfig()可以设置上传文件的大小
 */
 //在request域中获取 文件接口对象（part）。Part part=request.getPart("file");
 //使用 文件接口对象（part）。获取请求图片的后缀名信息。
 //使用 文件接口对象（part），将文件写入项目指定的文件夹中。
@WebServlet(name = "upload", urlPatterns = "/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet{


    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //解决中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String pname=null;
        double pprice=0;
        String pdesc=null;
        String pimg=null;
        try {
            //获取食品名称
            pname=request.getParameter("pname");
            //获取食品价格
            pprice=new Double(request.getParameter("pprice"));
            //获取文件描述信息
            pdesc=request.getParameter("pdesc");
            //获取上传的文件
            Part part=request.getPart("file");
            //获取请求的信息
            String name=part.getHeader("content-disposition");
            //System.out.println(name);//测试使用
            //System.out.println(desc);//

            //获取上传文件的目录，获取项目的绝对路径
            String root=request.getServletContext().getRealPath("/upload");
            System.out.println("测试上传文件的路径："+root);

            //字符串分割获取文件的后缀
            String str=name.substring(name.lastIndexOf("."), name.length()-1);
            System.out.println("测试获取文件的后缀："+str);

            //随机一个不重名的文件名
            String path=UUID.randomUUID().toString()+str;
            //获取图片路径
            pimg=getServletContext().getContextPath()+"/upload/"+path;
            System.out.println("pimg："+pimg);

            //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
            String filename=root+"\\"+path;
            System.out.println("测试产生新的文件名："+filename);


            //上传文件到指定目录，不想上传文件就不调用这个
            part.write(filename);
            request.setAttribute("info", "上传文件成功");//向request域添加文件上传的提示信息。
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "上传文件失败");
        }
        int rows=InsertData(pname,pprice,pdesc,pimg);
        if (rows==0){
            request.setAttribute("info", "插入数据失败");
        }else{
            request.getRequestDispatcher("/OperateServlet").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    public int InsertData(String pname,double pprice,String pdesc,String pimg){
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={UUID.randomUUID().toString(),pname,pprice,pdesc,pimg};
        String sql="insert into product values(?,?,?,?,?)";
        int rows=0;
        try {
           rows= qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("数据库连接失败");
        }
        return rows;
    }
}