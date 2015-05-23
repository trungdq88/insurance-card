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
                <p class="text-right"><b>Các ô có dấu * là bắt buộc</b></p>

                <form class="form-horizontal">
                    <fieldset>
                        <legend>Thông tin khách hàng</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="name">Tên khách hàng</label>

                            <div class="col-sm-4">
                                <input id="name" name="name" type="text" class="form-control input-md">
                            </div>

                            <div class="col-sm-2"
                                 data-toggle="modal"
                                 data-target="#select-customer-modal">
                                <a href="#" class="btn btn-primary btn-block" data-toggle="tooltip"
                                   data-placement="bottom"
                                   title="Chọn khách hàng có sẵn trong hệ thống">
                                    <i class="fa fa-search"></i>
                                    Chọn
                                </a>
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

                    <br/>
                    <br/>
                    <fieldset>
                        <legend>Thông tin về xe cơ giới</legend>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="bienso">Biển số đăng ký *</label>

                            <div class="col-sm-6">
                                <input id="bienso" name="bienso" type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="nhanhieu">Nhãn hiệu *</label>

                            <div class="col-sm-6">
                                <input id="nhanhieu" name="nhanhieu" type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="loaixe">Loại xe *</label>

                            <div class="col-sm-6">
                                <input id="loaixe" name="loaixe" type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="dungtich">Dung tích *</label>

                            <div class="col-sm-6">
                                <input id="dungtich" name="dungtich" type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="mauson">Màu sơn *</label>

                            <div class="col-sm-6">
                                <input id="mauson" name="mauson" type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="namsanxuat">Năm sản xuất *</label>

                            <div class="col-sm-6">
                                <input id="namsanxuat" name="namsanxuat" type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="somay">Số máy *</label>

                            <div class="col-sm-6">
                                <input id="somay" name="somay" type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="sokhung">Số khung *</label>

                            <div class="col-sm-6">
                                <input id="sokhung" name="sokhung" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cavet">Ảnh chụp cà-vẹt xe</label>

                            <div class="col-sm-6">
                                <input id="cavet" name="cavet" type="file" class="form-control input-md">
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
                            <label class="col-sm-4 control-label" for="startdate">Thời điểm có hiệu lực *</label>

                            <div class="col-sm-6">
                                <input id="startdate" name="startdate"
                                       type="date" class="form-control input-md"/>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="enddate">Thời điểm hết hiệu lực *</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    <input id="enddate" name="enddate"
                                           type="date" class="form-control input-md"/>
                                </div>
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="contractfee">Phí bảo hiểm (VNĐ) *</label>

                            <div class="col-sm-6">
                                <input id="contractfee" name="contractfee"
                                       type="text" class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>

                    <br/>
                    <br/>
                    <fieldset>
                        <legend>Thông tin thanh toán</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="paid">Số tiền phí đã trả (VNĐ) *</label>

                            <div class="col-sm-6">
                                <input id="paid" name="paid"
                                       type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="paiddate">Ngày nộp phí *</label>

                            <div class="col-sm-6">
                                <input id="paiddate" name="paiddate"
                                       type="date" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="paidnote">Ghi chú</label>

                            <div class="col-sm-6">
                                <input id="paidnote" name="paidnote"
                                       type="text" class="form-control input-md">
                            </div>
                        </div>

                    </fieldset>

                    <br/>
                    <br/>
                    <fieldset>
                        <legend>Thông tin khác</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="contractdate">Ngày cấp đơn *</label>

                            <div class="col-sm-6">
                                <input id="contractdate" name="contractdate"
                                       type="text" class="form-control input-md">
                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="contractplace">Nơi cấp đơn *</label>

                            <div class="col-sm-6">
                                <input id="contractplace" name="contractplace"
                                       type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="contractsigner">Người cấp đơn *</label>

                            <div class="col-sm-6">
                                <input id="contractsigner" name="contractsigner"
                                       type="text" class="form-control input-md">
                            </div>
                        </div>

                    </fieldset>
                </form>
                <br/>

                <div class="text-center">

                    <a href="${pageContext.request.contextPath}/staff/contract?action=preview" type="button" class="btn btn-success">
                        <i class="fa fa-arrow-right"></i>
                        Kiểm tra thông tin và hoàn tất
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

<!-- model for select customer -->
<div class="modal fade" id="select-customer-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Chọn khách hàng đã có sẵn trong hệ thống</h4>
            </div>
            <div class="modal-body">

                <input type="text" class="form-control" placeholder="Tìm theo tên KH, CMND, biển số xe"/>
                <br/>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Mã KH</th>
                            <th>Tên KH</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>DHTRU291</td>
                            <td>Đinh Quang Trung</td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs btn-block"  data-dismiss="modal">Chọn</a>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>DHTRU291</td>
                            <td>Đinh Quang Trung</td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs btn-block"  data-dismiss="modal">Chọn</a>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>DHTRU291</td>
                            <td>Đinh Quang Trung</td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs btn-block"  data-dismiss="modal">Chọn</a>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>DHTRU291</td>
                            <td>Đinh Quang Trung</td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs btn-block"  data-dismiss="modal">Chọn</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Đóng</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<%@ include file="_shared/footer.jsp"%>