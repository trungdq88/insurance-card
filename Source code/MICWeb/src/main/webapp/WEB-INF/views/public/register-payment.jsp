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
                    <form role="form" action="${pageContext.request.contextPath}/public/register" method="post">
                        <div class="card wizard-card ct-wizard-azzure" id="wizard">

                            <!-- You can switch "ct-wizard-azzure"  with one of the next bright colors: "ct-wizard-blue",
                            "ct-wizard-green", "ct-wizard-orange", "ct-wizard-red" -->

                            <div class="wizard-header">
                                <h3>
                                    <b> TẠO HỢP ĐỒNG MỚI THÀNH CÔNG </b><br>
                                    <small>Vui lòng thanh toán để hợp đồng có hiệu lực.</small>
                                </h3>
                                <div class="col-sm-10 col-sm-offset-3">
                                    <p><b>Mã khách hàng:</b> ABCABC</p>
                                    <p><b>Mật khẩu đăng nhập hệ thống:</b> (kiểm tra email khanhhang@gmail.com)</p>
                                    <p><b>Mã hợp đồng:</b> ABCABC</p>

                                </div>
                            </div>
                            <ul>
                                <li><a href="#payment" data-toggle="tab"><strong>Thanh toán</strong></a>
                                </li>
                            </ul>

                            <div class="tab-content">
                                <div class="tab-pane" id="payment">
                                    <h3 class="info-text"> PHƯƠNG THỨC THANH TOÁN </h3>
                                    <h5 class="info-text"> Quý khách vui lòng chọn phương thức thanh toán phí bảo
                                        hiểm </h5>

                                    <div class="row">
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="col-sm-4 col-sm-offset-2">
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
                                </div>
                            </div>

                            <div class="wizard-footer">
                                <div class="pull-right">
                                    <input type='button' onclick="update()"
                                           class='btn btn-next btn-fill btn-success btn-wd btn-sm'
                                           name='next' value='Tiếp theo'/>
                                    <input type="hidden" name="action" value="createContract"/>
                                    <input type='submit' class='btn btn-finish btn-fill btn-success btn-wd btn-sm'
                                           name='finish' value='Tạo hợp đồng và thanh toán'/>

                                </div>
                                <div class="pull-left">
                                    <input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm'
                                           name='previous' value='Quay lại'/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <input type="hidden" name="L_PAYMENTREQUEST_0_NAME0" value="${ddlContractype}">
                        <input type="hidden" name="L_PAYMENTREQUEST_0_DESC0" value="${ddlContractype}">
                        <input type="hidden" name="L_PAYMENTREQUEST_0_QTY0" value="1">
                        <input type="hidden" name="PAYMENTREQUEST_0_ITEMAMT" value="${txtFee/20000}">
                        <input type="hidden" name="PAYMENTREQUEST_0_TAXAMT" value="0">
                        <input type="hidden" name="PAYMENTREQUEST_0_AMT" value="${txtFee/20000}">
                        <input type="hidden" name="currencyCodeType" value="USD">
                        <input type="hidden" name="paymentType" value="Sale">
                        <input type="hidden" name="checkout" value="check out">
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