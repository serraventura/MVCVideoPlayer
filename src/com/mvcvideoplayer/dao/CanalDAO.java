package com.mvcvideoplayer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.mvcvideoplayer.model.Canal;
import com.mvcvideoplayer.model.Video;

public class CanalDAO {

	private Connection conn;
	
	public CanalDAO(Connection conn){
		this.conn = conn;
	}
	

	public List<Canal> listaCanais() throws ServletException {

	    PreparedStatement stmt;
	    ResultSet rst;
	    List<Canal> canais;

        String sql = "select * from canal order by id ";

        try{
        
	        stmt = this.conn.prepareStatement(sql);
	        rst = stmt.executeQuery();
	
	        canais = new ArrayList<Canal>();

	        while (rst.next()) {

	        	Canal canal = new Canal();
	        	
	        	canal.setId(rst.getInt("id"));
	        	canal.setNome(rst.getString("nome"));
	        	canal.setLink(rst.getString("link"));
	        	canal.setTextoTooltip(rst.getString("texto_tooltip"));
	        	canal.setPublicado((rst.getBoolean("publicado")) ? true : false);

	        	canais.add(canal);

	        }
	
	        rst.close();
	        stmt.close();
        
        }catch (SQLException e) {
			throw new ServletException("Erro: Não foi possível listar os canais ao vivo - "+e.getMessage());
		}catch (Exception e) {
			throw new ServletException("Erro: Ocorreu uma exceção. Não foi possível listar os canais ao vivo - "+e.getMessage());
    	}

        return canais;
		
	}
	

	public List<Canal> listaCanaisPublicados() throws ServletException {

	    PreparedStatement stmt;
	    ResultSet rst;
	    List<Canal> canais;

        String sql = "select * from canal where publicado = 1 order by id ";

        try{
        
	        stmt = this.conn.prepareStatement(sql);
	        rst = stmt.executeQuery();
	
	        canais = new ArrayList<Canal>();

	        while (rst.next()) {

	        	Canal canal = new Canal();
	        	
	        	canal.setId(rst.getInt("id"));
	        	canal.setNome(rst.getString("nome"));
	        	canal.setLink(rst.getString("link"));
	        	canal.setTextoTooltip(rst.getString("texto_tooltip"));
	        	canal.setPublicado(true);

	        	canais.add(canal);

	        }
	
	        rst.close();
	        stmt.close();
        
        }catch (SQLException e) {
			throw new ServletException("Erro: Não foi possível listar os canais ao vivo publicados - "+e.getMessage());
		}catch (Exception e) {
			throw new ServletException("Erro: Ocorreu uma exceção. Não foi possível listar os canais ao vivo  publicados - "+e.getMessage());
    	}

        return canais;
		
	}
	

	public void salvarCanal(Canal canal) throws ServletException {

		PreparedStatement stmt;

		StringBuilder sql = new StringBuilder();

        sql.append("Update canal SET ");
        sql.append("nome = ?, ");
        sql.append("link = ?, ");
        sql.append("texto_tooltip = ? ");
        sql.append("Where id = ?");

        try{

	        stmt = conn.prepareStatement(sql.toString());

	        stmt.setString(1, canal.getNome() );
	        stmt.setString(2, canal.getLink() );
	        stmt.setString(3, canal.getTextoTooltip() );
	        stmt.setInt(4, canal.getId() );

	        stmt.executeUpdate();
	        stmt.close();

        }catch (SQLException e) {
			throw new ServletException("Erro: Não foi possível salvar os dados do canal - "+e.getMessage());
		}catch (Exception e) {
			throw new ServletException("Erro: Ocorreu uma exceção. Não foi possível salvar os dados do canal - "+e.getMessage());
    	}

		
	}	

	
	
}
