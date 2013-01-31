<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:forEach var="video" items="${listaVideos}" varStatus="contador">

	<!-- div style=" width: 336px; border:solid 5px red; float: left;"-->
	<div class="boxItemVideo">
		<a href="#" onclick="playOnDemand(${video.id});">
			<div class="thumbnail" style="background-image: url('http://ec2-000-00-00-00.compute-00.amazonaws.com/upload-videos/publicado/${video.id}.jpg');" ></div>			
		</a>
		<input type="hidden" name="desc_${video.id}" id="desc_${video.id}" value="${video.descricao}" />
		<input type="hidden" name="duracao_${video.id}" id="duracao_${video.id}" value="${video.duracao}" />
		<input type="hidden" name="data_${video.id}" id="data_${video.id}" value="${video.dataCriacao}" />

		<div class="txtDescricaoVideo">

			${video.chamada}

		</div>

	</div>

</c:forEach>

<c:if test="${totalRegistro < 1}">
	<br />
	<br />
	<br />
	<br />
	<br />
	<div align="center">Nenhum vídeo publicado no momento.</div>
</c:if>

<div id="boxPaginacao">
	<div id="paginacao">
		<c:if test="${totalRegistro > 5}">
			${anterior}${proxima} 
		</c:if>
	</div>
</div>