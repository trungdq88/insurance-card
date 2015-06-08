<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="cont" value="${requestScope.CONTRACT}"/>
    <c:set var="cust" value="${requestScope.CUSTOMER}"/>

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    ${cont.contractCode}
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

                        <!-- Contract code -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value text-primary">
                                    <b>${cont.contractCode}</b>
                                </div>
                            </div>
                        </div>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${cont.contractTypeId}
                                </div>
                            </div>
                        </div>

                        <!-- Contract status -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Trạng thái</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${cont.status}
                                </div>
                            </div>
                        </div>

                        <!-- Contract remaining days -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời hạn hợp đồng còn</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    <%--Contract remaining days--%>
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
                                        <a href="#" class="btn btn-xs btn-success"
                                           data-toggle="modal" data-target="#card-history-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </a>
                                    </div>
                                </legend>

                                <!-- Customer name & personal ID number -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Họ tên</label>

                                    <div class="col-sm-3">
                                        <div class="text-value">
                                            <b><a href="detail-customer.html">${cust.name}</a></b>
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
                                        <a href="#" class="btn btn-xs btn-success"
                                           data-toggle="modal" data-target="#card-history-modal">
                                            <i class="fa fa-pencil"></i>
                                            Chỉnh sửa
                                        </a>
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

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.yearOfManufacture}
                                        </div>
                                    </div>

                                    <label class="col-sm-3 control-label">Tự trọng</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.weight}
                                        </div>
                                    </div>
                                </div>

                                <!-- Seat capacity -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Số người được chở</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            ${cont.seatCapacity}
                                        </div>
                                    </div>
                                </div>

                                <!-- Certification image -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Ảnh chụp cà-vẹt xe</label>

                                    <div class="col-sm-6">
                                        <div class="text-value">
                                            <a href="${pageContext.request.contextPath}/">
                                                ${cont.certImage}
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <%--/Vehicle information--%>
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

                                <!-- Start date -->
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Thời điểm có hiệu lực</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            <fmt:formatDate value="${cont.startDate}" pattern="dd/MM/yyyy" />
                                             lúc
                                            <fmt:formatDate value="${cont.startDate}" type="time" />
                                        </div>
                                    </div>
                                </div>

                                <!-- Contract term -->
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Thời hạn hợp đồng</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            <%--Contract term--%>
                                        </div>
                                    </div>
                                </div>

                                <!-- Expired date -->
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Thời điểm hết hiệu lực</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            <fmt:formatDate value="${cont.expiredDate}" pattern="dd/MM/yyyy" />
                                            lúc
                                            <fmt:formatDate value="${cont.expiredDate}" type="time" />
                                        </div>
                                    </div>
                                </div>

                                <!-- Contract fee -->
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Phí bảo hiểm (VNĐ)</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            <fmt:setLocale value="vi_VN" />
                                            <fmt:formatNumber value="${cont.contractFee}" type="currency"
                                                              maxFractionDigits="0" />
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <%--/Contract information--%>
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

                                <!-- Paid amount -->
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Số tiền phí đã trả</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            600.000
                                        </div>
                                    </div>
                                </div>

                                <!-- Paid date -->
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Ngày nộp phí</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">
                                            11/06/2015
                                        </div>
                                    </div>
                                </div>

                                <!-- Receiver -->
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">Người nhận</label>

                                    <div class="col-sm-4">
                                        <div class="text-value">

                                        </div>
                                    </div>
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
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>