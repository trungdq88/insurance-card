<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

                <c:set var="listType" value="${requestScope.CONTRACTTYPE}"/>

                <form action="${pageContext.request.contextPath}/staff/contract" method="post" class="form-horizontal">
                    <fieldset>
                        <legend>Thông tin khách hàng</legend>

                        <!-- Customer input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="customerCode">Khách hàng *</label>

                            <div class="col-sm-2">
                                <input id="customerCode" name="txtCustomerCode" type="text"
                                       class="form-control input-md">
                            </div>

                            <div class="col-sm-2" data-toggle="modal" data-target="#select-customer-modal">
                                <a href="#" class="btn btn-primary btn-block" data-toggle="tooltip"
                                   data-placement="bottom"
                                   title="Chọn khách hàng có sẵn trong hệ thống">
                                    <i class="fa fa-search"></i>
                                    Chọn
                                </a>
                            </div>
                        </div>

                        <!-- Load from database using JavaScript & AJAX -->
                        <div class="collapse" id="customerInfo">
                            <!-- Customer name -->
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Họ tên</label>

                                <div class="col-sm-4">

                                </div>
                            </div>

                            <!-- Address -->
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Địa chỉ</label>

                                <div class="col-sm-5">

                                </div>
                            </div>

                            <!-- Email -->
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Email</label>

                                <div class="col-sm-4">

                                </div>
                            </div>

                            <!-- Phone -->
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Số điện thoại</label>

                                <div class="col-sm-3">

                                </div>
                            </div>

                            <!-- Personal ID -->
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Số CMND / Hộ chiếu</label>

                                <div class="col-sm-3">

                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Customer information--%>
                    <br/>

                    <fieldset>
                        <legend>Thông tin về dịch vụ bảo hiểm</legend>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại hình bảo hiểm *</label>

                            <div class="col-sm-7">
                                <select class="form-control" name="ddlContractType"
                                        onchange="{
                                        var fee = this.options[this.selectedIndex].innerHTML;
                                        $('#displayFee').text(fee);
                                        $('#contractFee').val(fee);
                                        $('#amount').val(fee);
                                        }">
                                    <option value="" disabled selected style="display:none;">Vui lòng chọn loại hợp đồng</option>
                                    <c:forEach var="type" items="${listType}">
                                        <option label="${type.name}" value="${type.id}">
                                            ${type.pricePerYear}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="startDate">Thời điểm có hiệu lực *</label>

                            <div class="col-sm-3">
                                <input id="startDate" name="txtStartDate" type="date"
                                       class="form-control input-md"/>
                            </div>
                        </div>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="expiredDate">Thời điểm hết hiệu lực *</label>

                            <div class="col-sm-3">
                                <div class="text-value">
                                    <input id="expiredDate" name="txtExpiredDate" type="date"
                                           class="form-control input-md"/>
                                </div>
                            </div>
                        </div>

                        <!-- Contract fee -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="displayFee">Phí bảo hiểm (VNĐ) *</label>

                            <div class="col-sm-2">
                                <div class="text-value text-success">
                                    <span id="displayFee" style="font-weight: bolder; font-size: large"></span>
                                </div>
                                <input id="contractFee" name="txtContractFee" type="hidden">
                                <input id="amount" name="txtAmount" type="hidden">
                            </div>
                        </div>
                    </fieldset>
                    <%--/Contract information--%>
                    <br/>

                    <fieldset>
                        <legend>Thông tin về xe cơ giới</legend>

                        <!-- Plate & Brand -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="plate">Biển số đăng ký *</label>

                            <div class="col-sm-3">
                                <input id="plate" name="txtPlate" type="text" class="form-control input-md"
                                       placeholder="Ví dụ: 78Y9-15383">
                            </div>

                            <label class="col-sm-2 control-label" for="brand">Nhãn hiệu *</label>

                            <div class="col-sm-3">
                                <input id="brand" name="txtBrand" type="text" class="form-control input-md"
                                       placeholder="Ví dụ: Honda">
                            </div>
                        </div>

                        <!-- Engine & Chassis -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="engine">Số máy *</label>

                            <div class="col-sm-3">
                                <input id="engine" name="txtEngine" type="text" class="form-control input-md">
                            </div>

                            <label class="col-sm-2 control-label" for="chassis">Số khung *</label>

                            <div class="col-sm-3">
                                <input id="chassis" name="txtChassis" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Color & Capacity -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="capacity">Dung tích *</label>

                            <div class="col-sm-2">
                                <input id="capacity" name="txtCapacity" type="text" class="form-control input-md">
                            </div>

                            <label class="col-sm-3 control-label" for="color">Màu sơn</label>

                            <div class="col-sm-3">
                                <input id="color" name="txtColor" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Vehicle type & Model code -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="vehicleType">Loại xe</label>

                            <div class="col-sm-3">
                                <input id="vehicleType" name="txtType" type="text" class="form-control input-md"
                                       placeholder="Ví dụ: Hai bánh">
                            </div>

                            <label class="col-sm-2 control-label" for="modelCode">Số loại</label>

                            <div class="col-sm-3">
                                <input id="modelCode" name="txtModel" type="text" class="form-control input-md"
                                       placeholder="Ví dụ: Air Blade">
                            </div>
                        </div>

                        <!-- Year of manufacture & Weight -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="yearOfMan">Năm sản xuất</label>

                            <div class="col-sm-2">
                                <input id="yearOfMan" name="txtYearOfMan" type="text" class="form-control input-md">
                            </div>

                            <label class="col-sm-3 control-label" for="weight">Tự trọng</label>

                            <div class="col-sm-2">
                                <input id="weight" name="txtWeight" type="text" class="form-control input-md">
                            </div>
                        </div>

                        <!-- Seat capacity -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="seatCapacity">Số người được chở</label>

                            <div class="col-sm-2">
                                <input id="seatCapacity" name="txtSeatCapacity" type="text"
                                       class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>
                    <%--/Vehicle information--%>
                    <br/>

                    <fieldset>
                        <legend>Thông tin thanh toán</legend>

                        <!-- Paid date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="paidDate">Ngày nộp phí *</label>

                            <div class="col-sm-3">
                                <input id="paidDate" name="txtPaidDate" type="date" class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>
                    <%--/Payment information--%>
                    <br/>

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
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"
                        data-toggle="collapse" data-target="#customerInfo">Đóng
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
    $(document).ready(function () {
        $('#startDate').val(getCurrentDate());
        document.getElementById("startDate").min = getCurrentDateInLastWeek();
        $('#expiredDate').val(getCurrentDateInNextYear());
        document.getElementById("expiredDate").min = getCurrentDate();
        document.getElementById("expiredDate").max = getCurrentDateInNextYear();
        $('#paidDate').val(getCurrentDate());
    });
</script>

<%@ include file="_shared/footer.jsp" %>