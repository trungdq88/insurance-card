<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Hợp đồng BHBB374
                    <div class="pull-right">
                        <a href="#" class="btn btn-info">
                            <i class="fa fa-refresh"></i> Gia hạn
                        </a>
                        <a href="#" class="btn btn-danger">
                            <i class="fa fa-times"></i> Huỷ hợp đồng
                        </a>
                    </div>
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">

                <form class="form-horizontal">
                    <fieldset>
                        <legend>
                            Thông tin chung
                        </legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value text-primary">
                                    <b>BHBB374</b>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Bảo hiểm bắt buộc trách nhiệm dân sự của chủ xe cơ giới
                                </div>
                            </div>
                        </div>


                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Trạng thái</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    <span class="label label-success">Sẵn sàng</span>
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời hạn hợp đồng còn</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    194 ngày
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </form>
                <br/>
                <br/>

                <div role="tabpanel">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#contractinfo" aria-controls="home" role="tab" data-toggle="tab">Thông tin hợp đồng</a>
                        </li>
                        <li role="presentation">
                            <a href="#compensations" aria-controls="profile" role="tab" data-toggle="tab">Lịch sử bồi thường</a>
                        </li>
                        <li role="presentation">
                            <a href="#accidents" aria-controls="settings" role="tab" data-toggle="tab">Lịch sử gây tai nạn</a>
                        </li>
                        <li role="presentation">
                            <a href="#punishments" aria-controls="messages" role="tab" data-toggle="tab">Lịch sử vi phạm luật GT</a>
                        </li>
                    </ul>
                </div>

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="contractinfo">
                        <br/>
                        <form class="form-horizontal">
                            <fieldset>
                                <legend>Thông tin khách hàng
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <a href="#" class="btn btn-xs btn-success"
                                           data-toggle="modal" data-target="#card-history-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </a>
                                    </div>
                                </legend>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Họ tên</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            <b><a href="detail-customer.html">Đinh Quang Trung</a></b>
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label">CMND/Hộ chiếu</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            191919911
                                        </div>
                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            trungdq88@gmail.com
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label">Số điện thoại</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            0987.654.321
                                        </div>
                                    </div>
                                </div>


                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Địa chỉ</label>

                                    <div class="col-sm-6">
                                        <div class="text-value">
                                            Phường Tân Chánh Hiệp, Q.12, TPHCM
                                        </div>
                                    </div>
                                </div>
                            </fieldset>

                            <br/>
                            <br/>
                            <fieldset>
                                <legend>Thông tin về xe cơ giới
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <a href="#" class="btn btn-xs btn-success"
                                           data-toggle="modal" data-target="#card-history-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </a>
                                    </div>
                                </legend>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Biển số</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            93H3-6868
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label">Nhãn hiệu</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            YAHAMA
                                        </div>
                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Loại xe</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            Wave Alpha
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label">Dung tích</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            150cc
                                        </div>
                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Màu sơn</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            Đỏ
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label">Năm sản xuất</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            2000
                                        </div>
                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Số máy</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            1204852
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Số khung</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            8452952
                                        </div>
                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">

                                    <label class="col-sm-3 control-label">Ảnh chụp cà-vẹt xe</label>

                                    <div class="col-sm-6">
                                        <img src=""/>
                                    </div>
                                </div>
                            </fieldset>

                            <br/>
                            <br/>
                            <fieldset>
                                <legend>Thông tin về dịch vụ bảo hiểm
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <a href="#" class="btn btn-xs btn-success"
                                           data-toggle="modal" data-target="#card-history-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </a>
                                    </div>
                                </legend>

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
                                <legend>Thông tin thanh toán
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <a href="#" class="btn btn-xs btn-success"
                                           data-toggle="modal" data-target="#card-history-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </a>
                                    </div>
                                </legend>

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
                                <legend>Thông tin khác
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <a href="#" class="btn btn-xs btn-success"
                                           data-toggle="modal" data-target="#card-history-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </a>
                                    </div>
                                </legend>

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
                    </div>
                    <div role="tabpanel" class="tab-pane" id="compensations">...</div>
                    <div role="tabpanel" class="tab-pane" id="accidents">...</div>
                    <div role="tabpanel" class="tab-pane" id="punishments">...</div>
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
                <br/>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp"%>