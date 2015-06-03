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

    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">

</head>

<body>
<div class="image-container set-full-height" style="background-image: url('${pageContext.request.contextPath}/img/wizard-city.jpg')">
    <!--   MIC Branding   -->
    <a href="home.html">
        <div class="logo-container">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/img/logoCompany.png" style="width: 60px;height: 60px">
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
                    <form action="" method="">
                        <div class="card wizard-card ct-wizard-azzure" id="wizard">

                            <!-- You can switch "ct-wizard-azzure"  with one of the next bright colors: "ct-wizard-blue",
                            "ct-wizard-green", "ct-wizard-orange", "ct-wizard-red" -->

                            <div class="wizard-header">
                                <h3>
                                    <b> ĐĂNG KÝ BẢO HIỂM NFC </b><br>
                                    <small>Vui lòng điền đầy đủ những thông tin dưới đây.</small>
                                </h3>
                            </div>

                            <ul class="nav nav-tabs" role="tablist">
                                <li><a href="#personal" data-toggle="tab">1. Thông tin cá nhân </a>
                                </li>
                                <li><a href="#vehicle" data-toggle="tab">2. Thông tin xe </a></li>
                                <li><a href="#contract" data-toggle="tab">3. Hợp đồng bảo hiểm </a>
                                </li>
                                <li><a href="#payment" data-toggle="tab">4. Thanh toán </a></li>
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
                                                <input type="text" class="form-control" id="inputFullname"
                                                       placeholder="Tên đầy đủ người mua bảo hiểm?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Địa chỉ *</b></label><br>
                                                <input type="text" class="form-control" id="inputAddress"
                                                       placeholder="Địa chỉ của người mua bảo hiểm?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Email *</b></label>
                                                <input type="text" class="form-control" id="inputEmail"
                                                       placeholder="Email của người mua bảo hiểm?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Số điện thoại *</b></label>
                                                <input type="text" class="form-control" id="inputPhone"
                                                       placeholder="Số điện thoại của người mua bảo hiểm?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số CMDN/Hộ chiếu</b></label>
                                                <input type="text" class="form-control" id="inputCMND"
                                                       placeholder="Số CMND hoặc hộ chiếu?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Ngày bắt đầu *</b></label>
                                                <input type="date" class="form-control" id="inputStartDate"
                                                       placeholder="Ngày bắt đầu tham gia bảo hiểm?">
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Quyền lợi bảo hiểm *</b></label>
                                                <select class="form-control">
                                                    <option>Xe trên 50cc có tham gia BH tai nạn người trên xe</option>
                                                    <option>Xe trên 50cc không tham gia BH tai nạn người trên xe
                                                    </option>
                                                    <option>Xe từ 50cc trở xuống có tham gia BH tai nạn người trên xe
                                                    </option>
                                                    <option>Xe từ 50cc trở xuống không tham gia BH tai nạn người trên
                                                        xe
                                                    </option>
                                                    <option>Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới tương
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

                                                <p class="form-control-static">200.000 VND</p>
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
                                                <input type="text" class="form-control" id="inputPlateNumber"
                                                       placeholder="Biển số của xe đăng ký bảo hiểm?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Nhãn hiệu *</b></label><br>
                                                <input type="text" class="form-control" id="inputBrand"
                                                       placeholder="Ví dụ: Honda, Yamaha, Suzuki...">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số loại *</b></label>
                                                <input type="text" class="form-control" id="inputModelCode"
                                                       placeholder="Ví dụ: Air Blade, Future, Wave...">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Loại xe *</b></label>
                                                <input type="text" class="form-control" id="inputType"
                                                       placeholder="Ví dụ: hai bánh, ba bánh, khác...">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Màu sơn *</b></label>
                                                <input type="text" class="form-control" id="inputColor"
                                                       placeholder="Màu sơn của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Số máy *</b></label>
                                                <input type="text" class="form-control" id="inputEngine"
                                                       placeholder="Số máy của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số khung *</b></label>
                                                <input type="text" class="form-control" id="inputChassis"
                                                       placeholder="Số khung của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Dung tích *</b></label>
                                                <input type="text" class="form-control" id="inputCapacity"
                                                       placeholder="Dung tích của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Năm sản xuất *</b></label>
                                                <input type="text" class="form-control" id="inputYearOfMan"
                                                       placeholder="Năm xe được sản xuất?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <label><b>Tự trọng (kg) *</b></label>
                                                <input type="text" class="form-control" id="inputEmptyWeight"
                                                       placeholder="Trọng lượng không tải của xe?">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Số người *</b></label>
                                                <input type="text" class="form-control" id="inputSeatCapacity"
                                                       placeholder="Số người xe được phép chở?">
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <label><b>Tải lên </b></label>
                                                <input type="file" class="form-control" id="inputCertImage"
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
                                                    <label><b>Họ tên: </b></label>
                                                    Nguyễn Chí Kha
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Địa chỉ: </b></label>
                                                    82/5 Đông Hưng Thuận 5
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Email: </b></label>
                                                    hydrangea8604@gmail.com
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số điện thoại: </b></label>
                                                    0937337009
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số CMDN/Hộ chiếu: </b></label>
                                                    123456789
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Ngày bắt đầu: </b></label>
                                                    23/05/2015
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-10 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Quyền lợi bảo hiểm: </b></label>
                                                    Xe trên 50cc có tham gia BH tai nạn người
                                                    trên xe
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
                                                    <label><b>Phí bảo hiểm: </b></label>
                                                    200.000 VND
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Biển số đăng ký: </b></label>
                                                    59Y1-48384
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Nhãn hiệu: </b></label>
                                                    Honda
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số loại: </b></label>
                                                    Air Blade
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Loại xe: </b></label>
                                                    Hai bánh
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Màu sơn: </b></label>
                                                    Đen Bạc
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số máy: </b></label>
                                                    48373834
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số khung: </b></label>
                                                    5435323
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Dung tích: </b></label>
                                                    108
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Năm sản xuất: </b></label>
                                                    2011
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Tự trọng (kg): </b></label>
                                                    150</p>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-sm-offset-1">
                                            <div class="form-group">
                                                <p class="form-control-static">
                                                    <label><b>Số người: </b></label>
                                                    2</p>
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
                                                                    <a href="https://www.paypal.com/webapps/mpp/paypal-popup"
                                                                       title="How PayPal Works"
                                                                       onclick="javascript:window.open('https://www.paypal.com/webapps/mpp/paypal-popup','WIPaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1060, height=700'); return false;"><img
                                                                            src="https://www.paypalobjects.com/webstatic/mktg/logo/pp_cc_mark_74x46.jpg"
                                                                            border="0" alt="PayPal Logo"></a>
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
                                    <input type='button' class='btn btn-next btn-fill btn-success btn-wd btn-sm'
                                           name='next' value='Tiếp theo'/>
                                    <input type='button' class='btn btn-finish btn-fill btn-success btn-wd btn-sm'
                                           name='finish' value='Hoàn tất'/>

                                </div>
                                <div class="pull-left">
                                    <input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm'
                                           name='previous' value='Quay lại'/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
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


</div>

</body>

<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

<!--   plugins 	 -->
<script src="${pageContext.request.contextPath}/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/wizard.js"></script>

</html>