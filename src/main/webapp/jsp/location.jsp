<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jsp" %>
<link rel="stylesheet" href="css/main.css">

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Location</title>

</head>
<body>

<script src="javascripts/jquery.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDJf1ufh0uE0FYJM7Er53t10X3KMEapbG0"></script>
<script type="text/javascript" src="javascripts/jquery.googlemap.js"></script>

<div id="map" style="width: 300px; height: 300px;"></div>
<script type="text/javascript">
  $(function() {
    $("#map").googleMap({
      zoom: 10, // Initial zoom level (optional)
      coords: [48.895651, 2.290569], // Map center (optional)
      type: "ROADMAP" // Map type (optional)
    });
  })
</script>

</body>
</html>