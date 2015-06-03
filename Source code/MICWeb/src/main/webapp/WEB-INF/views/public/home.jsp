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

</head>

<body>
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
            <img src="${pageContext.request.contextPath}/img/logoCompany.png" style="width: 100px;height: 80px; background-color: white !important;">
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="top-navbar-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <table>
                        <tr>
                            <td class="text-left" style="padding-left: 16px">
                                Email Hoặc tên Đăng Nhập
                            </td>
                            <td class="text-left" style="padding-left: 16px">
                                Mật Khẩu
                            </td>
                        </tr>
                        <tr>
                            <td class="col-md-5">
                                <input type="text" class="form-control"
                                       placeholder="Tên Đăng Nhập">
                            </td>
                            <td class="col-md-5 ">
                                <input type="password" class="form-control">
                            </td>
                            <td>
                                <a href="/customer" class="btn btn-primary">Sign In</a>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-left" style="padding-left: 16px">
                                <input type="checkbox">
                                Duy Trì Đăng Nhập
                            </td>
                            <td class="text-left" style="padding-left: 16px">
                                <input type="checkbox">
                                Quên Mật Khẩu?
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
                <h1>Carousel</h1>

                <div id="carousel-example-captions" class="carousel slide" data-ride="carousel">
                    <%--<ol class="carousel-indicators">--%>
                        <%--<li data-target="#carousel-example-captions" data-slide-to="0" class=""></li>--%>
                        <%--<li data-target="#carousel-example-captions" data-slide-to="1" class="active"></li>--%>
                        <%--<li data-target="#carousel-example-captions" data-slide-to="2" class=""></li>--%>
                    <%--</ol>--%>
                    <div class="carousel-inner" role="listbox">
                        <%--<div class="item active">--%>
                        <%--<img alt="900x500"--%>
                        <%--src="http://tagbond.net/wp-content/uploads/2015/04/NFC-Logo-Image.jpg"--%>
                        <%--data-holder-rendered="true">--%>

                        <%--<div class="carousel-caption">--%>
                        <%--<h3 id="first-slide-label">First slide label<a class="anchorjs-link"--%>
                        <%--href="#first-slide-label"><span--%>
                        <%--class="anchorjs-icon"></span></a></h3>--%>

                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="item active">
                            <img style="width: 900px; height: 400px"
                            <%--src="http://athietkewebsite.com/wp-content/uploads/2014/03/thiet-ke-website-cac-cong-thanh-toan-online.jpg"--%>
                                 src="http://www.affairscloud.com/wp-content/uploads/2014/12/Insurance-32.jpg"
                                 data-holder-rendered="true">

                            <%--<div class="carousel-caption">--%>
                                <%--<h3 id="second-slide-label">Second slide label<a class="anchorjs-link"--%>
                                                                                 <%--href="#second-slide-label"><span--%>
                                        <%--class="anchorjs-icon"></span></a></h3>--%>


                            <%--</div>--%>
                        </div>
                        <%--<div class="item">--%>
                        <%--<img alt="900x500"--%>
                        <%--src="http://lqop.com/wp-content/uploads/2014/07/fastservice.png"--%>
                        <%--data-holder-rendered="true">--%>

                        <%--<div class="carousel-caption">--%>
                        <%--<h3 id="third-slide-label">Third slide label<a class="anchorjs-link"--%>
                        <%--href="#third-slide-label"><span--%>
                        <%--class="anchorjs-icon"></span></a></h3>--%>


                        <%--</div>--%>
                        <%--</div>--%>

                        <%--<a class="left carousel-control" href="#carousel-example-captions" role="button"--%>
                        <%--data-slide="prev">--%>
                        <%--<i class="fa fa-arrow-left" aria-hidden="true"></i>--%>
                        <%--<span class="sr-only">Previous</span>--%>
                        <%--</a>--%>
                        <%--<a class="right carousel-control" href="#carousel-example-captions" role="button"--%>
                        <%--data-slide="next">--%>
                        <%--<i class="fa fa-arrow-right" aria-hidden="true"></i>--%>
                        <%--<span class="sr-only">Next</span>--%>
                        <%--</a>--%>
                    </div>
                </div>

            </div>
            <div class="col-md-7">
                <div class="form-top-left col-md-12">
                    <h3>Đăng ký ngay</h3>

                    <p>Quý khách vui lòng điền những thông tin sau.</p>
                </div>
                <div class="form-top-right">
                    <i class="fa fa-pencil"></i>
                </div>
                <form role="form" action="${pageContext.request.contextPath}/public/register" method="post">
                    <div class="form-group">
                        <div class="form-group col-md-6">
                            Họ tên
                            <input type="text" name="form-full-name"
                                   class="form-control" id="form-full-name">
                        </div>
                        <div class="form-group col-md-6">
                            Email
                            <input type="text" name="form-email"
                                   class="form-control" id="form-email">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-group col-md-6">
                            Số điện thoại
                            <input type="text" name="form-phone"
                                   class="form-control" id="form-phone">
                        </div>
                        <div class="form-group col-md-6">
                            Số CMND/Hộ chiếu
                            <input type="text" name="form-cmnd"
                                   class="form-control" id="form-cmnd">
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        Địa chỉ
                        <input type="text" name="form-address"
                               class="form-control" id="form-address">
                    </div>
                    <div class="form-group  col-md-12">
                        Ngày bắt đầu
                        <input type="date" name="form-startDate"
                               class="form-control" id="form-startDate">
                    </div>
                    <div class="form-group  col-md-12">
                        Hình Thức Bảo Hiểm
                        <select class="form-control">
                            <option>Xe trên 50cc có tham gia BH tai nạn người trên xe</option>
                            <option>Xe trên 50cc không tham gia BH tai nạn người trên xe</option>
                            <option>Xe từ 50cc trở xuống có tham gia BH tai nạn người trên xe</option>
                            <option>Xe từ 50cc trở xuống không tham gia BH tai nạn người trên xe</option>
                            <option>Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới tương tự</option>
                        </select>
                    </div>
                    <div class="form-group  col-md-12">
                        <p class="form-control-static">
                            Thời hạn: 01 năm kể từ khi cấp GCNBH và/hoặc sau thời hạn thanh toán phí
                        </p>

                        <p class="form-control-static">
                            Phí bảo hiểm: 200.000 VND
                        </p>
                        <input type="hidden" name="action" value="register"/>
                        <input type="submit" class="btn" value="Tiếp theo"/>
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

</body>
</html>