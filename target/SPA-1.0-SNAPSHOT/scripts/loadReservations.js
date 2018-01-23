function loadReservations(){
    $.getJSON('resources/treatments/reservations',function(data){
                $('#sTable tbody').remove();
                var rowth = $('<tr>').appendTo($('#rTable'));
                $('<th>').text("Treatment name").appendTo(rowth);
                $('<th>').text("Client Name").appendTo(rowth);
                $('<th>').text("Date").appendTo(rowth);
                $('<th>').text("Hour").appendTo(rowth);
            $.each(data, function(i, reservation) {
                var row = $('<tr>').appendTo($('#rTable'));
                $('<td>').text(reservation.treatmentName).appendTo(row);
                $('<td>').text(reservation.clientName).appendTo(row);
                $('<td>').text(reservation.date).appendTo(row);
                $('<td>').text(reservation.hour).appendTo(row);
            });

        });
}
$(document).ready(function(){
    loadReservations();
});