<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.fpt.mic.micweb.utils.DateUtils" %>
<%@ page import="javax.rmi.CORBA.Util" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
    .handleInput {
        border: none;
        background-color: white;
        width: 100%;
        padding-top: 6px;
    }
</style>
<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Lịch Sử Giao Dịch
                </h2>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel panel-heading">
                <div class="pull-left">
                    <!--<input type="checkbox" class="check-all"/>-->
                    <b>Có ${listPayment.size()} giao dịch</b>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="panel panel-body">
                <div class="table table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="success">
                            <th class="text-center ">Mã giao dịch
                            </th>
                            <th class="text-center ">Thời gian
                            </th>
                            <th class="text-center ">Hình thức
                            </th>
                            <th class="text-center ">Dịch vụ
                            </th>
                            <th class="text-center ">Số tiền
                            </th>
                            <th class="text-center ">Người nhận
                            </th>
                            <th class="text-center ">
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="payments"
                               value="${listPayment}"/>
                        <c:choose>
                            <c:when test="${payments.size() == 0}">
                                <tr>
                                    <td colspan="5" style="vertical-align: middle; text-align: center;">
                                        Không có giao dịch nào
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${listPayment}"
                                           var="payment"
                                           varStatus="counter">
                                    <tr>
                                        <td class="text-center">
                                                ${payment.id}
                                        </td>
                                        <td>
                                            <fmt:formatDate value='${payment.paidDate}'
                                                            pattern='dd/MM/yyyy'/>
                                        </td>
                                        <td>
                                                ${payment.paymentMethod}
                                        </td>
                                        <td>
                                                ${payment.content}
                                        </td>
                                        <td>
                                            <fmt:setLocale value='vi_VN'/>
                                            <fmt:formatNumber
                                                    value='${payment.amount}'
                                                    type='currency'
                                                    maxFractionDigits='0'/>
                                        </td>
                                        <td>
                                                ${payment.getMicStaffByReceiver().name}
                                        </td>
                                        <td>
                                            <button type="button" data-toggle="modal" index="${counter.count}"
                                                    payment-id-value="${payment.id}"
                                                    contract-code-value="${payment.contractCode}"
                                                    amount-value="${payment.amount}"
                                                    content-value="${payment.content}"
                                                    payment-method-value="${payment.paymentMethod}"
                                                    paid-date-value="${payment.paidDate}"
                                                    paypal-id-value="${payment.paypalTransId}"
                                                    receiver-value="${payment.receiver}"
                                                    start-date-value="${payment.startDate}"
                                                    expired-date-value="${payment.expiredDate}"
                                                    data-target=".paymentDialog"
                                                    class="payment-id-clicker btn btn-primary btn-xs detail"
                                                   >
                                                Xem chi tiết
                                            </button>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                        </tbody>
                    </table>
                </div>
                <div class="panel panel-footer">
                    <nav class="text-right">
                        <ul class="pagination">

                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <div class="modal fade paymentDialog" aria-hidden="false">
            <div class="modal-dialog">
                <form class="form-horizontal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Chi tiết thông tin thanh toán</h4>
                        </div>
                        <div class="modal-body">
                            <fieldset>
                                <!-- Payment ID & Paid date -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Mã giao dịch</label>

                                    <div class="col-sm-1">
                                        <div class="text-value">
                                            <input disabled="disabled" class="handleInput"
                                                   id="payment-id-value"/>
                                        </div>
                                    </div>

                                    <label class="col-sm-3 control-label">Mã hợp đồng</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            <input disabled="disabled" class="handleInput" id="contract-code-value"/>
                                        </div>
                                    </div>
                                </div>

                                <!-- Amount & Content -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Số tiền</label>

                                    <div class="col-sm-2">
                                        <div class="text-value">
                                            <input disabled="disabled" class="handleInput" id="amount-value"/>
                                        </div>
                                    </div>

                                    <label class="col-sm-2 control-label">Dịch vụ</label>

                                    <div class="col-sm-5">
                                        <input disabled="disabled" class="handleInput" id="content-value"/>
                                    </div>
                                </div>

                                <!-- Payment method & Paid date -->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Hình thức</label>

                                    <div class="col-sm-2">
                                        <input disabled="disabled" class="handleInput" id="payment-method-value"/>
                                    </div>

                                    <label class="col-sm-2 control-label">Thời gian</label>

                                    <div class="col-sm-4">
                                        <input disabled="disabled" class="handleInput" id="paid-date-value"/>
                                    </div>
                                </div>

                                <!-- Paypal ID -->
                                <div id="ppIDCtrl" class="form-group">
                                    <label class="col-sm-3 control-label">Mã Paypal</label>

                                    <div class="col-sm-4">
                                        <input disabled="disabled" class="handleInput" id="paypal-id-value"/>
                                    </div>
                                </div>

                                <!-- Receiver -->
                                <div id="receiverCtrl" class="form-group hide">
                                    <label class="col-sm-3 control-label">Người nhận</label>

                                    <div class="col-sm-4">
                                        <input disabled="disabled" class="handleInput" id="receiver-value"/>
                                    </div>
                                </div>

                                <!-- Start date -->
                                <div id="stDateCtrl" class="form-group hide">
                                    <label class="col-sm-5 control-label">Ngày bắt đầu gia hạn</label>

                                    <div class="col-sm-4">
                                        <input disabled="disabled" class="handleInput" id="start-date-value"/>
                                    </div>
                                </div>

                                <!-- Expired date -->
                                <div id="expDateCtrl" class="form-group hide">
                                    <label class="col-sm-5 control-label">Ngày hết hạn hợp đồng</label>

                                    <div class="col-sm-4">
                                        <input disabled="disabled" class="handleInput" id="expired-date-value"/>
                                    </div>
                                </div>
                            </fieldset>

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

    </div>
</div>
<!-- /#page-wrapper -->


<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>
<script>
    $(document).ready(function () {

            $('.detail').click(function () {
                var index1 = $(this).attr('payment-id-value');
                var index2 = $(this).attr('contract-code-value');
                var index3 = $(this).attr('amount-value');
                var index4 = $(this).attr('content-value');
                var index5 = $(this).attr('payment-method-value');
                var index6 = $(this).attr('paid-date-value');
                var index7 = $(this).attr('paypal-id-value');
                var index8 = $(this).attr('receiver-value');
                var index9 = $(this).attr('start-date-value');
                var index10 = $(this).attr('expired-date-value');


                $('#payment-id-value').val(index1);
                $('#contract-code-value').val(index2);
                $('#amount-value').val(index3);

                $('#content-value').val(index4);
                $('#payment-method-value').val(index5);
                $('#paid-date-value').val(index6);
                $('#paypal-id-value').val(index7);
                $('#receiver-value').val(index8);
                $('#start-date-value').val(index9);
                $('#expired-date-value').val(index10);
                if(index8 != ''){
                    $('#receiverCtrl').removeClass('hide');
                }
                else{
                    $('#receiverCtrl').addClass('hide');
                }
                if(index9 != '' ){
                    $('#stDateCtrl').removeClass('hide');
                }
                else{
                    $('#stDateCtrl').addClass('hide');
                }
                if(index10 != ''){
                    $('#expDateCtrl').removeClass('hide');
                }
                else{
                    $('#expDateCtrl').addClass('hide');
                }


            })


    });
</script>