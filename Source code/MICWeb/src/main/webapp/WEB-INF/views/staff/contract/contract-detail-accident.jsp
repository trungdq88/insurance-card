<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form class="form-horizontal">
    <fieldset>
        <legend>
            Lịch sử tai nạn
            <div class="pull-right" style="margin-top: -10px;">
                <a href="${pageContext.request.contextPath}/staff/accident?action=create&code=${contract.contractCode}"
                   type="button" class="btn btn-success">
                    <i class="fa fa-plus"></i> Thông báo tai nạn mới
                </a>
            </div>
        </legend>
    </fieldset>
    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>Mã</th>
                <th>Thời gian</th>
                <th>Nội dung</th>
                <th>Đính kèm</th>
                <th>Chỉnh sửa</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accident" items="${listAccident}">
                <tr>
                    <td>${accident.id}</td>
                    <td><fmt:formatDate value="${accident.createdDate}" pattern="dd/MM/yyyy"/></td>
                    <td>${accident.title}</td>
                    <td>
                        <a href="${accident.attachment}">
                            Xem chi tiết
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/staff/accident?action=edit&id=${accident.id}"
                           type="button" class="btn btn-xs btn-primary">
                            <i class="fa fa-pencil"></i> Chỉnh sửa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>