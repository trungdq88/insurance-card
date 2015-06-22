<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thêm hợp đồng mới</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="bs-example well text-center text-primary">
                    <i class="fa fa-check"></i> Hợp đồng đã được khởi tạo thành công!
                </div>

                <c:set var="info" value="${requestScope.CREATED}"/>

                <c:if test="${not empty info}">
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>Mã hợp đồng</th>
                                <th>Tên khách hàng</th>
                                <th>Ngày bắt đầu</th>
                                <th>Ngày kết thúc</th>
                                <th>Trạng thái</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/staff/contract?action=detail&code=${info.contractCode}">
                                            ${info.contractCode}
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/staff/customer?action=detail&code=${info.customerCode}">
                                    ${info.micCustomerByCustomerCode.name}
                                    </a>
                                </td>
                                <td>
                                    <fmt:formatDate value="${info.startDate}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${info.expiredDate}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                <span class="label label-info">
                                        ${info.status}
                                </span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <br/>

                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/staff/contract" type="button" class="btn btn-primary">
                        <i class="fa fa-arrow-left"></i>
                        Quay lại trang hợp đồng
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>