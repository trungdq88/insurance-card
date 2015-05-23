<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

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
                <form class="form-horizontal">
                    <fieldset>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="name">Tên khách hàng *</label>
                            <div class="col-sm-6">
                                <input id="name" name="name" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="address">Địa chỉ *</label>
                            <div class="col-sm-6">
                                <input id="address" name="address" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="email">Email *</label>
                            <div class="col-sm-6">
                                <input id="email" name="email" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="phone">Số điện thoại</label>
                            <div class="col-sm-6">
                                <input id="phone" name="phone" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="personalid">Số CMND / Hộ chiếu</label>
                            <div class="col-sm-6">
                                <input id="personalid" name="personalid" type="text" class="form-control input-md">
                            </div>
                        </div>

                    </fieldset>
                </form>

                <div class="text-center">

                    <form action="${pageContext.request.contextPath}/staff/customer" method="post">
                        <input type="hidden" name="action" value="create"/>
                        <button type="submit" class="btn btn-success">
                            <i class="fa fa-arrow-right"></i>
                            Thêm khách hàng
                        </button>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp"%>