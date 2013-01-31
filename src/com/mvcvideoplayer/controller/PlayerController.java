package com.mvcvideoplayer.controller;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvcvideoplayer.utils.Paging;

@Controller
public class PlayerController {

	@RequestMapping("s1")
	public String s1(){return "player/displayAoVivo/s1";}
	
	@RequestMapping("s2")
	public String s2(){return "player/displayAoVivo/s2";}
	
	@RequestMapping("s3")
	public String s3(){return "player/displayAoVivo/s3";}
	
	@RequestMapping("s4")
	public String s4(){return "player/displayAoVivo/s4";}
	
	@RequestMapping("e1")
	public String e1(){return "player/displayAoVivo/e1";}
	
	@RequestMapping("e2")
	public String e2(){return "player/displayAoVivo/e2";}
	
	@RequestMapping("displayVideo")
	public String display(){
		//int x = 0/0;
		return "player/display-video";
	}

	@RequestMapping("player")
	public String playerVideo(Model model, HttpServletRequest request) throws Exception, ServletException {

		String videoParametro, tipoVideoParametro, idVideoVivoParametro;
		
		videoParametro = request.getParameter("v");
		tipoVideoParametro = request.getParameter("t");
		idVideoVivoParametro = request.getParameter("idvivo");
		
		videoParametro = ((videoParametro == null)) ? "" : videoParametro;
		tipoVideoParametro = ((tipoVideoParametro == null)) ? "" : tipoVideoParametro;
		idVideoVivoParametro = ((idVideoVivoParametro == null)) ? "" : idVideoVivoParametro;
		
		model.addAttribute("videoParametro", videoParametro);
		model.addAttribute("tipoVideoParametro", tipoVideoParametro);
		model.addAttribute("idVideoVivoParametro", idVideoVivoParametro);
		
		return "player/player-video";

	}
	
	@RequestMapping("listaVideoPlayer")
	public String listaVideosPaginados(Model model, HttpServletRequest request)  throws Exception, ServletException{

		//int x = 0/0;

		Connection conn = (Connection) request.getAttribute("conn");

		String sql = "SELECT * FROM video WHERE publicado = 1 ORDER BY id DESC";

        Paging p = new Paging(conn, "video", sql, "where publicado = 1");

        p.setPaginaCorrente(request.getParameter("pag"));
        p.setLimitePagina(5);
        p.setOnclick("carregaVideosOnDemand");

        model.addAttribute("totalRegistro", p.getTotalPagina());
        model.addAttribute("anterior", p.anterior());
        model.addAttribute("paginacao", p.paginas());
        model.addAttribute("proxima", p.proxima());
        model.addAttribute("listaVideos", p.listarDados());

		return "player/player-lista-video-paginados";
	}

}
