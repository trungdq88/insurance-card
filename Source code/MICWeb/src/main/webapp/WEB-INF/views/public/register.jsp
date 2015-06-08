<%@ page import="com.fpt.mic.micweb.model.entity.CustomerEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<script language="javascript">
    function update() {
        document.getElementById('txtName1').value = document.getElementById('txtName').value;
        document.getElementById('txtAddress1').value = document.getElementById('txtAddress').value;
        document.getElementById('txtEmail1').value = document.getElementById('txtEmail').value;
        document.getElementById('txtPhone1').value = document.getElementById('txtPhone').value;
        document.getElementById('txtPersonalId1').value = document.getElementById('txtPersonalId').value;
        document.getElementById('txtStartDate1').value = document.getElementById('txtStartDate').value;
        document.getElementById('ddlContractType').value = document.getElementById('ddlContractType').value;
        document.getElementById('txtFee1').value = document.getElementById('txtFee').value;
        document.getElementById('txtPlate1').value = document.getElementById('txtPlate').value;
        document.getElementById('txtBrand1').value = document.getElementById('txtBrand').value;
        document.getElementById('txtModel1').value = document.getElementById('txtModel').value;
        document.getElementById('txtType1').value = document.getElementById('txtType').value;
        document.getElementById('txtColor1').value = document.getElementById('txtColor').value;
        document.getElementById('txtEngine1').value = document.getElementById('txtEngine').value;
        document.getElementById('txtChassis1').value = document.getElementById('txtChassis').value;
        document.getElementById('txtCapacity1').value = document.getElementById('txtCapacity').value;
        document.getElementById('txtYearOfMan1').value = document.getElementById('txtYearOfMan').value;
        document.getElementById('txtWeight1').value = document.getElementById('txtWeight').value;
        document.getElementById('txtSeatCapacity1').value = document.getElementById('txtSeatCapacity').value;



    }
</script>

<head>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>MIC - Bảo hiểm trực tuyến</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gsdk-base.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-distributed-with-address-and-phones.css">

    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">

</head>

<body>
<div class="image-container set-full-height" style="background-image: url('${pageContext.request.contextPath}/img/wizard-city.jpg')">
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

    <!--   Big container   -->
    <div class="container">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2">

                <!--      Wizard container        -->
                <div class="wizard-container">
                    <form role="form" action="${pageContext.request.contextPath}/public/register" method="post">
                        <div class="card wizard-card ct-wizard-azzure" id="wizard">

                            <!-- You can switch "ct-wizard-azzure"  with one of the next bright colors: "ct-wizard-blue",
                            "ct-wizard-green", "ct-wizard-orange", "ct-wizard-red" -->

                            <div class="wizard-header">
                                <h3>
                                    <b> ĐĂNG KÝ BẢO HIỂM NFC </b><br>
                                    <small>Vui lòng điền đầy đủ những thông tin dưới đây.</small>
                                </h3>
                            </div>
                            <ul>
                                <li><a href="#personal" data-toggle="tab"><strong>1. Thông tin cá nhân </strong></a>
                                </li>
                                <li><a href="#vehicle" data-toggle="tab"><strong>2. Thông tin xe </strong></a></li>
                                <li><a href="#contract" onclick="update()" data-toggle="tab"><strong>3. Hợp đồng bảo hiểm </strong></a>
                                </li>
                                <li><a href="#payment" data-toggle="tab"><strong>4. Thanh toán </strong></a></li>
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
                                                       placeholder="Tên đầy đủ người mua bảo hiểm?" value="${customerEntity.name}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Địa chỉ *</b></label><br>
                                                <input type="text" class="form-control" name="txtAddress" id="txtAddress"
                                                       placeholder="Địa chỉ của người mua bảo hiểm?" value="${customerEntity.address}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Email *</b></label>
                                                <input type="text" class="form-control" name="txtEmail" id="txtEmail"
                                                       placeholder="Email của người mua bảo hiểm?" value="${customerEntity.email}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Số điện thoại *</b></label>
                                                <input type="text" class="form-control" name="txtPhone" id="txtPhone"
                                                       placeholder="Số điện thoại của người mua bảo hiểm?" value="${customerEntity.phone}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số CMDN/Hộ chiếu</b></label>
                                                <input type="text" class="form-control" name="txtPersonalId" id="txtPersonalId"
                                                       placeholder="Số CMND hoặc hộ chiếu?" value="${customerEntity.personalId}">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Ngày bắt đầu *</b></label>
                                                <input type="date" class="form-control" name="txtStartDate" id="txtStartDate"
                                                       placeholder="Ngày bắt đầu tham gia bảo hiểm?" value="${txtStartDate}">
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Quyền lợi bảo hiểm *</b></label>
                                                <select class="form-control" name="ddlContractType" id="ddlContractType" >
                                                    <option <%=request.getParameter("ddlContractType").equals("1") ? "selected='selected'": "" %> value="1">Xe trên 50cc có tham gia BH tai nạn người trên xe</option>
                                                    <option <%=request.getParameter("ddlContractType").equals("2") ? "selected='selected'": "" %> value="2">Xe trên 50cc không tham gia BH tai nạn người trên xe
                                                    </option >
                                                    <option <%=request.getParameter("ddlContractType").equals("3") ? "selected='selected'": "" %> value="3">Xe từ 50cc trở xuống có tham gia BH tai nạn người trên xe
                                                    </option>
                                                    <option <%=request.getParameter("ddlContractType").equals("4") ? "selected='selected'": "" %> value="4">Xe từ 50cc trở xuống không tham gia BH tai nạn người trên
                                                        xe
                                                    </option>
                                                    <option <%=request.getParameter("ddlContractType").equals("5") ? "selected='selected'": "" %> value="5">Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới tương
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
                                                <p class="form-control-static">${txtFee} VND</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="vehicle">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <h3 class="info-text"> THÔNG TIN XE </h3>
                                            <h5 class="info-text"> Quý khách có thể nhập thông tin dựa trên đăng ký xe
                                                hoặc tải lên ảnh chụp </h5>
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
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số loại *</b></label>
                                                <input type="text" class="form-control" name="txtModel" id="txtModel"
                                                       placeholder="Ví dụ: Air Blade, Future, Wave...">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Loại xe *</b></label>
                                                <input type="text" class="form-control" name="txtType" id="txtType"
                                                       placeholder="Ví dụ: hai bánh, ba bánh, khác...">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Màu sơn *</b></label>
                                                <input type="text" class="form-control" name="txtColor" id="txtColor"
                                                       placeholder="Màu sơn của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Số máy *</b></label>
                                                <input type="text" class="form-control" name="txtEngine" id="txtEngine"
                                                       placeholder="Số máy của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số khung *</b></label>
                                                <input type="text" class="form-control" name="txtChassis" id="txtChassis"
                                                       placeholder="Số khung của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Dung tích *</b></label>
                                                <input type="text" class="form-control" name="txtCapacity" id="txtCapacity"
                                                       placeholder="Dung tích của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Năm sản xuất *</b></label>
                                                <input type="text" class="form-control" name="txtYearOfMan" id="txtYearOfMan"
                                                       placeholder="Năm xe được sản xuất?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Tự trọng (kg) *</b></label>
                                                <input type="text" class="form-control" name="txtWeight" id="txtWeight"
                                                       placeholder="Trọng lượng không tải của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số người *</b></label>
                                                <input type="text" class="form-control" name="txtSeatCapacity" id="txtSeatCapacity"
                                                       placeholder="Số người xe được phép chở?">
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Tải lên </b></label>
                                                <input type="file" class="form-control" name="txtCertImage" id="txtCertImage"
                                                       placeholder="Ảnh chụp hình đăng ký xe?">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="contract">
                                    <h3 class="info-text"> THÔNG TIN HỢP ĐỒNG BẢO HIỂM </h3>
                                    <h5 class="info-text"> Quý khách vui lòng kiểm tra lại thông tin hợp đồng bảo hiểm
                                        đã đăng ký </h5>

                                    <div class="row">
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Name: </b></label>
                                                    <input type="text" class="form-control" name="txtName1" value="" id="txtName1" readonly>

                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Address: </b></label>
                                                    <input type="text" class="form-control" name="txtAddress1" value="" id="txtAddress1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Email: </b></label>
                                                    <input type="text" class="form-control" name="txtEmail1" value="" id="txtEmail1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số điện thoại: </b></label>
                                                    <input type="text" class="form-control" name="txtPhone1" id="txtPhone1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số CMDN/Hộ chiếu: </b></label>
                                                    <input type="text" class="form-control" name="txtPersonalId1" id="txtPersonalId1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Ngày bắt đầu: </b></label>
                                                    <input type="date" class="form-control" name="txtStartDate1" id="txtStartDate1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Quyền lợi bảo hiểm: </b></label>
                                                    <select class="form-control" name="ddlContractType1"  id="ddlContractType1" readonly>
                                                        <option <%=request.getParameter("ddlContractType").equals("1") ? "selected='selected'": "" %> value="1">Xe trên 50cc có tham gia BH tai nạn người trên xe</option>
                                                        <option <%=request.getParameter("ddlContractType").equals("2") ? "selected='selected'": "" %> value="2">Xe trên 50cc không tham gia BH tai nạn người trên xe
                                                        </option >
                                                        <option <%=request.getParameter("ddlContractType").equals("3") ? "selected='selected'": "" %> value="3">Xe từ 50cc trở xuống có tham gia BH tai nạn người trên xe
                                                        </option>
                                                        <option <%=request.getParameter("ddlContractType").equals("4") ? "selected='selected'": "" %> value="4">Xe từ 50cc trở xuống không tham gia BH tai nạn người trên
                                                            xe
                                                        </option>
                                                        <option <%=request.getParameter("ddlContractType").equals("5") ? "selected='selected'": "" %> value="5">Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới tương
                                                            tự
                                                        </option>
                                                    </select>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Thời hạn: </b></label>
                                                    01 năm kể từ khi cấp GCNBH và/hoặc sau
                                                    thời hạn thanh toán phí
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Phí bảo hiểm (VND): </b></label>
                                                <input type="text" class="form-control" name="txtFee1" value="" id="txtFee1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Biển số đăng ký: </b></label>
                                                    <input type="text" class="form-control" name="txtPlate1" id="txtPlate1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Nhãn hiệu: </b></label>
                                                    <input type="text" class="form-control" name="txtBrand1" id="txtBrand1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số loại: </b></label>
                                                    <input type="text" class="form-control" name="txtModel1" id="txtModel1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Loại xe: </b></label>
                                                    <input type="text" class="form-control" name="txtType1" id="txtType1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Màu sơn: </b></label>
                                                    <input type="text" class="form-control" name="txtColor1" id="txtColor1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số máy: </b></label>
                                                    <input type="text" class="form-control" name="txtEngine1" id="txtEngine1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số khung: </b></label>
                                                    <input type="text" class="form-control" name="txtChassis1" id="txtChassis1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Dung tích: </b></label>
                                                    <input type="text" class="form-control" name="txtCapacity1" id="txtCapacity1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Năm sản xuất: </b></label>
                                                    <input type="text" class="form-control" name="txtYearOfMan1" id="txtYearOfMan1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Tự trọng (kg): </b></label>
                                                    <input type="text" class="form-control" name="txtWeight1" id="txtWeight1" readonly>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số người: </b></label>
                                                    <input type="text" class="form-control" name="txtSeatCapacity1" id="txtSeatCapacity1" readonly>
                                                </p>
                                            </div>
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
                                                                    <input type="submit" value="" style="background-image: url(https://www.paypalobjects.com/webstatic/mktg/logo/pp_cc_mark_74x46.jpg); border: solid 0px #000000; width: 150px; height: 94px;" />
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
                                    <input type='button' onclick="update()" class='btn btn-next btn-fill btn-success btn-wd btn-sm'
                                           name='next' value='Tiếp theo'/>
                                    <input type="hidden" name="action" value="createContract"/>
                                    <input type='submit' class='btn btn-finish btn-fill btn-success btn-wd btn-sm'
                                           name='finish' value='Hoàn tất'/>

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

    <!--<div class="fixed-plugin">
        <div class="dropdown open">
            <a href="#" data-toggle="dropdown">
                <i class="fa fa-cog fa-2x"> </i>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="http://ct-freebies.herokuapp.com/wizard-demo-register">
                        <img src="images/thumb_register.png">
                        Register user flow
                    </a>
                </li>
                <li>
                    <a href="http://ct-freebies.herokuapp.com/wizard-demo-list-boat">
                        <img src="images/thumb_list_boat.png">
                        List your boat
                    </a>
                </li>
                <li class="active">
                    <a href="http://ct-freebies.herokuapp.com/wizard-demo-list-place">
                        <img src="images/thumb_list_place.png">
                        List your place
                    </a>
                </li>
                <li>
                    <a href="http://ct-freebies.herokuapp.com/wizard-components" target="_blank"
                       class="btn btn-default btn-fill">How to use</a>
                </li>
                <li>
                    <a href="http://www.creative-tim.com/product/wizard" target="_blank" class="btn btn-info btn-fill">Download,
                        it's free!</a>
                </li>
                <li><a></a></li>
            </ul>
        </div>
    </div>-->
</div>

</body>

<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

<!--   plugins 	 -->
<script src="${pageContext.request.contextPath}/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/wizard.js"></script>

</html>