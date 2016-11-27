<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="assets/img/favicon.ico">

    <title>mLocation - Get your lovers locations</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/ionicons.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/customCss.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/agile_carousel.css" type='text/css'>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        #map {
            height: 400px;
            width: 100%;

        }

    </style>

</head>

<body>

<div id="h">
    <div class="logo">mLocation.CO
    </div>
<div class="page-content">
  <div class="social hidden-xs">
      <a href="https://twitter.com/mlocation_SL" target="_blank"><i class="ion-social-twitter"></i></a>
      <a href="https://www.instagram.com/mlocation_sl" target="_blank"><i class="ion-social-instagram"></i></a>
      <a href="https://www.facebook.com/getlocation/" target="_blank"><i class="ion-social-facebook"></i></a>
  </div>
  <div class="container">
      <div class="row">
          <div class=" form-content col-md-4">
            <div class="btn-register">
              <a href="#middle">
                  <button class='btn btn-conf btn-green' type="button">Register</button>
              <a/>
            </div>
              <div class="mtb">
                  <form class="form-horizontal" role="form" enctype="plain"
                        style="text-align: center; display: inline-block">
                        <div class="input-pin">
                          <input id="pin" type="text" name="pin" class="tb1 subscribe-input" placeholder="Enter A PIN"
                                 required>
                        </div>
                        <div class = "btn-location">
                          <button id="sendPin" onclick="request_access()" class='btn btn-conf btn-green' type="button">Get
                              Your Location
                          </button>
                        </div>


                  </form>
              </div><!--/mt-->
              <h6>Get your Friend/ Lover/ Children Location</h6>
          </div>

          <!--adding google map-->
          <div class="col-md-8">
              <div id="map"></div>
              <script>
                  function initMap() {
                      var mapcanvas = document.createElement('div');
                      mapcanvas.style.border = '10px solid #0b0';
                      var uluru = {lat: 7.8731, lng: 80.7718};
                      var map = new google.maps.Map(document.getElementById('map'), {
                          zoom: 4,
                          center: uluru
                      });
                      var marker = new google.maps.Marker({
                          position: uluru,
                          map: map
                      });
                  }
              </script>
              <script async defer
                      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9zFd5A8dQyOhYVtTwnJtqQjfcm5Cdp1M&callback=initMap">
              </script>
              <!--google map end-->

          </div>
      </div><!--/row-->
  </div>
</div>
    <!--/container-->
</div><!-- /H -->


<div id='middle' class="">
    <div class="demo-container row centered">
        <h2 class="mb">Dial #775*911# on your Dialog Mobile.<br/>Or someone you want to get the location.<br/>Get Your
            PIN
        </h2>
        <div id="flavor_2" class="slideshow">
    </div><!--/row-->
    <div id="f">
        <div class="container">
            <div class="row centered">
                <h2>You Can Contact Us</h2>
                <h5>mLocationMail@gmail.COM</h5>

                <p class="mt">
                    <a href="https://twitter.com/mlocation_SL" target="_blank"><i class="ion-social-twitter"></i></a>
                    <a href="https://www.instagram.com/mlocation_sl" target="_blank"><i
                            class="ion-social-instagram"></i></a>
                    <a href="https://www.facebook.com/getlocation/" target="_blank"><i class="ion-social-facebook"></i></a>
                </p>
                <h6 class="mt">COPYRIGHT 2016 - mLocation</h6>
            </div><!--/row-->
        </div><!--/container-->
    </div>
</div><!--/container-->

<!--/F-->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/retina-1.1.0.js"></script>
<script src="assets/js/agile_carousel.alpha.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var pin = $('#pin');
        var sendPin = $('#sendPin');
        sendPin.click(function () {
            //alert(pin.val());
            $.ajax({
                        url: "/getLocation?",
                        data: 'pin=' + pin.val(),
                        async: false
                    })
                    .done(function (data) {
                        var uluru = {lat: parseInt(data["Latitude"]), lng: parseInt(data["Longitude"])};
                        var map = new google.maps.Map(document.getElementById('map'), {
                            zoom: 8,
                            center: uluru
                        });
                        var marker = new google.maps.Marker({
                            position: uluru,
                            map: map
                        });

                    })
                    .fail(function () {
                        alert("Ajax failed to fetch data")
                    })
        });
    });

    $.getJSON("assets/jsons/agile_carousel_data.php", function (data) {
        $(document).ready(function () {
            $("#flavor_2").agile_carousel({
                carousel_data: data,
                carousel_outer_height: 330,
                carousel_height: 230,
                slide_height: 230,
                carousel_outer_width: 480,
                slide_width: 480,
                transition_type: "fade",
                transition_time: 600,
                timer: 2000,
                continuous_scrolling: true,
                control_set_1: "numbered_buttons,previous_button,pause_button,next_button"
//                control_set_2: "content_buttons",
//                change_on_hover: "content_buttons"
            });
        });
    });
</script>
</body>
</html>
