<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<body>
<div class="image-container set-full-height"
     style="background-image: url('${pageContext.request.contextPath}/img/wizard-city.jpg')">
    <!--   MIC Branding   -->
    <a href="home">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/img/logoCompany.png"
                 style="height: 70px;">
        </div>
    </a>
<div class="container">
    <div class="row">
        <div style="max-width: 800px;margin: 0 auto;">

            <!--      Wizard container        -->

            <div class="wizard-container">
                <div class="card wizard-card ct-wizard-azzure" id="wizard">

                    <!-- You can switch "ct-wizard-azzure"  with one of the next bright colors: "ct-wizard-blue",
                    "ct-wizard-green", "ct-wizard-orange", "ct-wizard-red" -->

                    <div class="wizard-header">
                        <h3>
                            <b> THANH TOÁN</b><br>
                            <p class="lead">Xin lựa chọn phương thức thanh toán sau:</p>

                        </h3>
                        <form class="form" action="${pageContext.request.contextPath}/public/register" method="POST">
                            <input type="hidden" name="L_PAYMENTREQUEST_0_AMT"
                                   value="${fn:escapeXml(PAYMENTREQUEST_0_AMT)}"/>
                            <div class="row">
                                <div class="col-sm-9 col-sm-offset-3">
                                    <div class="col-sm-4">
                                        <div class="choice" data-toggle="wizard-radio" rel="tooltip"
                                             title="Chọn nếu quý khách thanh toán qua Paypal">
                                            <input type="radio" name="type" value="Paypal">

                                            <div class="form-group">
                                                <!-- PayPal Logo -->
                                                <table border="0" cellpadding="6" cellspacing="0"
                                                       align="center">
                                                    <tr>
                                                        <td align="center"></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center">
                                                            <input type="submit" value=""
                                                                   style="background-image: url(https://www.paypalobjects.com/webstatic/mktg/logo/pp_cc_mark_74x46.jpg); border: solid 0px #000000; width: 150px; height: 94px;"/>
                                                        </td>

                                                    </tr>
                                                </table>
                                                <!-- PayPal Logo -->
                                            </div>
                                            <h6>Paypal</h6>
                                        </div>
                                    </div>

                                    <div class="col-sm-4">
                                        <a href="#companyAddress" data-toggle="collapse"
                                           aria-expanded="false" aria-controls="companyAddress">
                                            <div class="choice" data-toggle="wizard-radio" rel="tooltip"
                                                 title="Chọn nếu quý khách thanh toán trực tiếp">
                                                <input type="radio" name="type" value="Trực tiếp">

                                                <div class="icon">
                                                    <i class="fa fa-building"></i>
                                                </div>
                                                <h6>Trực tiếp</h6>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col-sm-10 col-sm-offset-1">
                                        <div class="collapse" id="companyAddress">
                                            <div class="form-group">
                                                <p class="text-muted"><b>Địa chỉ:</b> Lầu 2, tòa nhà Innovation,
                                                    lô 24, Công viên phần mềm
                                                    Quang Trung, P.Tân Chánh Hiệp, Quận 12, TP. Hồ Chí Minh.
                                                </p>

                                                <p class="text-muted"><b>Số điện thoại:</b> 0937337009</p>

                                                <p class="text-muted"><b>Email:</b> hydrangea8604@gmail.com</p>
                                                <img src="${pageContext.request.contextPath}/img/map.png" alt="Trường đại học FPT"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="action" value="checkout">

                        </form>
                    </div>



                </div>
            </div>
            <!-- wizard container -->
        </div>
    </div>
    <!-- row -->
</div>
    <!--  big container -->
</div>
</body>
<%@ include file="_shared/footer.jsp" %>
</html>