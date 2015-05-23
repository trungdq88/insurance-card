<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thêm hợp đồng mới</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="well text-center text-primary">
                    Vui lòng kiểm tra lại các thông tin trong hợp đồng chính xác.<br/>
                    Nhấn nút <b>Hoàn tất hợp đồng</b> ở cuối trang để hoàn tất hợp đồng
                </div>

                <form class="form-horizontal">
                    <fieldset>
                        <legend>Thông tin khách hàng</legend>

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

                    <br/>
                    <br/>
                    <fieldset>
                        <legend>Thông tin về xe cơ giới</legend>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Biển số đăng ký</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    93H3-6868
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Nhãn hiệu</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    YAHAMA
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại xe</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Xe máy YAMAHA Wave Alpha
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Dung tích</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    150cc
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Màu sơn</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Đỏ
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Năm sản xuất</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    2000
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số máy</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    1204852
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số khung</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    8452952
                                </div>
                            </div>
                        </div>


                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Trong tải</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    1,5 tấn
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số chỗ ngồi</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    3
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mục đích sử dụng xe</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Chở hàng
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Ảnh chụp cà-vẹt xe</label>

                            <div class="col-sm-6">
                                <img src=""/>
                            </div>
                        </div>
                    </fieldset>

                    <br/>
                    <br/>
                    <fieldset>
                        <legend>Thông tin về dịch vụ bảo hiểm</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại hình bảo hiểm</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Bảo hiểm bắt buộc trách nhiệm dân sự của chủ xe cơ giới
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời điểm có hiệu lực</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    12/02/2015 lúc 12:00:00
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời hạn hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    1 năm
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời điểm hết hiệu lực</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    12/02/2016 lúc 12:00:00
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Phí bảo hiểm (VNĐ)</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    600.000
                                </div>
                            </div>
                        </div>
                    </fieldset>

                    <br/>
                    <br/>
                    <fieldset>
                        <legend>Thông tin thanh toán</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số tiền phí đã trả</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    600.000
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Ngày nộp phí</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    11/06/2015
                                </div>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Ghi chú</label>

                            <div class="col-sm-6">
                                <div class="text-value">

                                </div>
                            </div>
                        </div>

                    </fieldset>

                    <br/>
                    <br/>
                    <fieldset>
                        <legend>Thông tin khác</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Ngày cấp đơn</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    11/06/2015
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Nơi cấp đơn</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    TP Hồ Chí Minh
                                </div>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Người cấp đơn</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Nguyễn Văn A
                                </div>
                            </div>
                        </div>

                    </fieldset>
                </form>
                <br/>

                <div class="text-center">

                    <a href="${pageContext.request.contextPath}/staff/contract?action=create" type="button" class="btn btn-default">
                        <i class="fa fa-arrow-left"></i>
                        Quay lại chỉnh sửa
                    </a>
                    <a href="${pageContext.request.contextPath}/staff/contract?action=success" type="button" class="btn btn-success">
                        <i class="fa fa-check"></i>
                        Hoàn tất hợp đồng
                    </a>
                </div>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp"%>