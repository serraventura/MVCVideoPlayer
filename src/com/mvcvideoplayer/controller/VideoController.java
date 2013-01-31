package com.mvcvideoplayer.controller;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvcvideoplayer.dao.VideoDAO;
import com.mvcvideoplayer.model.Video;

@Controller
public class VideoController {

	@RequestMapping("listaVideosPendentes")
	public String listaPendentes(Model model, HttpServletRequest request) throws Exception, ServletException {
	
		Connection conn = (Connection) request.getAttribute("conn");
		String path = request.getSession().getServletContext().getInitParameter("pathUploadVideos");
		
		VideoDAO dao = new VideoDAO(conn, request);
		
		model.addAttribute("videos", dao.listaVideosPendentes(path));
		
		return "adm/video/lista-videos-pendentes";

	}
	
	@RequestMapping("listaVideosPublicados")
	public String listaPublicados(Model model, HttpServletRequest request) throws Exception, ServletException {

		Connection conn = (Connection) request.getAttribute("conn");
		
		VideoDAO dao = new VideoDAO(conn, request);
		
		model.addAttribute("videos", dao.listaVideosPublicados());
		
		return "adm/video/lista-videos-publicados";

	}
	
	@RequestMapping("publicarVideo")
	public String publicar(Model model, HttpServletRequest request, Video video) throws Exception, ServletException{
		
		Connection conn = (Connection) request.getAttribute("conn");
		String path = request.getSession().getServletContext().getInitParameter("pathUploadVideos");
		
		VideoDAO dao = new VideoDAO(conn, request);
		dao.publicar(path, video);

		return "redirect:listaVideosPendentes";
	}

	@RequestMapping("salvarDescricaoVideo")
	public String salvarDescricao(Model model, HttpServletRequest request, Video video) throws Exception, ServletException{
		
		Connection conn = (Connection) request.getAttribute("conn");
		
		VideoDAO dao = new VideoDAO(conn);
		dao.salvarDescricao(video);
		
		return "redirect:listaVideosPublicados";
	}
	
	
	
}
