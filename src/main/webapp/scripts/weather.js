var APPID= '2508abecb6d9037229e719a6adc445af';
var DEGREE = "\xB0";
function forecastWeather(){
    var weatherForm = $('#weatherForm');
    var REQUEST_URI = "http://api.openweathermap.org/data/2.5/forecast/daily";
    weatherForm.submit(function(event){
        event.preventDefault();
        var unit = $( "#unitSelect option:selected" ).val();
        var days = $("[name='numberOfDays']").val();
        var  city = $("[name='city']").val();
        $('#forecastWeatherTable tbody').remove();
        $.getJSON(REQUEST_URI,{'q':city, 'units':unit, 'APPID':APPID, 'mode':'like', 'cnt':days}, function(data){
        var table = $('#forecastWeatherTable');
        var row1 = $('<tr>').appendTo(table);
        $('<th>').text(" ").appendTo(row1);
        $('<th>').text(" ").appendTo(row1);
        $('<th>').text("General").appendTo(row1);
        $('<th>').text("Day temperature").appendTo(row1);
        $('<th>').text("Night temperature").appendTo(row1);
        $('<th>').text("Cloudiness").appendTo(row1);
        $('<th>').text("Pressure").appendTo(row1);
        $('<th>').text("Humidity").appendTo(row1);
        var date = new Date();
        for(var i =0;i<days;i++){
            var row2 = $('<tr>').appendTo(table);
            var icon_code = data.list[i].weather[0].icon;
            var src = 'http://openweathermap.org/img/w/' + icon_code + '.png';
            var img = document.createElement("IMG");
            img.src = src;
            $('<td>').text(getFormattedDate(date)).appendTo(row2);
            $('<td>').html(img).appendTo(row2);
            $('<td>').text(data.list[i].weather[0].description).appendTo(row2);
            $('<td>').text(data.list[i].temp.day + " "+ DEGREE+ unitRepresentation(unit)).appendTo(row2);
            $('<td>').text(data.list[i].temp.night + " "+ DEGREE+ unitRepresentation(unit)).appendTo(row2);
            $('<td>').text(data.list[i].clouds + "%").appendTo(row2);
            $('<td>').text(data.list[i].pressure + " hPa").appendTo(row2);
            $('<td>').text(data.list[i].humidity + "%").appendTo(row2);
            date = incrementDate(date);
        }
        }).fail(function(){
            var table = $('#forecastWeatherTable');
            var row1 = $('<tr>').appendTo(table);
            $('<td>').text("Sorry, we couldn't find any information about the weather in "+city).appendTo(row1);
        });
    }).error(function (err) {
                console.log(err.responseText);   
        });
 }
function currentWeather(){
    var REQUEST_URI = "http://api.openweathermap.org/data/2.5/find";
    $.getJSON(REQUEST_URI,{'q':"London", 'units':'metric', 'APPID':APPID, 'mode':'like', 'cnt':1}, function(data){
        $('#currentWeatherTable tbody').remove();
        var table = $('#currentWeatherTable');
        var row1 = $('<tr>').appendTo(table);
        $('<th>').text(" ").appendTo(row1);
        $('<th>').text("General").appendTo(row1);
        $('<th>').text("Temperature").appendTo(row1);
        $('<th>').text("Cloudiness").appendTo(row1);
        $('<th>').text("Pressure").appendTo(row1);
        var row2 = $('<tr>').appendTo(table);
        var icon_code = data.list[0].weather[0].icon;
        var src = 'http://openweathermap.org/img/w/' + icon_code + '.png';
        var img = document.createElement("IMG");
        img.src = src;
        $('<td>').html(img).appendTo(row2);
        $('<td>').text(data.list[0].weather[0].description).appendTo(row2);
        $('<td>').text(data.list[0].main.temp + " "+ DEGREE+"C").appendTo(row2);
        $('<td>').text(data.list[0].clouds.all + "%").appendTo(row2);
        $('<td>').text(data.list[0].main.pressure + " hPa").appendTo(row2);
        
        
    }).error(function (err) {
                console.log(err.responseText);      
        });
        
}


    
function unitRepresentation(unit){
    switch(unit){
        case 'metric':return 'C';
        case 'imperial':return 'F';
        default: return 'K';
    }
}

$(document).ready(function(){
    forecastWeather();
});


function incrementDate(date) {
  var result = new Date(date);
  result.setDate(result.getDate() + 1);
  return result;
}

function getFormattedDate(date) {
  var year = date.getFullYear();

  var month = (1 + date.getMonth()).toString();
  month = month.length > 1 ? month : '0' + month;

  var day = date.getDate().toString();
  day = day.length > 1 ? day : '0' + day;
  
  return day + '-' + month + '-' + year;
}
