<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form class="form-horizontal">
    <fieldset>
        <legend>
            Lịch sử bồi thường
            <div class="pull-right" style="margin-top: -10px;">
                <a href="${pageContext.request.contextPath}/staff/compensation?action=create&code=${contract.contractCode}"
                   class="btn btn-success">
                    <i class="fa fa-plus"></i> Gởi yêu cầu bồi thường
                </a>
            </div>
        </legend>
    </fieldset>
    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Mã</th>
                <th>Ngày yêu cầu</th>
                <th>Quyết định</th>
                <th>Trạng thái</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="compensation" items="${listCompensation}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/staff/compensation?action=detail&code=${compensation.compensationCode}">
                                ${compensation.compensationCode}
                        </a>
                    </td>
                    <td>
                        <fmt:formatDate value="${compensation.createdDate}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>${compensation.decision}</td>
                    <td>
                        <c:set var="resolve" value="${compensation.resolveDate}"/>
                        <c:choose>
                            <c:when test="${not empty resolve}">
                                <span class="label label-success">Đã giải quyết</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-default">Chưa giải quyết</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>