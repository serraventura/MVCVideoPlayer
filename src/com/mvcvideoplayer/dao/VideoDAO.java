package com.mvcvideoplayer.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mvcvideoplayer.model.Video;
import com.mvcvideoplayer.utils.FfmpegUtils;
import com.mvcvideoplayer.utils.FormataData;

public class VideoDAO {
	
	private static AmazonS3 s3client;
	private String extensoesPermitidas;
	private Connection conn;
	
	private int ultimoIdCadastrado;
	private String extensaoVideoCorrente;
	private String diretorioVideosPublicados;
	private String diretorioBaseInstalacaoFfmpeg;
	private String S3Buckets;

	private Logger logger;
	
	public int getUltimoIdCadastrado() {
		return ultimoIdCadastrado;
	}

	public void setUltimoIdCadastrado(int ultimoIdCadastrado) {
		this.ultimoIdCadastrado = ultimoIdCadastrado;
	}
	
	public String getExtensaoVideoCorrente() {
		return extensaoVideoCorrente;
	}

	public VideoDAO(Connection conn){
		logger = Logger.getLogger("MVCVideoPlayer");
		this.conn = conn;		
	}
	
	public VideoDAO(Connection conn, HttpServletRequest request){
		
		logger = Logger.getLogger("MVCVideoPlayer");
		
		this.conn = conn;
		this.extensoesPermitidas = request.getSession().getServletContext().getInitParameter("extensoesPermitidas");
		this.extensaoVideoCorrente = request.getSession().getServletContext().getInitParameter("extensaoVideoCorrente");
		this.diretorioVideosPublicados = request.getSession().getServletContext().getInitParameter("diretorioVideosPublicados");
		this.diretorioBaseInstalacaoFfmpeg = request.getSession().getServletContext().getInitParameter("diretorioBaseInstalacaoFfmpeg");
		this.S3Buckets = request.getSession().getServletContext().getInitParameter("S3Buckets");
		
		String AWSCredentialsKeyId = request.getSession().getServletContext().getInitParameter("AWSCredentialsKeyId");
		String AWSCredentialsSecretKey = request.getSession().getServletContext().getInitParameter("AWSCredentialsSecretKey");

		AWSCredentials creds = new BasicAWSCredentials(AWSCredentialsKeyId, AWSCredentialsSecretKey);
		s3client = new AmazonS3Client(creds);
		
	}

	public List<Video> listaVideosPendentes(String path) throws ServletException {
		
		List<Video> listaArquivosVideoPendente;
		
		try{
		
			listaArquivosVideoPendente = new ArrayList<Video>();
			File arquivosVideos = new File(path);
	
			for (File f : arquivosVideos.listFiles()) {

				if(f.isFile() && isPermission(f.getName())){
	
					listaArquivosVideoPendente.add(populaVideo(f));
	
				}
	
			}
		
		}catch (IOException e) {
			throw new ServletException(e.getMessage());
		}catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		return listaArquivosVideoPendente;
		
	}

	public List<Video> listaVideosPublicados() throws ServletException {

		//ObjectMetadata x = s3client.getObjectMetadata("videos.publicados", "86.flv");
		
		//boolean ret = S3Exists(s3client, "videos.publicados", "86.flvdsdadada");
		
		//System.out.println("##### retorno: "+ret);
		//System.out.println("############Tamanho Arquivo: "+x.getContentLength());
		
	    PreparedStatement stmt;
	    ResultSet rst;
	    List<Video> videos;

        String sql = "select * from video order by id DESC";

        try{
        
	        stmt = this.conn.prepareStatement(sql);
	        rst = stmt.executeQuery();
	
	        videos = new ArrayList<Video>();

	        while (rst.next()) {

	            Video video = new Video();
	
	            video.setId(rst.getInt("id"));
	            video.setNome(rst.getString("nome"));
	            //video.setExtensao(rst.getString("extensao"));
	            video.setTamanhoArquivo(rst.getInt("tamanho_arquivo"));
	            video.setDuracao(rst.getString("duracao"));
	            video.setDataCriacao(rst.getString("data_criacao"));
	            video.setNomeAntigoArquivo(rst.getString("nome_antigo_arquivo"));
	            video.setWidth(rst.getInt("width"));
	            video.setHeight(rst.getInt("height"));
	            video.setFramerate(rst.getInt("framerate"));
	            video.setDescricao(rst.getString("descricao"));
	            video.setChamada(rst.getString("chamada"));
	            
	            if(S3Exists(s3client, this.S3Buckets, rst.getInt("id")+this.extensaoVideoCorrente)){
	            	video.setPublicado(true);	
	            }else{
	            	video.setPublicado(false);
	            }

	            videos.add(video);

	        }
	
	        rst.close();
	        stmt.close();
        
        }catch (SQLException e) {
			throw new ServletException("Erro: N‹o foi poss’vel listar os arquivos de v’deo publicados - "+e.getMessage());
		}catch (Exception e) {
			throw new ServletException("Erro: Ocorreu uma exce‹o. N‹o foi poss’vel listar os arquivos de v’deo publicados - "+e.getMessage());
    	}

        return(videos);
		
	}
	
	public boolean publicar(String path, Video video)  throws ServletException {

		boolean retorno = true;
		
		try{
		
			if(inserir(video)){
				if(moverVideos(path, this.diretorioVideosPublicados, video, getUltimoIdCadastrado())){

					// caminho + diretorio de v’deos publicados + œltimo ID cadastrado + extensao
					// /aaa/bbb/publicado/22.flv
					String pathAbsolutoVideoPublicado = path+this.diretorioVideosPublicados+this.getUltimoIdCadastrado()+this.extensaoVideoCorrente;
					
					Video videoMetadados = capturaMetadados(pathAbsolutoVideoPublicado);
					videoMetadados.setNome(this.getUltimoIdCadastrado()+this.extensaoVideoCorrente);
					videoMetadados.setNomeAntigoArquivo(video.getNomeAntigoArquivo());
					videoMetadados.setDataCriacao(video.getDataCriacao());
					videoMetadados.setPublicado(false);

					geraThumbnail(path+this.diretorioVideosPublicados, videoMetadados);

					inserirMetadadosVideoPublicado(videoMetadados);
					
					if(uploadVideoPublicadoS3(pathAbsolutoVideoPublicado)){
						//removeVideoJaPublicadoS3(pathAbsolutoVideoPublicado);
						logger.info("V’deo UPADO para o S3 com sucesso.");
					}else{
						retorno = false;
						logger.error("N‹o foi poss’vel carregar o v’deo para o reposit—rio S3 da Amazon.");
						throw new ServletException("N‹o foi poss’vel carregar o v’deo para o reposit—rio S3 da Amazon.");
					}
					
				}else{
					retorno = false;
					throw new ServletException("N‹o foi poss’vel mover o arquivo de v’deo para o diret—rio de pœblicados.");
				}
			}else{
				retorno = false;
				throw new ServletException("N‹o foi poss’vel gravar os dados iniciais do arquivo v’deo.");
			}
		
		}catch (IOException e) {
			retorno = false;
			throw new ServletException("Erro: N‹o foi poss’vel publicar o v’deo - "+e.getMessage());
		}catch (Exception e) {
			retorno = false;
			throw new ServletException("Erro: N‹o foi poss’vel publicar o v’deo - "+e.getMessage());
		}
		
		return retorno;

	}
	
	public void salvarDescricao(Video video) throws ServletException {

		PreparedStatement stmt;

		StringBuilder sql = new StringBuilder();

        sql.append("Update video SET ");
        sql.append("descricao = ?, ");
        sql.append("chamada = ?, ");
        sql.append("data_criacao = ?, ");
        sql.append("publicado = 1 ");
        sql.append("Where id = ?");

        try{

	        stmt = conn.prepareStatement(sql.toString());

	        stmt.setString(1, video.getDescricao() );
	        stmt.setString(2, video.getChamada() );
	        stmt.setString(3, video.getDataCriacao() );
	        stmt.setInt(4, video.getId() );

	        stmt.executeUpdate();
	        stmt.close();

        }catch (SQLException e) {
			throw new ServletException("Erro: N‹o foi poss’vel salvar a descri‹o do v’deo publicado - "+e.getMessage());
		}catch (Exception e) {
			throw new ServletException("Erro: Ocorreu uma exce‹o. N‹o foi poss’vel salvar a descri‹o do v’deo publicado - "+e.getMessage());
    	}

		
	}	
	
	
	
	/*
	 * 	
	 * 			MƒTODOS PRIVADOS
	 * 
	 * */

	private boolean moverVideos(String path, String pathDestino, Video video, int novoNome) throws ServletException, IOException{

		boolean retorno = false;

		try{

			File arquivo = new File(path+video.getNomeAntigoArquivo());
			File dir = new File(path+pathDestino);

			if(arquivo.exists()){
				retorno = arquivo.renameTo(new File(dir, novoNome+extensaoVideoCorrente));
			}else{
				retorno = false;
				throw new ServletException("Arquivo n‹o encontrado na origem.");
			}

		}catch (Exception e) {
			retorno = false;
			throw new ServletException(e.getMessage());
		}
		
		return retorno;
		
	}
	
	private void inserirMetadadosVideoPublicado(Video video) throws ServletException {

		PreparedStatement stmt;

		StringBuilder sql = new StringBuilder();

        sql.append("Update video SET ");
        sql.append("nome = ?, ");
        sql.append("extensao = ?, ");
        sql.append("tamanho_arquivo = ?, ");
        sql.append("duracao = ?, ");
        sql.append("width = ?, ");
        sql.append("height = ?, ");
        sql.append("framerate = ?, ");
        sql.append("publicado = ? ");
        sql.append("Where id = ?");

        try{

	        stmt = conn.prepareStatement(sql.toString());

	        stmt.setString(1, video.getNome() );
	        stmt.setString(2, video.getExtensao() );
	        stmt.setInt(3, video.getTamanhoArquivo() );
	        stmt.setString(4, video.getDuracao() );
	        stmt.setInt(5, video.getWidth() );
	        stmt.setInt(6, video.getHeight() );
	        stmt.setInt(7, video.getFramerate() );
	        stmt.setInt(8, (video.isPublicado()) ? 1 : 0 );

	        stmt.setInt(9, this.getUltimoIdCadastrado() );

	        stmt.executeUpdate();
	        stmt.close();

        }catch (SQLException e) {
			throw new ServletException("Erro: N‹o foi poss’vel gravar os dados finais (metadados) do arquivo de v’deo - "+e.getMessage());
		}catch (Exception e) {
			throw new ServletException("Erro: Ocorreu uma exce‹o. N‹o foi poss’vel gravar os dados finais (metadados) do arquivo de v’deo - "+e.getMessage());
    	}

		
	}

	private boolean uploadVideoPublicadoS3(String pathVideoPublicado) throws IOException{
		
		boolean retorno = true;
		
		try{

			PutObjectRequest putObj = new PutObjectRequest("videos.publicados", this.ultimoIdCadastrado+this.extensaoVideoCorrente, new File(pathVideoPublicado) );
			putObj.setCannedAcl(CannedAccessControlList.PublicRead);
			s3client.putObject(putObj);
			
			putObj = new PutObjectRequest("videos.publicados", this.ultimoIdCadastrado+".jpg", new File(pathVideoPublicado.replace("flv", "jpg")) );
			putObj.setCannedAcl(CannedAccessControlList.PublicRead);
			s3client.putObject(putObj);

			

			
			//AmazonS3 s3 = new AmazonS3Client(new PropertiesCredentials(VideoDAO.class.getResourceAsStream("AwsCredentials.properties")));
			//s3.putObject(new PutObjectRequest("videos.publicados", this.ultimoIdCadastrado+this.extensaoVideoCorrente, new File(pathVideoPublicado) ));

		}catch (Exception e) {
			retorno = false;
		}
		
		return retorno;
		
	}

	/*
	private void removeVideoJaPublicadoS3(String pathVideoPublicado) throws IOException, ServletException{
		
		try{

			File arquivo = new File(pathVideoPublicado);

			if(arquivo.exists()){

				if(arquivo.delete() == false){
					throw new ServletException("N‹o foi poss’vel deletar o arquivo.");
				}

			}else{
				throw new ServletException("Arquivo n‹o encontrado na origem.");
			}

		}catch (SecurityException e) {
			throw new ServletException("Erro de permiss‹o ao manipular o arquivo - "+e.getMessage());
		}catch (Exception e) {
			throw new ServletException("Erro removeVideoJaPublicadoS3() - "+e.getMessage());
		}
		 
		
	}
*/
	private boolean inserir(Video video) throws ServletException {
		
		boolean retorno = true;
		
		PreparedStatement stmt;
		ResultSet rst;
		
		StringBuilder sql = new StringBuilder();
		
        sql.append("Insert INTO video( ");
        sql.append("nome_antigo_arquivo, ");
        sql.append("data_criacao, ");
        sql.append("descricao, ");
        sql.append("chamada ");
        sql.append(") ");
        sql.append("Values(?, ?, ?, ?)");

        try{

	        stmt = conn.prepareStatement(sql.toString());

	        stmt.setString(1, video.getNomeAntigoArquivo() );
	        stmt.setString(2, video.getDataCriacao() );
	        stmt.setString(3, video.getDescricao() );

	        if(video.getDescricao().length() > 140){
	        	stmt.setString(4, video.getDescricao().substring(0, 140) );
	        }else{
	        	stmt.setString(4, video.getDescricao() );
	        }

	        stmt.executeUpdate();
	        
	        rst = stmt.executeQuery("SELECT LAST_INSERT_ID()");
	        rst.next();
	        
	        this.setUltimoIdCadastrado(rst.getInt(1));
	        
	        stmt.close();
        
        }catch (SQLException e) {
			retorno = false;
		}catch (Exception e) {
    		retorno = false;
    	}
        
        return retorno;
		
	}
	
	private Video populaVideo(File f) throws ServletException{
		
		Video video;
		
		try{
		
			video = new Video();
			
			video.setNomeAntigoArquivo(f.getName());
			video.setDataCriacao(FormataData.retornaData(f.lastModified())+" - "+FormataData.retornaHora(f.lastModified()));
		
		}catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		return video;
		
	}
	
	private Video capturaMetadados(String nomeVideo) throws ServletException {

		//logger.info("capturaMetadados()");
		logger.debug("capturaMetadados()");

		Video video;

		try{
		
			FfmpegUtils ffmpeg = new FfmpegUtils(diretorioBaseInstalacaoFfmpeg+"ffmpeg");
			
			logger.debug("antes ffmpeg.getMetadata()");
			Map<String,String> fieldMap = ffmpeg.getMetadata(nomeVideo);
			logger.debug("depois ffmpeg.getMetadata()");
			
			video = new Video();
			
	        if(fieldMap.containsKey( "duration" )){
	        	logger.debug("capturaMetadados() - duration");
	            video.setDuracao(fieldMap.get( "duration" ));
	        }
	
	        if(fieldMap.containsKey( "width" )){
	        	logger.debug("capturaMetadados() - width");
	            video.setWidth(Integer.parseInt(fieldMap.get( "width" )));
	        }
	        
	        if(fieldMap.containsKey( "height" )){
	        	logger.debug("capturaMetadados() - height");
	            video.setHeight(Integer.parseInt(fieldMap.get( "height" )));
	        }
	
	        if(fieldMap.containsKey( "filesize" )){
	        	logger.debug("capturaMetadados() - filesize");
	            video.setTamanhoArquivo(Integer.parseInt(fieldMap.get( "filesize" )));
	        }
	
	        if(fieldMap.containsKey( "framerate" )){
	        	logger.debug("capturaMetadados() - framerate");
	            video.setFramerate(Integer.parseInt(fieldMap.get( "framerate" )));
	        }

		}catch (Exception e) {
			logger.error("Erro: N‹o foi poss’vel capturar os metadados do v’deo - "+e.getMessage());
			throw new ServletException("Erro: N‹o foi poss’vel capturar os metadados do v’deo - "+e.getMessage());
		}
		
		return video;
		
	}
	
	private void geraThumbnail(String path, Video video) throws ServletException{
		
		try{

			FfmpegUtils ffmpeg = new FfmpegUtils(diretorioBaseInstalacaoFfmpeg+"ffmpeg");
			//ffmpeg.generateThumbnails(path+video.getNome(), new File(path), video.getWidth(), video.getHeight(), 1, true);

			ffmpeg.generateThumbnails(path+video.getNome(), new File(path), 100, 62, 1, true);
			
		}catch (Exception e) {
			logger.error("Erro: N‹o foi poss’vel gerar o thumbnail do v’deo - "+e.getMessage());
			throw new ServletException("Erro: N‹o foi poss’vel gerar o thumbnail do v’deo - "+e.getMessage());
		}
		
	}
	
    private boolean isPermission(String fileName) throws Exception {

        String lowerCaseName = fileName.toLowerCase();  
        StringTokenizer st = new StringTokenizer(this.extensoesPermitidas," ,.?! \'-:;|");

        while(st.hasMoreTokens()){
            if (lowerCaseName.endsWith("."+st.nextToken())){
                return true;
            }
        }  

        return false;

    }
    
    private boolean S3Exists(AmazonS3 s3, String bucketName, String path) throws AmazonClientException, AmazonServiceException {

        boolean isValidFile = true;
        
        try {

            ObjectMetadata objectMetadata = s3.getObjectMetadata(bucketName, path);

        } catch (AmazonS3Exception s3e) {

            if (s3e.getStatusCode() == 404) {
            	// i.e. 404: NoSuchKey - The specified key does not exist
                isValidFile = false;
            }else {
                throw s3e;    // rethrow all S3 exceptions other than 404   
            }
        }

        return isValidFile;

    }

	
}
