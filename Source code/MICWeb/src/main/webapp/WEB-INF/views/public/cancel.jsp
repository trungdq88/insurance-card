<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>MIC - Bảo hiểm trực tuyến</title>
	<!--Including Bootstrap style files-->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery.js"></script>
</head>
<body>
<div class="container-fluid">
	<div class="well">

		<h2 class="text-center">MIC - Bảo hiểm trực tuyến</h2>
	</div>
	<div class="row-fluid">

	<span class="span4">
	</span>
	<span class="span5">
		<div class="hero-unit">
			You cancelled the order.
		</div>
	</span>
	<span class="span3">
	</span>

	</div> <!-- Row-Fluid ends here -->
</div>  <!--Container-Fluid ends here -->
<% HttpSession nsession = request.getSession(false);
	if(nsession!=null)
		session.invalidate();
%>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>

