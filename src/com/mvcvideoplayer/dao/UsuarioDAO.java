package com.mvcvideoplayer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.mvcvideoplayer.model.Usuario;



public class UsuarioDAO {

	private Connection conn;
	
	public UsuarioDAO(Connection conn){
		this.conn = conn;
	}
	
	public boolean existeUsuario(Usuario usuario) throws ServletException {
		
		if(usuario == null) {
			throw new IllegalArgumentException("Objeto usu‡rio n‹o deve ser nulo");
		}
		
		try {
			
			PreparedStatement stmt = this.conn.prepareStatement("select 1 from usuario where login = ? and senha = ? and ativo = 1");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();

			return encontrado;
			
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		
	}
	
}
