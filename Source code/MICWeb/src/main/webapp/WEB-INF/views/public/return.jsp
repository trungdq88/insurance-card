<%@page import="java.util.HashMap"%>
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

		<% if((request.getAttribute("ack").equals("SUCCESS") || request.getAttribute("ack").equals("SUCCESSWITHWARNING") ) ) {
			HashMap<String,String> result = (HashMap<String,String>) request.getAttribute("result");
		%>
			<span class="span4">
    		</span>
    		<span class="span5">
    			<div class="hero-unit">
					<!-- Display the Transaction Details-->
					<h4> <%=result.get("FIRSTNAME")%>
						<%=result.get("LASTNAME")%> , Bạn đã thanh toán thành công! </h4>

					<h4> Chúng tôi sẽ gửi thẻ bảo hiểm đến cho bạn sớm nhất. </h4>
					<p>Transaction ID: <%=result.get("PAYMENTINFO_0_TRANSACTIONID")%> </p>
					<p>Payment Total Amount: <%=result.get("PAYMENTINFO_0_AMT")%> </p>
					<p>Currency : <%=result.get("PAYMENTINFO_0_CURRENCYCODE")%> </p>
					<p>Payment Status: <%=result.get("PAYMENTINFO_0_PAYMENTSTATUS")%> </p>

					<h3><a href='home'>Trở về trang chủ</a> </h3>

				</div>
    		</span>
    		<span class="span3">
    		</span>

		<% } else {
			HashMap<String,String> result = (HashMap<String,String>) request.getAttribute("result");
		%>

		<div class="hero-unit">
			<!-- Display the Transaction Details-->
			<h4> Thanh toán không thành công! </h4>
			Payment Status: <%=result.get("PAYMENTINFO_0_PAYMENTSTATUS")%>


		</div>

		<% } %>

	</div> <!-- Row-Fluid ends here -->
</div>  <!--Container-Fluid ends here -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>