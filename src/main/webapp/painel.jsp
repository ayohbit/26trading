<html ng-app="painel">
	<head>
		<title>Painel Twenty Six</title>
		
		<meta charset="utf-8">
		<script type="text/javascript" src="/${requestScope.contextName}/js/Chart.js"></script>
		<script type="text/javascript" src="/${requestScope.contextName}/js/angular.js"></script>
		<script type="text/javascript" src="/${requestScope.contextName}/js/painel.js"></script>
		
		<style type="text/css">
body {
	background-image:
		url("/${requestScope.contextName}/img/background.jpg");
	background-size: 100% 100%;
	margin: 0 0 0 0;
	background-repeat: no-repeat;
    overflow: hidden;
}

/*CONFIGURACOES DE ELEMENTOS*/

.stock-ticker{margin:auto}
.ticker_holder{width:900px}
.ticker_holder .ticker{border-style:solid;border-width:0;font-size:16px;font-weight:bold;white-space:nowrap;display:block;height:500px;width:2500px;margin:0 auto;overflow:hidden;position:absolute;left:68px;top:-1px;z-index:2}
.ticker_holder .ticker div{display:inline}
.ticker_holder .ticker span{font-weight:normal}
.ticker_pill{margin-top:0;height:60px}
.cl_tickername{color:#333;font-weight:bold}
.cl_tickername a{color:#333}
.clstsu,.clstsd{display:block;float:left;height:40px;line-height:38px;padding-left:23px;font-size:16px;font-weight:bold;vertical-align:middle}
.clstsu{background:url("/${requestScope.contextName}/img/tickerArrows.png") no-repeat scroll 13px -32px transparent}
.clstsd{background:url("/${requestScope.contextName}/img/tickerArrows.png") no-repeat scroll 13px -2px transparent}
.inner_pill{background:transparent;color:white;vertical-align:middle}
.inner_pill span{margin:0 3px}
.outer_tickerbar .cl_price,.outer_tickerbar .cl_yield{padding-left:2px}
.inner_tickerbar{background:transparent;border-width:1px;position:relative;width:100%;height:40px;overflow:hidden}

#cambio {
	background-color: black;
	height: 44px;
	width: 75%;
	position: absolute;
	bottom: 60px;
	left: 0;
	font-family: Arial,sans-serif;
	overflow:hidden;
}

#logo {
	position: absolute;
	bottom: 637px;
	right: 197px;
	height: 120px;
	width: 205px;
}


#airplane {
	position: absolute;
	bottom: 112px;
	right: 380px;
	height: 140px;
	width: 205px;
	}

#tempo {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
	position: absolute;
	right: 0px;	
	width: 200px;
	top: 0px;
}

.time {
	font-style: normal;
	font-weight: bold;
	font-family: Segoe UI;
}

#noticias {
	background-image: linear-gradient(to right, #EF9D07, #D08C18);
	height: 65px;
	width: 100%;
	position: absolute;
	bottom: -4;
	left: 0;
}

.noticia {
	font-family: Segoe UI;
	font-size: 14px;
	line-height: 150%;
	font-weight: bold;
	color: white;
}

#ranking {
	background-color: white;
	height: 127px;
	width: 349px;
	position: absolute;
	bottom: -22px;
	right: 0px;
	z-index: 20;
}

#airplane {
	position: absolute;
	top: 135px;
	right: -14px;
}

#ship {
	position: absolute;
	bottom: 117px;
	left: 538px;
	height: 135px;
	width: 206px;
}

/*CONFIGURACOES DAS TABELAS E PLANILHAS*/

.cabecalho {
	background-color: #02855b;
}

#planilha {
	position: absolute;
	top: 183px;
	left: 474px;
	font-family: Segoe UI;
	background-color: #EEEEEE;
	border-style: groove;
	border-color: white;
}

#tabelaeuro {
	position: absolute;
	bottom: 436px;
	left: 38px;
	font-family: Segoe UI;
	background-color: #EEEEEE;
	border-style: groove;
	border-color: white;
}

#tabeladolar {
	position: absolute;
	bottom: 251px;
	right: 1004px;
	font-family: Segoe UI;
	background-color: #EEEEEE;
	border-style: groove;
	border-color: white;
}

/*CONFIGURACOES DE CADA OLD CLOCK*/

.ampm {
	top: 36px;
	left:-77px;
	position: relative;
	}
	
#hong-kong {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
}

#Hongkongsub {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
	top: 120px;
	left: 19px;
	right: 0px;
	position: absolute;
}

#lisboa {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
}

#lisboasub {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
	top: 120px;
	left: 316px;
	right: 0px;
	position: absolute;
}
#miami {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;	
}

#miamisub {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
	top: 120px;
	left: 586px;
	right: 0px;
	position: absolute;
}

#new-york {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
}

#Nysub {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
	top: 120px;
	left: 849px;
	right: 0px;
	position: absolute;
	}

.dia {
	font-family: Segoe UI;
	font-weight: bold;
	display: inline;
}

.calendario {
	font-family: Segoe UI;
	font-weight: bold;
	display: inline;
}

.previsao {
	font-family: Segoe UI;
	color: white;
	font-weight: bold;
	display: inline;
}

.hours {
	font-size: 25px;
}

.minutes {
	font-size: 25px;
}

.oldClock {
	display: inline;
	margin: 50 120 0 0;
}

#grafico {
	float: right;
	top: 130px;
    position: relative;
    margin-right: 10;
}

</style>
	</head>
	<body>
	<section id="tempo">
		<ng-digital-clock id="detalhes"></ng-digital-clock>

		<ng-weather id="previsao" url="/${requestScope.contextName}/weather"></ng-weather>
	</section>

	<ng-old-clock id="hong-kong"  offset="+8" background="/${requestScope.contextName}/img/clock.png"></ng-old-clock>

	<ng-old-clock id="lisboa"  offset="+1" background="/${requestScope.contextName}/img/clock.png"></ng-old-clock>

	<ng-old-clock id="miami"  offset="-4" background="/${requestScope.contextName}/img/clock.png"></ng-old-clock>

	<ng-old-clock id="new-york"  offset="-4" background="/${requestScope.contextName}/img/clock.png"></ng-old-clock>

	<!--TABELA DO PAINEL DE IMPORTACOES TWENTY SIX-->

	<section id="planilha">
		<section class="cabecalho">
			<strong><font color="white">Twenty Six Trading -
					Importações</font></strong>
		</section>
		<ng-plain id="tabela" url="/${requestScope.contextName}/plain"/>
	</section>

	<!--2 TABELAS DE COTAÇÃO BANCO CENTRAL | EURO E DÓLAR-->

	<section id="tabelaeuro">
		<ng-cotacao id="euro" moeda="Euro" titulo="Cotação de Euro" url="/${requestScope.contextName}/cotacao?codigo=222"></ng-cotacao>
	</section>

	<section id="tabeladolar">
		<ng-cotacao id="dolar" moeda="Dolar Americano" titulo="Cotação de Dolar" url="/${requestScope.contextName}/cotacao?codigo=61"></ng-cotacao>
	</section>

	<!--IMAGENS UTILIZADAS-->

	<img id="logo" alt="Twenty"
		src="/${requestScope.contextName}/img/logo26.png">
		
	<img id="ship" alt="Navio"
		src="/${requestScope.contextName}/img/ship.png">
		
	
	<!-- NOMES NO RELÓGIO -->
	
	<class id="Hongkongsub"> Hong Kong </class>
	<class id="Lisboasub"> Lisboa </class>
	<class id="Miamisub"> Miami </class>
	<class id="Nysub"> New York </class>
	
	<!--ELEMENTOS FOOTER-->

	<footer id="noticias">
		<ng-noticia url="/${requestScope.contextName}/websocket/noticia/5"></ng-noticia>
		<ng-noticia url="/${requestScope.contextName}/websocket/noticia/2"></ng-noticia>
		<ng-noticia url="/${requestScope.contextName}/websocket/noticia/4"></ng-noticia>
	</footer>

	<footer id="cambio" class="outer_tickerbar">
		<section class="cambio" class="inner_tickerbar">
			<ng-cambio id="ngCambio" url="/${requestScope.contextName}/websocket/cambio"></ng-cambio>
		</section>
	</footer>

	<section id="ranking"><ng-infraero id="infraero" url="/${requestScope.contextName}/infraero"></ng-infraero></section>
	
	<ng-grafico id="grafico" url="/${requestScope.contextName}/websocket/grafico" />
</body>
</html>