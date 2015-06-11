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
                                    <b> ĐĂNG KÝ BẢO HIỂM NFC </b><br>
                                </h3>
                            </div>
                            <ul>
                                <li><a href="#personal" data-toggle="tab"><strong>1. Thông tin cá nhân </strong></a>
                                </li>
                                <li><a href="#vehicle" data-toggle="tab"><strong>2. Thông tin xe </strong></a></li>
                                <li><a href="#contract" onclick="update()" data-toggle="tab"><strong>3. Tạo hợp đồng và
                                    thanh toán </strong></a>
                                </li>
                            </ul>

                            <div class="tab-content">
                                <div class="tab-pane" id="personal">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <h3 class="info-text"> THÔNG TIN CHỦ SỞ HỮU XE </h3>
                                            <h5 class="info-text"> Các ô có dấu * là bắt buộc </h5>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Họ tên *</b></label>
                                                <input type="text" class="form-control" name="txtName" id="txtName"
                                                       placeholder="Tên đầy đủ người mua bảo hiểm?"
                                                       value="${customerEntity.name}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Địa chỉ *</b></label><br>
                                                <input type="text" class="form-control" name="txtAddress"
                                                       id="txtAddress"
                                                       placeholder="Địa chỉ của người mua bảo hiểm?"
                                                       value="${customerEntity.address}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Email *</b></label>
                                                <input type="text" class="form-control" name="txtEmail" id="txtEmail"
                                                       placeholder="Email của người mua bảo hiểm?"
                                                       value="${customerEntity.email}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Số điện thoại *</b></label>
                                                <input type="text" class="form-control" name="txtPhone" id="txtPhone"
                                                       placeholder="Số điện thoại của người mua bảo hiểm?"
                                                       value="${customerEntity.phone}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số CMDN/Hộ chiếu</b></label>
                                                <input type="text" class="form-control" name="txtPersonalId"
                                                       id="txtPersonalId"
                                                       placeholder="Số CMND hoặc hộ chiếu?"
                                                       value="${customerEntity.personalId}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Ngày bắt đầu *</b></label>
                                                <input type="date" class="form-control" name="txtStartDate"
                                                       id="txtStartDate"
                                                       placeholder="Ngày bắt đầu tham gia bảo hiểm?"
                                                       value="${txtStartDate}">
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Quyền lợi bảo hiểm *</b></label>
                                                <select class="form-control" name="ddlContractType"
                                                        id="ddlContractType">
                                                    <option <%=request.getParameter("ddlContractType").equals("1") ? "selected='selected'" : "" %>
                                                            value="1">Xe trên 50cc có tham gia BH tai nạn người trên xe
                                                    </option>
                                                    <option <%=request.getParameter("ddlContractType").equals("2") ? "selected='selected'" : "" %>
                                                            value="2">Xe trên 50cc không tham gia BH tai nạn người trên
                                                        xe
                                                    </option>
                                                    <option <%=request.getParameter("ddlContractType").equals("3") ? "selected='selected'" : "" %>
                                                            value="3">Xe từ 50cc trở xuống có tham gia BH tai nạn người
                                                        trên xe
                                                    </option>
                                                    <option <%=request.getParameter("ddlContractType").equals("4") ? "selected='selected'" : "" %>
                                                            value="4">Xe từ 50cc trở xuống không tham gia BH tai nạn
                                                        người trên
                                                        xe
                                                    </option>
                                                    <option <%=request.getParameter("ddlContractType").equals("5") ? "selected='selected'" : "" %>
                                                            value="5">Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới
                                                        tương
                                                        tự
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Thời hạn: </b></label>

                                                <p class="form-control-static">01 năm kể từ khi cấp GCNBH và/hoặc sau
                                                    thời hạn thanh toán phí</p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Phí bảo hiểm: </b></label>
                                                <input type="hidden" name="txtFee" value="${txtFee}" id="txtFee">

                                                <p class="form-control-static" style="color: red">${txtFee} VND</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="vehicle">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <h3 class="info-text"> THÔNG TIN XE </h3>
                                            <h5 class="info-text"> Quý khách vui lòng nhập thông tin dựa trên đăng ký xe
                                            </h5>
                                            <h5 class="info-text">Các ô có dấu * là bắt buộc</h5>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Biển số đăng ký *</b></label>
                                                <input type="text" class="form-control" name="txtPlate" id="txtPlate"
                                                       placeholder="Biển số của xe đăng ký bảo hiểm?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Nhãn hiệu *</b></label><br>
                                                <input type="text" class="form-control" name="txtBrand" id="txtBrand"
                                                       placeholder="Ví dụ: Honda, Yamaha, Suzuki...">
                                            </div>
                                        </div>
                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số khung *</b></label>
                                                <input type="text" class="form-control" name="txtChassis"
                                                       id="txtChassis"
                                                       placeholder="Số khung của xe?">

                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label><b>Số máy *</b></label>
                                                <input type="text" class="form-control" name="txtEngine" id="txtEngine"
                                                       placeholder="Số máy của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label><b>Dung tích *</b></label>
                                                <input type="text" class="form-control" name="txtCapacity"
                                                       id="txtCapacity"
                                                       placeholder="Dung tích của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số loại</b></label>
                                                <input type="text" class="form-control" name="txtModel" id="txtModel"
                                                       placeholder="Ví dụ: Air Blade, Future, Wave...">
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label><b>Loại xe</b></label>
                                                <input type="text" class="form-control" name="txtType" id="txtType"
                                                       placeholder="Ví dụ: hai bánh, ba bánh, khác...">
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label><b>Màu sơn</b></label>
                                                <input type="text" class="form-control" name="txtColor" id="txtColor"
                                                       placeholder="Màu sơn của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Năm sản xuất </b></label>
                                                <input type="text" class="form-control" name="txtYearOfMan"
                                                       id="txtYearOfMan"
                                                       placeholder="Năm xe được sản xuất?">
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label><b>Tự trọng (kg)</b></label>
                                                <input type="text" class="form-control" name="txtWeight" id="txtWeight"
                                                       placeholder="Trọng lượng không tải của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label><b>Số người</b></label>
                                                <input type="text" class="form-control" name="txtSeatCapacity"
                                                       id="txtSeatCapacity"
                                                       placeholder="Số người xe được phép chở?">
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                    <br/>
                                    <br/>
                                </div>

                                <div class="tab-pane" id="contract">
                                    <h3 class="info-text"> THÔNG TIN HỢP ĐỒNG BẢO HIỂM </h3>
                                    <h5 class="info-text">Quý khách vui lòng kiểm tra lại thông tin hợp đồng bảo hiểm
                                        đã đăng ký </h5>

                                    <div class="row text-big">
                                        <div class="col-sm-11 col-sm-offset-1">
                                            <label><b>Tên: </b><span id="txtName1"></span></label>
                                        </div>
                                        <div class="col-sm-11 col-sm-offset-1">
                                            <label><b>Địa chỉ: </b> <span id="txtAddress1"></span></label>

                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <label><b>Email: </b></label>
                                            <label id="txtEmail1"></label>
                                        </div>
                                        <div class="col-sm-5">
                                            <label><b>Số điện thoại: </b></label>
                                            <label id="txtPhone1"></label>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <label><b>Số CMDN/Hộ chiếu: </b></label>
                                            <label id="txtPersonalId1"></label>
                                        </div>
                                        <div class="col-sm-5">
                                            <label><b>Ngày bắt đầu: </b></label>
                                            <label id="txtStartDate1"></label>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <label><b>Quyền lợi bảo hiểm: </b></label>
                                            <label id="ddlContractType1"></label>
                                            <%--<select class="form-control" name="ddlContractType1"  id="ddlContractType1">--%>
                                            <%--<option <%=request.getParameter("ddlContractType").equals("1") ? "selected='selected'": "" %> value="1">Xe trên 50cc có tham gia BH tai nạn người trên xe</option>--%>
                                            <%--<option <%=request.getParameter("ddlContractType").equals("2") ? "selected='selected'": "" %> value="2">Xe trên 50cc không tham gia BH tai nạn người trên xe--%>
                                            <%--</option >--%>
                                            <%--<option <%=request.getParameter("ddlContractType").equals("3") ? "selected='selected'": "" %> value="3">Xe từ 50cc trở xuống có tham gia BH tai nạn người trên xe--%>
                                            <%--</option>--%>
                                            <%--<option <%=request.getParameter("ddlContractType").equals("4") ? "selected='selected'": "" %> value="4">Xe từ 50cc trở xuống không tham gia BH tai nạn người trên--%>
                                            <%--xe--%>
                                            <%--</option>--%>
                                            <%--<option <%=request.getParameter("ddlContractType").equals("5") ? "selected='selected'": "" %> value="5">Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới tương--%>
                                            <%--tự--%>
                                            <%--</option>--%>
                                            <%--</select>--%>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <label><b>Thời hạn: </b></label>
                                            01 năm kể từ khi cấp GCNBH và/hoặc sau
                                            thời hạn thanh toán phí
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <label><b>Phí bảo hiểm (VND): </b></label>
                                            <label id="txtFee1"></label>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <label><b>Biển số đăng ký: </b></label>
                                            <label id="txtPlate1"></label>
                                        </div>
                                        <div class="col-sm-5">
                                            <label><b>Nhãn hiệu: </b></label>
                                            <label id="txtBrand1"></label>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <label><b>Số loại: </b></label>
                                            <label id="txtModel1"></label>
                                        </div>
                                        <div class="col-sm-5">
                                            <label><b>Loại xe: </b></label>
                                            <label id="txtType1"></label>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <label><b>Màu sơn: </b></label>
                                            <label id="txtColor1"></label>
                                        </div>
                                        <div class="col-sm-5">
                                            <label><b>Số máy: </b></label>
                                            <label id="txtEngine1"></label>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <label><b>Số khung: </b></label>
                                            <label id="txtChassis1"></label>
                                        </div>
                                        <div class="col-sm-5">
                                            <label><b>Dung tích: </b></label>
                                            <label id="txtCapacity1"></label>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <label><b>Năm sản xuất: </b></label>
                                            <label id="txtYearOfMan1"></label>
                                        </div>
                                        <div class="col-sm-5">
                                            <label><b>Tự trọng (kg): </b></label>
                                            <label id="txtWeight1"></label>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <label><b>Số người: </b></label>
                                            <label id="txtSeatCapacity1"></label>
                                        </div>
                                    </div>
                                </div>

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
                                                        <img src="${pageContext.request.contextPath}/img/map.png"
                                                             alt="Trường đại học FPT"/>
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
                        <%--<input type="hidden" name="L_PAYMENTREQUEST_0_NAME0" value="${ddlContractype}">--%>
                        <%--<input type="hidden" name="L_PAYMENTREQUEST_0_DESC0" value="${ddlContractype}">--%>
                        <%--<input type="hidden" name="L_PAYMENTREQUEST_0_QTY0" value="1">--%>
                        <%--<input type="hidden" name="PAYMENTREQUEST_0_ITEMAMT" value="${txtFee/20000}">--%>
                        <%--<input type="hidden" name="PAYMENTREQUEST_0_TAXAMT" value="0">--%>
                        <%--<input type="hidden" name="PAYMENTREQUEST_0_AMT" value="${txtFee/20000}">--%>
                        <%--<input type="hidden" name="currencyCodeType" value="USD">--%>
                        <%--<input type="hidden" name="paymentType" value="Sale">--%>
                        <input type="hidden" name="checkout" value="check out">
                        <%--<input type="hidden" name="successUrl" value="/public/register?action=activeContract">--%>
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
</div>
</body>
<%@ include file="_shared/footer.jsp" %>