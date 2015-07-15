<%--
  Created by IntelliJ IDEA.
  User: TriPQM
  Date: 07/15/2015
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <h1 class="page-header">Quản lý loại hợp đồng</h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <div class="text-info">
      ${param.info}
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
        <form action="${pageContext.request.contextPath}/admin/contractType"
              method="post" class="form-horizontal">
          <fieldset>
            <legend>Thêm loại hợp đồng mới</legend>

            <label class="col-sm-4 control-label">Tên loại hợp đồng</label>

            <div class="col-sm-7">
              <input name="contractType:name" type="text" class="form-control input-md" required
                      value="${submitted.name}">

            </div>
            <label class="col-sm-4 control-label">Miêu tả</label>

            <div class="col-sm-7">
              <textarea name="contractType:description" cols="2" class="form-control input-md"
                     required>${submitted.description}</textarea>

            </div>
            <label class="col-sm-4 control-label">Phí hằng năm(VNĐ)</label>

            <div class="col-sm-7">
              <input name="contractType:pricePerYear" type="number" class="form-control input-md"
                     required min="0" max="1000000000"
                     value="${submitted.pricePerYear}">

            </div>
          </fieldset>
          <br>

          <div class="text-center">
            <input type="hidden" name="action" value="addContractType">
            <button type="submit" class="btn btn-success">
              Thêm
            </button>

          </div>
          <fieldset>
            <legend>Lịch sử</legend>
            <div class="table-responsive">
              <table class="table table-hover table-striped">
                <thead>
                <tr>
                  <th>#</th>
                  <th>Tên</th>
                  <th>Miêu tả</th>
                  <th>Phí hằng năm (VNĐ)</th>
                  <%--<th></th>--%>
                </tr>
                </thead>
                <tbody>
                <c:set var="contractTypes"
                       value="${contractTypePaginator.getItemsOnCurrentPage(param.page)}"/>
                <c:choose>
                  <c:when test="${contractTypes.size() == 0}">
                    <tr>
                      <td colspan="6" style="vertical-align: middle; text-align: center;">
                        Không có loại hợp đồng nào
                      </td>
                    </tr>
                  </c:when>
                  <c:otherwise>
                    <c:forEach var="row" items="${contractTypes}" varStatus="counter">
                      <tr>
                        <td>${(contractTypePaginator.getCurrentPage(param.page) - 1) * contractTypePaginator.itemPerPage + counter.count}</td>
                        <td>
                          ${row.name}
                        </td>
                        <td>
                              <a tabindex="0" data-trigger="focus" data-toggle="popover" title="Miêu tả"
                                 role="button" data-content="${row.description}"><i class="fa fa-file"></i></a>
                        </td>
                        <td>
                          <fmt:formatNumber
                                  value="${row.pricePerYear}"
                                  type="currency"
                                  maxFractionDigits="0"/>
                        </td>
                        <%--<td>--%>
                          <%--<a href="${pageContext.request.contextPath}/admin/contractType?action=deleteContractType&contractTypeId=${row.id}&page=${param.page}"--%>
                             <%--class="btn btn-danger">--%>
                            <%--<i class="fa fa-trash"></i>--%>
                            <%--Xóa--%>
                          <%--</a>--%>
                        <%--</td>--%>
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
                    <a href="?action=view&keyword=${param.keyword}&page=1"
                       aria-label="Previous">
                      <span aria-hidden="true">Đầu</span>
                    </a>
                  </li>
                </c:if>
                <c:forEach begin="1" end="${contractTypePaginator.pageSize}" var="pageNumber">
                  <li ${param.page == pageNumber ||(pageNumber == 1 && empty param.page) ? "class='active'": ""} >
                    <a href="?action=view&keyword=${param.keyword}&page=${pageNumber}">${pageNumber}</a>
                  </li>
                </c:forEach>
                <c:if test="${param.page != contractTypePaginator.pageSize && contractTypePaginator.pageSize != 1}">
                  <li>
                    <a href="?action=view&keyword=${param.keyword}&page=${contractTypePaginator.pageSize}"
                       aria-label="Next">
                      <span aria-hidden="true">Cuối</span>
                    </a>
                  </li>
                </c:if>
              </ul>
            </nav>
          </fieldset>
        </form>


      </div>
    </div>
  </div>
  <!-- /#wrapper -->


  <%@ include file="_shared/footer.jsp" %>
