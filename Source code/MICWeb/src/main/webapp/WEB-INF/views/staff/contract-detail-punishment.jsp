<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form class="form-horizontal">
    <fieldset>
        <legend>
            Lịch sử vi phạm luật ATGT
            <div class="pull-right">
                <a href="${pageContext.request.contextPath}/staff/punishment?action=create"
                   class="btn btn-sm btn-success">
                    <i class="fa fa-plus"></i>
                    Thêm vi phạm
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
                <th>Nội dung</th>
                <th>Thời gian</th>
                <th>Biên bản</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="punishment" items="${listPunishment}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${punishment.id}</td>
                    <td>${punishment.title}</td>
                    <td>
                        <%--<fmt:formatDate value="${punishment.createdDate}" pattern="dd/MM/yyyy"/>--%>
                    </td>
                    <td>${punishment.attachment}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>