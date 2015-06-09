<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>MIC - Bảo hiểm trực tuyến</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gsdk-base.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-distributed-with-address-and-phones.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register-style.css">

    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">

</head>
<body>
<div class="image-container set-full-height"
     style="background-image: url('${pageContext.request.contextPath}/img/wizard-city.jpg')">
    <!--   MIC Branding   -->
    <a href="home.html">
        <div class="logo-container">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/img/unnamed.png">
            </div>
            <div class="brand">
                MIC
            </div>
        </div>
    </a>

    <div class="container">
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
                        <input type="hidden" name="L_PAYMENTREQUEST_0_AMT"
                               value="${fn:escapeXml(PAYMENTREQUEST_0_AMT)}"/>

                        <div class="row-fluid">
                            <div class="span6 inner-span">
                                <p class="lead">Xác nhận thanh toán </p>
                                <table>
                                    <tr>
                                        <td width="30%">Họ tên khách hàng:</td>
                                        <td>${sessionScope.checkoutDetails.txtName}</td>
                                    </tr>
                                    <tr>
                                        <td>Địa chỉ:</td>
                                        <td>${sessionScope.checkoutDetails.txtAddress}</td>
                                    </tr>
                                    <tr>
                                        <td>Số tiền thanh toán:</td>
                                        <td> ${sessionScope.checkoutDetails.txtFee/20000} (USD)</td>
                                        <>
                                </table>
                                <input type="hidden" name="action" value="checkout">
                                <input type="submit" id="placeOrderBtn" class="btn btn-primary btn-large"
                                       value="Thanh Toán"/>
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
            </div>
        </div>
    </div>
    <!--  big container -->
    <footer class="footer-distributed">
        <div class="footer-left">
            <h3>Company<span>logo</span></h3>

            <p class="footer-links">
                <a href="home.html">Trang chủ</a>
                ·
                <a href="#">Liên hệ</a>
                ·
                <a href="#">Hỗ trợ</a>
            </p>

            <p class="footer-company-name">MIC Group 2 &copy; 2015</p>
        </div>

        <div class="footer-center">
            <div>
                <i class="fa fa-map-marker"></i>

                <p>
                    <span>Tòa nhà Innovation, Công viên phần mềm Quang Trung</span>
                    P. Tân Chánh Hiệp, Quận 12, TP. Hồ Chí Minh.
                </p>
            </div>
            <div>
                <i class="fa fa-phone"></i>

                <p>+84 37337009</p>
            </div>
            <div>
                <i class="fa fa-envelope"></i>

                <p><a href="mailto:hydrangea8604@gmail.com">hydrangea8604@gmail.com</a></p>
            </div>
        </div>
        <div class="footer-right">
            <p class="footer-company-about">
                <span>Sơ lược về đề tài</span>
            </p>

            <p class="footer-company-name">Bản quyền thuộc về Group 2</p>
        </div>
    </footer>
</div>
</body>
</html>