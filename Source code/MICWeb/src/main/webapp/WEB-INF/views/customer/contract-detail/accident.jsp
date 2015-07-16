
<%----------------------------------------------------------------------------------------------------%>
<%------------------------------------------Accident------------------------------------------------%>
<div role="tabpane1" class="tab-pane" id="accidents">

  <div class="row">
    <div class="col-lg-12">
      <div class="pull-right">
        <c:if test="${!contract.status.equalsIgnoreCase('Cancelled')}">
          <a href="${pageContext.request.contextPath}/customer/accident?action=create&code=${contract.contractCode}"
             class="btn btn-success">
            <i class="fa fa-plus"></i> Thông báo tai nạn mới
          </a>
        </c:if>

      </div>
    </div>
  </div>
  <br/>

  <div class="panel panel-default">
    <div class="panel panel-heading">
      <div class="pull-left">
        <!--<input type="checkbox" class="check-all"/>-->
        <b>Có ${accidentPaginator.itemSize}  tai nạn</b>
      </div>
      <div class="pull-right ">
        <input type="text" class="form-control long-text-box"
               placeholder="Tìm kiếm theo mã tai nạn"/>
        <input type="button" class="btn btn-default" value="Tìm kiếm"/>
      </div>
      <div class="clearfix"></div>
    </div>

    <div class="panel panel-body">

      <c:set var="accidents"
             value="${accidentPaginator.getItemsOnCurrentPage(param.page, param.active)}"/>
      <div class="table table-responsive">
        <table class="table table-bordered">
          <thead>
          <tr class="success">
            <th class="text-center col-md-1">
              Mã tai nạn
            </th>
            <th class="text-center">
              Nội dung
            </th>
            <th class=" text-center" style="width: 14% !important;">
              Thời gian
            </th>
            <th class="text-center col-md-1">
              Mô tả
            </th>
          </tr>
          </thead>
          <tbody>
          <c:choose>
            <c:when test="${accidents.size() == 0}">
              <tr>
                <td colspan="5" style="vertical-align: middle; text-align: center;">
                  Không có tai nạn nào
                </td>
              </tr>
            </c:when>
            <c:otherwise>
              <c:forEach items="${accidents}"
                         var="accident"
                         varStatus="counter">
                <tr>
                  <td class="text-center">
                      ${accident.id}
                  </td>
                  <td>
                      ${accident.title}
                  </td>
                  <td class="text-center">
                    <fmt:formatDate value='${accident.createdDate}'
                                    pattern='dd/MM/yyyy'/>
                  </td>
                  <td class="text-center">
                    <a href="${accident.attachment}"
                       target="_newtab"><i class="fa fa-file-text-o"></i></a>
                  </td>
                </tr>

              </c:forEach>
              <%--<tr id="addMore" class="hide">--%>
              <%--<td class="text-center">--%>

              <%--</td>--%>
              <%--<td class="text-center">--%>
              <%--<input type="text" class="form-control" id="accidentContent">--%>
              <%--</td>--%>
              <%--<td class="text-center">--%>
              <%--<input type="text" class="text-center handleInput form-control"--%>
              <%--disabled="disabled"--%>
              <%--id="accidentDate">--%>
              <%--</td>--%>
              <%--<td class="text-center">--%>
              <%--<input type="file" class="text-center" id="accidentAtt">--%>
              <%--</td>--%>
              <%--</tr>--%>
            </c:otherwise>
          </c:choose>

          </tbody>
        </table>
      </div>
    </div>

    <div class="panel panel-footer">
      <nav class="text-right">
        <ul class="pagination">
          <c:set var="currentPage" value="${param.page}"/>

          <c:if test="${param.active != accidentPaginator.tag}">
            <c:set var="currentPage" value="1"/>
          </c:if>

          <c:if test="${currentPage != 1 && not empty currentPage}">
            <li>
              <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=1&active=${accidentPaginator.tag}"
                 aria-label="Previous">
                <span aria-hidden="true">Đầu</span>
              </a>
            </li>
          </c:if>
          <c:forEach begin="1" end="${accidentPaginator.pageSize}" var="pageNumber">
            <li ${currentPage == pageNumber ||(pageNumber == 1 && empty currentPage) ? "class='active'": ""} >
              <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=${pageNumber}&active=${accidentPaginator.tag}">${pageNumber}</a>
            </li>
          </c:forEach>
          <c:if test="${currentPage != accidentPaginator.pageSize && accidentPaginator.pageSize != 1}">
            <li>
              <a href="?action=${param.action}&code=${contract.contractCode}&keyword=${param.keyword}&page=${accidentPaginator.pageSize}&active=${accidentPaginator.tag}"
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