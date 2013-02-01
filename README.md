MVCVideoPlayer
==============

MVCVideoPlayer é um pequeno projeto feito em Java. Apesar de ser um projeto funcional, a publicação do mesmo é apenas para fins de estudo.

Se você deseja utilizar o projeto para estudar uma básica aplicação em Java, com a utilização de Spring MVC, MVCVideoPlayer é um bom exemplo para isso. Porém, se você deseja se espelhar no projeto para uma aplicação comercial, você precisará melhorá-lo.

Pontos interessantes para estudo que estão dentro do projeto:

 - Framework Spring MVC;
 - <a href='https://github.com/FFmpeg/FFmpeg'>FFmpeg:</a>
 - AWS S3;


Funcionamento
==============

MVCVideoPlayer é uma pequena galeria de vídeos em formato .FLV.
<br /><br /><b>Como funciona:</b>
 - O usuário faz o upload do arquivo de vídeo .FLV para uma pasta específica no servidor;
 - Através da administração da ferramenta é possível visualizar todos os arquivos de vídeo no servidor e escolher qual será processado. A opção "Processar" utiliza o <b>FFmpeg</b> para capturar metadados do vídeo e gerar um thumbnail automaticamente;
 - A opção "Publicar" irá separar o arquivo do vídeo publicado dos demais e fazer o upload do mesmo para o serviço S3 da Amazon;

<br /><b>Canais:</b>
<br />A opção de canais dentro da ferramenta foi pensada para transmissões ao vivo. A aplicação não faz nada além de chamar um player .SWF já apontando para um servidor que está fazendo a transmissão.


Instalação
==============

Para os testes da aplicação foram utilizados os serviços da Amazon EC2 e S3; Um servidor Linux com MySql, Tomcat e FFmpeg instalados. A estrutura do banco se encontra na pasta SQL.<br />
Você não precisa necessariamente usar os serviços da Amazon, basta ter as ferramentas citadas instaladas no seu computador (MAC, Windows, Linux...).
