<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="contract" value="${requestScope.CONTRACT}" scope="request"/>
    <c:set var="customer" value="${requestScope.CUSTOMER}" scope="request"/>
    <c:set var="listPayment" value="${requestScope.PAYMENT}"/>
    <c:set var="listCompensation" value="${requestScope.COMPENSATION}" scope="request"/>
    <c:set var="listAccident" value="${requestScope.ACCIDENT}" scope="request"/>
    <c:set var="listPunishment" value="${requestScope.PUNISHMENT}" scope="request"/>
    <c:set var="config" value="${requestScope.CONFIG}"/>

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    ${contract.contractCode}
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
                    <c:if test="${(contract.status eq 'Pending') and (empty listPayment)}">
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

                    <c:if test="${contract.status eq 'Request cancel'}">
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
                                        <fmt:formatDate value="${contract.cancelDate}" pattern="dd/MM/yyyy"/>
                                        lúc
                                        <fmt:formatDate value="${contract.cancelDate}" type="time"/>
                                    </div>
                                </div>
                            </div>

                            <!-- Cancel reason -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Lý do hủy</label>

                                <div class="col-sm-7">
                                    <div class="text-value">${contract.cancelReason}</div>
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

                    <c:if test="${contract.status eq 'Cancelled'}">
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
                                        <fmt:formatDate value="${contract.cancelDate}" pattern="dd/MM/yyyy"/>
                                        lúc
                                        <fmt:formatDate value="${contract.cancelDate}" type="time"/>
                                    </div>
                                </div>
                            </div>

                            <!-- Cancel reason -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Lý do hủy</label>

                                <div class="col-sm-7">
                                    <div class="text-value">${contract.cancelReason}</div>
                                </div>
                            </div>

                            <!-- Cancel note -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Ghi chú hủy</label>

                                <div class="col-sm-7">
                                    <div class="text-value">
                                        <c:choose>
                                            <c:when test="${empty contract.cancelNote}">
                                                <span class="empty-value">Không có</span>
                                            </c:when>
                                            <c:otherwise>
                                                ${contract.cancelNote}
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <%--/Show cancel contract information --%>

                    <c:import url="contract-detail-general.jsp"/>
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
                            <c:import url="contract-detail-customer.jsp"/>
                            <%--/Customer information--%>
                            <br/>

                            <c:import url="contract-detail-vehicle.jsp"/>
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
                                            <th>Chi tiết</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="payment" items="${listPayment}" varStatus="counter">
                                            <tr>
                                                <td>
                                                    ${payment.id}
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
                                                <td>

                                                    <a href="javascript:;" class="payment-id-clicker btn btn-primary btn-xs"
                                                       payment-id="${payment.id}">
                                                        Chi tiết
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
                        <c:import url="contract-detail-compensation.jsp"/>
                    </div>
                    <%--/Compensation information tab --%>

                    <div role="tabpanel" class="tab-pane" id="accidents">
                        <br/>
                        <c:import url="contract-detail-accident.jsp"/>
                    </div>
                    <%--/Accident information tab --%>

                    <div role="tabpanel" class="tab-pane" id="punishments">
                        <br/>
                        <c:import url="contract-detail-punishment.jsp"/>
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

<c:import url="contract-detail-modal.jsp"/>

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

        var contractStatus = '${contract.status}';
        var expDate = new Date("${contract.expiredDate}");
        var pricePerYear = '${contract.micContractTypeByContractTypeId.pricePerYear}';
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
        if (remainDays < 60) {
            $('#btnRenew').removeClass('hide');
        }

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
            $('#btnCancel').removeClass('hide');
        }

        if (contractStatus == 'No card') {
            $('#btnCancel').removeClass('hide');
        }

        if (contractStatus == 'Ready') {
            $('#btnCancel').removeClass('hide');
        }

        if (contractStatus == 'Expired') {
            $('#startDate').val(getCurrentDate());
            document.getElementById("expiredDate").min = '${config.expiredDateMin}';
            document.getElementById("expiredDate").max = '${config.expiredDateMax}';
        } else {
            $('#startDate').val('${contract.expiredDate}');
            $('#expiredDate').val(getInputDateInNextYear(expDate));
            document.getElementById("expiredDate").min = getInputDateNextDate(expDate);
            document.getElementById("expiredDate").max = getInputDateInNextYear(expDate);
        }

        if (contractStatus == 'Request cancel') {
            $('#btnCancel').removeClass('hide');
            $('#cancelReason').attr('disabled', true);
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
                $("#receiver-value").html(data.micStaffByReceiver ? data.micStaffByReceiver.name : '');
                $("#paypal-id-value").html(data.paypalTransId);
                $("#start-date-value").html(getDateTime(data.startDate));
                $("#expired-date-value").html(getDateTime(data.expiredDate));

                $("#detail-payment-modal").modal("show");
            });
        });
    });
</script>

<%@ include file="_shared/footer.jsp" %>