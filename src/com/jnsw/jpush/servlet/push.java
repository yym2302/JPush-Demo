package com.jnsw.jpush.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.jnsw.jpush.JPush;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import cn.jpush.*;

/**
 * Servlet implementation class tuisong
 */
public class push extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String appKey ="e3d98b312def78016d80f0da";
	private static final String masterSecret = "8887af72e116c33a1ea77f68";
	
    public push() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("远程访问测试通过");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		response.setHeader("Access-Control-Allow-Origin", "*");//授权的源控制
		response.setHeader("Access-Control-Allow-Methods", "*");//控制是否开启与Ajax的Cookie提交方式
		response.setHeader("Access-Control-Max-Age", "600");//授权的时间
		response.setHeader("Access-Control-Allow-Credentials", "true");//
		
		String _m = request.getParameter("_m");//传过来的参数

		JSONObject reJson = new JSONObject();//返回json格式
		reJson.put("success", false);//默认失败
		
		try {
			JSONObject json = new JSONObject();
			json = JPush.testSendPush(appKey, masterSecret,_m);
			reJson.put("value", json);
			reJson.put("success", true);//返回成功
		} catch (Exception exp) {
			// TODO: handle exception
			String msg = exp.getMessage();
			reJson.put("msg","error request");
			reJson.put("success", false);
			exp.printStackTrace();
		}finally{
			PrintWriter out =response.getWriter();
			out.write(reJson.toString());
			out.flush();
			out.close();
		}
	}

}
