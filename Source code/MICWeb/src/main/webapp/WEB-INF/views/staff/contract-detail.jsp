<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="cont" value="${requestScope.CONTRACT}"/>
    <c:set var="cust" value="${requestScope.CUSTOMER}"/>
    <c:set var="remain" value="${requestScope.REMAIN}"/>
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
                            </div>
                        </c:if>

                        <c:if test="${cont.status eq 'Cancelled'}">
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $('button[type=button]').attr('disabled', true);
                                });
                            </script>

                            <div class="alert alert-warning">
                                <p class="bs-example text-center text-uppercase">
                                    <strong>Hợp đồng này đã bị hủy</strong>
                                </p>
                            </div>
                        </c:if>

                        <c:if test="${cont.status eq 'Request cancel' or cont.status eq 'Cancelled'}">
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

                            <c:if test="${cont.status eq 'Cancelled'}">
                                <!-- Cancel note -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Ghi chú hủy</label>

                                    <div class="col-sm-7">
                                        <div class="text-value">${cont.cancelNote}</div>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                        <%--/Show cancel contract information--%>

                        <!-- Contract code & Contract status -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Mã hợp đồng</label>

                            <div class="col-sm-2">
                                <div class="text-value text-primary">
                                    <b>${cont.contractCode}</b>
                                </div>
                            </div>

                            <label class="col-sm-3 control-label">Trạng thái</label>

                            <div class="col-sm-2">
                                <div class="text-value">${cont.status}</div>
                            </div>
                        </div>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Loại hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${cont.micContractTypeByContractTypeId.name}
                                </div>
                            </div>
                        </div>

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Thời điểm có hiệu lực</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:formatDate value="${cont.startDate}" pattern="dd/MM/yyyy"/>
                                    lúc
                                    <fmt:formatDate value="${cont.startDate}" type="time"/>
                                </div>
                            </div>
                        </div>

                        <!-- Remaining days -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Thời hạn hợp đồng còn</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    ${remain} ngày
                                </div>
                            </div>
                        </div>

                        <c:set var="expiredDate" value="${cont.expiredDate}"/>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Thời điểm hết hiệu lực</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:formatDate value="${expiredDate}" pattern="dd/MM/yyyy"/>
                                    lúc
                                    <fmt:formatDate value="${expiredDate}" type="time"/>
                                </div>
                            </div>
                        </div>

                        <!-- Contract fee -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Phí bảo hiểm (VNĐ)</label>

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

                                <!-- Customer name & personal ID number -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Họ tên</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            <b>
                                                <a href="${pageContext.request.contextPath}/staff/customer?action=detail&code=${cust.customerCode}">
                                                    ${cust.name}
                                                </a>
                                            </b>
                                        </div>
                                    </div>
                                    <label class="col-sm-3 control-label">Số CMND / Hộ chiếu</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cust.personalId}
                                        </div>
                                    </div>
                                </div>

                                <!-- Customer email & phone number -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Email</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            ${cust.email}
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label">Số điện thoại</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cust.phone}
                                        </div>
                                    </div>
                                </div>

                                <!-- Address -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Địa chỉ</label>

                                    <div class="col-sm-6">
                                        <div class="text-value">
                                            ${cust.address}
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

                                <!-- Vehicle plate number & type -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Biển số</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.plate}
                                        </div>
                                    </div>
                                    <label class="col-sm-3 control-label">Loại xe</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.vehicleType}
                                        </div>
                                    </div>
                                </div>

                                <!-- Vehicle brand & model code -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Nhãn hiệu</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.brand}
                                        </div>
                                    </div>
                                    <label class="col-sm-3 control-label">Số loại</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.modelCode}
                                        </div>
                                    </div>
                                </div>

                                <!-- Color & Capacity -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Màu sơn</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.color}
                                        </div>
                                    </div>
                                    <label class="col-sm-3 control-label">Dung tích</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.capacity}
                                        </div>
                                    </div>
                                </div>

                                <!-- Engine & Chassis -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Số máy</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.engine}
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Số khung</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            ${cont.chassis}
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
                                                <td>${payment.receiver}</td>
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

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Thời điểm có hiệu lực</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <input type="hidden" name="txtNewStartDate" value="${expiredDate}"/>
                                    <fmt:formatDate value="${expiredDate}" pattern="dd/MM/yyyy"/>
                                    lúc
                                    <fmt:formatDate value="${expiredDate}" type="time"/>
                                </div>
                            </div>
                        </div>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label" for="expiredDate">Gia hạn đến *</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <input id="expiredDate" name="txtExpiredDate" type="date"
                                           class="form-control input-md"/>
                                </div>
                            </div>
                        </div>

                        <!-- Renew fee -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Phí gia hạn *</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:setLocale value="vi_VN"/>
                                    <fmt:formatNumber value="${cont.micContractTypeByContractTypeId.pricePerYear}"
                                                      type="currency" maxFractionDigits="0"/>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Contract information--%>
                    <br/>

                    <fieldset>
                        <legend>Thông tin thanh toán</legend>

                        <!-- Paid date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label" for="paidDate">Ngày nộp phí *</label>

                            <div class="col-sm-4">
                                <input id="paidDate" name="txtPaidDate" type="date" class="form-control input-md">
                                <input value="${cont.micContractTypeByContractTypeId.pricePerYear}"
                                       type="hidden" name="txtAmount"/>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="txtContractCode" value="${cont.contractCode}"/>
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
                        <!-- Cancel date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelDate">Ngày hủy hợp đồng *</label>

                            <div class="col-sm-4">
                                <input id="cancelDate" name="txtCancelDate" type="date"
                                       class="form-control input-md" value="${cont.cancelDate}">
                            </div>
                        </div>

                        <!-- Cancel reason -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelReason">Lý do hủy hợp đồng *</label>

                            <div class="col-sm-7">
                                <input id="cancelReason" name="txtCancelReason" type="text"
                                       class="form-control input-md" value="${cont.cancelReason}">
                            </div>
                        </div>

                        <!-- Cancel note -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelNote">Ghi chú</label>

                            <div class="col-sm-7">
                                <textarea id="cancelNote" name="txtCancelNote" rows="4" class="form-control input-lg"></textarea>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="txtContractCode" value="${cont.contractCode}"/>
                    <input type="hidden" name="action" value="cancel"/>
                    <button type="submit" class="btn btn-danger">
                        <i class="fa fa-arrow-right"></i>
                        Hủy hợp đồng
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

<script>
    $(document).ready(function () {
        $('#expiredDate').val(getInputDateInNextYear('${expiredDate}'));
        document.getElementById("expiredDate").min = getCurrentDateInNextYear();
        $('#paidDate').val(getCurrentDate());
        document.getElementById("paidDate").min = getCurrentDateInLastWeek();
        $('#cancelDate').val(getCurrentDate());
        document.getElementById("cancelDate").min = getCurrentDateInLastWeek();
    });
</script>

<%@ include file="_shared/footer.jsp" %>