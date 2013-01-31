document.write('<script type="text/javascript" src="resources/plugin/jQuery/jquery-1.7.2.js"></script>');
document.write('<script type="text/javascript" src="resources/plugin/easyTooltip/easyTooltip.js"></script>');

function carregaTwitter(){
	
	return new TWTR.Widget({
		  version: 2,
		  type: 'search',
		  search: 'MVC',
		  interval: 30000,
		  title: 'Twitter',
		  subject: '#MVC',
		  width: 249,
		  height: 372,
		  theme: {
		    shell: {
		      background: '#fac905',
		      color: '#ffffff'
		    },
		    tweets: {
		      background: '#ffffff',
		      color: '#444444',
		      links: '#94790c'
		    }
		  },
		  features: {
		    scrollbar: false,
		    loop: true,
		    live: true,
		    behavior: 'default'
		  }
		}).render().start();

}

function init(videoParametro, tipoVideoParametro, idVideoVivoParametro){
	
	displayVideoIframeOnDemand();
	displayVideoIframeAoVivo();
	//setInterval("displayVideoIframeAoVivo()",2500);
	
	carregaVideosOnDemand(1);
	carregaCanais();
	
	setTimeout("setaVideo('"+videoParametro+"', '"+tipoVideoParametro+"', '"+idVideoVivoParametro+"')",2500);

}

function setaVideo(videoParametro, tipoVideoParametro, idVideoVivoParametro){
	
	if(tipoVideoParametro == 'vivo'){
		playAoVivo(videoParametro, idVideoVivoParametro);
	}else if(tipoVideoParametro == 'demand'){
		playOnDemand(videoParametro);
	}
	
}

function displayVideoIframeOnDemand(){

	var ret = true;
	
    try{

    	$('<iframe />', {
    		
    		name 		: 'ifDisplayVideoIframeOnDemand',
    		id   		: 'ifDisplayVideoIframeOnDemand',
    		src  		: 'displayVideo',
    		frameborder : 0,
    		scrolling	: "no"
    			
    	}).appendTo('#displayVideo');

    	$("#ifDisplayVideoIframeOnDemand").addClass("diplayVideo");


     }catch(err){

    	 ret = false;

     }
	
	return ret;
	
}

function displayVideoIframeAoVivo(){

	var ret = true;
	
    try{

    	$('<iframe />', {
    		
    		name 		: 'ifDisplayVideoIframeAoVivo',
    		id   		: 'ifDisplayVideoIframeAoVivo',
    		src  		: '',
    		frameborder : 0,
    		scrolling	: "no"
    			
    	}).appendTo('#displayVideo');

    	$("#ifDisplayVideoIframeAoVivo").addClass("diplayVideo");
    	$("#ifDisplayVideoIframeAoVivo").hide();

     }catch(err){

    	 ret = false;

     }
	
	return ret;

}

function playOnDemand(video){
	
	$("#ifDisplayVideoIframeOnDemand").show();
	$("#ifDisplayVideoIframeAoVivo").hide();
	
	var $f = $("#ifDisplayVideoIframeOnDemand");
	$f[0].contentWindow.carregaVideo(video);
	
	$("#descVideoCorrente").html(">> "+$("#desc_"+video).val());
	$("#duracaoVideoCorrente").html("<strong>Dura&ccedil;&atilde;o: </strong>"+$("#duracao_"+video).val());
	$("#dataVideoCorrente").html("<strong>Data: </strong>"+$("#data_"+video).val());

}

function playAoVivo(salaUrl, id){

	var $f = $("#ifDisplayVideoIframeOnDemand");
	$f[0].contentWindow.carregaVideo(0);

	$("#descVideoCorrente").html(">> "+$("#textoTooltip_"+id).val());
	$("#duracaoVideoCorrente").html("");
	$("#dataVideoCorrente").html("");

	$("#ifDisplayVideoIframeOnDemand").hide();
	$("#ifDisplayVideoIframeAoVivo").show();
	$("#ifDisplayVideoIframeAoVivo").attr("src", salaUrl);

}

function carregaVideosOnDemand(pag){

	//timeout: em milesegundos 
	//dataType: pode retornar em xml, html, json, jsonp, script, or text

    $.ajax({

       type			: "POST",
       async		: false,
       cache		: false,
       url			: "listaVideoPlayer",
       timeout		: 5000, 											
       data			: { pag: pag },
       dataType		: "html",
       beforeSend	: function(jqXHR, settings){},

       error		: function(jqXHR, textStatus, errorThrown) {
    	   				$(location).attr("href", "resources/500.html");
       				},

       success		: function(data){
    	   				//alert(data);
    	   				$("#listContent").html(data);
       				},

       complete		: function(jqXHR, textStatus){}

    });        

}

function carregaCanais(){

	//timeout: em milesegundos 
	//dataType: pode retornar em xml, html, json, jsonp, script, or text

    $.ajax({

       type			: "POST",
       async		: false,
       cache		: false,
       url			: "listaCanaisPublicados",
       timeout		: 5000, 											
       data			: { pag: '' },
       dataType		: "html",
       beforeSend	: function(jqXHR, settings){},

       error		: function(jqXHR, textStatus, errorThrown) {
    	   				$(location).attr("href", "resources/500.html");
       				},

       success		: function(data){
    	   				//alert(data);
    	   				$("#boxBtnCanais").html(data);
       				},

       complete		: function(jqXHR, textStatus){}

    });        

}

function isWindows(){

	var ret = false;

	var os = (function() {
	    var ua = navigator.userAgent.toLowerCase();
	    return {
	        isWin2K: /windows nt 5.0/.test(ua),
	        isXP: /windows nt 5.1/.test(ua),
	        isVista: /windows nt 6.0/.test(ua),
	        isWin7: /windows nt 6.1/.test(ua)
	    };
	}());

	if(os.isWin2K || os.isXP || os.isVista || os.isWin7) {
	    ret = true;
	}
	
	return ret;
	
}
