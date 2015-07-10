<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    .handleInput {
        border: none;
        background-color: white;
        width: 100%;
        padding-top: 6px;
    }
</style>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thông Tin Cá Nhân
                        <span class="pull-right">
                            <button type="submit" class="btn btn-primary" id="btn_Modify">Chỉnh sữa thông tin</button>

                            <button type="button" class="btn btn-primary" data-toggle="modal" id="changePass"
                                    data-target="#change-password-model">
                                Đổi mật khẩu
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
        <form class="form-horizontal" action="${pageContext.request.contextPath}/customer" method="post" >

            <div class="form-group">
                <label class="col-sm-4 control-label">Tên đăng nhập</label>

                <div class="col-sm-7">
                    <p class="form-control-static">${customer.customerCode}</p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Họ và tên</label>

                <div class="col-sm-7">
                    <p class="form-control-static">${customer.name}</p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Địa chỉ</label>

                <div class="col-sm-7">
                    <input type="text" class="form-control handleInput textInFormation"
                           id="txtAddress"
                           name="editCustomer:address"
                           required
                           maxlength="250"
                           placeholder="Địa Chỉ" disabled="disabled"
                           onFocus="geolocate()"
                           value="${customer.address}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Email</label>

                <div class="col-sm-7">
                    <input type="email" class="form-control handleInput textInFormation"
                           required
                           name="editCustomer:email"
                           maxlength="50" disabled="disabled"
                           placeholder="Email"
                           value="${customer.email}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Số đện thoại</label>

                <div class="col-sm-7">
                    <input type="text" class="form-control handleInput textInFormation"
                           required
                           name="editCustomer:phone"
                           maxlength="15"
                           disabled="disabled"
                           placeholder="Số điện thoại"
                           value="${customer.phone}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">CMND/Hộ chiếu</label>

                <div class="col-sm-7">
                    <input type="text" class="form-control handleInput textInFormation"
                           required
                           name="editCustomer:personalID"
                           maxlength="15"
                           disabled="disabled"
                           placeholder="CMND/Hộ chiếu"
                           value="${customer.personalId}">
                </div>
            </div>

            <div class="text-center saveInformation hide">
                <input type="hidden" name="action" value="EditProfile"/>
                <input type="hidden" name="customerCode" value="${customer.customerCode}"/>
                <input id="btn_Save" type="submit" class="btn btn-primary" value="Lưu thay đổi" name="save">
            </div>
        </form>


        <form action="${pageContext.request.contextPath}/customer" method="post">
            <div id="change-password-model" class="modal fade bs-example-modal-md" tabindex="-1" role="dialog"
                 aria-labelledby="myLargeModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-md">
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
                                Mật khẩu mới không được trùng với mật khẩu hiện tại
                            </div>
                            <div class="text-center alert alert-danger alert-dismissible hide" id="notify2"
                                 role="alert">
                                Xác nhận mật khẩu không khớp với mật khẩu mới
                            </div>
                            <c:if test="${customer.isDefaultPassword == 0}">
                                <div class="text-center" style="color: red">
                                    Mật khẩu của bạn chưa được đổi, vui lòng đổi mật khẩu để bảo mật tài khoản của bạn.
                                </div>
                            </c:if>
                            <br/>

                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Xác nhận mật khẩu hiện tại *</label>

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
                                    <label class="col-sm-5 control-label">Mật khẩu mới *</label>

                                    <div class="col-sm-5">
                                        <input type="password" class="form-control"
                                               required
                                               title="Nhập mật khẩu mới"
                                               minlength="6"
                                               maxlength="32"
                                               id="newPass"
                                               name="newPass:newPassword"
                                                >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Xác nhận mật khẩu mới *

                                    </label>

                                    <div class="col-sm-5">
                                        <input type="password" class="form-control"
                                               required
                                               title="Xác nhận lại mật khẩu"
                                               minlength="6"
                                               maxlength="32"
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

<script type="text/javascript">
    initialize();
    $(document).ready(function () {
        if ($('#createdDate').val() == "") {
            $('#createdDate').val(getCurrentDate());
        }
        document.getElementById("createdDate").max = getCurrentDate();
        if ($('#accidentDate').val() == "") {
            $('#accidentDate').val(getCurrentDate());
        }
        document.getElementById("accidentDate").max = getCurrentDate();
    });
</script>

<script src="${pageContext.request.contextPath}/js/geolocation.js" type="text/javascript"></script>
<!-- Google API Autocomplete for address-->
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

