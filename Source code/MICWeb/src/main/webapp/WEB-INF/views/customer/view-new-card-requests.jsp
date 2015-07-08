<%--
  Created by IntelliJ IDEA.
  User: TriPQM
  Date: 07/04/2015
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
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
                              ${newRequest.micCardInstanceByOldCardInstanceId.cardId}
                          </a>
                        </td>
                        <td>
                          <a href="${pageContext.request.contextPath}/customer/contract?action=detail&code=${mapCardContract[newRequest.micCardInstanceByOldCardInstanceId.cardId]}">
                              ${mapCardContract[newRequest.micCardInstanceByOldCardInstanceId.cardId]}
                          </a>
                        </td>
                        <td>
                          <c:if test="${empty newRequest.resolveDate}">
                            <span class="label label-danger">Chưa cấp</span>
                          </c:if>
                          <fmt:formatDate value="${newRequest.resolveDate}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td>
                          <a href="${pageContext.request.contextPath}/customer/card?action=detail&cardId=${map[newRequest.id]}">
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


      </div>
    </div>
  </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp"%>