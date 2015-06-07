<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

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

                <form action="${pageContext.request.contextPath}/staff/contract" method="post" class="form-horizontal">
                    <fieldset>
                        <legend>Thông tin khách hàng</legend>

                        <!-- Customer input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="customerCode">Khách hàng</label>

                            <div class="col-sm-2">
                                <input id="customerCode" name="txtCustomerCode" type="text" class="form-control input-md">
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

                        <!-- Load from database using JavaScript & AJAX -->
                        <!-- Customer name -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="name">Họ tên</label>

                            <div class="col-sm-4">
                                <input id="name" name="txtName" type="text" class="form-control input-md" disabled>
                            </div>
                        </div>

                        <!-- Address -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="address">Địa chỉ</label>

                            <div class="col-sm-5">
                                <input id="address" name="txtAddress" type="text" class="form-control input-md" disabled>
                            </div>
                        </div>

                        <!-- Email -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="email">Email</label>

                            <div class="col-sm-4">
                                <input id="email" name="txtEmail" type="text" class="form-control input-md" disabled>
                            </div>
                        </div>

                        <!-- Phone -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="phone">Số điện thoại</label>

                            <div class="col-sm-3">
                                <input id="phone" name="txtPhone" type="text" class="form-control input-md" disabled>
                            </div>
                        </div>

                        <!-- Personal ID -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="personalid">Số CMND / Hộ chiếu</label>

                            <div class="col-sm-3">
                                <input id="personalid" name="txtPersonalID" type="text" class="form-control input-md"
                                       disabled>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Thông tin về dịch vụ bảo hiểm</legend>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại hình bảo hiểm</label>

                            <div class="col-sm-6">
                                <!-- Load from database, add later -->
                                <select class="form-control" name="ddlContractType">
                                    <option value="1">Xe trên 50cc có tham gia BH tai nạn người trên xe</option>
                                    <option value="2">Xe trên 50cc không tham gia BH tai nạn người trên xe</option>
                                    <option value="3">Xe từ 50cc trở xuống có tham gia BH tai nạn người trên xe</option>
                                    <option value="4">Xe từ 50cc trở xuống không tham gia BH tai nạn người trên xe
                                    </option>
                                    <option value="5">Xe mô tô 3 bánh, xe gắn máy và các loại xe cơ giới tương tự
                                    </option>
                                </select>
                            </div>
                        </div>

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="startdate">Thời điểm có hiệu lực *</label>

                            <div class="col-sm-2">
                                <input id="startdate" name="txtStartDate" type="date" class="form-control input-md"/>
                            </div>
                        </div>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="enddate">Thời điểm hết hiệu lực *</label>

                            <div class="col-sm-2">
                                <div class="text-value">
                                    <input id="enddate" name="txtExpiredDate" type="date" class="form-control input-md"/>
                                </div>
                            </div>
                        </div>

                        <!-- Contract fee -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="contractFee">Phí bảo hiểm (VNĐ) *</label>

                            <div class="col-sm-2">
                                <input id="contractFee" name="txtContractFee" type="text" class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Thông tin về xe cơ giới</legend>

                        <!-- Plate -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="plate">Biển số đăng ký *</label>

                            <div class="col-sm-2">
                                <input id="plate" name="txtPlate" type="text" class="form-control input-md"
                                       placeholder="Ví dụ: 78Y9-15383">
                            </div>
                        </div>

                        <!-- Brand -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="brand">Nhãn hiệu *</label>

                            <div class="col-sm-3">
                                <input id="brand" name="txtBrand" type="text" class="form-control input-md"
                                       placeholder="Ví dụ: Honda, Yamaha, Suzuki...">
                            </div>
                        </div>

                        <!-- Model code -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="modelCode">Số loại *</label>

                            <div class="col-sm-3">
                                <input id="modelCode" name="txtModel" type="text" class="form-control input-md"
                                       placeholder="Ví dụ: Air Blade, Future, Wave...">
                            </div>
                        </div>

                        <!-- Vehicle type -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="vehicleType">Loại xe *</label>

                            <div class="col-sm-3">
                                <input id="vehicleType" name="txtType" type="text" class="form-control input-md"
                                       placeholder="Ví dụ: hai bánh, ba bánh, khác...">
                            </div>
                        </div>

                        <!-- Color -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="color">Màu sơn *</label>

                            <div class="col-sm-2">
                                <input id="color" name="txtColor" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Engine -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="engine">Số máy *</label>

                            <div class="col-sm-2">
                                <input id="engine" name="txtEngine" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Chassis -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="chassis">Số khung *</label>

                            <div class="col-sm-2">
                                <input id="chassis" name="txtChassis" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Capacity -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="capacity">Dung tích *</label>

                            <div class="col-sm-2">
                                <input id="capacity" name="txtCapacity" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Year of manufacture -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="yearOfMan">Năm sản xuất *</label>

                            <div class="col-sm-2">
                                <input id="yearOfMan" name="txtYearOfMan" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Weight -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="weight">Tự trọng *</label>

                            <div class="col-sm-2">
                                <input id="weight" name="txtWeight" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Seat capacity -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="seatCapacity">Số người được phép chở *</label>

                            <div class="col-sm-2">
                                <input id="seatCapacity" name="txtSeatCapacity" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Image of vehicle registration certificate -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="certImage">Ảnh chụp cà-vẹt xe</label>

                            <div class="col-sm-4">
                                <input id="certImage" name="txtCertImage" type="file" class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend>Thông tin thanh toán</legend>

                        <!-- Paid date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="paidDate">Ngày nộp phí *</label>

                            <div class="col-sm-2">
                                <input id="paidDate" name="txtPaidDate" type="date" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Paid amount -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="amount">Số tiền phí đã trả (VNĐ) *</label>

                            <div class="col-sm-2">
                                <input id="amount" name="txtAmount" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Receiver -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="receiver">Người nhận</label>

                            <div class="col-sm-3">
                                <input id="receiver" name="txtReceiver" type="text" class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>
                    <div class="text-center">
                        <input type="hidden" name="action" value="create"/>
                        <button type="submit" class="btn btn-success">
                            <i class="fa fa-arrow-right"></i>
                            Thêm hợp đồng
                        </button>
                        <!-- <a href="${pageContext.request.contextPath}/staff/contract?action=preview" type="button"
                           class="btn btn-success">
                            <i class="fa fa-arrow-right"></i>
                            Kiểm tra thông tin và hoàn tất
                        </a> -->
                    </div>
                    <br/>
                    <br/>
                </form>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
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
                                <a href="#" class="btn btn-primary btn-xs btn-block" data-dismiss="modal">Chọn</a>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>DHTRU291</td>
                            <td>Đinh Quang Trung</td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs btn-block" data-dismiss="modal">Chọn</a>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>DHTRU291</td>
                            <td>Đinh Quang Trung</td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs btn-block" data-dismiss="modal">Chọn</a>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>DHTRU291</td>
                            <td>Đinh Quang Trung</td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs btn-block" data-dismiss="modal">Chọn</a>
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
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<%@ include file="_shared/footer.jsp" %>