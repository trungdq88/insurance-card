<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thêm nhân viên mới</h1>
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
                            <label class="col-sm-4 control-label" for="name">Mã nhân viên *</label>

                            <div class="col-sm-6">
                                <input id="code" name="code" type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="name">Tên nhân viên *</label>

                            <div class="col-sm-6">
                                <input id="name" name="name" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="address">Email *</label>

                            <div class="col-sm-6">
                                <input id="address" name="address" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="phone">Số điện thoại</label>

                            <div class="col-sm-6">
                                <input id="phone" name="phone" type="text" class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>
                </form>

                <div class="text-center">

                    <a href="${pageContext.request.contextPath}/staff/member?action=success" type="button"
                       class="btn btn-success">
                        <i class="fa fa-arrow-right"></i>
                        Thêm nhân viên
                    </a>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp" %>