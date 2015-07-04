<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form class="form-horizontal">
    <fieldset>
        <legend>
            Lịch sử tai nạn
            <div class="pull-right">
                <a href="${pageContext.request.contextPath}/staff/accident?action=create"
                   class="btn btn-sm btn-success">
                    <i class="fa fa-plus"></i>
                    Thêm tai nạn
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
            <c:forEach var="accident" items="${listAccident}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${accident.id}</td>
                    <td>${accident.title}</td>
                    <td>
                        <fmt:formatDate value="${accident.createdDate}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>${accident.attachment}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>