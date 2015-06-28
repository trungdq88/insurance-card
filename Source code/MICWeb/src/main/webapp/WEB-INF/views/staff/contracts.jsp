<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Hợp đồng

                    <div class="pull-right">
                        <a href="${pageContext.request.contextPath}/staff/contract?action=create"
                           class="btn btn-success">
                            <i class="fa fa-plus"></i>
                            Hợp đồng mới
                        </a>
                    </div>
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">

            <c:set var="info" value="${contractPaginator.getItemsOnCurrentPage(param.page)}"/>

            <div class="panel-heading">
                <div class="pull-left center-dropdown-button">
                    <!--<input type="checkbox" class="check-all"/>-->
                    <b>Có ${contractPaginator.itemSize} hợp đồng (${contractPaginator.pageSize} trang)</b>
                </div>
                <div class="pull-right no-wrap">
                    <form action="${pageContext.request.contextPath}/staff/contract" method="get">
                        <input type="text" class="form-control long-text-box" name="keyword"
                               placeholder="Tìm kiếm theo tên, mã hợp đồng" value="${param.keyword}"/>
                        <input type="submit" class="btn btn-default" value="Tìm kiếm"/>
                        <input type="hidden" name="action" value="search"/>
                    </form>
                </div>
                <div class="clearfix"></div>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-hover table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Mã hợp đồng</th>
                            <th>Tên khách hàng</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cont" items="${info}" varStatus="counter">
                            <tr>
                                <td>${(contractPaginator.getCurrentPage(param.page) - 1) * contractPaginator.itemPerPage + counter.count}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/staff/contract?action=detail&code=${cont.contractCode}">
                                            ${cont.contractCode}
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/staff/customer?action=detail&code=${cont.customerCode}">
                                            ${cont.micCustomerByCustomerCode.name}
                                    </a>
                                </td>
                                <td>
                                    <fmt:formatDate value="${cont.startDate}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${cont.expiredDate}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                    <c:set var="status" value="${cont.status}"/>
                                    <c:choose>
                                        <c:when test="${status.equalsIgnoreCase('Pending')}">
                                            <span class="label label-gray">Chờ thanh toán</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('No card')}">
                                            <span class="label label-primary">Chưa có thẻ</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('Ready')}">
                                            <span class="label label-success">Sẵn sàng</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('Request cancel')}">
                                            <span class="label label-warning">Yêu cầu hủy</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('Expired')}">
                                            <span class="label label-danger">Hết hạn</span>
                                        </c:when>
                                        <c:when test="${status.equalsIgnoreCase('Cancelled')}">
                                            <span class="label label-dark">Đã huỷ</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="label label-default">Không trạng thái</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
                <nav class="text-right">
                    <ul class="pagination">
                        <li>
                            <a href="?action=${param.action}&keyword=${param.keyword}&page=1" aria-label="Previous">
                                <span aria-hidden="true">Đầu</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${contractPaginator.pageSize}" var="pageNumber">
                            <li ${param.page == pageNumber ||
                            (pageNumber == 1 && empty param.page) ? "class='active'": ""} >
                                <a href="?action=${param.action}&keyword=${param.keyword}&page=${pageNumber}">${pageNumber}</a>
                            </li>
                        </c:forEach>
                        <li>
                            <a href="?action=${param.action}&keyword=${param.keyword}&page=${contractPaginator.pageSize}" aria-label="Next">
                                <span aria-hidden="true">Cuối</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->


        <div class="panel panel-info">
            <div class="panel-heading">
                Các trạng thái của hợp đồng:
            </div>
            <div class="panel-body">
                <ul>
                    <li>
                        <span class="label label-gray">Chờ thanh toán</span>
                        Khách hàng đăng ký online nhưng chưa thanh toán.
                    </li>
                    <li>
                        <span class="label label-primary">Chưa có thẻ</span>
                        Khách hàng đã thanh toán cho hợp đồng nhưng chưa được in thẻ để sử dụng.
                    </li>
                    <li>
                        <span class="label label-success">Sẵn sàng</span>
                        Khánh hàng đã hoàn thành hợp đồng và đã có thẻ để sẵn sàng sử dụng.
                    </li>
                    <li>
                        <span class="label label-warning">Yêu cầu huỷ</span>
                        Khách hàng gửi yêu cầu huỷ hợp đồng và đang chờ nhân viên xác nhận.
                    </li>
                    <li>
                        <span class="label label-danger">Hết hạn</span>
                        Hợp đồng đã hết hạn và không có giá trị. Khánh hàng phải gia hạn để tiếp tục sử dụng chương
                        trình bảo hiểm.
                    </li>
                    <li>
                        <span class="label label-dark">Đã huỷ</span>
                        Hợp đồng bị huỷ. Khánh hàng muốn tiếp tục sử dụng cần phải đăng ký hợp đồng mới.
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>