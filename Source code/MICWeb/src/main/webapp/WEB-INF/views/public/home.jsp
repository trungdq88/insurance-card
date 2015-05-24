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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-style.css">

</head>

<body>
<!-- Top menu -->
<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">MIC - Bảo hiểm trực tuyến</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="top-navbar-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="${pageContext.request.contextPath}/customer" class="form-inline">
                        <div class="form-group">
                            <label class="sr-only" for="inputUsername">Tên đăng nhập</label>
                            <input type="email" class="form-control" id="inputUsername" placeholder="Tên đăng nhập">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="inputPassword">Mật khẩu</label>
                            <input type="password" class="form-control" id="inputPassword" placeholder="Mật khẩu">
                        </div>
                        <button type="submit" class="btn btn-default">Sign in</button>
                        <br/>

                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Ghi nhớ?
                            </label>
                        </div>
                        <span><a href="home.html">Quên mật khẩu?</a></span>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-7 text">
                    <h1>Carousel</h1>

                    <ul>
                        <li>Thẻ NFC hiện đại</li>
                        <li>Thao tác đơn giản</li>
                        <li>Phục vụ 24/7</li>
                        <li>Thanh toán online hoặc trực tiếp</li>
                        <li>Dịch vụ nhanh chóng</li>
                    </ul>
                </div>
                <div class="col-sm-5 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Đăng ký ngay</h3>

                            <p>Quý khách vui lòng điền những thông tin sau đây:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-pencil"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="${pageContext.request.contextPath}/public/register" method="post" class="registration-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-full-name">Họ tên</label>
                                <input type="text" name="form-full-name" placeholder="Họ tên *"
                                       class="form-first-name form-control" id="form-full-name">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-address">Địa chỉ</label>
                                <input type="text" name="form-address" placeholder="Địa chỉ *"
                                       class="form-last-name form-control" id="form-address">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-email">Email</label>
                                <input type="text" name="form-email" placeholder="Email *"
                                       class="form-email form-control" id="form-email">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-phone">Số điện thoại</label>
                                <input type="text" name="form-phone" placeholder="Số điện thoại *"
                                       class="form-email form-control" id="form-phone">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-cmnd">Số CMND/Hộ chiếu</label>
                                <input type="text" name="form-cmnd" placeholder="Số CMND/Hộ chiếu"
                                       class="form-email form-control" id="form-cmnd">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-startDate">Ngày bắt đầu</label>
                                <input type="date" name="form-startDate" placeholder="Ngày bắt đầu *"
                                       class="form-email form-control" id="form-startDate">
                            </div>
                            <div class="form-group">
                                <select class="form-control">
                                    <option>Xe trên 50cc có tham gia BH tai nạn người trên xe</option>
                                    <option>Xe trên 50cc không tham gia BH tai nạn người trên xe</option>
                                    <option>Xe từ 50cc trở xuống có tham gia BH tai nạn người trên xe</option>
                                    <option>Xe từ 50cc trở xuống không tham gia BH tai nạn người trên xe</option>
                                    <option>Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới tương tự</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <p class="form-control-static">
                                    <label>Thời hạn: </label>
                                    01 năm kể từ khi cấp GCNBH và/hoặc sau thời hạn thanh toán phí
                                </p>
                            </div>
                            <div class="form-group">
                                <p class="form-control-static">
                                    <label>Phí bảo hiểm: </label>
                                    200.000 VND
                                </p>
                            </div>
                            <input type="hidden" name="action" value="register"/>
                            <input type="submit" class="btn" value="Tiếp theo" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Javascript -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.backstretch.min.js"></script>
<script src="${pageContext.request.contextPath}/js/retina-1.1.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>

</body>
</html>