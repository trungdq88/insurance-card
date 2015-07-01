<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    .handleInput {
        border:none ;
        background-color: white;
        width: 100%;
        padding-top: 6px ;
    }
</style>
<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thông Tin Cá Nhân
                        <span class="pull-right">
                            <button type="submit" class="btn btn-primary" id="btn_Modify">Chỉnh Sửa Thông Tin</button>
                            <button type="submit" class="btn btn-primary hide" id="btn_Save">Lưu Thay Đổi</button>
                            <button type="button" class="btn btn-primary" data-toggle="modal" id="changePass"
                                    data-target=".bs-example-modal-lg">
                                Đổi Mật Khẩu
                            </button>
                        </span>
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="col-lg-12">
            <c:if test="${not empty validateErrors}">

                <div class="text-danger ">
                    <ul>
                        <c:forEach var="error" items="${validateErrors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>

            </c:if>
        </div>

        <form class="form-horizontal">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-4 control-label">Tên đăng nhập</label>

                <div class="col-sm-7">
                    <p class="form-control-static">${customer.customerCode}</p>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label">Họ và tên</label>

                <div class="col-sm-7">
                    <p class="form-control-static">${customer.name}</p>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label">Địa chỉ</label>

                <div class="col-sm-7">
                    <input type="text" class="form-control handleInput textInFormation"
                           required
                           maxlength="250"
                           placeholder="Địa Chỉ" disabled="disabled"
                           value="${customer.address}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label">Email</label>

                <div class="col-sm-7">
                    <input type="email" class="form-control handleInput textInFormation"
                           required
                           maxlength="50"
                           placeholder="Email" disabled="disabled"
                           value="${customer.email}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label">Số đện thoại</label>

                <div class="col-sm-7">
                    <input type="text" class="form-control handleInput textInFormation"
                           required
                           maxlength="15"
                           disabled="disabled"
                           value="${customer.phone}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label">CMND/Hộ chiếu</label>

                <div class="col-sm-7">
                    <input type="text" class="form-control handleInput textInFormation"
                           required
                           maxlength="15"
                           disabled="disabled"
                           value="${customer.personalId}">
                </div>
            </div>

        </form>

        <form action="${pageContext.request.contextPath}/customer" method="post">
            <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
                 aria-labelledby="myLargeModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">
                                <label class="text-danger">Đổi mật khẩu</label></h4>

                        </div>
                        <div class="modal-body">
                            <div class="text-center alert alert-danger alert-dismissible hide" id="notify1"
                                 role="alert">
                                Mật khẩu hiện tại không đúng
                            </div>
                            <div class="text-center alert alert-danger alert-dismissible hide" id="notify2"
                                 role="alert">
                                Xác nhận mật khẩu không khớp với mật khẩu mới
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Xác nhận mật khẩu hiện tại</label>

                                    <div class="col-sm-5">
                                        <input type="password" class="form-control"
                                               required
                                               title="Nhập mật khẩu hiện tại"
                                               id="currentPass"
                                               name="newPass:currentPassword"
                                                >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Mật khẩu mới</label>

                                    <div class="col-sm-5">
                                        <input type="password" class="form-control"
                                               required
                                               title="Nhập mật khẩu mới"
                                               maxlength="50"
                                               id="newPass"
                                               name="newPass:newPassword"
                                                >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Xác nhận mật khẩu mới

                                    </label>

                                    <div class="col-sm-5">
                                        <input type="password" class="form-control"
                                               required
                                               title="Xác nhận lại mật khẩu"
                                               maxlength="50"
                                               id="confirmPass"
                                               name="newPass:confirmPassword"
                                                >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <%--Post to server (PersonalController)--%>
                            <input type="hidden" name="action" value="ChangePassword"/>
                            <input type="hidden" id="isFirstLogin"
                                   value="${customer.isDefaultPassword}"/>
                            <input type="hidden" name="newPass:customerCode" id="customerCode"
                                   value="${customer.customerCode}"/>
                            <input type="hidden" id="customerPass"
                                   value="${customer.password}"/>
                            <%---------------------------------------%>
                            <input id="confirm" type="submit" class="btn btn-primary" name="Xác Nhận"
                                   value="Xác Nhận"/>
                            <input type="button" class="btn btn-danger" id="cancel" data-dismiss="modal"
                                   value="Hủy Bỏ"/>
                        </div>
                    </div>

                </div>
            </div>
        </form>
    </div>
    <!-- /#page-wrapper -->

</div>


<%@ include file="_shared/footer.jsp" %>