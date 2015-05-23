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
                <div class="well text-success text-center">
                    <i class="fa fa-check"></i>
                    Đã thêm khách hàng mới thành công!
                </div>
                <form class="form-horizontal">
                    <fieldset>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã khách hàng</label>

                            <div class="col-sm-6">
                                <div class="text-value text-primary">
                                    <a href="detail-customer.html"><b>HDTRU839</b></a>
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Tên khách hàng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Đinh Quang Trung
                                </div>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Địa chỉ</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Phường Tân Chánh Hiệp, Q.12, TPHCM
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

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số CMND / Hộ chiếu</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    191919911
                                </div>
                            </div>
                        </div>

                    </fieldset>
                </form>

                <div class="text-center">
                    <button type="button" class="btn btn-primary">
                        <i class="fa fa-arrow-right"></i>
                        Thêm hợp đồng cho khách hàng này
                    </button>
                    <br/><br/>
                    <a href="${pageContext.request.contextPath}/staff/customer" type="button" class="btn btn-success">
                        <i class="fa fa-arrow-left"></i>
                        Quay lại trang khách hàng
                    </a>
                </div>

                <br/>

                <div class="panel panel-success">
                    <div class="panel-heading">
                        Hướng dẫn cách phát hành thẻ cho khách hàng
                    </div>
                    <div class="panel-body">
                        Để cấp thẻ cho khách hàng, nhân viên cần sử dụng <b>Ứng dụng in thẻ</b> trên điện thoại để in
                        thông tin khách hàng lên thẻ.
                        <ol>
                            <li>Tìm kiếm thông tin khách hàng trên ứng dụng.</li>
                            <li>Kiểm tra chính xác thông tin khác hàng.</li>
                            <li>In thông tin ra thẻ và chuyển phát cho khách hàng.</li>
                        </ol>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp"%>