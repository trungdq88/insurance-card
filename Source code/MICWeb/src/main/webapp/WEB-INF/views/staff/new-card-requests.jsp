<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Yêu cầu cấp thẻ mới</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="pull-left center-dropdown-button">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có ${unresolvedRequestCount} yêu cầu cấp thẻ mới chưa xử lý</b>
                        </div>
                        <div class="pull-right no-wrap">
                            <input type="text" class="form-control long-text-box"
                                   placeholder="Tìm kiếm theo mã thẻ, tên chủ thẻ"/>
                            <input type="button" class="btn btn-default" value="Tìm kiếm"/>

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
                                    <th>Thời gian</th>
                                    <th>Ghi chú</th>
                                    <th>Mã thẻ cũ</th>
                                    <th>Hợp đồng</th>
                                    <th>Ngày cấp mới</th>
                                    <th>Thẻ mới cấp</th>
                                </tr>
                                </thead>
                                <c:set var="requests" value="${requestPaginator.getItemsOnCurrentPage(param.page)}"/>
                                <tbody>
                                <c:choose>
                                    <c:when test="${requests.size() == 0}">
                                        <tr>
                                            <td colspan="6" style="vertical-align: middle; text-align: center;">
                                                Không có yêu cầu thẻ mới nào
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="newRequest" items="${requests}" varStatus="counter">
                                            <tr>
                                                <td>${(requestPaginator.getCurrentPage(param.page) - 1) * requestPaginator.itemPerPage + counter.count}</td>
                                                <td>
                                                    <fmt:formatDate value="${newRequest.requestDate}" pattern="dd/MM/yyyy"/>
                                                </td>
                                                <td><a tabindex="0" data-trigger="focus" data-toggle="popover" title="Ghi chú từ khách hàng"
                                                       role="button" data-content="${newRequest.note}"><i class="fa fa-file"></i></a></td>
                                                <td>
                                                    <a href="#">
                                                            ${newRequest.oldCardInstanceId}
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/staff/contract?action=detail&code=${newRequest.micCardInstanceByOldCardInstanceId.contractCode}">
                                                            ${newRequest.micCardInstanceByOldCardInstanceId.contractCode}
                                                    </a>

                                                </td>
                                                <td>
                                                    <c:if test="${empty newRequest.resolveDate}">
                                                        <span class="label label-danger">Chưa cấp</span>
                                                     </c:if>
                                                    <fmt:formatDate value="${newRequest.resolveDate}" pattern="dd/MM/yyyy"/>

                                                </td>
                                                <td>
                                                    <a href="#">
                                                            ${map[newRequest.id]}
                                                    </a>

                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

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
                                <c:forEach begin="1" end="${requestPaginator.pageSize}" var="pageNumber">
                                    <li ${param.page == pageNumber ||(pageNumber == 1 && empty param.page) ? "class='active'": ""} >
                                        <a href="?action=${param.action}&keyword=${param.keyword}&page=${pageNumber}">${pageNumber}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${param.page != requestPaginator.pageSize && requestPaginator.pageSize != 1}">
                                    <li>
                                        <a href="?action=${param.action}&keyword=${param.keyword}&page=${requestPaginator.pageSize}"
                                           aria-label="Next">
                                            <span aria-hidden="true">Cuối</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->


                <div class="panel panel-success">
                    <div class="panel-heading">
                        Quy trình cấp lại thẻ mới
                    </div>
                    <div class="panel-body">
                        <p>Để cấp thẻ mới cho khách hàng trong trường hợp thẻ bị mất, cần thực hiện các bước sau đây:</p>
                        <ol>
                            <li>Sử dụng <b>Ứng dụng in thẻ</b> trên điện thoại, dùng chức năng <b>Cấp lại thẻ</b> để tìm và in thẻ mới cho khách hàng.</li>
                            <li>Chuyển phát thẻ mới cho khách hàng, thẻ cũ sẽ tự động bị <b>vô hiệu hoá</b> và sẽ <b>không thể sử dụng lại được nữa</b>.</li>
                            <li>Thông tin về thẻ mới sẽ được <b>tự động cập nhật</b> ở trang này sau khi thẻ mới được cấp.</li>
                        </ol>

                        <p>Trong trường hợp cần thiết, các thông tin lưu trên thẻ cũ vẫn có thể được tra cứu lại tại trang <a href="cards.html">Quản lý thẻ</a></p>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp"%>