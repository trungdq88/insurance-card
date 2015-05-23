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
                <div class="well text-success text-center">
                    <i class="fa fa-check"></i>
                    Đã thêm nhân viên mới thành công!
                </div>
                <form class="form-horizontal">
                    <fieldset>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã nhân viên</label>

                            <div class="col-sm-6">
                                <div class="text-value text-primary">
                                    <a href="detail-customer.html"><b>HDTRU839</b></a>
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Tên nhân viên</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Đinh Quang Trung
                                </div>
                            </div>
                        </div>


                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Email</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    trungdq88@gmail.com
                                </div>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số điện thoại</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    0987.654.321
                                </div>
                            </div>
                        </div>

                    </fieldset>
                </form>

                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/staff/member" type="button" class="btn btn-success">
                        <i class="fa fa-arrow-left"></i>
                        Quay lại trang nhân viên
                    </a>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp" %>