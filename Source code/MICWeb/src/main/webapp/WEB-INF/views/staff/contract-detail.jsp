<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="cont" value="${requestScope.CONTRACT}"/>
    <c:set var="cust" value="${requestScope.CUSTOMER}"/>
    <c:set var="listPayment" value="${requestScope.PAYMENT}"/>

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    ${cont.contractCode}
                    <div class="pull-right">
                        <button id="btnRenew" type="button" class="btn btn-info"
                                data-toggle="modal" data-target="#renew-contract-modal">
                            <i class="fa fa-refresh"></i> Gia hạn
                        </button>
                        <button id="btnCancel" type="button" class="btn btn-danger"
                                data-toggle="modal" data-target="#cancel-contract-modal">
                            <i class="fa fa-times"></i> Hủy hợp đồng
                        </button>
                    </div>
                </h1>
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

                <form class="form-horizontal">
                    <fieldset>
                        <legend>Thông tin về dịch vụ bảo hiểm
                            <div class="pull-right" style="margin-top: -5px;">
                                <button type="button" class="btn btn-xs btn-success"
                                        data-toggle="modal" data-target="#edit-contract-modal">
                                    <i class="fa fa-pencil"></i>
                                    Chỉnh sửa
                                </button>
                            </div>
                        </legend>

                        <c:if test="${cont.status eq 'Request cancel'}">
                            <div class="alert alert-info">
                                <p class="bs-example text-center text-uppercase">
                                    Hợp đồng này đang có yêu cầu hủy từ khách hàng
                                </p>
                                <br/>
                                <!-- Cancel date -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thời điểm hủy</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            <fmt:formatDate value="${cont.cancelDate}" pattern="dd/MM/yyyy"/>
                                            lúc
                                            <fmt:formatDate value="${cont.cancelDate}" type="time"/>
                                        </div>
                                    </div>
                                </div>

                                <!-- Cancel reason -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Lý do hủy</label>

                                    <div class="col-sm-7">
                                        <div class="text-value">${cont.cancelReason}</div>
                                    </div>
                                </div>

                                <p class="text-center">
                                    <button class="btn btn-primary" type="button" data-toggle="modal"
                                            data-target="#cancel-contract-modal">
                                        <i class="fa fa-check"></i> Giải quyết
                                    </button>
                                </p>
                            </div>
                        </c:if>

                        <c:if test="${cont.status eq 'Cancelled'}">
                            <div class="alert alert-warning">
                                <p class="bs-example text-center text-uppercase">
                                    <strong>Hợp đồng này đã bị hủy</strong>
                                </p>
                                <br/>
                                <!-- Cancel date -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thời điểm hủy</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            <fmt:formatDate value="${cont.cancelDate}" pattern="dd/MM/yyyy"/>
                                            lúc
                                            <fmt:formatDate value="${cont.cancelDate}" type="time"/>
                                        </div>
                                    </div>
                                </div>

                                <!-- Cancel reason -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Lý do hủy</label>

                                    <div class="col-sm-7">
                                        <div class="text-value">${cont.cancelReason}</div>
                                    </div>
                                </div>

                                <!-- Cancel note -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Ghi chú hủy</label>

                                    <div class="col-sm-7">
                                        <div class="text-value">${cont.cancelNote}</div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <%--/Show cancel contract information--%>

                        <!-- Contract code & Contract status -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã hợp đồng</label>

                            <div class="col-sm-2">
                                <div class="text-value text-primary">
                                    <b>${cont.contractCode}</b>
                                </div>
                            </div>

                            <label class="col-sm-3 control-label">Trạng thái</label>

                            <div class="col-sm-3">
                                <div class="text-value">${cont.status}</div>
                            </div>
                        </div>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${cont.micContractTypeByContractTypeId.name}
                                </div>
                            </div>
                        </div>

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Bắt đầu có hiệu lực từ</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:formatDate value="${cont.startDate}" pattern="dd/MM/yyyy"/>
                                    lúc
                                    <fmt:formatDate value="${cont.startDate}" type="time"/>
                                </div>
                            </div>
                        </div>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời điểm hết hiệu lực</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:formatDate value="${cont.expiredDate}" pattern="dd/MM/yyyy"/>
                                    lúc
                                    <fmt:formatDate value="${cont.expiredDate}" type="time"/>
                                </div>
                            </div>
                        </div>

                        <!-- Remaining days -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời gian đến ngày hết hạn</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <span id="remain"
                                          style="color:deepskyblue; font-weight: bolder; font-size: large"></span> ngày
                                </div>
                            </div>
                        </div>

                        <!-- Contract fee -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Phí bảo hiểm (VNĐ)</label>

                            <div class="col-sm-3">
                                <div class="text-value">
                                    <fmt:setLocale value="vi_VN"/>
                                    <fmt:formatNumber value="${cont.contractFee}" type="currency"
                                                      maxFractionDigits="0"/>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </form>
                <%--/General information--%>
                <br/>

                <div role="tabpanel">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#contractinfo" aria-controls="home" role="tab" data-toggle="tab">Thông tin hợp
                                đồng</a>
                        </li>
                        <li role="presentation">
                            <a href="#compensations" aria-controls="profile" role="tab" data-toggle="tab">Lịch sử bồi
                                thường</a>
                        </li>
                        <li role="presentation">
                            <a href="#accidents" aria-controls="settings" role="tab" data-toggle="tab">Lịch sử gây tai
                                nạn</a>
                        </li>
                        <li role="presentation">
                            <a href="#punishments" aria-controls="messages" role="tab" data-toggle="tab">Lịch sử vi phạm
                                luật GT</a>
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
                                        <button type="button" class="btn btn-xs btn-success"
                                                data-toggle="modal" data-target="#edit-contract-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </button>
                                    </div>
                                </legend>

                                <!-- Customer name -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Họ tên</label>

                                    <div class="col-sm-8">
                                        <div class="text-value">
                                            <a href="${pageContext.request.contextPath}/staff/customer?action=detail&code=${cust.customerCode}">
                                                <strong>${cust.name}</strong>
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Address -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Địa chỉ</label>

                                    <div class="col-sm-8">
                                        <div class="text-value">
                                            ${cust.address}
                                        </div>
                                    </div>
                                </div>

                                <!-- Customer email -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Email</label>

                                    <div class="col-sm-8">
                                        <div class="text-value">
                                            ${cust.email}
                                        </div>
                                    </div>
                                </div>

                                <!-- Email & personal ID -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Số điện thoại</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cust.phone}
                                        </div>
                                    </div>

                                    <label class="col-sm-3 control-label">Số CMND / Hộ chiếu</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cust.personalId}
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <%--/Customer information--%>
                            <br/>

                            <fieldset>
                                <legend>Thông tin về xe cơ giới
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <button type="button" class="btn btn-xs btn-success"
                                                data-toggle="modal" data-target="#edit-contract-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </button>
                                    </div>
                                </legend>

                                <!-- Plate number & brand -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Biển số</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.plate}
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Nhãn hiệu</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.brand}
                                        </div>
                                    </div>
                                </div>

                                <!-- Chassis & Engine -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Số khung</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.chassis}
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Số máy</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.engine}
                                        </div>
                                    </div>
                                </div>

                                <!-- Capacity & Color -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Dung tích</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.capacity}
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Màu sơn</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.color}
                                        </div>
                                    </div>
                                </div>

                                <!-- Vehicle type & model code -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Số loại</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.modelCode}
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Loại xe</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.vehicleType}
                                        </div>
                                    </div>
                                </div>

                                <!-- Year of manufacture & empty weight -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Năm sản xuất</label>

                                    <div class="col-sm-1">
                                        <div class="text-value">
                                            ${cont.yearOfManufacture}
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Tự trọng</label>

                                    <div class="col-sm-1">
                                        <div class="text-value">
                                            ${cont.weight}
                                        </div>
                                    </div>

                                    <label class="col-sm-3 control-label">Số người được chở</label>

                                    <div class="col-sm-1">
                                        <div class="text-value">
                                            ${cont.seatCapacity}
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <%--/Vehicle information--%>
                            <br/>

                            <fieldset>
                                <legend>Thông tin thanh toán
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <button type="button" class="btn btn-xs btn-success"
                                                data-toggle="modal" data-target="#edit-contract-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </button>
                                    </div>
                                </legend>

                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Mã GD</th>
                                            <th>Thời gian</th>
                                            <th>Hình thức</th>
                                            <th>Dịch vụ</th>
                                            <th>Số tiền</th>
                                            <th>Người nhận</th>
                                            <th>Mã PayPal</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="payment" items="${listPayment}" varStatus="counter">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>${payment.id}</td>
                                                <td>
                                                    <fmt:formatDate value="${payment.paidDate}" pattern="dd/MM/yyyy"/>
                                                </td>
                                                <td>${payment.paymentMethod}</td>
                                                <td>${payment.content}</td>
                                                <td>
                                                    <fmt:setLocale value="vi_VN"/>
                                                    <fmt:formatNumber value="${payment.amount}"
                                                                      type="currency" maxFractionDigits="0"/>
                                                </td>
                                                <td>${payment.micStaffByReceiver.name}</td>
                                                <td>${payment.paypalTransId}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                            </fieldset>
                            <%--/Payment information--%>
                        </form>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="compensations">...</div>
                    <div role="tabpanel" class="tab-pane" id="accidents">...</div>
                    <div role="tabpanel" class="tab-pane" id="punishments">...</div>
                </div>
                <br/>
                <br/>

                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/staff/contract" type="button" class="btn btn-success">
                        <i class="fa fa-arrow-left"></i>
                        Danh sách hợp đồng
                    </a>
                </div>
                <br/>
                <br/>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<!-- model for renew contract -->
<div class="modal fade" id="renew-contract-modal">
    <div class="modal-dialog">
        <form action="${pageContext.request.contextPath}/staff/contract" method="post" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Gia hạn hợp đồng</h4>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <legend>Thông tin hợp đồng bảo hiểm</legend>

                        <div id="renewMsg" class="alert alert-info">
                            <p class="bs-example text-center text-uppercase">
                                Hợp đồng này còn thời hạn <span id="remain2"></span> ngày
                            </p>
                        </div>

                        <!-- Contract code -->
                        <input type="hidden" name="renew:contractCode" value="${cont.contractCode}"/>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Loại hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${cont.micContractTypeByContractTypeId.name}
                                </div>
                            </div>
                        </div>

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Thời điểm có hiệu lực</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:formatDate value="${cont.startDate}" pattern="dd/MM/yyyy"/>
                                    lúc
                                    <fmt:formatDate value="${cont.startDate}" type="time"/>
                                </div>
                            </div>
                        </div>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Thời điểm hết hiệu lực</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:formatDate value="${cont.expiredDate}" pattern="dd/MM/yyyy"/>
                                    lúc
                                    <fmt:formatDate value="${cont.expiredDate}" type="time"/>
                                </div>
                            </div>
                        </div>

                        <!-- New start date -->
                        <input type="hidden" id="startDate" name="renew:startDate"/>

                        <!-- New expired date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label" for="expiredDate">Gia hạn đến *</label>

                            <div class="col-sm-4">
                                <input id="expiredDate" name="renew:expiredDate" class="form-control input-md"
                                       type="date" required>
                            </div>
                        </div>

                        <!-- Renew fee -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Phí gia hạn (VNĐ) *</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <span id="renewFee"
                                          style="color:deepskyblue; font-weight: bolder; font-size: large"></span>
                                    <input type="hidden" id="contractFee" name="renew:contractFee"/>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Contract information--%>
                    <br/>

                    <fieldset>
                        <legend>Thông tin thanh toán</legend>

                        <!-- Paid date -->
                        <div class=" form-group">
                            <label class="col-sm-5 control-label" for="paidDate">Ngày nộp phí *</label>

                            <div class="col-sm-4">
                                <input id="paidDate" name="renew:paidDate" class="form-control input-md"
                                       type="date" required>
                                <input type="hidden" id="amount" name="renew:amount"/>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Payment information--%>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="action" value="renew"/>
                    <button type="submit" class="btn btn-success">
                        <i class="fa fa-arrow-right"></i>
                        Gia hạn hợp đồng
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- model for cancel contract -->
<div class="modal fade" id="cancel-contract-modal">
    <div class="modal-dialog">
        <form action="${pageContext.request.contextPath}/staff/contract" method="post" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Hủy hợp đồng</h4>
                </div>
                <div class="modal-body">
                    <fieldset>

                        <!-- Contract code -->
                        <input type="hidden" name="cancel:contractCode" value="${cont.contractCode}"/>

                        <!-- Cancel date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelDate">Ngày hủy hợp đồng *</label>

                            <div class="col-sm-4">
                                <input id="cancelDate" name="cancel:cancelDate" type="date" required
                                       class="form-control input-md"
                                       value="<fmt:formatDate value="${cont.cancelDate}" pattern="yyyy-MM-dd"/>">
                            </div>
                        </div>

                        <!-- Cancel reason -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelReason">Lý do hủy hợp đồng *</label>

                            <div class="col-sm-7">
                                <c:if test="${cont.status eq 'Request cancel'}">
                                    <input type="hidden" name="cancel:cancelReason" value="${cont.cancelReason}"/>
                                </c:if>
                                <input id="cancelReason" name="cancel:cancelReason" type="text"
                                       required maxlength="255"
                                       class="form-control input-md" value="${cont.cancelReason}">
                            </div>
                        </div>

                        <!-- Cancel note -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelNote">Ghi chú</label>

                            <div class="col-sm-7">
                                <textarea id="cancelNote" name="cancel:cancelNote" rows="4" maxlength="2000"
                                          class="form-control input-lg"></textarea>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="action" value="cancel"/>
                    <button type="submit" class="btn btn-danger">
                        <i class="fa fa-check"></i>
                        Đồng ý hủy
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">
    $(document).ready(function () {
        $('#paidDate').val(getCurrentDate());
        /*document.getElementById("paidDate").min = getCurrentDateInLastWeek();
         document.getElementById("paidDate").max = getCurrentDateInNextYear();*/
        $('#cancelDate').val(getCurrentDate());
        /*document.getElementById("cancelDate").min = getCurrentDateInLastWeek();
         document.getElementById("cancelDate").max = getCurrentDateInNextYear();*/

        var contractStatus = '${cont.status}';
        var expDate = new Date("${cont.expiredDate}");
        var pricePerYear = '${cont.micContractTypeByContractTypeId.pricePerYear}';
        var contractTerm = 365;
        $('#contractFee').val(pricePerYear);
        $('#amount').val(pricePerYear);
        $('#renewFee').text(parseFloat(pricePerYear).formatMoney(0));

        var remainDays = daysBetween(new Date(), expDate);
        $('#remain').text(remainDays);
        $('#remain2').text(remainDays);
        if (remainDays < 60) {
            $("#renewMsg").hide();
        }

        $('input[type="date"]').not('#paidDate').blur(function () {
            var inputDate = new Date(this.value);
            if (contractStatus != 'Expired') {
                contractTerm = daysBetween(expDate, inputDate);
            } else {
                contractTerm = daysBetween(new Date(getCurrentDate()), inputDate);
            }
            var renewFee = calculateContractFee(contractTerm, pricePerYear);
            $('#contractFee').val(pricePerYear);
            $('#amount').val(pricePerYear);
            $('#renewFee').text(parseFloat(renewFee).formatMoney(0));
        });

        $('#expiredDate').val(getCurrentDateInNextYear());
        if (contractStatus == 'Expired') {
            document.getElementById("btnCancel").disabled = true;
            $('#startDate').val(getCurrentDate());
            document.getElementById("expiredDate").min = getCurrentDate();
            document.getElementById("expiredDate").max = getCurrentDateInNextYear();
        } else {
            $('#startDate').val('${cont.expiredDate}');
            $('#expiredDate').val(getInputDateInNextYear(expDate));
            document.getElementById("expiredDate").min = getInputDateNextDate(expDate);
            document.getElementById("expiredDate").max = getInputDateInNextYear(expDate);
        }

        if (contractStatus == 'Cancelled') {
            $('button[type=button]').attr('disabled', true);
            $('#remain').text(0);
        }
        if (contractStatus == 'Request cancel') {
            document.getElementById("cancelReason").disabled = true;
        }
    });
</script>

<%@ include file="_shared/footer.jsp" %>