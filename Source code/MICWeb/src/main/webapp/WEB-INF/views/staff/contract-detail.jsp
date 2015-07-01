<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="cont" value="${requestScope.CONTRACT}"/>
    <c:set var="cust" value="${requestScope.CUSTOMER}"/>
    <c:set var="listPayment" value="${requestScope.PAYMENT}"/>
    <c:set var="listCompensation" value="${requestScope.COMPENSATION}"/>
    <c:set var="listAccident" value="${requestScope.ACCIDENT}"/>
    <c:set var="listPunishment" value="${requestScope.PUNISHMENT}"/>
    <c:set var="config" value="${requestScope.CONFIG}"/>

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    ${cont.contractCode}
                    <div class="pull-right">
                        <button id="btnRenew" type="button" class="btn btn-primary hide"
                                data-toggle="modal" data-target="#renew-contract-modal">
                            <i class="fa fa-refresh"></i> Gia hạn
                        </button>
                        <button id="btnCancel" type="button" class="btn btn-danger hide"
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
                                <button type="button" class="btn btn-xs btn-primary"
                                        data-toggle="modal" data-target="#edit-contract-modal">
                                    <i class="fa fa-pencil"></i> Chỉnh sửa
                                </button>
                            </div>
                        </legend>

                        <c:if test="${cont.status eq 'Pending' && contract.startDate != contract.expiredDate}">
                            <div class="alert alert-info">
                                <p class="bs-example text-center text-uppercase">
                                    Hợp đồng này chưa được thanh toán
                                </p>
                                <br/>

                                <p class="text-center">
                                    <button class="btn btn-success" type="button" data-toggle="modal"
                                            data-target="#complete-payment-modal">
                                        <i class="fa fa-check"></i> Hoàn tất thanh toán
                                    </button>
                                </p>
                            </div>
                        </c:if>
                        <%--/Show pending contract alert --%>

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
                        <%--/Show request cancel contract information --%>

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
                                        <div class="text-value">
                                            <c:choose>
                                                <c:when test="${empty cont.cancelNote}">
                                                    <span class="empty-value">Không có</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${cont.cancelNote}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <%--/Show cancel contract information --%>

                        <!-- Contract code & Contract status -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã hợp đồng</label>

                            <div class="col-sm-2">
                                <div class="text-value text-primary">
                                    <b>${cont.contractCode}</b>
                                </div>
                            </div>

                            <label class="col-sm-2 control-label">Trạng thái</label>

                            <div class="col-sm-3">
                                <div class="text-value">
                                    <c:set var="status" value="${cont.status}"/>
                                    <c:choose>
                                        <c:when test="${status.equalsIgnoreCase('Pending')}">
                                            <span class="label label-gray">Chưa kích hoạt</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('No card')}">
                                            <span class="label label-primary">Chưa có thẻ</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('Ready')}">
                                            <span class="label label-success">Sẵn sàng</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('Request cancel')}">
                                            <span class="label label-warning">Yêu cầu hủy</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('Expired')}">
                                            <span class="label label-danger">Hết hạn</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('Cancelled')}">
                                            <span class="label label-dark">Đã huỷ</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="label label-default">Không trạng thái</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại hợp đồng</label>

                            <div class="col-sm-7">
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

                            <div class="col-sm-2">
                                <div class="text-value">
                                    <span id="remain"
                                          style="color:deepskyblue; font-weight: bolder; font-size: large"></span> ngày
                                </div>
                            </div>

                            <label class="col-sm-2 control-label">Phí bảo hiểm</label>

                            <div class="col-sm-2">
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
                            <a href="#contractInfo" aria-controls="home" role="tab" data-toggle="tab">
                                <strong>Thông tin hợp đồng</strong>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#compensations" aria-controls="profile" role="tab" data-toggle="tab">
                                <strong>Lịch sử bồi thường</strong>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#accidents" aria-controls="settings" role="tab" data-toggle="tab">
                                <strong>Lịch sử tai nạn</strong>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#punishments" aria-controls="messages" role="tab" data-toggle="tab">
                                <strong>Lịch sử vi phạm luật ATGT</strong>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="contractInfo">
                        <br/>

                        <form class="form-horizontal">
                            <fieldset>
                                <legend>Thông tin khách hàng
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <button type="button" class="btn btn-xs btn-primary"
                                                data-toggle="modal" data-target="#edit-contract-modal">
                                            <i class="fa fa-pencil"></i> Chỉnh sửa
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
                                            <c:choose>
                                                <c:when test="${empty cust.personalId}">
                                                    <span class="empty-value">Không có</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${cust.personalId}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <%--/Customer information--%>
                            <br/>

                            <fieldset>
                                <legend>Thông tin về xe cơ giới
                                    <div class="pull-right" style="margin-top: -5px;">
                                        <button type="button" class="btn btn-xs btn-primary"
                                                data-toggle="modal" data-target="#edit-contract-modal">
                                            <i class="fa fa-pencil"></i> Chỉnh sửa
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
                                            <c:choose>
                                                <c:when test="${empty cont.color}">
                                                    <span class="empty-value">Không có</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${cont.color}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>

                                <!-- Vehicle type & model code -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Số loại</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            <c:choose>
                                                <c:when test="${empty cont.modelCode}">
                                                    <span class="empty-value">Không có</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${cont.modelCode}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Loại xe</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            <c:choose>
                                                <c:when test="${empty cont.vehicleType}">
                                                    <span class="empty-value">Không có</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${cont.vehicleType}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>

                                <!-- Year of manufacture & empty weight -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Năm sản xuất</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            <c:choose>
                                                <c:when test="${empty cont.yearOfManufacture}">
                                                    <span class="empty-value">Không có</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${cont.yearOfManufacture}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <label class="col-sm-3 control-label">Tự trọng</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            <c:choose>
                                                <c:when test="${empty cont.weight}">
                                                    <span class="empty-value">Không có</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${cont.weight}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Số người được chở</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            <c:choose>
                                                <c:when test="${empty cont.seatCapacity}">
                                                    <span class="empty-value">Không có</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${cont.seatCapacity}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <%--/Vehicle information--%>
                            <br/>

                            <fieldset>
                                <legend>Thông tin thanh toán</legend>

                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>Mã GD</th>
                                            <th>Thời gian</th>
                                            <th>Hình thức</th>
                                            <th>Dịch vụ</th>
                                            <th>Số tiền</th>
                                            <th>Người nhận</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="payment" items="${listPayment}" varStatus="counter">
                                            <tr>
                                                <td>
                                                    <a href="javascript:;" class="payment-id-clicker"
                                                       payment-id="${payment.id}">
                                                            ${payment.id}
                                                    </a>
                                                </td>
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
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/staff/member?action=detail&code=${payment.micStaffByReceiver.staffCode}">
                                                            ${payment.micStaffByReceiver.name}
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </fieldset>
                            <%--/Payment information--%>
                        </form>
                    </div>
                    <%--/Contract information tab --%>
                    <div role="tabpanel" class="tab-pane" id="compensations">
                        <br/>

                        <form class="form-horizontal">
                            <fieldset>
                                <legend>Lịch sử bồi thường</legend>
                            </fieldset>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Mã</th>
                                        <th>Thời gian</th>
                                        <th>Quyết định</th>
                                        <th>Trạng thái</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="compensation" items="${listCompensation}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/staff/compensation?action=detail&code=${compensation.compensationCode}">
                                                        ${compensation.compensationCode}
                                                </a>
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${payment.createdDate}" pattern="dd/MM/yyyy"/>
                                            </td>
                                            <td>${compensation.decision}</td>
                                            <td>
                                                <c:set var="resolve" value="${compensation.resolveDate}"/>
                                                <c:choose>
                                                    <c:when test="${not empty resolve}">
                                                        <span class="label label-success">Đã giải quyết</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-default">Chưa giải quyết</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>
                    <%--/Compensation information tab --%>
                    <div role="tabpanel" class="tab-pane" id="accidents">
                        <br/>

                        <form class="form-horizontal">
                            <fieldset>
                                <legend>Lịch sử tai nạn</legend>
                            </fieldset>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Mã</th>
                                        <th>Nội dung</th>
                                        <th>Thời gian</th>
                                        <th>Biên bản</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="accident" items="${listAccident}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${accident.id}</td>
                                            <td>${accident.title}</td>
                                            <td>
                                                <fmt:formatDate value="${accident.createdDate}" pattern="dd/MM/yyyy"/>
                                            </td>
                                            <td>${accident.attachment}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>
                    <%--/Accident information tab --%>
                    <div role="tabpanel" class="tab-pane" id="punishments">
                        <br/>

                        <form class="form-horizontal">
                            <fieldset>
                                <legend>Lịch sử vi phạm luật ATGT</legend>
                            </fieldset>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Mã</th>
                                        <th>Nội dung</th>
                                        <th>Thời gian</th>
                                        <th>Biên bản</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="punishment" items="${listPunishment}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${punishment.id}</td>
                                            <td>${punishment.title}</td>
                                            <td>
                                                <fmt:formatDate value="${punishment.createdDate}" pattern="dd/MM/yyyy"/>
                                            </td>
                                            <td>${punishment.attachment}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>
                    <%--/Punishments information tab --%>
                </div>
                <%--/Tab content --%>
                <br/>

                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/staff/contract" type="button" class="btn btn-default">
                        <i class="fa fa-arrow-left"></i> <strong>Danh sách hợp đồng</strong>
                    </a>
                </div>
                <br/>
                <br/>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<!-- model for detail payment -->
<div class="modal fade" id="detail-payment-modal">
    <div class="modal-dialog">
        <form class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Chi tiết thông tin thanh toán</h4>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <!-- Payment ID & Paid date -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Mã giao dịch</label>

                            <div class="col-sm-2">
                                <div class="text-value" id="payment-id-value"></div>
                            </div>

                            <label class="col-sm-2 control-label">Thời gian</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="paid-date-value"></div>
                            </div>
                        </div>

                        <!-- Payment method & Content -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Hình thức</label>

                            <div class="col-sm-2">
                                <div class="text-value" id="payment-method-value"></div>
                            </div>


                            <label class="col-sm-2 control-label">Dịch vụ</label>

                            <div class="col-sm-5">
                                <div class="text-value" id="content-value"></div>
                            </div>
                        </div>

                        <!-- Amount -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Số tiền</label>

                            <div class="col-sm-2">
                                <div class="text-value" id="amount-value"></div>
                            </div>
                        </div>

                        <!-- Receiver -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Người nhận</label>

                            <div class="col-sm-3">
                                <div class="text-value" id="receiver-value"></div>
                            </div>
                        </div>

                        <!-- Paypal ID -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Mã Paypal</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="paypal-id-value"></div>
                            </div>
                        </div>

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Ngày bắt đầu gia hạn</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="start-date-value"></div>
                            </div>
                        </div>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Ngày hết hạn hợp đồng</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="expired-date-value"></div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Payment information--%>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- model for complete payment -->
<div class="modal fade" id="complete-payment-modal">
    <div class="modal-dialog">
        <form action="${pageContext.request.contextPath}/staff/contract" method="post" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Hoàn tất thông tin thanh toán</h4>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <legend>Thông tin hợp đồng bảo hiểm</legend>

                        <!-- Contract code -->
                        <input type="hidden" name="payment:contractCode" value="${cont.contractCode}"/>

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

                        <!-- Contract fee -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Phí bảo hiểm</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:setLocale value="vi_VN"/>
                                    <fmt:formatNumber value="${cont.contractFee}" type="currency"
                                                      maxFractionDigits="0"/>
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
                                <input id="paymentDate" name="payment:paidDate" class="form-control input-md"
                                       type="date" required>
                                <input type="hidden" name="payment:amount" value="${cont.contractFee}"/>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Payment information--%>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="action" value="completePayment"/>
                    <button type="submit" class="btn btn-success">
                        <i class="fa fa-arrow-right"></i> Hoàn tất thanh toán
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

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

                    <!-- Contract code -->
                    <input type="hidden" name="renew:contractCode" value="${cont.contractCode}"/>

                    <!-- Contract type -->
                    <div class="form-group">
                        <label class="col-sm-5 control-label">Loại hợp đồng</label>

                        <div class="col-sm-7">
                            <div class="text-value">
                                ${cont.micContractTypeByContractTypeId.name}
                            </div>
                        </div>
                    </div>

                    <!-- Start date -->
                    <div class="form-group">
                        <label class="col-sm-5 control-label">Bắt đầu có hiệu lực từ</label>

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
                        <label class="col-sm-5 control-label">Phí gia hạn</label>

                        <div class="col-sm-4">
                            <div class="text-value">
                                    <span id="renewFee"
                                          style="color:deepskyblue; font-weight: bolder; font-size: large"></span> đ
                                <input type="hidden" id="contractFee" name="renew:contractFee"/>
                            </div>
                        </div>
                    </div>

                    <!-- New card -->
                    <div class="form-group">
                        <label class="col-sm-5 control-label">Báo mất thẻ và yêu cầu thẻ mới</label>

                        <div class="col-sm-4">
                            <div class="text-value">
                                <a data-toggle="collapse" href="#collapseNewCard" data-parent="#accordion">
                                    <input type="checkbox" id="newCard" name="renew:newCard">
                                </a>
                            </div>
                        </div>
                    </div>

                    <div id="collapseNewCard" class="panel-collapse collapse in">
                        <!-- New card fee -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Phí thẻ mới</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <span id="newCardFee"
                                          style="color:navy; font-weight: bolder; font-size: large"></span> đ
                                </div>
                            </div>
                        </div>

                        <!-- Total fee -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Tổng chi phí</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <span id="totalFee"
                                          style="color:red; font-weight: bolder; font-size: large"></span> đ
                                    <input type="hidden" id="amount" name="renew:amount"/>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Paid date -->
                    <div class=" form-group">
                        <label class="col-sm-5 control-label" for="paidDate">Ngày nộp phí *</label>

                        <div class="col-sm-4">
                            <input id="paidDate" name="renew:paidDate" class="form-control input-md"
                                   type="date" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="action" value="renew"/>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-arrow-right"></i> Gia hạn hợp đồng
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
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
                                <input id="cancelReason" name="cancel:cancelReason" class="form-control input-md"
                                       type="text" required maxlength="250" pattern="^\w.+$"
                                       title="Vui lòng nhập lý do hủy hợp đồng" placeholder="Ví dụ: Mất xe"
                                       value="${cont.cancelReason}">
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
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-check"></i> Đồng ý hủy
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
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
        $('#expiredDate').val(getCurrentDateInNextYear());
        $('#paymentDate').val(getCurrentDate());
        document.getElementById("paymentDate").min = '${config.paidDateMin}';
        document.getElementById("paymentDate").max = '${config.paidDateMax}';
        $('#paidDate').val(getCurrentDate());
        document.getElementById("paidDate").min = '${config.paidDateMin}';
        document.getElementById("paidDate").max = '${config.paidDateMax}';
        $('#cancelDate').val(getCurrentDate());
        document.getElementById("cancelDate").min = '${config.cancelDateMin}';
        document.getElementById("cancelDate").max = '${config.cancelDateMax}';

        var contractStatus = '${cont.status}';
        var expDate = new Date("${cont.expiredDate}");
        var pricePerYear = '${cont.micContractTypeByContractTypeId.pricePerYear}';
        var contractTerm = 365;
        var renewFee = pricePerYear;
        var newCardFee = 10000;
        var totalFee = parseFloat(renewFee) + newCardFee;
        $('#contractFee').val(pricePerYear);
        $('#amount').val(pricePerYear);
        $('#renewFee').text(parseFloat(pricePerYear).formatMoney(0));
        $('#newCardFee').text(parseFloat(newCardFee).formatMoney(0));
        $('#totalFee').text(parseFloat(totalFee).formatMoney(0));

        $('.collapse').collapse();
        $('#newCard').on('click', function (e) {
            e.stopPropagation();
            $(this).parent().trigger('click');
        })
        $('#collapseNewCard').on('show.bs.collapse', function (e) {
            if (!$('#newCard').is(':checked')) {
                return false;
            }
        });

        var remainDays = daysBetween(new Date(), expDate);
        $('#remain').text(remainDays);
        $('#remain2').text(remainDays);

        $('input[type="date"]').not('#paidDate').blur(function () {
            var inputDate = new Date(this.value);
            if (contractStatus != 'Expired') {
                contractTerm = daysBetween(expDate, inputDate);
            } else {
                contractTerm = daysBetween(new Date(getCurrentDate()), inputDate);
            }
            renewFee = calculateContractFee(contractTerm, pricePerYear);
            $('#contractFee').val(renewFee);
            $('#amount').val(renewFee);
            $('#renewFee').text(parseFloat(renewFee).formatMoney(0));
            $('#totalFee').text(parseFloat(renewFee).formatMoney(0));
            if (document.getElementById("newCard").checked) {
                totalFee = parseFloat(renewFee) + newCardFee;
                $('#amount').val(totalFee);
                $('#totalFee').text(parseFloat(totalFee).formatMoney(0));
            }
        });

        if (contractStatus == 'Pending') {
            if (remainDays < 60) {
                $('#btnRenew').removeClass('hide');
            }
            $('#btnCancel').removeClass('hide');
        }

        if (contractStatus == 'No card') {
            if (remainDays < 60) {
                $('#btnRenew').removeClass('hide');
            }
            $('#btnCancel').removeClass('hide');
        }

        if (contractStatus == 'Ready') {
            if (remainDays < 60) {
                $('#btnRenew').removeClass('hide');
            }
            $('#btnCancel').removeClass('hide');
        }

        if (contractStatus == 'Expired') {
            if (remainDays < 60) {
                $('#btnRenew').removeClass('hide');
            }
            $('#startDate').val(getCurrentDate());
            document.getElementById("expiredDate").min = '${config.expiredDateMin}';
            document.getElementById("expiredDate").max = '${config.expiredDateMax}';
        } else {
            $('#startDate').val('${cont.expiredDate}');
            $('#expiredDate').val(getInputDateInNextYear(expDate));
            document.getElementById("expiredDate").min = getInputDateNextDate(expDate);
            document.getElementById("expiredDate").max = getInputDateInNextYear(expDate);
        }

        if (contractStatus == 'Request cancel') {
            $('#btnRenew').removeClass('hide');
            $('#btnCancel').removeClass('hide');
        }

        if (contractStatus == 'Cancelled') {
            $('button[type=button]').not('#btnRenew, #btnCancel').addClass('hide');
            $('#remain').text(0);
        }

        $(".payment-id-clicker").on("click", function () {
            var that = this;
            var paymentId = that.getAttribute("payment-id");
            $.ajax({
                url: "${pageContext.request.contextPath}/ajax?action=paymentDetail&paymentId=" + paymentId,
                type: "GET",
            }).done(function (data) {
                $("#payment-id-value").html(data.id);
                $("#paid-date-value").html(getDateTime(data.paidDate));
                $("#payment-method-value").html(data.paymentMethod);
                $("#content-value").html(data.content);
                $("#amount-value").html(data.amount);
                $("#receiver-value").html(data.micStaffByReceiver.name);
                $("#paypal-id-value").html(data.paypalTransId);
                $("#start-date-value").html(getDateTime(data.startDate));
                $("#expired-date-value").html(getDateTime(data.expiredDate));

                $("#detail-payment-modal").modal("show");
            });
        });
    });
</script>

<%@ include file="_shared/footer.jsp" %>