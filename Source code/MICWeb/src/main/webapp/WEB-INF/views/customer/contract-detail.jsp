<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.fpt.mic.micweb.utils.DateUtils" %>
<%@ page import="javax.rmi.CORBA.Util" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: PhucNguyen
  Date: 26/05/2015
  Time: 10:09 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<style type="text/css">
    .handleInput {
        border: none;
        background-color: white;
        width: 100%;
        padding-top: 6px;
    }

    .tooltip-inner {
        font-size: 13px;
        background-color: #3D6199;
        color: White;
        min-width: 400px;
        border-radius: 5px;
    }

    .tooltip:before {
        border-color: transparent #3D6199 transparent transparent;
        border-right: 6px solid #3D6199;
        border-style: solid;
        border-width: 6px 6px 6px 0px;
        content: "";
        display: block;
        height: 0;
        width: 0;
        line-height: 0;
        position: absolute;

    }

    .tooltip-arrow {
        display: none;
    }

</style>
<div id="wrapper">
    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">

            <div class="col-lg-12">
                <h2 class="page-header ">Hợp Đồng ${contract.contractCode}
                     <span class="pull-right">
                            <input type="hidden" id="contractStatus1" value="${contract.status}">
                             <button type="submit" class="btn btn-primary" data-toggle="modal" id="renew"
                                     data-target=".renew-contract-modal"><i
                                     class="fa fa-refresh"></i> Gia Hạn
                             </button>

                               <button type="button" class="btn btn-danger" data-toggle="modal" id="delete"
                                       data-target=".bs-example-modal-lg"><i class="fa fa-times"></i> Hủy Hợp Đồng
                               </button>
                     </span>
                </h2>
            </div>
            <form action="${pageContext.request.contextPath}/customer/contract" method="post">
                <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">
                                    <label class="text-danger">Hủy Hợp Đồng</label></h4>

                            </div>
                            <div class="modal-body">
                                <div class="alert alert-danger alert-dismissible hide" id="notify" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    Vui lòng chọn lí do hủy hợp đồng trước khi xác nhận. Cảm ơn!
                                </div>
                                <div class="text-info">
                                    <label>
                                        Quý khách vui lòng cung cấp lý do hoặc trường hợp hủy hợp đồng
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="radio" value="" name="rdbReason1" class="check" id="rdbReason1">
                                        Xe cơ giới bị thu hồi đăng ký và biển số theo quy định của pháp luật
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="radio" value="" name="rdbReason2" id="rdbReason2" class="check">
                                        Xe cơ giới hết niên hạn sử dụng theo quy định của pháp luật
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="radio" value="" name="rdbReason3" id="rdbReason3" class="check">
                                        Xe cơ giới bị mất được cơ quan công an xác nhận
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="radio" value="" name="rdbReason4" id="rdbReason4" class="check">
                                        Xe cơ giới hỏng không sử dụng được hoặc bị phá huỷ do tai nạn giao thông được cơ
                                        quan công an xác nhận
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="radio" value="" name="rdbAnother" id="rdbAnother" class="check">
                                        Lý do khác
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                       <textarea name="txtAnotherReason" rows="3" cols="95" id="anotherReason"
                                                 class="hide"
                                                 autofocus="autofocus">
                                       </textarea>


                                    </label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <%--Post to server (ContractController)--%>
                                <input type="hidden" name="action" value="CancelContract"/>
                                <input type="hidden" name="cancel:cancelReason" id="reason">
                                <input type="hidden" name="cancel:contractCode" id="contractId"
                                       value="${contract.contractCode}"/>
                                <%---------------------------------------%>
                                <input id="deleteContract" type="submit" class="btn btn-primary" name="Xác Nhận"
                                       value="Xác Nhận"/>
                                <input type="button" class="btn btn-danger" id="cancelAction" data-dismiss="modal"
                                       value="Hủy Bỏ"/>
                            </div>
                        </div>

                    </div>
                </div>
            </form>

            <form action="${pageContext.request.contextPath}/customer/contract" method="post">

                <div class="modal fade renew-contract-modal" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">Gia hạn hợp đồng</h3>
                            </div>
                            <div class="modal-body">
                                <div class="form-horizontal">
                                    <div class="alert alert-block alert-error fade
                                     in well well-lg text-info alertRenew hide">
                                        <h4 class="alert-heading text-center">KHÔNG THỂ GIA HẠN HỢP ĐỒNG CÒN GIÁ TRỊ
                                            TRÊN 2 THÁNG</h4>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-5 text-right">Loại hợp đồng </label>

                                        <div class="col-sm-7">
                                            <input style="border:none ; background-color: white;width: 100% "
                                                   value="${contract.getMicContractTypeByContractTypeId().getName()}"
                                                   type="text" disabled="disabled"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-5 text-right">Thời điểm bắt đầu</label>

                                        <div class="col-sm-4">
                                            <input type="hidden" id="startDate"
                                                   value="${contract.startDate}"/>
                                            <input type="hidden" name="txtNewStartDate" id="newStartDate"
                                                   value="${contract.expiredDate}"/>

                                            <fmt:formatDate value="${contract.startDate}" pattern="dd/MM/yyyy"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-5 text-right">Thời điểm kết thúc </label>

                                        <div class="col-sm-4">
                                            <fmt:formatDate value="${contract.expiredDate}" pattern="dd/MM/yyyy"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-5 text-right">Gia hạn đến </label>

                                        <div class="col-sm-4">
                                            <input id="newExpiredDate" style="border:none; background-color: white"
                                                   type="datetime" disabled="disabled"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-5 text-right">Phí thanh toán </label>

                                        <div class="col-sm-4">
                                            <fmt:setLocale value="vi_VN"/>
                                            <input style="border:none; background-color: white" type="hidden"
                                                   id="payAmount" disabled="disabled"
                                                   value="${contract.getMicContractTypeByContractTypeId().getPricePerYear()} VNĐ"/>
                                            <fmt:formatNumber
                                                    value="${contract.getMicContractTypeByContractTypeId().getPricePerYear()}"
                                                    type="currency"
                                                    maxFractionDigits="0"/>
                                        </div>

                                    </div>
                                </div>


                            </div>
                            <div class="modal-footer">
                                <input type="hidden" name="L_PAYMENTREQUEST_0_NAME0" id="content1" value="">
                                <input type="hidden" name="L_PAYMENTREQUEST_0_DESC0" id="content2">
                                <input type="hidden" name="L_PAYMENTREQUEST_0_QTY0" value="1">
                                <input type="hidden" name="PAYMENTREQUEST_0_ITEMAMT" id="payment">
                                <input type="hidden" name="PAYMENTREQUEST_0_TAXAMT" value="0">
                                <input type="hidden" name="PAYMENTREQUEST_0_AMT" id="paymentATM">
                                <input type="hidden" name="currencyCodeType" value="USD">
                                <input type="hidden" name="paymentType" value="Sale">
                                <input type="hidden" name="successUrl"
                                       value="/customer/contract?action=ActiveRenewContract">
                                <input type="hidden" name="txtContractCode" value="${contract.contractCode}"/>
                                <input type="hidden" name="action" value="RenewContract" id="actionId"/>
                                <input type="hidden" id="contractStatus" value="${contract.status}"/>
                                <input type="submit" class="btn btn-primary" value="Gia hạn hợp đồng" id="acceptRenew"/>

                                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- /.modal-content -->
            </form>
        </div>

        <!---------------------------------------- /.modal-dialog ---------------------------------------------->
        <div class="col-lg-12">
            <c:if test="${not empty validateErrors}">
                <div class="well well-lg text-danger ">
                    <ul>
                        <c:forEach var="error" items="${validateErrors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <div class="text-info text-center">
                ${message}
            </div>
            <c:if test="${contract.status.equalsIgnoreCase('Request cancel')}">
                <form action="${pageContext.request.contextPath}/customer/contract" method="post">
                    <div class="well well-lg text-center text-danger " style="height:128px !important;">
                        <label>Hợp đồng đã được yêu cầu hủy vui lòng chờ xác nhận của nhân viên</label> &nbsp;
                        <input type="hidden" name="contractcode"
                               value="${contract.contractCode}"/>
                        <input type="hidden" name="action" value="RejectRequestCancel"/>
                        <input type="submit" class="btn btn-danger small" value="Hủy Yêu Cầu"/><br/>

                        <div class="form-group" style="margin-bottom:5px">
                            <label class="col-md-4 text-right">Đã cầu hủy lúc: </label>

                            <div class="col-md-8 text-left">
                                <fmt:formatDate value="${contract.cancelDate}" pattern="dd/MM/yyyy"/>
                            </div>
                        </div>
                        <br/>

                        <div class="form-group" style="margin-bottom:5px">
                            <label class="col-md-4 text-right">Lý do hủy: </label>

                            <div class="col-md-8 text-left">
                                    ${contract.cancelReason}
                            </div>
                        </div>

                    </div>
                </form>

            </c:if>
            <c:if test="${contract.status.equalsIgnoreCase('Cancelled')}">
                <form action="${pageContext.request.contextPath}/customer/contract" method="post">
                    <div class="well well-lg text-center text-danger " style="height:140px !important;">
                        <div class="form-group">
                            <label class="col-md-6 text-right fa-lg"> Hợp đồng đã bị hủy </label>

                            <div class="col-md-6">

                            </div>
                        </div>
                        <br/>

                        <div class="form-group" style="margin-bottom:5px">
                            <label class="col-md-3 text-right">Đã cầu hủy lúc: </label>

                            <div class="col-md-9 text-left">
                                <fmt:formatDate value="${contract.cancelDate}" pattern="dd/MM/yyyy"/>
                            </div>
                        </div>
                        <br/>

                        <div class="form-group" style="margin-bottom:5px">
                            <label class="col-md-3 text-right">Lý do hủy: </label>

                            <div class="col-md-9 text-left">
                                    ${contract.cancelReason}
                            </div>
                        </div>
                        <br/>

                        <div class="form-group" style="margin-bottom:5px">
                            <label class="col-md-3 text-right">Ghi chú hủy: </label>

                            <div class="col-md-9 text-left">
                                <c:choose>
                                    <c:when test="${empty contract.cancelNote}">
                                        <label class="empty-value">Không có</label>
                                    </c:when>
                                    <c:otherwise>
                                        ${contract.cancelNote}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </form>

            </c:if>
            <c:if test="${contract.status.equalsIgnoreCase('Pending') && contract.startDate == contract.expiredDate}">

                <div class="alert alert-block alert-error fade in well well-lg text-info">
                    <h4 class="alert-heading">Hợp đồng của quý khách chưa được thanh toán!</h4>

                    <p>Quý khách có thể thanh toán trực tiếp tại công ty
                        <button class="btn" data-toggle="modal" title="Hiện địa chỉ công ty"
                                data-target=".map-modal"><i class="fa fa-map-marker"></i>
                        </button>
                    </p>
                    <form action="${pageContext.request.contextPath}/customer/contract" method="post">

                        <button class="btn btn-primary choice" type="submit" id="payContract"
                                rel="tooltip"
                                data-toggle="tooltip"
                                data-trigger="hover"
                                data-placement="bottom"
                                data-html="true"
                                data-title="
                                     <div class='form-horizontal'>
                                    <div class='form-group'>
                                        <label class='col-sm-5 text-right'>Loại hợp đồng :</label>

                                        <div class='col-sm-7'>
                                            ${contract.getMicContractTypeByContractTypeId().getName()}
                                        </div>
                                    </div>
                                    <div class='form-group'>
                                        <label class='col-sm-5 text-right'>Thời điểm bắt đầu :</label>

                                        <div class='col-sm-4'>
                                            <fmt:formatDate value='${contract.startDate}' pattern='dd/MM/yyyy'/>
                                        </div>
                                    </div>
                                    <div class='form-group'>
                                        <label class='col-sm-5 text-right'>Thời điểm kết thúc :</label>

                                        <div class='col-sm-4'>
                                            <fmt:formatDate value='${contract.expiredDate}' pattern='dd/MM/yyyy'/>
                                        </div>
                                    </div>
                                    <div class='form-group'>
                                        <label class='col-sm-5 text-right'>Phí thanh toán :</label>

                                        <div class='col-sm-4'>
                                            <fmt:setLocale value='vi_VN'/>
                                            <fmt:formatNumber
                                                    value='${contract.getMicContractTypeByContractTypeId().getPricePerYear()}'
                                                    type='currency'
                                                    maxFractionDigits='0'/>
                                        </div>

                                    </div>
                                </div>
                                ">
                            Thanh toán online bằng Paypal
                        </button>
                        <!-- input hidden -->
                        <input id="payAmount1" disabled="disabled" type="hidden"
                               value="${contract.getMicContractTypeByContractTypeId().getPricePerYear()} VNĐ"/>
                        <input id="contractCode" disabled="disabled" type="hidden"
                               value="${contract.contractCode}"/>
                        <input type="hidden" name="L_PAYMENTREQUEST_0_NAME0" value="">
                        <input type="hidden" name="L_PAYMENTREQUEST_0_DESC0" id="content3">
                        <input type="hidden" name="L_PAYMENTREQUEST_0_QTY0" value="1">
                        <input type="hidden" name="PAYMENTREQUEST_0_ITEMAMT" id="payment1">
                        <input type="hidden" name="PAYMENTREQUEST_0_TAXAMT" value="0">
                        <input type="hidden" name="PAYMENTREQUEST_0_AMT" id="paymentATM1">
                        <input type="hidden" name="currencyCodeType" value="USD">
                        <input type="hidden" name="paymentType" value="Sale">
                        <input type="hidden" name="successUrl"
                               value="/customer/contract?action=ActivePayContract">
                        <input type="hidden" name="txtContractCode" value="${contract.contractCode}">
                        <input type="hidden" name="action" value="PayContract">

                    </form>


                </div>
                <div class="modal fade map-modal" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-body">
                                <iframe
                                        width="100%"
                                        height="500"
                                        frameborder="0" style="border:0"
                                        src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBHWaWHbQJEFOvVmZw7tcR0qIGQQUoxsKM&q=Trường Đại Học FPT, tòa nhà Innovation, Công viên phần mềm Quang Trung, P.Tân Chánh Hiệp, Quận 12, TP. Hồ Chí Minh"
                                        >

                                </iframe>
                            </div>

                        </div>
                    </div>
                </div>

            </c:if>
        </div>

        <div role="tabpanel">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#commonInfo" aria-controls="profile" role="tab" data-toggle="tab">Thông tin chung</a>
                </li>
                <li role="presentation">
                    <a href="#compensations" aria-controls="profile" role="tab" data-toggle="tab">Lịch sử bồi
                        thường</a>
                </li>
                <li role="presentation" id="showPunishment">
                    <a href="#punishments" aria-controls="messages" role="tab" data-toggle="tab">Lịch sử vi phạm
                        luật
                        GT</a>
                </li>
                <li role="presentation">
                    <a href="#accidents" aria-controls="messages" role="tab" data-toggle="tab">Lịch sử tai nạn
                    </a>
                </li>
                <li role="presentation">
                    <a href="#cards" aria-controls="messages" role="tab" data-toggle="tab">Thẻ đang hoạt động
                    </a>
                </li>
                <c:if test="${contract.status.equalsIgnoreCase('Ready') }">
                    <%--<form action="${pageContext.request.contextPath}/customer/card" method="get">--%>
                    <c:if test="${isNewCardRequested == false}">
                        <div class="pull-right">
                            <a href="${pageContext.request.contextPath}/customer/card?action=newCard&contractCode=${param.code}"
                               class="btn btn-sm btn-primary">
                                Yêu cầu thẻ mới
                            </a>

                        </div>
                    </c:if>
                    <c:if test="${isNewCardRequested == true}">
                        <div class="pull-right">
                            <p class="text-value">
                        <span class="label label-info"
                              style="font-size: 16px">Đang yêu cầu thẻ mới</span>
                            </p>
                        </div>
                    </c:if>


                    <%--</form>--%>
                </c:if>
            </ul>
        </div>
        <br/>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="commonInfo">

                <%--<div class="col-md-5">--%>
                <%--<img src="http://finefrugality.files.wordpress.com/2012/06/handshake.jpg" width="100%" height="100%">--%>
                <%--</div>--%>
                <div class="form-horizontal">
                    <table class="table table-bordered">
                        <tr>
                            <td class="col-md-5">
                                <table class="table table-bordered">
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Mã hợp đồng</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            ${contract.contractCode}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="col-md-5">
                                            <label class="text-center">Ngày tham gia lúc</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            <fmt:formatDate value="${contract.startDate}" pattern="dd/MM/yyyy"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Ngày hết hạn</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            <fmt:formatDate value="${contract.expiredDate}" pattern="dd/MM/yyyy"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td class="col-md-5">
                                <table class="table table-bordered">

                                    <tr>
                                        <td class="text-center">
                                            <label>Quyền lợi bảo hiểm</label>
                                        </td>
                                        <td class="">
                                            ${contract.getMicContractTypeByContractTypeId().getDescription()}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="text-center">
                                            <label> Tình trạng hợp đồng</label>
                                        </td>
                                        <c:if test="${!contract.status.equalsIgnoreCase('Cancelled')}">
                                            <td class="text-center " colspan="2">
                                                <input type="hidden" name="txtNewStartDate" id="expiredDate"
                                                       value="${contract.expiredDate}"/>
                                                <label>
                                                    <input id="dateAvailable" type="text" disabled="disabled"
                                                           style="border:none ; background-color: white; width: 100%"/></label>
                                            </td>
                                        </c:if>
                                        <c:if test="${contract.status.equalsIgnoreCase('Cancelled')}">
                                            <td class="text-center" colspan="2">
                                                <label>Hợp đồng đã bị hủy</label>
                                            </td>
                                        </c:if>
                                    </tr>
                                    <tr>
                                        <td class="text-center">
                                            <label>Trạng thái</label>
                                        </td>
                                        <c:if test="${contract.status.equalsIgnoreCase('Ready')}">
                                            <td class="text-center">
                                                <span class="fa label label-success"
                                                      style="font-size: 16px">Sẵn sàng</span>
                                            </td>
                                        </c:if>
                                        <c:if test="${contract.status.equalsIgnoreCase('Cancelled')}">
                                            <td class="text-center"><span class="label label-dark"
                                                                          style="font-size: 16px">Đã huỷ</span></td>
                                        </c:if>
                                        <c:if test="${contract.status.equalsIgnoreCase('No card')}">
                                            <td class="text-center"><span class="label label-primary"
                                                                          style="font-size: 16px">Chưa có thẻ</span>
                                            </td>
                                        </c:if>
                                        <c:if test="${contract.status.equalsIgnoreCase('Expired')}">
                                            <td class="text-center"><span class="label label-danger"
                                                                          style="font-size: 16px"> Hết hạn</span></td>
                                        </c:if>
                                        <c:if test="${contract.status.equalsIgnoreCase('Pending')}">
                                            <td class="text-center"><span
                                                    class="label label-default"
                                                    style="font-size: 16px">Chưa kích hoạt</span></td>
                                        </c:if>
                                        <c:if test="${contract.status.equalsIgnoreCase('Request cancel')}">
                                            <td class="text-center"><span class="label label-warning"
                                                                          style="font-size: 16px">Yêu cầu hủy</span>
                                            </td>
                                        </c:if>
                                    </tr>

                                </table>
                            </td>
                        </tr>
                        <tr class="active">
                            <td colspan="2" class="text-center" style="font-size: 15px"><label class="text-center">Thông
                                tin xe</label></td>
                        </tr>
                        <tr>
                            <td>
                                <table class="table table-bordered">
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Biển số đăng ký</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            ${contract.plate}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Số khung</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            ${contract.chassis}
                                        </td>

                                    </tr>
                                </table>
                            </td>
                            <td>
                                <table class="table table-bordered">
                                    <tr>
                                        <td class="col-md-5">
                                            <label class="text-center">Nhãn hiệu</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            ${contract.brand}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Số máy</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            ${contract.engine}
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table class="table table-bordered">
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Dung tích</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            ${contract.capacity}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Số loại</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            <c:choose>
                                                <c:when test="${empty contract.modelCode}">
                                                    <label class="empty-value">Không có</label>
                                                </c:when>
                                                <c:otherwise>
                                                    ${contract.modelCode}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                </table>
                            </td>
                            <td>
                                <table class="table table-bordered">
                                    <tr>
                                        <td class="col-md-5">
                                            <label class="text-center">Màu sơn</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            <c:choose>
                                                <c:when test="${empty contract.color}">
                                                    <label class="empty-value">Không có</label>
                                                </c:when>
                                                <c:otherwise>
                                                    ${contract.color}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Loại xe</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            <c:choose>
                                                <c:when test="${empty contract.vehicleType}">
                                                    <label class="empty-value">Không có</label>
                                                </c:when>
                                                <c:otherwise>
                                                    ${contract.vehicleType}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <table class="table table-bordered">
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Năm sản xuất</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            <c:choose>
                                                <c:when test="${empty contract.yearOfManufacture}">
                                                    <label class="empty-value">Không có</label>
                                                </c:when>
                                                <c:otherwise>
                                                    ${contract.yearOfManufacture}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Tự trọng</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            ${contract.capacity}
                                        </td>
                                    </tr>
                                </table>
                            </td>

                            <td>
                                <table class="table table-bordered">
                                    <tr>
                                        <td class="col-md-5">
                                            <label>Số người được chở</label>
                                        </td>
                                        <td class="col-md-5 ">
                                            <c:choose>
                                                <c:when test="${empty contract.seatCapacity}">
                                                    <label class="empty-value">Không có</label>
                                                </c:when>
                                                <c:otherwise>
                                                    ${contract.seatCapacity}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                </table>
                            </td>
                        </tr>


                    </table>
                </div>
            </div>

            <%----------------------------------------------------------------------------------------------------%>
            <%------------------------------------------COMPENSATION----------------------------------------------%>
            <div role="tabpane1" class="tab-pane" id="compensations">
                <div class="row">
                    <div class="col-lg-12">
                          <span class="pull-right">
                            <form action="${pageContext.request.contextPath}/customer/compensation" method="get">
                                <input type="hidden" name="action" value="Create">
                                <input type="hidden" name="contractCode" value="${contract.contractCode}">
                                <button href="${pageContext.request.contextPath}/customer/compensation?action=create"
                                        type="submit"
                                        class="btn btn-success ">Yêu cầu bồi thường
                                </button>
                            </form>
                          </span>
                    </div>
                </div>
                <br/>

                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <div class="pull-left">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có ${compensationPaginator.itemSize} vụ bồi thường</b>
                        </div>
                        <div class="pull-right ">
                            <input type="text" class="form-control long-text-box"
                                   placeholder="Tìm kiếm theo tên, mã hợp đồng"/>
                            <input type="button" class="btn btn-default" value="Tìm kiếm"/>

                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel panel-body">
                        <div class="table table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr class="success">
                                    <th class="text-center ">#
                                    </th>
                                    <th class="text-center ">Mã yêu cầu
                                    </th>
                                    <th class="text-center ">Mã hợp đồng
                                    </th>
                                    <th class="text-center ">Ngày yêu cầu
                                    </th>
                                    <th class="text-center ">Ngày giải quyết
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="compensations"
                                       value="${compensationPaginator.getItemsOnCurrentPage(param.page)}"/>
                                <c:choose>
                                    <c:when test="${compensations.size() == 0}">
                                        <tr>
                                            <td colspan="5" style="vertical-align: middle; text-align: center;">
                                                Không có đền bù nào
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${compensationPaginator.getItemsOnCurrentPage(param.page)}"
                                                   var="compensation"
                                                   varStatus="counter">
                                            <tr>
                                                <td class="text-center">
                                                        ${(compensationPaginator.getCurrentPage(param.page) - 1) * compensationPaginator.itemPerPage + counter.count}
                                                </td>
                                                <td class="text-center"><a
                                                        href="${pageContext.request.contextPath}/customer/compensation?action=Detail&code=${compensation.compensationCode}">
                                                        ${compensation.compensationCode}</a>
                                                </td>
                                                <td class="text-center"><a
                                                        href="${pageContext.request.contextPath}/customer/contract?action=Detail&code=${compensation.contractCode}">
                                                        ${compensation.contractCode}</a>
                                                </td>

                                                <td class="text-center">
                                                    <fmt:formatDate value='${compensation.createdDate}'
                                                                    pattern='dd/MM/yyyy'/>
                                                </td>
                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${empty compensation.resolveDate}">
                                                            <span class="label label-warning">Đang giải quyết</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <fmt:formatDate value='${compensation.resolveDate}'
                                                                            pattern='dd/MM/yyyy'/>
                                                        </c:otherwise>
                                                    </c:choose>
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
                                    <c:if test="${param.page != 1 && not empty param.page}">
                                        <li>
                                            <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=1"
                                               aria-label="Previous">
                                                <span aria-hidden="true">Đầu</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="1" end="${compensationPaginator.pageSize}" var="pageNumber">
                                        <li ${param.page == pageNumber ||(pageNumber == 1 && empty param.page) ? "class='active'": ""} >
                                            <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=${pageNumber}">${pageNumber}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${param.page != compensationPaginator.pageSize && compensationPaginator.pageSize != 1}">
                                        <li>
                                            <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=${compensationPaginator.pageSize}"
                                               aria-label="Next">
                                                <span aria-hidden="true">Cuối</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
            <%----------------------------------------------------------------------------------------------------%>
            <%------------------------------------------PUNISHMENT------------------------------------------------%>
            <div role="tabpane1" class="tab-pane" id="punishments">

                <form action="${pageContext.request.contextPath}/customer/punishment" method="post">
                    <div class="modal fade punishmentDialog" tabindex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <div class="text-center alert alert-danger alert-dismissible hide"
                                         id="notifyPunishment"
                                         role="alert">
                                        Nội dung vi phạm không được để trống
                                    </div>
                                    <div class="text-center alert alert-danger alert-dismissible hide"
                                         id="notifyPunishment1"
                                         role="alert">
                                        Văn bản vi phạm không được để trống
                                    </div>
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">Nội dung vi phạm *</label>

                                            <div class="col-sm-5">
                                                <textarea rows="8" cols="80" id="contentPunishment"
                                                          autofocus="autofocus">

                                                </textarea>
                                                <input type="hidden" id="titlePunishment"
                                                       name="punishment:title">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">Đính kèm *</label>

                                            <div class="col-sm-5" style="padding-top: 5px">
                                                <img id="imgAttachment" height="100px" src=""/>
                                                <input id="attachment" name="punishment:attachment"
                                                       class="form-control input-md"
                                                       type="hidden" maxlength="255">


                                                <script type="text/javascript"
                                                        src="//api.filepicker.io/v2/filepicker.js"></script>

                                                <input type="filepicker" data-fp-apikey="AEbPPQfPfRHqODjEl5AZ2z"
                                                       required id="attImage"
                                                       title="Đính kèm ảnh vi phạm không được trống"
                                                       onchange="$('#imgAttachment').attr('src', event.fpfile.url);$('#attachment').val(event.fpfile.url);">
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <%--Post to server (PersonalController)--%>
                                    <input type="hidden" name="action" value="Create"/>
                                    <input type="hidden" name="punishment:contractCode"
                                           value="${contract.contractCode}"/>
                                    <%---------------------------------------%>
                                    <input id="confirmPunishment" type="submit" class="btn btn-primary" name="Xác Nhận"
                                           value="Xác Nhận"/>
                                    <input type="button" class="btn btn-danger" id="cancel" data-dismiss="modal"
                                           value="Hủy Bỏ"/>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="pull-right">
                            <button type="button" data-toggle="modal" data-target=".punishmentDialog"
                                    class="btn btn-success" title="Thêm vi phạm" id="addPunishment">
                                Thêm vi phạm
                            </button>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="panel panel-default">

                    <div class="panel panel-heading">
                        <div class="pull-left">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có ${punishmentPaginator.itemSize} trường hợp vi phạm</b>
                        </div>

                        <div class="pull-right ">
                            <input type="text" class="form-control long-text-box"
                                   placeholder="Tìm kiếm theo mã vi phạm"/>
                            <input type="button" class="btn btn-default" value="Tìm kiếm"/>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel panel-body">
                        <div class="table table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr class="success">
                                    <th class="text-center " style="width: 10% !important;">Mã vi phạm
                                    </th>
                                    <th class=" text-center">
                                        Nội dung vi phạm
                                    </th>
                                    <th class="text-center">
                                        Ngày gởi đi
                                    </th>
                                    <th class="text-center col-md-1">
                                        Biên bản
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="punishments"
                                       value="${punishmentPaginator.getItemsOnCurrentPage(param.page)}"/>
                                <c:choose>
                                    <c:when test="${punishments.size() == 0}">
                                        <tr>
                                            <td colspan="5" style="vertical-align: middle; text-align: center;">
                                                Không có vi phạm nào
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${punishmentPaginator.getItemsOnCurrentPage(param.page)}"
                                                   var="punishment"
                                                   varStatus="counter">
                                            <tr>
                                                <td class="text-center">
                                                        ${punishment.id}
                                                </td>
                                                <td>
                                                        ${punishment.title}
                                                </td>
                                                <td class="text-center">
                                                    <fmt:formatDate value='${punishment.createdDate}'
                                                                    pattern='dd/MM/yyyy'/>
                                                </td>
                                                <td class="text-center">
                                                    <a href="${punishment.attachment}"
                                                       target="_newtab"><i class="fa fa-file-text-o"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel panel-footer">
                        <nav class="text-right">
                            <ul class="pagination">
                                <c:if test="${param.page != 1 && not empty param.page}">
                                    <li>
                                        <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=1"
                                           aria-label="Previous">
                                            <span aria-hidden="true">Đầu</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${punishmentPaginator.pageSize}" var="pageNumber">
                                    <li ${param.page == pageNumber ||(pageNumber == 1 && empty param.page) ? "class='active'": ""} >
                                        <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=${pageNumber}">${pageNumber}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${param.page != punishmentPaginator.pageSize && compensationPaginator.pageSize != 1}">
                                    <li>
                                        <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=${punishmentPaginator.pageSize}"
                                           aria-label="Next">
                                            <span aria-hidden="true">Cuối</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <%----------------------------------------------------------------------------------------------------%>
            <%------------------------------------------Accident------------------------------------------------%>
            <div role="tabpane1" class="tab-pane" id="accidents">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="pull-right">
                            <a href="${pageContext.request.contextPath}/customer/accident?action=create&code=${contract.contractCode}"
                               class="btn btn-success">
                                <i class="fa fa-plus"></i> Thông báo tai nạn mới
                            </a>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <div class="pull-left">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có ${listAccident.size()} tai nạn</b>
                        </div>
                        <div class="pull-right ">
                            <input type="text" class="form-control long-text-box"
                                   placeholder="Tìm kiếm theo mã tai nạn"/>
                            <input type="button" class="btn btn-default" value="Tìm kiếm"/>
                        </div>
                        <div class="clearfix"></div>
                    </div>

                    <div class="panel panel-body">

                        <div class="table table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr class="success">
                                    <th class="text-center col-md-1">
                                        Mã tai nạn
                                    </th>
                                    <th class="text-center">
                                        Nội dung
                                    </th>
                                    <th class=" text-center" style="width: 14% !important;">
                                        Thời gian
                                    </th>
                                    <th class="text-center col-md-1">
                                        Mô tả
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${listAccident.size() == 0}">
                                        <tr>
                                            <td colspan="5" style="vertical-align: middle; text-align: center;">
                                                Không có tai nạn nào
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${listAccident}"
                                                   var="accident"
                                                   varStatus="counter">
                                            <tr>
                                                <td class="text-center">
                                                        ${accident.id}
                                                </td>
                                                <td>
                                                        ${accident.title}
                                                </td>
                                                <td class="text-center">
                                                    <fmt:formatDate value='${accident.createdDate}'
                                                                    pattern='dd/MM/yyyy'/>
                                                </td>
                                                <td class="text-center">
                                                    <a href="${accident.attachment}"
                                                       target="_newtab"><i class="fa fa-file-text-o"></i></a>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                        <%--<tr id="addMore" class="hide">--%>
                                        <%--<td class="text-center">--%>

                                        <%--</td>--%>
                                        <%--<td class="text-center">--%>
                                        <%--<input type="text" class="form-control" id="accidentContent">--%>
                                        <%--</td>--%>
                                        <%--<td class="text-center">--%>
                                        <%--<input type="text" class="text-center handleInput form-control"--%>
                                        <%--disabled="disabled"--%>
                                        <%--id="accidentDate">--%>
                                        <%--</td>--%>
                                        <%--<td class="text-center">--%>
                                        <%--<input type="file" class="text-center" id="accidentAtt">--%>
                                        <%--</td>--%>
                                        <%--</tr>--%>
                                    </c:otherwise>
                                </c:choose>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel panel-footer">
                        <nav class="text-right">

                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <%----------------------------------------------------------------------------------------------------%>
            <%------------------------------------------Card Active------------------------------------------------%>
            <div role="tabpane1" class="tab-pane" id="cards">

                <div class="panel panel-default">
                    <div class="panel panel-body">

                        <div class="table table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr class="success">

                                    <th class="text-center col-md-2">
                                        Mã thẻ
                                    </th>
                                    <th class="text-center col-md-2">
                                        Ngày kích hoạt
                                    </th>
                                    <th class=" text-center col-md-1">
                                        Trạng thái
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${empty card.cardId}">
                                        <tr>
                                            <td colspan="5" style="vertical-align: middle; text-align: center;">
                                                Không có thẻ nào
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/customer/card?action=detail&cardId=${card.cardId}"
                                                   target="_newtab">
                                                        ${card.cardId}
                                                </a>
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${card.activatedDate}" pattern="dd/MM/yyyy"/>
                                                lúc
                                                <fmt:formatDate value="${card.activatedDate}" type="time"/>
                                            </td>
                                            <td class="text-center">
                                                <c:choose>
                                                    <c:when test="${empty card.deactivatedDate}">
                                                        <span class="label label-success">Hoạt động</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-danger">Ngưng hoạt động</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:otherwise>

                                </c:choose>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%--<div class="panel panel-footer">--%>
                    <%--<nav class="text-right">--%>

                    <%--</ul>--%>
                    <%--</nav>--%>
                    <%--</div>--%>
                </div>
            </div>
            <%----------------------------------------------------------------------------------------------------%>
        </div>

    </div>

</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<script src="//www.paypalobjects.com/api/checkout.js" async></script>

<%@ include file="_shared/footer.jsp" %>
