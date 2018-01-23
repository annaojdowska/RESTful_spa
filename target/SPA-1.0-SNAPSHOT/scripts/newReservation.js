function newReservation(){
    var id = findParam('treatment_id');
    $("[name='treatment_id']").val(id);
    $.getJSON('resources/treatments/findById',{'treatment_id':id}, function(data){
        $('#treatmentName').html(data.name + "<br>Price: "+data.price+" &pound");
    });
    
    
}

function findParam(param_name) {
                if (param_name = (new RegExp('[?&]' + encodeURIComponent(param_name) + '=([^&]*)')).exec(location.search))
                    return decodeURIComponent(param_name[1]);

}
            
$(document).ready(function(){
    newReservation();
});