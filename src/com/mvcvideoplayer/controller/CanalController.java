package com.mvcvideoplayer.controller;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvcvideoplayer.dao.CanalDAO;
import com.mvcvideoplayer.model.Canal;

@Controller
public class CanalController {

	@RequestMapping("listaCanais")
	public String listaCanais(Model model, HttpServletRequest request) throws Exception, ServletException {

		Connection conn = (Connection) request.getAttribute("conn");
		
		CanalDAO dao = new CanalDAO(conn);
		
		model.addAttribute("canais", dao.listaCanais());
		
		return "adm/canal/lista-canais";

	}
	
	@RequestMapping("listaCanaisPublicados")
	public String listaCanaisPublicados(Model model, HttpServletRequest request) throws Exception, ServletException {

		Connection conn = (Connection) request.getAttribute("conn");
		
		CanalDAO dao = new CanalDAO(conn);
		
		model.addAttribute("canais", dao.listaCanaisPublicados());
		
		return "player/lista-canais-publicados";

	}

	@RequestMapping("salvarCanal")
	public String salvarCanal(Model model, HttpServletRequest request, Canal canal) throws Exception, ServletException{
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		CanalDAO dao = new CanalDAO(conn);
		dao.salvarCanal(canal);
		
		return "redirect:listaCanais";
	}	
	
}
