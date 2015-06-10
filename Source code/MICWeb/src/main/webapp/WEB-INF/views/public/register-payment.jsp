<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

<body>
<div class="image-container set-full-height"
     style="background-image: url('${pageContext.request.contextPath}/img/wizard-city.jpg')">
    <!--   MIC Branding   -->
    <a href="home.html">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/img/logoCompany.png"
                 style="height: 70px;">
        </div>
    </a>

    <!--   Big container   -->
    <div class="container">
        <div class="row">
            <div style="max-width: 800px;margin: 0 auto;">

                <!--      Wizard container        -->
                <div class="wizard-container">
                    <form role="form" action="${pageContext.request.contextPath}/public/checkout" method="get">
                        <div class="card wizard-card ct-wizard-azzure" id="wizard">

                            <!-- You can switch "ct-wizard-azzure"  with one of the next bright colors: "ct-wizard-blue",
                            "ct-wizard-green", "ct-wizard-orange", "ct-wizard-red" -->

                            <div class="wizard-header">
                                <h3>
                                    <b> TẠO HỢP ĐỒNG MỚI THÀNH CÔNG </b><br>
                                    <small>Vui lòng thanh toán để hợp đồng có hiệu lực.</small>
                                </h3>
                                <div class="col-sm-10 col-sm-offset-3">
                                    <p><b>Mã khách hàng:</b> ${register.customerEntity.customerCode}</p>
                                    <p><b>Mật khẩu đăng nhập hệ thống:</b> (kiểm tra email ${register.customerEntity.email})</p>
                                    <p><b>Mã hợp đồng:</b> ${register.contractEntity.contractCode}</p>
                                    <p><b>Phí cần thanh toán:</b> ${register.contractEntity.contractFee} VND</p>

                                </div>
                            </div>
                            <div class="row">
                                <br/>
                                <br/>
                                <div class="col-sm-10 col-sm-offset-1 text-center">
                                    <input type="submit" class="btn btn-primary btn-lg" value="Thanh toán ngay bây giờ"/>
                                </div>
                            </div>

                        </div>
                        <input type="hidden" name="L_PAYMENTREQUEST_0_NAME0" value="Create new contract">
                        <input type="hidden" name="L_PAYMENTREQUEST_0_DESC0" value="Create new contract">
                        <input type="hidden" name="L_PAYMENTREQUEST_0_QTY0" value="1">
                        <input type="hidden" name="PAYMENTREQUEST_0_ITEMAMT" value="${register.contractEntity.contractFee}">
                        <input type="hidden" name="PAYMENTREQUEST_0_TAXAMT" value="0">
                        <input type="hidden" name="PAYMENTREQUEST_0_AMT" value="${register.contractEntity.contractFee}">
                        <input type="hidden" name="currencyCodeType" value="USD">
                        <input type="hidden" name="paymentType" value="Sale">
                        <input type="hidden" name="checkout" value="true">
                        <input type="hidden" name="action" value="checkout">
                    </form>
                </div>
                <!-- wizard container -->
            </div>
        </div>
        <!-- row -->
    </div>


<script language="javascript">
    function update() {
        $('#txtName1').text($('#txtName').val());
        $('#txtAddress1').text($('#txtAddress').val());
        $('#txtEmail1').text($('#txtEmail').val());
        $('#txtPhone1').text($('#txtPhone').val());
        $('#txtPersonalId1').text($('#txtPersonalId').val());
        $('#txtStartDate1').text($('#txtStartDate').val());
        $('#ddlContractType1').text($('#ddlContractType option:selected').text());
        $('#txtFee1').text($('#txtFee').val());
        $('#txtPlate1').text($('#txtPlate').val());
        $('#txtBrand1').text($('#txtBrand').val());
        $('#txtModel1').text($('#txtModel').val());
        $('#txtType1').text($('#txtType').val());
        $('#txtColor1').text($('#txtColor').val());
        $('#txtEngine1').text($('#txtEngine').val());
        $('#txtChassis1').text($('#txtChassis').val());
        $('#txtCapacity1').text($('#txtCapacity').val());
        $('#txtYearOfMan1').text($('#txtYearOfMan').val());
        $('#txtWeight1').text($('#txtWeight').val());
        $('#txtSeatCapacity1').text($('#txtSeatCapacity').val());


    }
</script>

<%@ include file="_shared/footer.jsp" %>