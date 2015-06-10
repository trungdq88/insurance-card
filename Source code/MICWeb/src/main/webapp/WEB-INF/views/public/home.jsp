<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MIC - Bảo hiểm trực tuyến</title>

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/form-elements.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-style.css">
    <!-- Google API Autocomplete for address-->
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
    <script>
        // This example displays an address form, using the autocomplete feature
        // of the Google Places API to help users fill in the information.

        var placeSearch, autocomplete;


        function initialize() {
            // Create the autocomplete object, restricting the search
            // to geographical location types.
            autocomplete = new google.maps.places.Autocomplete(
                    /** @type {HTMLInputElement} */(document.getElementById('txtAddress')),
                    {types: ['geocode'], componentRestrictions: {country: 'vn'}});
        }

        // [START region_geolocation]
        // Bias the autocomplete object to the user's geographical location,
        // as supplied by the browser's 'navigator.geolocation' object.
        function geolocate() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    var geolocation = new google.maps.LatLng(
                            position.coords.latitude, position.coords.longitude);
                    var circle = new google.maps.Circle({
                        center: geolocation,
                        radius: position.coords.accuracy
                    });
                    autocomplete.setBounds(circle.getBounds());
                });
            }
        }
        // [END region_geolocation]

    </script>
</head>

<body onload="initialize()">
<!-- Top menu -->
<nav class="navbar  navbar-no-bg" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img src="${pageContext.request.contextPath}/img/logoCompany.png" style="height: 80px;">
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="top-navbar-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <table id="login-box">
                        <tr>
                            <td class="text-left" style="padding-left: 16px">
                                Mã khách hàng/email
                            </td>
                            <td class="text-left" style="padding-left: 16px">
                                Mật Khẩu
                            </td>
                        </tr>
                        <tr>
                            <td class="col-md-5">
                                <input type="text" class="form-control"
                                       placeholder="">
                            </td>
                            <td class="col-md-5 ">
                                <input type="password" class="form-control">
                            </td>
                            <td>
                                <a href="/customer" class="btn btn-success">Đăng nhập</a>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-left" style="padding-left: 16px">
                                <input type="checkbox">
                                Duy trì đăng nhập
                            </td>
                            <td class="text-left" style="padding-left: 16px;">
                                <a href="#" style="color: white">Quên Mật Khẩu?</a>
                            </td>
                        </tr>
                    </table>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Top content -->
<div class="top-content">


    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <h1>Bảo hiểm xe máy</h1>

                <div id="tab1" class="tab-details" style="display: block;">
                    <img src="${pageContext.request.contextPath}/img/home-img.png"
                         style="width: 100%; margin: 15px 0; border-radius: 20px;"/>

                    <p style="text-align: justify;">
                        Chiếc xe máy là người bạn đồng hành không thể thiếu trong mỗi gia đình người Việt.
                        &nbsp;Tham gia <span style="color:#ff0000;"><strong>Gói bảo hiểm xe máy toàn diện
                    </strong></span> của MIC (bao gồm bảo hiểm cháy nổ xe, bảo hiểm trách nhiệm dân sự
                        chủ xe và bảo hiểm tai nạn người ngồi trên xe) với một khoản chi phí nhỏ
                        <span style="color:#ff0000;"><strong>chỉ khoảng 200.000 đồng</strong></span>, bạn
                        đã có thể bảo vệ toàn diện cho chiếc xe và người thân khi tham gia giao thông.</p>

                    <p>
                        &nbsp;</p>

                    <p class="text-center">
                        <a href="#" class="btn btn-success btn-lg">
                            <i class="fa fa-arrow-right"></i>
                            Xem thông tin các gói bảo hiểm
                        </a>
                    </p>
                </div>
            </div>
            <div class="col-md-7">
                <div class="form-top-left col-md-12">
                    <h3>Đăng ký ngay</h3>

                    <p>Tạo hợp đồng mới và thanh toán online</p>
                </div>
                <div class="form-top-right">
                    <i class="fa fa-pencil"></i>
                </div>
                <form role="form" action="${pageContext.request.contextPath}/public/register" method="post">
                    <div class="form-group">
                        <div class="form-group col-md-6">
                            <label>Họ tên *</label>
                            <input type="text" name="txtName"
                                   class="form-control" id="form-full-name">
                        </div>
                        <div class="form-group col-md-6">
                            <label>Email *</label>
                            <input type="text" name="txtEmail"
                                   class="form-control" id="form-email">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group col-md-6">
                            <label>Số điện thoại *</label>
                            <input type="text" name="txtPhone"
                                   class="form-control" id="form-phone">
                        </div>
                        <div class="form-group col-md-6">
                            <label>Số CMND/Hộ chiếu</label>
                            <input type="text" name="txtPersonalId"
                                   class="form-control" id="form-cmnd">
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label>Địa chỉ *</label>
                        <input type="text" name="txtAddress" onFocus="geolocate()"
                               class="form-control" id="txtAddress">
                    </div>
                    <div class="form-group">
                        <div class="form-group  col-md-5">
                            <label>Ngày bắt đầu *</label>
                            <input type="date" name="txtStartDate"
                                   class="form-control" id="form-startDate">
                        </div>
                        <div class="form-group  col-md-7">
                            <label>Hình Thức Bảo Hiểm *</label>
                            <select class="form-control" name="ddlContractType">
                                <option value="1">Xe trên 50cc có tham gia BH tai nạn người trên xe</option>
                                <option value="2">Xe trên 50cc không tham gia BH tai nạn người trên xe</option>
                                <option value="3">Xe từ 50cc trở xuống có tham gia BH tai nạn người trên xe</option>
                                <option value="4">Xe từ 50cc trở xuống không tham gia BH tai nạn người trên xe</option>
                                <option value="5">Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới tương tự</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-md-12">
                        <p class="form-control-static">
                            <label>Thời hạn:</label>
                            01 năm kể từ khi cấp GCNBH và/hoặc sau thời hạn thanh toán phí
                        </p>

                        <p class="form-control-static">
                            <label>Phí bảo hiểm:</label>
                            <b style="color: red">200.000 VND</b>
                            <input type="hidden" name="txtFee" value="200000">
                        </p>
                        <input type="hidden" name="action" value="register"/>
                        <input type="submit" class="btn btn-primary btn-lg" value="Tiếp theo"/>
                    </div>

                </form>

            </div>
        </div>


    </div>

    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/jquery.backstretch.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/retina-1.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</div>
</body>
</html>