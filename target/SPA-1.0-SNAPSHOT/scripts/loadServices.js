function loadServices(){
    var filterForm = $('#filterForm');
    filterForm.submit(function(event){
        event.preventDefault();
        $('#sTable tbody').remove();
        var selectedCat = $( "#catSelect option:selected" ).text();
        var minPrice = $("[name='minPrice']").val();
        var maxPrice = $("[name='maxPrice']").val();
        $.getJSON('resources/treatments/list/filter', {"category":selectedCat, "minPrice":minPrice, "maxPrice":maxPrice}, function(data){
             var rowth = $('<tr>').appendTo($('#sTable'));
                $('<th>').text("Category").appendTo(rowth);
                $('<th>').text("Name").appendTo(rowth);
                $('<th>').text("Price").appendTo(rowth);
            $.each(data, function(i, treatment) {
                var row = $('<tr>').appendTo($('#sTable'));
                $('<td>').text(treatment.category).appendTo(row);
                $('<td>').text(treatment.name).appendTo(row);
                $('<td>').text(treatment.price).appendTo(row);
                var reserve_uri = "reserveForm.html?treatment_id=" + treatment.id;
                var reserve_td = $('<td>').appendTo(row);
                $('<a class="weatherClick">').attr('href', encodeURI(reserve_uri)).text("Reserve").appendTo(reserve_td);
                });
            }).error(function (err) {
                console.log(err.responseText);      
        });
        
        });
            $.getJSON('resources/treatments/list',function(data){
                $('#sTable tbody').remove();
                var rowth = $('<tr>').appendTo($('#sTable'));
                $('<th>').text("Category").appendTo(rowth);
                $('<th>').text("Name").appendTo(rowth);
                $('<th>').text("Price").appendTo(rowth);
            $.each(data, function(i, treatment) {
                var row = $('<tr>').appendTo($('#sTable'));
                $('<td>').text(treatment.category).appendTo(row);
                $('<td>').text(treatment.name).appendTo(row);
                $('<td>').text(treatment.price).appendTo(row);
                var reserve_uri = "reserveForm.html?treatment_id=" + treatment.id+"&treatment_name="+treatment.name;
                var reserve_td = $('<td>').appendTo(row);
                $('<a class="weatherClick">').attr('href', encodeURI(reserve_uri)).text("Reserve").appendTo(reserve_td);
                });
            }).error(function (err) {
                console.log(err.responseText);  
        });
    }
$(document).ready(function(){
    loadServices();
});