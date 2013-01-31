document.write('<script type="text/javascript" src="resources/plugin/jQuery/jquery-1.7.2.js"></script>');

function confirmar(id, contador){

	var descricao = $('#desc_'+contador).val();
	var dataCriacao = $('#dataCriacao_'+contador).val();
	var chamada = $('#chamada_'+contador).val();

	if(descricao.length < 10){
		alert('Digite a descri‹o.');
		return false;
	}
	
	if(chamada.length < 40){
		alert('Informe uma chamada entre 40 a 140 caracteres.');
		return false;
	}
	
	if(chamada.length > 140){
		alert('Informe uma chamada entre 40 a 140 caracteres.');
		return false;
	}
	
	if(confirm('Deseja realmente salvar os dados ?')){
		salvar(id, descricao, dataCriacao, chamada);
	}
	
}

function copiarChamada(contador){
	
	var valor = $("#desc_"+contador).val();
	$("#chamada_"+contador).val(valor.substring(0, 140));

}

function salvar(id, descricao, dataCriacao, chamada){

	$('<input>').attr({
		type: 'hidden',
		id: 'id',
		name: 'id',
		value: id
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

	$('<input>').attr({
		type: 'hidden',
		id: 'chamada',
		name: 'chamada',
		value: chamada
	}).appendTo('form');
	
	$("form").submit();
    $('#id').remove();
    $('#dataCriacao').remove();
    $('#descricao').remove();
    $('#chamada').remove();
	
}
