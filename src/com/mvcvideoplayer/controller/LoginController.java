package com.mvcvideoplayer.controller;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvcvideoplayer.dao.UsuarioDAO;
import com.mvcvideoplayer.model.Usuario;

@Controller
public class LoginController {

	@RequestMapping("loginForm")
	public String loginForm(){
		return "login/formulario-login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:loginForm";
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session, HttpServletRequest request) throws ServletException {
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		if(new UsuarioDAO(conn).existeUsuario(usuario)){
			session.setAttribute("usuarioLogado", usuario);
			return "adm/main-adm";
		}
		
		return "redirect:loginForm";
	}
	
	@RequestMapping("mainAdmin")
	public String mainAdmin(){
		return "adm/main-adm";
	}
	
}
