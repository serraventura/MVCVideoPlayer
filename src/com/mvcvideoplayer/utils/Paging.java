package com.mvcvideoplayer.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mvcvideoplayer.model.Video;

public class Paging{

    private int limitePagina;
    private int totalPagina;
    private String paginaCorrente;
    private int valorLimite;
    private String tabela;
    private String query;
    private String onclick;
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rst;

    public int getLimitePagina() {
        return limitePagina;
    }

    public void setLimitePagina(int LimitePagina) {
        this.limitePagina = LimitePagina;
    }

    public int getTotalPagina() {
        return totalPagina;
    }

    public void setTotalPagina(int TotalPagina) {
        this.totalPagina = TotalPagina;
    }

    public String getPaginaCorrente() {
        return paginaCorrente;
    }

    public void setPaginaCorrente(String PaginaCorrente) {

        if(PaginaCorrente == null || PaginaCorrente.equals("")){
            PaginaCorrente = "1";
        }

        this.paginaCorrente = PaginaCorrente;
    }

    public int getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(int ValorLimite) {
        this.valorLimite = ValorLimite;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String Tabela) {
        this.tabela = Tabela;
    }
    

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
    

    public Paging(Connection conn, String tabela, String query, String filtro) throws Exception{

        this.conn = conn;
        this.query = query;

        totalRegistro(tabela, filtro);
        
        //if(getOnclick().isEmpty()){
        	//setOnclick("paginacao");
        //}
        
    }
    
    private void totalRegistro(String tabela, String filtro) throws Exception{

        // obtém a quantidade de registros
        stmt = conn.prepareStatement("SELECT COUNT(*) AS total FROM "+tabela+" "+filtro);
        rst = stmt.executeQuery();
        
        if(rst.next()){
            this.setTotalPagina(rst.getInt("total"));
        }

        rst.close();
        stmt.close();

    }
    
    public List<Video> listarDados() throws Exception{

        this.setValorLimite((Integer.parseInt(this.getPaginaCorrente()) * this.getLimitePagina()) - this.getLimitePagina());

        stmt = conn.prepareStatement(this.query+" LIMIT " + this.getValorLimite() + ", " + this.getLimitePagina());
        rst = stmt.executeQuery();

        
        List lista = new ArrayList<Video>();
        
        while (rst.next()) {        	

        	Video video = new Video();

        	video.setId(rst.getInt("id"));
        	video.setDescricao(rst.getString("descricao"));
        	video.setDataCriacao(rst.getString("data_criacao"));
        	video.setDuracao(rst.getString("duracao"));
        	video.setChamada(rst.getString("chamada"));

        	lista.add(video);
        }        
        
        stmt.close();
        rst.close();
        
        return lista;
        

    }

    public String anterior() throws Exception{

        int anterior;
        String result = "";

        int paginaCorrente = Integer.parseInt(this.getPaginaCorrente());

        if(paginaCorrente > 1){  
            anterior = paginaCorrente - 1; 
            result = "<div class='number' onclick='"+getOnclick()+"("+anterior+")' alt='P‡gina anterior'> << </div>";
        }else{
            result = "<div class='paginaDesabilitada' alt='P‡gina anterior'> << </div>";
        }
        
        return(result);
        
    }
    

    public String paginas() throws Exception{

        int numOfPages = this.getTotalPagina() / this.getLimitePagina();
        int i;
        String result = "";
        int paginaCorrente = Integer.parseInt(this.getPaginaCorrente());

        for(i = 1; i <= numOfPages; i++){
            if(i == paginaCorrente){ 
                result += "<div class='numCorrente' >" + i + "</div> ";
            }else{
                result += "<div class='number' onclick='"+getOnclick()+"("+i+")'>" + i + "</div> ";
            }
        }

        if((this.getTotalPagina() % this.getLimitePagina()) != 0){

            if(i == paginaCorrente){
                result += i + " ";
            }else{
                result += "<div class='number' onclick='"+getOnclick()+"("+i+")'>" + i + "</div> ";
            }
        }

        return(result);

    }
  
    public String proxima() throws Exception{

      int proxima;
      String result = "";
      int paginaCorrente = Integer.parseInt(this.getPaginaCorrente());

      if((this.getTotalPagina() - (this.getLimitePagina() * paginaCorrente)) > 0){ 
        proxima = paginaCorrente + 1;
        result = "<div class='number' onclick='"+getOnclick()+"("+proxima+")' alt='Pr—xima p‡gina'> >> </div>";
      }else{
        result = "<div class='paginaDesabilitada' alt='Pr—xima p‡gina'> >> </div>";
      }

      return(result);

    }


}
