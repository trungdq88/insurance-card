<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form class="form-horizontal">
    <fieldset>
        <legend>
            Lịch sử bồi thường
            <div class="pull-right">
                <a href="${pageContext.request.contextPath}/staff/compensation?action=create"
                   class="btn btn-sm btn-success">
                    <i class="fa fa-plus"></i>
                    Thêm bồi thường
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
                <th>Thời gian</th>
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
                        <fmt:formatDate value="${payment.createdDate}" pattern="dd/MM/yyyy"/>
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