package com.mvcvideoplayer.interceptor;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mvcvideoplayer.ConnectionFactory;

public class ConnectionInterceptor extends HandlerInterceptorAdapter {
	
	private Connection conn;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		System.out.println("preHandle: Abrindo Conexao BD");
		
        conn = new ConnectionFactory().getConnection();
        request.setAttribute("conn", conn);

		return true;
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		System.out.println("postHandle: Fechando conexao bd");
		
        conn.close();
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		//System.out.println("afterCompletion: Fechando conexao bd");
		
        //conn.close();
		
	}	

}
