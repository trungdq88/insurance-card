<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thêm khách hàng mới</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <p class="text-right"><b>Các ô có dấu * là bắt buộc</b></p>
                <br/>

                <!-- Form to create new customer -->
                <form action="${pageContext.request.contextPath}/staff/customer" method="post" class="form-horizontal">
                    <fieldset>
                        <!-- Customer full name input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="name">
                                Tên khách hàng *
                            </label>
                            <div class="col-sm-4">
                                <input id="name" name="txtName" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Address input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="address">
                                Địa chỉ *
                            </label>
                            <div class="col-sm-5">
                                <input id="address" name="txtAddress" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Email address input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="email">
                                Email *
                            </label>
                            <div class="col-sm-4">
                                <input id="email" name="txtEmail" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Phone number input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="phone">
                                Số điện thoại *
                            </label>
                            <div class="col-sm-2">
                                <input id="phone" name="txtPhone" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Personal ID input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="personalid">
                                Số CMND / Hộ chiếu
                            </label>
                            <div class="col-sm-2">
                                <input id="personalid" name="txtPersonalID" type="text" class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>

                    <!-- Create new customer button -->
                    <div class="text-center">
                        <input type="hidden" name="action" value="create"/>
                        <button type="submit" class="btn btn-success">
                            <i class="fa fa-arrow-right"></i>
                            Thêm khách hàng
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>