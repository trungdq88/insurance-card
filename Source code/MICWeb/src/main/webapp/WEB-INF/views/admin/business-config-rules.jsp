<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TriPQM
  Date: 07/02/2015
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thiết lập cấu hình</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="text-info" >
            ${info}
        </div>
        <div class="row">
            <div class="col-lg-12">
                <br/>
                <c:if test="${not empty validateErrors}">
                    <div class="text-danger">
                        <ul>
                            <c:forEach var="error" items="${validateErrors}">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/admin"
                      method="get" class="form-horizontal">

                    <fieldset>
                        <legend>Thời gian tạo hợp đồng
                        </legend>
                        <div class="form-group">
                            <div class="col-sm-3 ">
                                <label>Trước ngày hiện tại:</label>
                                <input  name="config:startDateBefore" type="number" class="form-control input-md" required min="0" value="${submitted.startDateBefore}" > ngày
                            </div>
                            <div class="col-sm-1 col-sm-offset-0.5">
                                <p class="text-center">Đến</p>
                                <p class="text-value text-center">-</p>
                            </div>
                            <div class="col-sm-3 col-sm-offset-0.5">
                                <label>Sau ngày hiện tại:</label>
                                <input  name="config:startDateAfter" type="number" class="form-control input-md" required min="0" value="${submitted.startDateAfter}"> ngày
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Kỳ hạn hợp đồng mặc định</legend>
                        <div class="form-group">
                            <div class="col-sm-3 ">
                                <label>Thời gian:</label>
                                <input  name="config:contractDefaultTerm" type="number" class="form-control input-md" required min="0" value="${submitted.contractDefaultTerm}"> tháng
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Ngày thanh toán</legend>
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label>Trước ngày tạo hợp đồng:</label>
                                <input  name="config:paidDaterBefore" type="number" class="form-control input-md" required min="0" value="${submitted.paidDaterBefore}"> ngày
                            </div>
                            <div class="col-sm-1 col-sm-offset-0.5">
                                <p class="text-center">Đến</p>
                                <p class="text-value text-center">-</p>
                            </div>
                            <div class="col-sm-3 col-sm-offset-0.5">
                                <label>Sau ngày tạo hợp đồng: </label>
                                <input  name="config:paidDateAfter" type="number" class="form-control input-md" required min="0" value="${submitted.paidDateAfter}"> ngày
                            </div>
                        </div>

                    </fieldset>
                        <!-- Text input-->

                    <fieldset>
                        <legend>Ngày hủy hợp đồng</legend>
                    </fieldset>
                        <div class="form-group">
                            <div class="col-sm-3 ">
                                <label>Trước ngày hiện tại:</label>
                                <input  name="config:cancelDateBefore" type="number" class="form-control input-md" required min="0" value="${submitted.cancelDateBefore}" >ngày
                            </div>
                            <div class="col-sm-1 col-sm-offset-0.5">
                                <p class="text-center">Đến</p>
                                <p class="text-value text-center">-</p>
                            </div>
                            <div class="col-sm-3 col-sm-offset-0.5">
                                <label>Sau ngày hiện tại:</label>
                                <input  name="config:cancelDateAfter" type="number" class="form-control input-md" required min="0" value="${submitted.cancelDateAfter}" >ngày
                            </div>
                        </div>
                        <fieldset>
                            <legend>
                                Thông báo hợp đồng sắp hết hạn khi hợp đồng còn:
                            </legend>
                        </fieldset>
                        <!-- Text input-->
                        <div class="form-group">
                            <div class="col-sm-3 ">
                                <label>Lần 1:</label>
                                <input  name="config:nearlyExceedExpiredOne" type="number" class="form-control input-md" required min="0" value="${submitted.nearlyExceedExpiredOne}"> ngày
                            </div>
                            <div class="col-sm-3 col-sm-offset-1">
                                <label>Lần 2</label>
                                <input  name="config:nearlyExceedExpiredTwo" type="number" class="form-control input-md" required min="0" value="${submitted.nearlyExceedExpiredTwo}"> ngày
                            </div>
                            <div class="col-sm-3 col-sm-offset-1">
                                <label>Lần 3</label>
                                <input  name="config:nearlyExceedExpiredThree" type="number" class="form-control input-md" required min="0" value="${submitted.nearlyExceedExpiredThree}"> ngày
                            </div>

                        </div>
                    <fieldset>
                        <legend>Thời hạn thanh toán hợp đồng</legend>
                        <div class="form-group">
                            <div class="col-sm-4">
                                <label class="text-value">Sau ngày đăng ký hợp đồng:</label>

                            </div>
                            <div class="col-sm-3">
                                <input  name="config:paymentDueDate" type="number" class="form-control input-md" required min="0" value="${submitted.paymentDueDate}"> ngày
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>
                            Các khoản phí
                        </legend>
                        <div class="form-group">

                            <div class="col-sm-5 ">
                                <label>Phí làm lại thẻ:</label>
                                <input id="newCardRequestFee"  name="config:newCardRequestFee" type="text" class="form-control input-md" required  value="${submitted.newCardRequestFee}"> VND
                            </div>
                            <div class="col-sm-5 col-sm-offset-1">
                                <label>Phí vận chuyển:</label>
                                <input id="deliveryFee"  name="config:deliveryFee" type="text" class="form-control input-md" required  value="${submitted.deliveryFee}"> VND
                            </div>
                        </div>
                    </fieldset>
                    <div class="text-center">
                        <input type="hidden" name="action" value="editConfig">
                        <button type="submit" class="btn btn-success">
                            Cập nhật
                        </button>

                    </div>
                </form>


            </div>
        </div>
        <!-- /#wrapper -->


        <%@ include file="_shared/footer.jsp" %>