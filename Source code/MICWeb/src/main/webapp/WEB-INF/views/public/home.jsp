<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html lang="en">
<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

<!--   plugins 	 -->
<script src="${pageContext.request.contextPath}/js/wizard.js"></script>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MIC - Bảo hiểm trực tuyến</title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/form-elements.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-distributed-with-address-and-phones.css">
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

                            <sql:setDataSource var="datasource" driver="com.mysql.jdbc.Driver"
                                               url="jdbc:mysql://localhost/mic_data"
                                               user="root" password=""/>
                            <sql:query dataSource="${datasource}" var="result">
                                SELECT * from mic_contract_type;
                            </sql:query>
                            <select class="form-control" name="ddlContractType" id="ddlContractType"
                                    onchange="updateFee()">
                                <c:forEach var="row" items="${result.rows}">
                                    <option name="<c:out value="${row.price_per_year}"/>"
                                            value="<c:out value="${row.id}"/>"><c:out value="${row.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-md-12">
                        <p class="form-control-static">
                            <label>Thời hạn:</label>
                            01 năm kể từ khi cấp
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

                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                &nbsp;
                &nbsp;
                &nbsp;
                <br/>
                <br/>
                <br/>
            </div>
        </div>

    </div>


</div>

<script language="javascript">
    function updateFee() {
        var fee = $('#txtFee');
        var newFee = $('#ddlContractType').option[$('#ddlContractType').selectedIndex].name;
        alert(" " + newFee.valueOf);
        fee.val($('#ddlContractType').option[$('#ddlContractType').selectedIndex].name);
    }
</script>
</body>

<%@ include file="_shared/footer.jsp" %>