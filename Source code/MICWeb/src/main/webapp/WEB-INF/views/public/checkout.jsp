<%@ page import="com.fpt.mic.micweb.model.dto.PayPal" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
    <div class="span2">
    </div>
    <div class="span5">
      <!--Form containing item parameters and seller credentials needed for SetExpressCheckout Call-->
      <form class="form" action="${pageContext.request.contextPath}/public/register" method="POST">
        <input type="hidden" name="L_PAYMENTREQUEST_0_AMT" value="${fn:escapeXml(PAYMENTREQUEST_0_AMT)}"  />
        <div class="row-fluid">
          <div class="span6 inner-span">
            <p class="lead">Xác nhận thanh toán </p>
            <table>
              <tr><td width="30%">Họ tên khách hàng:</td><td>${sessionScope.checkoutDetails.txtName}</td></tr>
              <tr><td>Địa chỉ: </td><td>${sessionScope.checkoutDetails.txtAddress}</td></tr>
              <tr><td>Số tiền thanh toán:</td><td> ${sessionScope.checkoutDetails.txtFee/20000} (USD) </td><>
            </table>
            <input type="hidden" name="action" value="checkout">
            <input type="submit" id="placeOrderBtn" class="btn btn-primary btn-large" value="Thanh Toán" />
          </div>
        </div>

      </form>
    </div>
    <%--<script type="text/javascript">--%>
    <%--window.paypalCheckoutReady = function () {--%>
    <%--paypal.checkout.setup('<%= new PayPal().getGvAPIUserName() %>', {--%>
    <%--button: 'placeOrderBtn',--%>
    <%--environment: '<%= new PayPal().getEnvironment() %>',--%>
    <%--condition: function () {--%>
    <%--return !!document.getElementById('paypal_payment_option').checked;--%>
    <%--}--%>
    <%--});--%>
    <%--};--%>
    <%--</script>--%>
    <%--<script src="//www.paypalobjects.com/api/checkout.js" async></script>--%>
    <%--</div> <!-- Row-Fluid ends here -->--%>
    <%--</div>  <!--Container-Fluid ends here -->--%>
    <%--<!-- Include all compiled plugins (below), or include individual files as needed -->--%>
    <%--<script src="js/bootstrap.min.js"></script>--%>
</body>
</html>