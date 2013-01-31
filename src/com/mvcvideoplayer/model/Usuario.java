package com.mvcvideoplayer.model;

public class Usuario {
	
	private String login;
	private String senha;
	private String nome;
	private boolean ativo;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		
		if(login == null){
			login = "";
		}
		
		if(login.length() > 45){
			login = login.substring(0, 45);
		}
		
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		
		if(senha == null){
			senha = "";
		}
		
		if(senha.length() > 45){
			senha = senha.substring(0, 45);
		}
		
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		
		if(nome == null){
			nome = "";
		}
		
		if(nome.length() > 45){
			nome = nome.substring(0, 45);
		}
		
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}


	
}
