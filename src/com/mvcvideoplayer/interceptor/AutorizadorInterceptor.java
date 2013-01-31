package com.mvcvideoplayer.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		boolean retorno = true;
		String uri = request.getRequestURI();

		//if(uri.endsWith("loginForm") || uri.endsWith("efetuaLogin") || uri.endsWith("player") || uri.endsWith("player-video.js") || uri.endsWith("player.swf")){
			//return true;
		//}

		if(uri.endsWith("listaVideosPendentes") || uri.endsWith("listaVideosPublicados") || uri.endsWith("publicarVideo") || uri.endsWith("salvarDescricaoVideo") || uri.endsWith("mainAdmin") || uri.endsWith("listaCanais")){
			
			if(request.getSession().getAttribute("usuarioLogado") != null){
				retorno = true;
			}else{
				retorno = false;
			}
			
			//response.sendRedirect("loginForm");
			//return false;
		}

		//if(request.getSession().getAttribute("usuarioLogado") != null){
			//response.sendRedirect("loginForm");
			//return true;
		//}
		
		if(!retorno){
			response.sendRedirect("loginForm");
		}
		
		return retorno;

	}
	
	/*
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	*/
}
