<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

  <c:set var="card" value="${requestScope.CARD}"/>

  <%@ include file="_shared/navigation.jsp" %>
  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Thẻ ${card.cardId}</h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <div class="row">
      <div class="col-lg-12">
        <form class="form-horizontal">
          <fieldset>
            <legend>Thông tin chi tiết thẻ</legend>

            <div class="form-group">
              <label class="col-sm-4 control-label">Mã thẻ</label>

              <div class="col-sm-3">
                <div class="text-value">
                  <strong>${card.cardId}</strong>
                </div>
              </div>

              <label class="col-sm-2 control-label">Trạng thái</label>

              <div class="col-sm-3">
                <div class="text-value">
                  <c:choose>
                    <c:when test="${empty card.deactivatedDate}">
                      <span class="label label-success">Hoạt động</span>
                    </c:when>
                    <c:otherwise>
                      <span class="label label-danger">Ngưng hoạt động</span>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
            </div>

            <%--<div class="form-group">--%>
              <%--<label class="col-sm-4 control-label">Khách hàng sở hữu</label>--%>

              <%--<div class="col-sm-6">--%>
                <%--<div class="text-value">--%>
                  <%--<a href="${pageContext.request.contextPath}/staff/customer?action=detail&code=${card.micContractByContractCode.micCustomerByCustomerCode.customerCode}">--%>
                    <%--<strong>${card.micContractByContractCode.micCustomerByCustomerCode.name}</strong>--%>
                  <%--</a>--%>
                <%--</div>--%>
              <%--</div>--%>
            <%--</div>--%>

            <div class="form-group">
              <label class="col-sm-4 control-label">Mã hợp đồng</label>

              <div class="col-sm-2">
                <div class="text-value">
                  <a href="${pageContext.request.contextPath}/customer/contract?action=detail&code=${card.contractCode}">
                    <strong>${card.contractCode}</strong>
                  </a>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-4 control-label">Bắt đầu có hiệu lực từ</label>

              <div class="col-sm-6">
                <div class="text-value">
                  <fmt:formatDate value="${card.activatedDate}" pattern="dd/MM/yyyy"/> lúc
                  <fmt:formatDate value="${card.activatedDate}" type="time"/>
                </div>
              </div>
            </div>

            <c:if test="${not empty card.deactivatedDate}">
              <div class="form-group">
                <label class="col-sm-4 control-label">Thời điểm hết hiệu lực</label>

                <div class="col-sm-6">
                  <div class="text-value">
                    <fmt:formatDate value="${card.deactivatedDate}" pattern="dd/MM/yyyy"/> lúc
                    <fmt:formatDate value="${card.deactivatedDate}" type="time"/>
                  </div>
                </div>
              </div>

            </c:if>
          </fieldset>
          <br/>
          <br/>
          <fieldset>
            <legend id="card-access-history">Lịch sử truy cập</legend>

            <c:set var="logs" value="${calPaginator.getItemsOnCurrentPage(param.page)}"/>

            <div class="panel-heading">
              <div class="pull-left center-dropdown-button">
                <b>Có tổng số ${calPaginator.itemSize} lượt truy cập</b>
              </div>
              <div class="pull-right no-wrap">
                <form action="${pageContext.request.contextPath}/customer/card" method="get">
                  Lọc kết quả từ <input type="date" class="form-control short-input"/>
                  đến <input type="date" class="form-control short-input"/>
                  <input type="submit" class="btn btn-default" value="Tìm kiếm"/>
                  <input type="hidden" name="action" value="search"/>
                </form>
              </div>
              <div class="clearfix"></div>
            </div>
            <div class="panel-body">
              <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>#</th>
                    <th>Thời gian</th>
                    <th>Thiết bị</th>
                    <th>Dịch vụ</th>
                    <th>Kết quả trả về</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:choose>
                    <c:when test="${cards.size() eq 0}">
                      <tr>
                        <td colspan="6" style="vertical-align: middle; text-align: center;">
                          Không có lượt truy cập nào
                        </td>
                      </tr>
                    </c:when>
                    <c:otherwise>
                      <c:forEach var="log" items="${logs}" varStatus="counter">
                        <tr>
                          <td>${counter.count}</td>
                          <td>
                            <fmt:formatDate value="${log.accessDate}" pattern="dd/MM/yyyy"/> lúc
                            <fmt:formatDate value="${log.accessDate}" type="time"/>
                          </td>
                          <td>${log.device}</td>
                          <td>${log.requestService}</td>
                          <td>
                            <c:set var="result" value="${log.responseContent}"/>
                            <c:choose>
                              <c:when test="${result.toLowerCase().contains('valid')}">
                                <span class="label label-success">Hợp lệ</span>
                              </c:when>
                              <c:otherwise>
                                <span class="label label-danger">Không hợp lệ</span>
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
              <!-- /.table-responsive -->
            </div>
            <nav class="text-right">
              <ul class="pagination">
                <c:if test="${param.page != 1 && not empty param.page}">
                  <li>
                    <a href="?action=${param.action}&keyword=${param.keyword}&page=1" aria-label="Previous">
                      <span aria-hidden="true">Đầu</span>
                    </a>
                  </li>
                </c:if>
                <c:forEach begin="1" end="${calPaginator.pageSize}" var="pageNumber">
                  <li ${param.page == pageNumber ||(pageNumber == 1 && empty param.page) ? "class='active'": ""} >
                    <a href="?action=${param.action}&keyword=${param.keyword}&page=${pageNumber}">${pageNumber}</a>
                  </li>
                </c:forEach>
                <c:if test="${param.page != calPaginator.pageSize && calPaginator.pageSize != 1}">
                  <li>
                    <a href="?action=${param.action}&keyword=${param.keyword}&page=${calPaginator.pageSize}"
                       aria-label="Next">
                      <span aria-hidden="true">Cuối</span>
                    </a>
                  </li>
                </c:if>
              </ul>
            </nav>
          </fieldset>
        </form>
        <br/>
        <div class="text-center">
          <a href="${pageContext.request.contextPath}/customer/card" type="button" class="btn btn-default">
            <i class="fa fa-arrow-left"></i> <strong>Danh sách thẻ đã phát hành</strong>
          </a>
        </div>
        <br/> <br/>
      </div>
      <!-- col -->
    </div>
  </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>
