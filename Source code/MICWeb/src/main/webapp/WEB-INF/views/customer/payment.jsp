<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.fpt.mic.micweb.utils.DateUtils" %>
<%@ page import="javax.rmi.CORBA.Util" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <th class="text-center ">Mã Paypal
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
                                        <td >
                                                ${payment.paymentMethod}
                                        </td>
                                        <td >
                                                ${payment.content}
                                        </td>
                                        <td >
                                            <fmt:setLocale value='vi_VN'/>
                                            <fmt:formatNumber
                                                    value='${payment.amount}'
                                                    type='currency'
                                                    maxFractionDigits='0'/>
                                        </td>
                                        <td >
                                                ${payment.getMicStaffByReceiver().name}
                                        </td>
                                        <td >
                                                ${payment.paypalTransId}
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


    </div>
</div>
<!-- /#page-wrapper -->


<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>
