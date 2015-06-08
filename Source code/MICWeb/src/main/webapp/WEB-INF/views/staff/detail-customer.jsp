<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="customer" value="${requestScope.CUSTOMER}"/>
    <c:set var="contract" value="${requestScope.CONTRACT}"/>

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    ${customer.name}
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal">
                    <fieldset>
                        <legend>
                            Thông tin cá nhân
                            <div class="pull-right" style="margin-top: -5px;">
                                <a href="#" class="btn btn-xs btn-success">
                                    <i class="fa fa-pencil"></i>
                                    Chỉnh sửa
                                </a>
                            </div>
                        </legend>

                        <!-- Customer code -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã khách hàng</label>

                            <div class="col-sm-6">
                                <div class="text-value text-primary">
                                    <b>${customer.customerCode}</b>
                                </div>
                            </div>
                        </div>

                        <!-- Customer name -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Tên khách hàng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${customer.name}
                                </div>
                            </div>
                        </div>

                        <!-- Address -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Địa chỉ</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${customer.address}
                                </div>
                            </div>
                        </div>

                        <!-- Email -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Email</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${customer.email}
                                </div>
                            </div>
                        </div>

                        <!-- Phone -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số điện thoại</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${customer.phone}
                                </div>
                            </div>
                        </div>

                        <!-- Personal ID -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số CMND / Hộ chiếu</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${customer.personalId}
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Customer information--%>
                    <br/>

                    <fieldset>
                        <legend>
                            Thẻ đang sở hữu

                            <div class="pull-right" style="margin-top: -5px;">
                                <a href="#" class="btn btn-xs btn-primary"
                                   data-toggle="modal" data-target="#card-history-modal">
                                    <i class="fa fa-history"></i>
                                    Xem lịch sử
                                </a>

                                <a href="#" class="btn btn-xs btn-success"
                                   data-toggle="modal" data-target="#change-card-modal">
                                    <i class="fa fa-refresh"></i>
                                    Đổi thẻ mới
                                </a>
                            </div>
                        </legend>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã thẻ</label>

                            <div class="col-sm-6">
                                <div class="text-value text-primary">
                                    <a href="detail-card.html"><b>AC7-37F-E8E-DDC</b></a>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Ngày bắt đầu có hiệu lực</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    Ngày 2 tháng 3 năm 2014, lúc 09:23:22
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Lần cuối truy cập</label>

                            <div class="col-sm-8">
                                <div class="text-value">
                                    Ngày 7 tháng 4 năm 2015, lúc 17:20:21
                                    <i class="fa fa-question-circle"
                                       data-toggle="tooltip" data-placement="bottom"
                                       title="Ghi nhận lần cuối cùng thẻ được đọc bởi thiết bị từ Cảnh Sát Giao Thông."></i>
                                    <a href="detail-card.html#card-access-history">(xem lịch sử)</a>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Card information--%>
                    <br/>

                    <fieldset>
                        <legend>
                            Hợp đồng bảo hiểm
                            <div class="pull-right" style="margin-top: -5px;">
                                <a href="${pageContext.request.contextPath}/staff/contract?action=create"
                                   class="btn btn-xs btn-success">
                                    <i class="fa fa-plus-square"></i>
                                    Hợp đồng mới
                                </a>
                            </div>
                        </legend>

                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Mã hợp đồng</th>
                                    <th>Loại hợp đồng</th>
                                    <th>Ngày bắt đầu</th>
                                    <th>Ngày kết thúc</th>
                                    <th>Trạng thái</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="cont" items="${contract}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/staff/contract?action=detail&code=${cont.contractCode}">
                                                    ${cont.contractCode}
                                            </a>
                                        </td>
                                        <td>${cont.contractTypeId}</td>
                                        <td>
                                            <fmt:formatDate value="${cont.startDate}" pattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${cont.expiredDate}" pattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>${cont.status}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </fieldset>
                </form>
                <br/>
                <br/>
            </div>
            <!-- col -->
        </div>
    </div>
</div>
<!-- /#wrapper -->

<!-- model for card history -->
<div class="modal fade" id="card-history-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Các thẻ đã được sử dụng bởi Đinh Quang Trung</h4>
            </div>
            <div class="modal-body">
                <p>
                    <b>Tổng số: 2 thẻ</b>
                </p>

                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Mã thẻ</th>
                            <th>Ngày hiệu lực</th>
                            <th>Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td><a href="detail-card.html">AC7-37F-E8E-DDC</a></td>
                            <td>7/5/2015</td>
                            <td><span class="label label-success">Hoạt động</span></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td><a href="detail-card.html">FC3-DDA-4B6-773</a></td>
                            <td>7/4/2015</td>
                            <td><span class="label label-danger">Ngưng hoạt động</span></td>
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

<!-- model for change card -->
<div class="modal fade" id="change-card-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Quy trình đổi thẻ mới</h4>
            </div>
            <div class="modal-body">
                <p>Để đổi thẻ mới cho khách hàng, cần thực hiện các bước sau đây:</p>
                <ol>
                    <li>Nhận lại thẻ cũ từ khách hàng (nếu có).</li>
                    <li>Sử dụng <b>Ứng dụng in thẻ</b> trên điện thoại, dùng chức năng <b>Đổi thẻ</b> để tìm và in thẻ
                        mới cho khách hàng.
                    </li>
                    <li>Chuyển phát thẻ mới cho khách hàng, thẻ cũ sẽ tự động bị <b>vô hiệu hoá</b> và sẽ <b>không thể
                        sử dụng lại được nữa</b>.
                    </li>
                </ol>

                <p>Trong trường hợp cần thiết, các thông tin lưu trên thẻ cũ vẫn có thể được tra cứu lại tại trang <a
                        href="cards.html">Quản lý thẻ</a></p>
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