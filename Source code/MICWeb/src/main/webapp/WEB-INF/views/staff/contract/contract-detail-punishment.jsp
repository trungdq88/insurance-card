<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form class="form-horizontal">
    <fieldset>
        <legend>
            Lịch sử vi phạm luật ATGT
            <div class="pull-right" style="margin-top: -10px;">
                <a href="${pageContext.request.contextPath}/staff/punishment?action=create&code=${contract.contractCode}"
                   type="button" class="btn btn-success editBtn">
                    <i class="fa fa-plus"></i> Thêm vi phạm luật ATGT
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
            <c:forEach var="punishment" items="${listPunishment}">
                <tr>
                    <td>${punishment.id}</td>
                    <td>
                        <fmt:formatDate value="${punishment.createdDate}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>${punishment.title}</td>
                    <td>
                        <a href="${punishment.attachment}">
                            Xem chi tiết
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/staff/punishment?action=edit&id=${punishment.id}"
                           type="button" class="btn btn-xs btn-primary editBtn">
                            <i class="fa fa-pencil"></i> Chỉnh sửa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>