package com.mvcvideoplayer.model;

public class Canal {
	
	private int id;
	private String nome;
	private String link;
	private String textoTooltip;
	private boolean publicado;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		
		if(link == null){
			link = "";
		}
		
		if(link.length() > 255){
			link = link.substring(0, 255);
		}
		
		this.link = link;
	}
	
	public String getTextoTooltip() {
		return textoTooltip;
	}
	public void setTextoTooltip(String textoTooltip) {
		
		if(textoTooltip == null){
			textoTooltip = "";
		}
		
		if(textoTooltip.length() > 140){
			textoTooltip = textoTooltip.substring(0, 140);
		}
		
		this.textoTooltip = textoTooltip;
	}
	
	
	public boolean isPublicado() {
		return publicado;
	}
	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (publicado ? 1231 : 1237);
		result = prime * result
				+ ((textoTooltip == null) ? 0 : textoTooltip.hashCode());
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
		Canal other = (Canal) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (publicado != other.publicado)
			return false;
		if (textoTooltip == null) {
			if (other.textoTooltip != null)
				return false;
		} else if (!textoTooltip.equals(other.textoTooltip))
			return false;
		return true;
	}

}
