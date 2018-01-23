 function initMap() {
                    var london = {lat: 51.513255, lng: -0.115629};
                    var map = new google.maps.Map(document.getElementById('map'), {
                      zoom: 14,
                      center: london
                    });
                    var marker = new google.maps.Marker({
                      position: london,
                      map: map
                    });
                }
