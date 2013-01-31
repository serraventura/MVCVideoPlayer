document.write('<script type="text/javascript" src="resources/plugin/jQuery/jquery-1.7.2.js"></script>');

function confirmar(id, contador){

	var nome = $('#nome_'+contador).val();
	var link = $('#link_'+contador).val();
	var textoTooltip = $('#textoTooltip_'+contador).val();

	if(nome.length < 1){
		alert('Digite o nome do Canal.');
		return false;
	}
	
	if(link.length < 1){
		alert('Digite o link do Canal.');
		return false;
	}
	
	if(textoTooltip.length < 1){
		alert('Digite o Tooltip.');
		return false;
	}
	
	if(confirm('Deseja realmente salvar os dados ?')){
		salvar(id, nome, link, textoTooltip);
	}
	
}

function salvar(id, nome, link, textoTooltip){

	$('<input>').attr({
		type: 'hidden',
		id: 'id',
		name: 'id',
		value: id
	}).appendTo('form');

	$('<input>').attr({
		type: 'hidden',
		id: 'nome',
		name: 'nome',
		value: nome
	}).appendTo('form');
	
	$('<input>').attr({
		type: 'hidden',
		id: 'link',
		name: 'link',
		value: link
	}).appendTo('form');

	$('<input>').attr({
		type: 'hidden',
		id: 'textoTooltip',
		name: 'textoTooltip',
		value: textoTooltip
	}).appendTo('form');
	
	$("form").submit();
    $('#id').remove();
    $('#nome').remove();
    $('#link').remove();
    $('#textoTooltip').remove();
	
}
