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


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>

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
    <div class="logo">BLACKTIE.CO</div>
    <div class="social hidden-xs">
        <a href="#"><i class="ion-social-twitter"></i></a>
        <a href="#"><i class="ion-social-instagram"></i></a>
        <a href="#"><i class="ion-social-facebook"></i></a>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <!--<h1>Welcome to our new app site.<br/>Sign up now & prepare to enjoy our service.</h1>-->
                <div class="mtb">
                    <form class="form-horizontal" role="form" action="register.php" method="post" enctype="plain"
                          style="text-align: center; display: inline-block">
                        <input type="text" name="email" class="tb1 subscribe-input" placeholder="Enter A PIN" required>
                        <button class='btn btn-conf btn-green' type="submit">Get Your Location</button>
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
    </div><!--/container-->
</div><!-- /H -->


<div class="container ptb">
    <div class="row centered">
        <h2 class="mb">Our Pricing Model<br/>It's Quite Easy To Understand.</h2>

    </div><!--/row-->
</div><!--/container-->

<div id="g">
    <div class="container">
        <div class="row sponsor centered">
            <div class="col-sm-2 col-sm-offset-1">
                <img src="assets/img/client1.png" alt="">
            </div>
            <div class="col-sm-2">
                <img src="assets/img/client3.png" alt="">
            </div>
            <div class="col-sm-2">
                <img src="assets/img/client2.png" alt="">
            </div>
            <div class="col-sm-2">
                <img src="assets/img/client4.png" alt="">
            </div>
            <div class="col-sm-2">
                <img src="assets/img/client5.png" alt="">
            </div>
        </div><!--/row-->
    </div>
</div><!--/g-->

<div id="green">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 centered">
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <h3>I enjoyed so much the last edition of Landing Sumo, that I bought the tickets for the new one edition of the event the first day.</h3>
                            <h5><tgr>DAVID JHONSON</tgr></h5>
                        </div>
                        <div class="item">
                            <h3>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.</h3>
                            <h5><tgr>MARK LAWRENCE</tgr></h5>
                        </div>
                        <div class="item">
                            <h3>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration, by injected humour.</h3>
                            <h5><tgr>LISA SMITH</tgr></h5>
                        </div>
                    </div>
                </div><!--/Carousel-->

            </div>
        </div><!--/row-->
    </div><!--/container-->
</div><!--/green-->

<div id="f">
    <div class="container">
        <div class="row centered">
            <h2>You Can Contact Us</h2>
            <h5>HELLO@LANDINGSUMO.COM</h5>

            <p class="mt">
                <a href="#"><i class="ion-social-twitter"></i></a>
                <a href="#"><i class="ion-social-dribbble"></i></a>
                <a href="#"><i class="ion-social-instagram"></i></a>
                <a href="#"><i class="ion-social-facebook"></i></a>
                <a href="#"><i class="ion-social-pinterest"></i></a>
                <a href="#"><i class="ion-social-tumblr"></i></a>
            </p>
            <h6 class="mt">COPYRIGHT 2016 - mLocation</h6>
        </div><!--/row-->
    </div><!--/container-->
</div><!--/F-->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/retina-1.1.0.js"></script>
</body>
</html>
