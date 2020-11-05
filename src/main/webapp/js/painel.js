Date.prototype.getDayWeekend = function() {
	if (this.getDay() === 0) {
		return "Domingo";
	}
	
	if (this.getDay() === 1) {
		return "Segunda-Feira";
	}
	
	if (this.getDay() === 2) {
		return "Terça-Feira";
	}
	
	if (this.getDay() === 3) {
		return "Quarta-Feira";
	}
	
	if (this.getDay() === 4) {
		return "Quinta-Feira";
	}
	
	if (this.getDay() === 5) {
		return "Sexta-Feira";
	}
	
	return "Sábado";
}

Date.prototype.getMonthName = function() {
	if (this.getMonth() === 0) {
		return "Janeiro";
	}

	if (this.getMonth() === 1) {
		return "Fevereiro";
	}

	if (this.getMonth() === 2) {
		return "Março";
	}

	if (this.getMonth() === 3) {
		return "Abril";
	}

	if (this.getMonth() === 4) {
		return "Maio";
	}

	if (this.getMonth() === 5) {
		return "Junho";
	}

	if (this.getMonth() === 6) {
		return "Julho";
	}

	if (this.getMonth() === 7) {
		return "Agosto";
	}

	if (this.getMonth() === 8) {
		return "Setembro";
	}

	if (this.getMonth() === 9) {
		return "Outubro";
	}

	if (this.getMonth() === 10) {
		return "Novembro";
	}
	
	return "Dezembro";
}

//full app
var app = angular.module('painel', [ 'ngDigitalClock', 'ngOldClock', 'ngWeather', 'ngPlain', 'ngCotacao', 'ngNoticia', 'ngInfraero', 'ngCambio', 'ngGrafico']);

// ngClock module
// ================================================
angular.module('ngDigitalClock', [])

// filters
// ================================================
.filter('pad', function() {
	return function(num) {
		return (num < 10 ? '0' + num : num); // coloca o zero na frente
	};
})
// directives
// ================================================
.directive('ngDigitalClock', [ "$timeout", function($timeout) {
	return {
		restrict : 'E',
		scope: {
			id: '@',
			url: '@'
		},
		templateUrl : "digital-clock.html",
		controller : function($scope, $element) {
			$scope.date = new Date();
			
			var tick = function() {
				$scope.date = new Date();
				$timeout(tick, 1000);
			};
			$timeout(tick, 1000);
		}
	}

} ]);

angular.module('ngWeather', []).directive('ngWeather', ['$timeout', function($timeout) {
	return {
		restrict: 'E',
		scope: {
			id: '@',
			url: '@'
		},
		templateUrl: "weather-detail.html",
		controller: function($scope, $element, $http) {
			$http.get($scope.url).success(function(response) {
				$scope.weather = response.main.temp;
				$scope.icon = response.weather[0].icon;
				$scope.description = response.weather[0].description;
			});
			
			var update = function() {
				$http.get($scope.url).success(function(response) {
					$scope.weather = response.main.temp;
					$scope.icon = response.weather[0].icon;
					$scope.description = response.weather[0].description;
				});
				$timeout(update, 60000);
			}
			
			$timeout(update, 60000);
		}
	}
}]);

angular.module('ngPlain', []).directive('ngPlain', ['$timeout', function($timeout) {
	return {
		restrict: 'E',
		scope: {
			id: '@',
			url: '@'
		},
		controller: function($scope, $element, $http) {
			$http.get($scope.url).success(function(response) {
				$element.html(response);
			});
			
			var update = function() {
				$http.get($scope.url).success(function(response) {
					$element.html(response);
				});
				$timeout(update, 1000);
			}
			
			$timeout(update, 1000);
		}
	}
}]);

angular.module('ngOldClock', []).directive('ngOldClock', function(){
	
	return {
		restrict : 'E',
		scope: {
			id: '@',
			cidade: '@',
			offset: '@',
			background: '@'
		},
		templateUrl : "old-clock.html",
		controller : function($scope, $element) {
			var canvas = $element.find("canvas")[0];
			var ctx = canvas.getContext("2d");
			var radius = canvas.height / 2;
			ctx.translate(radius, radius);
			radius = radius * 0.90
			setInterval(drawClock, 1000);
	
			function drawClock() {
				var date = new Date();
				var utc = date.getTime()+(date.getTimezoneOffset()*60000);
			    var now = new Date(utc + (3600000*$scope.offset));
			    drawFace(ctx, radius, now);
			    drawNumbers(ctx, radius);
			    drawTime(ctx, radius, now);
			    var ampm = now.getHours() >= 12 ? 'PM' : 'AM';
			    $scope.ampm = ampm;
				/*var ampm = now.getHours() >= 12 ? 'PM' : 'AM';
				var fillStyle = ctx.fillStyle;
				ctx.font="bold 15px Segoe UI";
				ctx.fillStyle = '#30a289';
				ctx.fillText(ampm, 2, 30);
				ctx.fillStyle = fillStyle;*/
			}
	
			function drawFace(ctx, radius, now) {
				if ($scope.background) {
					var img = document.getElementById($scope.id+"-background");
					ctx.drawImage(img, -61, -61, 120, 120);
				} else {
					var grad;
					ctx.beginPath();
					ctx.arc(0, 0, radius, 0, 2*Math.PI);
					ctx.fillStyle = 'white';
					ctx.fill();
					grad = ctx.createRadialGradient(0,0,radius*0.95, 0,0,radius*1.05);
					grad.addColorStop(0, '#333');
					grad.addColorStop(0.5, 'white');
					grad.addColorStop(1, '#333');
					ctx.strokeStyle = grad;
					ctx.lineWidth = radius*0.1;
					ctx.stroke();
					ctx.beginPath();
					ctx.arc(0, 0, radius*0.1, 0, 2*Math.PI);
					ctx.fillStyle = '#333';
					ctx.fill();
				}
			}
	
			function drawNumbers(ctx, radius) {
			  var ang;
			  var num;
			  ctx.font = radius*0.15 + "px segoe ui";
			  ctx.textBaseline="middle";
			  ctx.textAlign="center";
			  for(num = 1; num < 13; num++){
			    ang = num * Math.PI / 6;
			    ctx.rotate(ang);
			    ctx.translate(0, -radius*0.85);
			    ctx.rotate(-ang);
			    ctx.fillText(num.toString(), 0, 0);
			    ctx.rotate(ang);
			    ctx.translate(0, radius*0.85);
			    ctx.rotate(-ang);
			  }
			}
	
			function drawTime(ctx, radius, now){
			    var hour = now.getHours();
			    var minute = now.getMinutes();
			    var second = now.getSeconds();
			    //hour
			    hour=hour%12;
			    hour=(hour*Math.PI/6)+
			    (minute*Math.PI/(6*60))+
			    (second*Math.PI/(360*60));
			    drawHand(ctx, hour, radius*0.5, radius*0.07);
			    //minute
			    minute=(minute*Math.PI/30)+(second*Math.PI/(30*60));
			    drawHand(ctx, minute, radius*0.8, radius*0.07);
			    // second
			    second=(second*Math.PI/30);
			    drawHand(ctx, second, radius*0.9, radius*0.02);
			}
	
			function drawHand(ctx, pos, length, width) {
			    ctx.beginPath();
			    ctx.lineWidth = width;
			    ctx.lineCap = "round";
			    ctx.moveTo(0,0);
			    ctx.rotate(pos);
			    ctx.lineTo(0, -length);
			    ctx.stroke();
			    ctx.rotate(-pos);
			}
		}
	}
});

angular.module('ngCotacao', []).directive('ngCotacao', ['$timeout', '$compile', function($timeout, $compile) {
	return {
		restrict: 'E',
		scope: {
			id: '@',
			moeda: '@',
			titulo: '@',
			url: '@'
		},
		controller: function($scope, $element, $http) {
			$http.get($scope.url).success(function(response) {
	            var content = $compile(response)($scope);
	            $element.empty();
	            $element.append(content);
			});
			
			var update = function() {
				$http.get($scope.url).success(function(response) {
		            var content = $compile(response)($scope);
		            $element.empty();
		            $element.append(content);
				});
				$timeout(update, 1000);
			}
			
			$timeout(update, 1000);
		}
	}
}]);

angular.module('ngCambio', []).directive('ngCambio', ['$timeout', function($timeout) {
	return {
		restrict: 'E',
		scope: {
			id: '@',
			url: '@',
		},
		templateUrl: "cambio.html",
		controller: function($scope, $element, $http) {
			
			$scope.carrossel = window.innerWidth/2+300;
			
			var carousel = function() {
				$scope.carrossel--;
				
				if ($scope.carrossel < -2500) {
					$scope.carrossel = window.innerWidth/2+300;
				}
				
				$timeout(carousel, 100);
			}
			
			$timeout(carousel, 100);
			
			var update = new WebSocket("ws:"+ window.location.host +$scope.url);
			
			update.onmessage = function(evt) {
				$scope.cambios = JSON.parse(evt.data);
			}
		}
	}
}]);

angular.module('ngNoticia', []).directive('ngNoticia', ['$timeout', function($timeout) {
	return {
		restrict: 'E',
		scope: {
			url: '@'
		},
		template: '<section class="noticia">{{noticia}}</section>',
		controller: function($scope, $element, $http) {
			var update = new WebSocket("ws:"+ window.location.host +$scope.url);
			
			update.onmessage = function(evt) {
				$scope.noticia = evt.data;
			}
		}
	}
}]);

angular.module('ngInfraero', []).directive('ngInfraero', ['$timeout', function($timeout) {
	return {
		restrict: 'E',
		scope: {
			id: "@",
			url: '@'
		},
		template: '<img id="{{id}}" alt="Embedded Image" src="data:image/png;base64,{{infraero}}" />',
		controller: function($scope, $element, $http) {
			$http.get($scope.url).success(function(response) {
				$scope.infraero = response !== null?response:"";
			});
			
			var update = function() {
				$http.get($scope.url).success(function(response) {
					$scope.infraero = response !== null?response:"";
				});
				
				$timeout(update, 1000);
			}
			
			$timeout(update, 1000);
		}
	}
}]);

angular.module('ngGrafico', []).directive('ngGrafico', ['$timeout', function($timeout) {
	return {
		restrict: 'E',
		scope: {
			id: '@',
			url: '@',
		},
		templateUrl: "grafico.html",
		controller: function($scope, $element, $http) {
			var update = new WebSocket("ws:"+ window.location.host +$scope.url);
			
			update.onmessage = function(evt) {
				var data = JSON.parse(evt.data);
				
				data.datasets[0].label = "Euro";
				data.datasets[0].fillColor = "rgba(220,220,220,0.2)";
				data.datasets[0].strokeColor = "rgba(220,220,220,1)";
				data.datasets[0].pointColor = "rgba(220,220,220,1)";
				data.datasets[0].pointStrokeColor = "#fff";
				data.datasets[0].pointHighlightFill = "#fff";
				data.datasets[0].pointHighlightStroke = "rgba(220,220,220,1)";
				
				data.datasets[1].label = "Dolar";
				data.datasets[1].fillColor = "rgba(151,187,205,0.2)";
				data.datasets[1].strokeColor = "rgba(151,187,205,1)";
				data.datasets[1].pointColor = "rgba(151,187,205,1)";
				data.datasets[1].pointStrokeColor = "#fff";
				data.datasets[1].pointHighlightFill = "#fff";
				data.datasets[1].pointHighlightStroke = "rgba(151,187,205,1)";
				
				$scope.datasets = data.datasets;
				
				var options = {

				    ///Boolean - Whether grid lines are shown across the chart
				    scaleShowGridLines : true,

				    //String - Colour of the grid lines
				    scaleGridLineColor : "rgba(220,220,220,0.2)",

		            scaleLineColor: "white",
		            
		            scaleFontColor: "white",
		            
		            scaleLabel: "<%= Number(value).toFixed(4).replace('.',',') %>",

				    //Number - Width of the grid lines
				    scaleGridLineWidth : 1,

				    //Boolean - Whether to show horizontal lines (except X axis)
				    scaleShowHorizontalLines: true,

				    //Boolean - Whether to show vertical lines (except Y axis)
				    scaleShowVerticalLines: true,

				    //Boolean - Whether the line is curved between points
				    bezierCurve : true,

				    //Number - Tension of the bezier curve between points
				    bezierCurveTension : 0.4,

				    //Boolean - Whether to show a dot for each point
				    pointDot : true,

				    //Number - Radius of each point dot in pixels
				    pointDotRadius : 4,

				    //Number - Pixel width of point dot stroke
				    pointDotStrokeWidth : 1,

				    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
				    pointHitDetectionRadius : 20,

				    //Boolean - Whether to show a stroke for datasets
				    datasetStroke : true,

				    //Number - Pixel width of dataset stroke
				    datasetStrokeWidth : 2,

				    //Boolean - Whether to fill the dataset with a colour
				    datasetFill : true,

				    //String - A legend template
				    legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"

				};
				
				var canvas = $element.find("canvas")[0];
				var ctx = canvas.getContext("2d");
				var chart = new Chart(ctx).Line(data, options);
				
				chart.generateLegend();
			}
		}
	}
}]);