<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Khách hàng

                    <div class="pull-right">
                        <a href="${pageContext.request.contextPath}/staff/customer?action=create"
                           class="btn btn-success">
                            <i class="fa fa-plus"></i>
                            Khách hàng mới
                        </a>
                    </div>
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <div class="panel panel-default">

            <c:set var="info" value="${customerPaginator.getItemsOnCurrentPage(param.page)}"/>

            <div class="panel-heading">
                <div class="pull-left center-dropdown-button">
                    <b>Có ${customerPaginator.itemSize} khách hàng (${customerPaginator.pageSize} trang)</b>
                </div>
                <div class="pull-right no-wrap">
                    <form action="${pageContext.request.contextPath}/staff/customer" method="get">
                        <input type="text" class="form-control long-text-box" name="keyword"
                               placeholder="Tìm kiếm theo tên, mã hợp đồng, mã thẻ" value="${param.keyword}"/>
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
                            <th>Mã KH</th>
                            <th>Tên</th>
                            <th>Số điện thoại</th>
                            <th>Mã hợp đồng</th>
                            <th>Mã thẻ</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="customer" items="${info}" varStatus="counter">
                            <tr>
                                <td>${(customerPaginator.getCurrentPage(param.page) - 1) * customerPaginator.itemPerPage + counter.count}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/staff/customer?action=detail&code=${customer.customerCode}">
                                            ${customer.customerCode}
                                    </a>
                                </td>
                                <td>${customer.name}</td>
                                <td>${customer.phone}</td>
                                <td><a href="#"></a>Add later</td>
                                <td><a href="#"></a>Add later</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
                <nav class="text-right">
                    <ul class="pagination">
                        <c:if test="${param.page != 1 && not empty param.page}">
                            <li>
                                <a href="?action=${param.action}&keyword=${param.keyword}&page=1" aria-label="Previous">
                                    <span aria-hidden="true">Đầu</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${customerPaginator.pageSize}" var="pageNumber">
                            <li ${param.page == pageNumber ||(pageNumber == 1 && empty param.page) ? "class='active'": ""} >
                                <a href="?action=${param.action}&keyword=${param.keyword}&page=${pageNumber}">${pageNumber}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${param.page != customerPaginator.pageSize && customerPaginator.pageSize != 1}">
                            <li>
                                <a href="?action=${param.action}&keyword=${param.keyword}&page=${customerPaginator.pageSize}"
                                   aria-label="Next">
                                    <span aria-hidden="true">Cuối</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
                <%--/.pagination--%>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>