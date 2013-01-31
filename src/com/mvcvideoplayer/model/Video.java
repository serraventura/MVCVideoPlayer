package com.mvcvideoplayer.model;

public class Video {
	
	private int id;
	private String nome;
	private String extensao;
	private int tamanhoArquivo;
	private String duracao;
	private String dataCriacao;
	private String nomeAntigoArquivo;
	private int width;
	private int height;
	private int framerate;
	private String descricao;
	private boolean publicado;
	private String chamada;

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
	
	public String getExtensao() {
		return extensao;
	}
	public void setExtensao(String extensao) {
		
		if(extensao == null){
			extensao = "";
		}
		
		if(extensao.length() > 5){
			extensao = extensao.substring(0, 5);
		}
		
		this.extensao = extensao;
	}
	
	public int getTamanhoArquivo() {
		return tamanhoArquivo;
	}
	public void setTamanhoArquivo(int tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}
	
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		
		if(dataCriacao == null){
			dataCriacao = "";
		}
		
		if(dataCriacao.length() > 25){
			dataCriacao = dataCriacao.substring(0, 25);
		}
		
		this.dataCriacao = dataCriacao;
	}
	
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		
		if(duracao == null){
			duracao = "";
		}
		
		if(duracao.length() > 15){
			duracao = duracao.substring(0, 15);
		}
		
		this.duracao = duracao;
	}
	
	public String getNomeAntigoArquivo() {
		return nomeAntigoArquivo;
	}
	public void setNomeAntigoArquivo(String nomeAntigoArquivo) {
		
		if(nomeAntigoArquivo == null){
			nomeAntigoArquivo = "";
		}
		
		if(nomeAntigoArquivo.length() > 255){
			nomeAntigoArquivo = nomeAntigoArquivo.substring(0, 255);
		}
		
		this.nomeAntigoArquivo = nomeAntigoArquivo;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getFramerate() {
		return framerate;
	}
	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}
	
	public String getDescricao() {
		//return descricao.replaceAll("\n", "<br />");
		return descricao;
	}
	public void setDescricao(String descricao) {
		
		if(descricao == null){
			descricao = "";
		}
		
		if(descricao.length() > 255){
			descricao = descricao.substring(0, 255);
		}
		
		this.descricao = descricao;
	}
	
	public boolean isPublicado() {
		return publicado;
	}
	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	
	public String getChamada() {
		return chamada;
	}
	public void setChamada(String chamada) {
		
		if(chamada == null){
			chamada = "";
		}
		
		if(chamada.length() > 140){
			chamada = chamada.substring(0, 140);
		}
		
		this.chamada = chamada;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chamada == null) ? 0 : chamada.hashCode());
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((duracao == null) ? 0 : duracao.hashCode());
		result = prime * result
				+ ((extensao == null) ? 0 : extensao.hashCode());
		result = prime * result + framerate;
		result = prime * result + height;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime
				* result
				+ ((nomeAntigoArquivo == null) ? 0 : nomeAntigoArquivo
						.hashCode());
		result = prime * result + (publicado ? 1231 : 1237);
		result = prime * result + tamanhoArquivo;
		result = prime * result + width;
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
		Video other = (Video) obj;
		if (chamada == null) {
			if (other.chamada != null)
				return false;
		} else if (!chamada.equals(other.chamada))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (duracao == null) {
			if (other.duracao != null)
				return false;
		} else if (!duracao.equals(other.duracao))
			return false;
		if (extensao == null) {
			if (other.extensao != null)
				return false;
		} else if (!extensao.equals(other.extensao))
			return false;
		if (framerate != other.framerate)
			return false;
		if (height != other.height)
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeAntigoArquivo == null) {
			if (other.nomeAntigoArquivo != null)
				return false;
		} else if (!nomeAntigoArquivo.equals(other.nomeAntigoArquivo))
			return false;
		if (publicado != other.publicado)
			return false;
		if (tamanhoArquivo != other.tamanhoArquivo)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	

}
