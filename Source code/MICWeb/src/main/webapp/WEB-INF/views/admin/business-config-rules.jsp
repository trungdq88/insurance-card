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
                <form action="${pageContext.request.contextPath}/admin/config"
                      method="post" class="form-horizontal">
                    <table class="table table-striped table-bordered table-condensed">
                        <tbody>
                        <tr>
                            <td><strong>Các giới hạn ngày theo chính sách của công ty</strong></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Thời hạn thanh toán hợp đồng mới:</td>
                            <td><input  name="config:paymentDueDate" type="number" class="form-control input-md" required value="${submitted.paymentDueDate}"></td>
                            <td> ngày</td>
                        </tr>
                        <tr>
                            <td>Thời gian thông báo hợp đồng sắp hết hạn lần 1: Trước</td>
                            <td><input  name="config:nearlyExceedExpiredOne" type="number" class="form-control input-md" required min="0" value="${submitted.nearlyExceedExpiredOne}"></td>
                            <td> ngày</td>
                        </tr>
                        <tr>
                            <td>Thời gian thông báo hợp đồng sắp hết hạn lần 2: Trước</td>
                            <td><input  name="config:nearlyExceedExpiredTwo" type="number" class="form-control input-md" required min="0" value="${submitted.nearlyExceedExpiredTwo}"></td>
                            <td>ngày</td>
                        </tr>
                        <tr>
                            <td>Thời gian thông báo hợp đồng sắp hết hạn lần 3: Trước</td>
                            <td><input  name="config:nearlyExceedExpiredThree" type="number" class="form-control input-md" required min="0" value="${submitted.nearlyExceedExpiredThree}"></td>
                            <td>ngày</td>
                        </tr>
                        <tr>
                            <td>Thời hạn mặc định của hợp đồng:</td>
                            <td><input  name="config:contractDefaultTerm" type="number" class="form-control input-md" required min="0" value="${submitted.contractDefaultTerm}"></td>
                            <td> tháng</td>
                        </tr>
                        <tr>
                            <td>Thời hạn tối thiểu của hợp đồng:</td>
                            <td><input  name="config:contractMinTerm" type="number" class="form-control input-md" required min="0" value="${submitted.contractMinTerm}"></td>
                            <td> tháng</td>
                        </tr>
                        <tr>
                            <td><strong>Giới hạn chỉnh sửa ngày cho nhân viên</strong></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Ngày bắt đầu hợp đồng trước ngày hiện tại:</td>
                            <td><input id="startDateBefore"  name="config:startDateBefore" type="number" class="form-control input-md" required min="0" value="${submitted.startDateBefore}" ></td>
                            <td> ngày </td>
                        </tr>
                        <tr>
                            <td>Ngày bắt đầu hợp đồng sau ngày hiện tại:</td>
                            <td><input id="startDateAfter"  name="config:startDateAfter" type="number" class="form-control input-md" required min="0" value="${submitted.startDateAfter}"></td>
                            <td> ngày </td>
                        </tr>
                        <tr>
                            <td>Ngày thanh toán hợp đồng trước ngày hiện tại:</td>
                            <td><input  name="config:paidDaterBefore" type="number" class="form-control input-md" required min="0" value="${submitted.paidDaterBefore}"></td>
                            <td> ngày </td>
                        </tr>
                        <tr>
                            <td>Ngày thanh toán hợp đồng sau ngày hiện tại:</td>
                            <td><input  name="config:paidDateAfter" type="number" class="form-control input-md" required min="0" value="${submitted.paidDateAfter}"></td>
                            <td> ngày </td>
                        </tr>
                        <tr>
                            <td>Ngày huỷ hợp đồng trước ngày hiện tại:</td>
                            <td><input  name="config:cancelDateBefore" type="number" class="form-control input-md" required min="0" value="${submitted.cancelDateBefore}" ></td>
                            <td> ngày </td>
                        </tr>
                        <tr>
                            <td>Ngày huỷ hợp đồng sau ngày hiện tại:</td>
                            <td><input  name="config:cancelDateAfter" type="number" class="form-control input-md" required min="0" value="${submitted.cancelDateAfter}" ></td>
                            <td> ngày </td>
                        </tr>
                        <tr>
                            <td><strong>Các loại chi phí</strong></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Phí làm thẻ mới:</td>
                            <td><input id="newCardRequestFee"  name="config:newCardRequestFee" type="number" class="form-control input-md" required  value="${submitted.newCardRequestFee}"></td>
                            <td>VNĐ</td>
                        </tr>
                        <tr>
                            <td>Phí vận chuyển thẻ:</td>
                            <td><input id="deliveryFee"  name="config:deliveryFee" type="number" class="form-control input-md" required  value="${submitted.deliveryFee}"></td>
                            <td>VNĐ</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="text-center">
                        <input type="hidden" name="action" value="editConfig">
                        <button type="submit" class="btn btn-success">
                            Cập nhật
                        </button>

                    </div>
                </form>


            </div>
        </div>
        </div>
        <!-- /#wrapper -->


        <%@ include file="_shared/footer.jsp" %>