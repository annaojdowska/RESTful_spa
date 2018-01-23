function getOrigin(){
    var REQUEST_URI = 'resources/treatments/distance';
    var distanceForm = $('#distanceForm');
    distanceForm.submit(function(event){
        event.preventDefault();
        $('#distanceResultTable tbody').remove();
        var city = $("[name='cityOrigin']").val();
        var street = $("[name='streetOrigin']").val();
        var no = $("[name='noOrigin']").val();
        var travelMode = $("#travelModeSelect option:selected").val();
        var units = $("#unitSelect option:selected").val();
        $.getJSON(REQUEST_URI, {'cityOrigin':city, 'streetOrigin':street, 'noOrigin':no,
        'travelMode':travelMode, 'units':units}, function(data){
                var table = $('#distanceResultTable');
                var row1 = $('<tr>').appendTo(table);
                if(data.rows[0].elements[0].status ==='OK'){
                $('<th>').text("Origin address").appendTo(row1);
                $('<td>').text(data.originAddresses[0]).appendTo(row1);
                var row2 = $('<tr>').appendTo(table);
                $('<th>').text("Destination address").appendTo(row2);
                $('<td>').text(data.destinationAddresses[0]).appendTo(row2);
                var row3 = $('<tr>').appendTo(table);
                $('<th>').text("Distance").appendTo(row3);
                $('<td>').text(data.rows[0].elements[0].distance.humanReadable).appendTo(row3);
                var row4 = $('<tr>').appendTo(table);
                $('<th>').text("Duration").appendTo(row4);
                $('<td>').text(data.rows[0].elements[0].duration.humanReadable).appendTo(row4);
            }
            else{
                $('<td>').text("Sorry, we couldn't find your origin address. Try again.").appendTo(row1);
            }
        }).error(function (err) {
                console.log(err.responseText);   
        });
    });
}
$(document).ready(function(){
    getOrigin();
});