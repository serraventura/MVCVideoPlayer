document.write('<script type="text/javascript" src="resources/plugin/jQuery/jquery-1.7.2.js"></script>');

function confirmar(video, dataCriacao, contador){
	
	var descricao = $('#desc_'+contador).val();
	
	if(descricao.length < 10){
		alert('Digite a descri‹o.');
		return false;
	}
	
	if(confirm('Deseja realmente publicar o v’deo '+video+' ?')){
		publicar(video, dataCriacao, descricao);
	}
	
}

function publicar(video, dataCriacao, descricao){

	$('<input>').attr({
		type: 'hidden',
		id: 'nomeAntigoArquivo',
		name: 'nomeAntigoArquivo',
		value: video
	}).appendTo('form');

	$('<input>').attr({
		type: 'hidden',
		id: 'descricao',
		name: 'descricao',
		value: descricao
	}).appendTo('form');

	$('<input>').attr({
		type: 'hidden',
		id: 'dataCriacao',
		name: 'dataCriacao',
		value: dataCriacao
	}).appendTo('form');
	
	$("form").submit();
    $('#nomeAntigoArquivo').remove();
    $('#descricao').remove();
    $('#dataCriacao').remove();
	
}

function publicarWithAjax(video, descricao){

    $.ajax({
        
       type: "POST",
       async: false,
       cache: false,
       url: "publicarVideoxxxxx",
       timeout: 0, //milesegundos
       data: { name: "John", location: "Boston" },
       dataType: "text", //xml, html, json, jsonp, script, or text
       
       beforeSend: function(jqXHR, settings){
       },
       
       error: function(jqXHR, textStatus, errorThrown) {
    	   
    	   alert(jqXHR);
    	   alert(textStatus);
    	   alert(errorThrown);
    	   
       },
       
       success: function(data){

           try{

           }catch(err){
            
           }
        
       },

       complete: function(jqXHR, textStatus){
       }

    });        

}

