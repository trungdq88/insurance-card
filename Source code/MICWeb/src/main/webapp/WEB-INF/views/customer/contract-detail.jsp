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
                <li role="presentation">
                    <a href="#commonInfo" aria-controls="profile" role="tab" data-toggle="tab">Thông Tin Chung</a>
                </li>
                <li role="presentation" class="active">
                    <a href="#compensations" aria-controls="profile" role="tab" data-toggle="tab">Lịch sử bồi
                        thường</a>
                </li>
                <li role="presentation">
                    <a href="#punishments" aria-controls="messages" role="tab" data-toggle="tab">Lịch sử vi phạm
                        luật
                        GT</a>
                </li>
                <form action="${pageContext.request.contextPath}/customer/card" method="post">
                <div class="pull-right">
                    <%--<a href="${pageContext.request.contextPath}/customer/card?action=newCard&code=${param.code}"--%>
                       <%--class="btn btn-sm btn-success">--%>
                        <%--<i class="fa fa-plus"></i>--%>
                        <%--Yêu cầu thẻ mới--%>
                    <%--</a>--%>
                    <button type="submit" class="btn btn-success">
                        <i class="fa fa-plus"></i> Yêu cầu thẻ mới
                    </button>
                    <input type="hidden" name="action" value="newCard">
                    <input type="hidden" name="contractCode" value="${contract.contractCode}">
                </div>
                    </form>
            </ul>
        </div>
        <br/>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane " id="commonInfo">

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

            <div role="tabpane1" class="tab-pane active" id="compensations">
                <div class="row">
                    <div class="col-lg-12">
                          <span class="pull-right">
                              <a href="${pageContext.request.contextPath}/customer/compensation?action=create"
                                 class="btn btn-primary">Yêu Cầu Bồi Thường</a>
                          </span>
                    </div>

                </div>
                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <div class="pull-left">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có ${compensationPaginator.itemSize} Vụ Bồi Thường</b>
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
                                    <th class="text-center ">Mã yêu Cầu
                                    </th>
                                    <th class="text-center ">Mã Hợp Đồng
                                    </th>
                                    <th class="text-center ">Tên Khách Hàng
                                    </th>
                                    <th class="text-center ">Ngày Yêu Cầu
                                    </th>
                                    <th class="text-center ">Ngày Giải Quyết
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
                                                        href="${pageContext.request.contextPath}/customer/compensation?action=detail">
                                                        ${compensation.compensationCode}</a>
                                                </td>
                                                <td class="text-center">
                                                    <a href="detail-contract.html">${compensation.contractCode}</a>
                                                </td>
                                                <td class="text-center">
                                                    <a href="detail-customer.html">${compensation.driverName}</a>
                                                </td>
                                                <td class="text-center">
                                                        ${compensation.createdDate}
                                                </td>
                                                <td class="text-center">
                                                        ${compensation.resolveDate}
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
                                            <a href="?action=${param.action}&keyword=${param.keyword}&page=1"
                                               aria-label="Previous">
                                                <span aria-hidden="true">Đầu</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="1" end="${compensationPaginator.pageSize}" var="pageNumber">
                                        <li ${param.page == pageNumber ||(pageNumber == 1 && empty param.page) ? "class='active'": ""} >
                                            <a href="?action=${param.action}&keyword=${param.keyword}&page=${pageNumber}">${pageNumber}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${param.page != compensationPaginator.pageSize && compensationPaginator.pageSize != 1}">
                                        <li>
                                            <a href="?action=${param.action}&keyword=${param.keyword}&page=${compensationPaginator.pageSize}"
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

            <div role="tabpane1" class="tab-pane" id="punishments">

                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <div class="pull-left">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có 10 trường hợp vi phạm</b>
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
                                <tbody>
                                <tr>
                                    <th class="col-md-2 text-center">
                                        Ngày Vi Phạm
                                    </th>
                                    <th class="col-md-9 text-center">
                                        Nội Dung Vi Phạm
                                    </th>
                                    <th class="col-md-1 text-center">
                                        Biên Bản
                                    </th>
                                </tr>
                                </tbody>
                                <tbody>
                                <% for (int i = 1; i <= 10; i++) {
                                    out.print("   <tr>\n" +
                                            "                            <td class=\"text-center\">\n" +
                                            "                                12/02/2015\n" +
                                            "                            </td>\n" +
                                            "                            <td class=\"text-left\">\n" +
                                            "                                Sử dụng Giấy đăng ký xe bị tẩy xóa; Không đúng số khung, số máy hoặc không do cơ quan có\n" +
                                            "                                thẩm quyền cấp.\n" +
                                            "                                Phạt tiền từ 300.000 đến 400.000. Đồng thời tịch thu Giấy đăng ký không hợp lệ\n" +
                                            "                            </td>\n" +
                                            "                            <td class=\"text-center\">\n" +
                                            "                                <a href=\"#\" id=\"showPunishment_" + i + "\" class=\"fa-lg\"><i class=\"fa fa-file-image-o\"></i></a>\n" +
                                            "                            </td>\n" +

                                            "     </tr> ");
                                }

                                %>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel panel-footer">
                        <nav class="text-right">
                            <ul class="pagination">
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li>
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<script src="//www.paypalobjects.com/api/checkout.js" async></script>

<%@ include file="_shared/footer.jsp" %>
