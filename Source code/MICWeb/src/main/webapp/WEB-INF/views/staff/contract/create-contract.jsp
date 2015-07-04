<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <%@ include file="../_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thêm hợp đồng mới</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <c:if test="${not empty validateErrors}">
                    <div class="text-danger">
                        <ul>
                            <c:forEach var="error" items="${validateErrors}">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <p class="text-right"><b>Các ô có dấu * là bắt buộc</b></p>

                <c:set var="listType" value="${requestScope.CONTRACTTYPE}"/>
                <c:set var="config" value="${requestScope.CONFIG}"/>
                <c:set var="customerCode" value="${param.code}"/>

                <form action="${pageContext.request.contextPath}/staff/contract"
                      method="post" class="form-horizontal">
                    <fieldset>
                        <legend>Thông tin khách hàng</legend>

                        <!-- Customer input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="customerCode">Mã khách hàng *</label>

                            <div class="col-sm-2">
                                <c:if test="${not empty customerCode}">
                                    <input id="customerCode" name="contract:customerCode" class="form-control input-md"
                                           type="text" required pattern="^KH([0-9A-Z]{4,8})$"
                                           title="Ví dụ: KH49S4" value="${customerCode}">
                                </c:if>
                                <c:if test="${empty customerCode}">
                                    <input id="customerCode" name="contract:customerCode" class="form-control input-md"
                                           type="text" required pattern="^KH([0-9A-Z]{4,8})$"
                                           title="Ví dụ: KH49S4" value="${submitted.customerCode}">
                                </c:if>
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
                                <select required class="form-control" name="contract:contractTypeId" id="contractType">
                                    <option value="" disabled selected style="display:none;">
                                        Vui lòng chọn loại hợp đồng
                                    </option>
                                    <c:forEach var="type" items="${listType}">
                                        <option label="${type.name}" value="${type.id}"
                                            ${type.id == submitted.contractTypeId ? "selected" : ""}>
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
                                <input id="startDate" name="contract:startDate" class="form-control input-md"
                                       type="date" required
                                       value="<fmt:formatDate value="${submitted.startDate}" pattern="yyyy-MM-dd" />"/>
                            </div>
                        </div>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="expiredDate">Thời điểm hết hiệu lực *</label>

                            <div class="col-sm-3">
                                <input id="expiredDate" name="contract:expiredDate" class="form-control input-md"
                                       type="date" required
                                       value="<fmt:formatDate value="${submitted.expiredDate}" pattern="yyyy-MM-dd" />"/>
                            </div>
                        </div>

                        <!-- Contract fee -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="displayFee">Phí bảo hiểm (VNĐ) *</label>

                            <div class="col-sm-2">
                                <div class="text-value">
                                    <span id="displayFee"
                                          style="color:red; font-weight: bolder; font-size: large"></span>
                                </div>
                                <input type="hidden" id="contractFee" name="contract:contractFee">
                                <input type="hidden" id="amount" name="contract:amount">
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
                                <input id="plate" name="contract:plate" class="form-control input-md"
                                       type="text" required minlength="4" maxlength="15"
                                       title="Vui lòng nhập biển số xe!" placeholder="Ví dụ: 78Y9-15383"
                                       value="${submitted.plate}">
                            </div>

                            <label class="col-sm-2 control-label" for="brand">Nhãn hiệu *</label>

                            <div class="col-sm-3">
                                <input id="brand" name="contract:brand" class="form-control input-md"
                                       type="text" required minlength="2" maxlength="20"
                                       title="Vui lòng nhập nhãn hiệu xe!" placeholder="Ví dụ: Honda"
                                       value="${submitted.brand}">
                            </div>
                        </div>

                        <!-- Engine & Chassis -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="engine">Số máy *</label>

                            <div class="col-sm-3">
                                <input id="engine" name="contract:engine" class="form-control input-md"
                                       type="text" required minlength="2" maxlength="20"
                                       title="Vui lòng nhập số máy xe!"
                                       value="${submitted.engine}">
                            </div>

                            <label class="col-sm-2 control-label" for="chassis">Số khung *</label>

                            <div class="col-sm-3">
                                <input id="chassis" name="contract:chassis" class="form-control input-md"
                                       type="text" required minlength="2" maxlength="20"
                                       title="Vui lòng nhập số khung xe!"
                                       value="${submitted.chassis}">
                            </div>
                        </div>

                        <!-- Color & Capacity -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="capacity">Dung tích *</label>

                            <div class="col-sm-2">
                                <input id="capacity" name="contract:capacity" class="form-control input-md"
                                       type="text" required minlength="2" maxlength="20"
                                       title="Vui lòng nhập dung tích xe!"
                                       value="${submitted.capacity}">
                            </div>

                            <label class="col-sm-3 control-label" for="color">Màu sơn</label>

                            <div class="col-sm-3">
                                <input id="color" name="contract:color" class="form-control input-md"
                                       type="text" minlength="2" maxlength="20"
                                       title="Vui lòng nhập màu sơn xe!"
                                       value="${submitted.color}">
                            </div>
                        </div>

                        <!-- Vehicle type & Model code -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="vehicleType">Loại xe</label>

                            <div class="col-sm-3">
                                <input id="vehicleType" name="contract:vehicleType" class="form-control input-md"
                                       type="text" minlength="2" maxlength="20"
                                       title="Vui lòng nhập loại xe!" placeholder="Ví dụ: Hai bánh"
                                       value="${submitted.vehicleType}">
                            </div>

                            <label class="col-sm-2 control-label" for="modelCode">Số loại</label>

                            <div class="col-sm-3">
                                <input id="modelCode" name="contract:modelCode" class="form-control input-md"
                                       type="text" minlength="2" maxlength="20"
                                       title="Vui lòng nhập số loại xe!" placeholder="Ví dụ: Air Blade"
                                       value="${submitted.modelCode}">
                            </div>
                        </div>

                        <!-- Year of manufacture & Weight -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="yearOfMan">Năm sản xuất</label>

                            <div class="col-sm-2">
                                <input id="yearOfMan" name="contract:yearOfManufacture" class="form-control input-md"
                                       type="number" min="1900" max="2200"
                                       title="Vui lòng nhập năm sản xuất xe!"
                                       value="${submitted.yearOfManufacture}">
                            </div>

                            <label class="col-sm-3 control-label" for="weight">Tự trọng (kg)</label>

                            <div class="col-sm-2">
                                <input id="weight" name="contract:weight" class="form-control input-md"
                                       type="number" min="1" max="1000"
                                       title="Vui lòng nhập tự trọng của xe!"
                                       value="${submitted.weight}">
                            </div>
                        </div>

                        <!-- Seat capacity -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="seatCapacity">Số người được chở</label>

                            <div class="col-sm-2">
                                <input id="seatCapacity" name="contract:seatCapacity" class="form-control input-md"
                                       type="number" min="1" max="1000"
                                       title="Vui lòng nhập số người cho phép chở!"
                                       value="${submitted.seatCapacity}">
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
                                <input id="paidDate" name="contract:paidDate" class="form-control input-md"
                                       type="date" required
                                       value="<fmt:formatDate value="${submitted.paidDate}" pattern="yyyy-MM-dd" />">
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
        if ($('#startDate').val() == "") {
            $('#startDate').val(getCurrentDate());
        }
        document.getElementById("startDate").min = '${config.startDateMin}';
        document.getElementById("startDate").max = '${config.startDateMax}';
        if ($('#expiredDate').val() == "") {
            $('#expiredDate').val(getCurrentDateInNextYear());
        }
        document.getElementById("expiredDate").min = '${config.expiredDateMin}';
        document.getElementById("expiredDate").max = '${config.expiredDateMax}';
        if ($('#paidDate').val() == "") {
            $('#paidDate').val(getCurrentDate());
        }
        document.getElementById("paidDate").min = '${config.paidDateMin}';
        document.getElementById("paidDate").max = '${config.paidDateMax}';

        var stDate = new Date($("#startDate").val());
        var expDate = new Date($("#expiredDate").val());
        var contractTerm = daysBetween(stDate, expDate);
        $('#contractType').change(function () {
            var pricePerYear = parseFloat(this.options[this.selectedIndex].innerHTML);
            stDate = new Date($("#startDate").val());
            expDate = new Date($("#expiredDate").val());
            contractTerm = daysBetween(stDate, expDate);
            var contractFee = calculateContractFee(contractTerm, pricePerYear);

            $('input[type="date"]').not('#paidDate').blur(function () {
                stDate = new Date($("#startDate").val());
                expDate = new Date($("#expiredDate").val());
                document.getElementById("expiredDate").min = getInputDateNextDate(stDate);
                document.getElementById("expiredDate").max = getInputDateInNextYear(stDate);
                contractTerm = daysBetween(stDate, expDate);
                contractFee = calculateContractFee(contractTerm, pricePerYear);
                $('#contractFee').val(contractFee)
                $('#amount').val(contractFee);
                $('#displayFee').text(contractFee.formatMoney(0));
            });

            $('#contractFee').val(contractFee)
            $('#amount').val(contractFee);
            $('#displayFee').text(contractFee.formatMoney(0));
        }).change();
    });
</script>

<%@ include file="../_shared/footer.jsp" %>