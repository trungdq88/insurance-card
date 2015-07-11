<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="accident" value="${requestScope.ACCIDENT}"/>

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Chỉnh sửa thông báo tai nạn ${accident.id}</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">

                <c:if test="${not empty validateErrors}">
                    <div class="text-danger">
                        <ul>
                            <c:forEach var="error" items="${validateErrors}">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <p class="text-right"><b>Các ô có dấu * là bắt buộc</b></p>
                <br/>

                <!-- Form to create new customer -->
                <form action="${pageContext.request.contextPath}/staff/accident" method="post" class="form-horizontal">
                    <fieldset>
                        <!-- Contract input -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="contractCode">Mã hợp đồng *</label>

                            <div class="col-sm-2">
                                <input id="contractCode" name="edit:contractCode" class="form-control input-md"
                                       type="text" required pattern="^HD([0-9A-Z]{4,8})$"
                                       title="Ví dụ: HD1024" value="${accident.contractCode}">
                            </div>

                            <div class="col-sm-2" data-toggle="modal" data-target="#select-customer-modal">
                                <a href="#" class="btn btn-primary btn-block" data-toggle="tooltip"
                                   data-placement="bottom"
                                   title="Chọn hợp đồng có sẵn trong hệ thống">
                                    <i class="fa fa-search"></i>
                                    Chọn
                                </a>
                            </div>
                        </div>

                        <!-- Created date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="createdDate">Ngày gởi thông báo *</label>

                            <div class="col-sm-3">
                                <input id="createdDate" name="edit:createdDate"
                                       value="<fmt:formatDate value="${accident.createdDate}" pattern="yyyy-MM-dd" />"
                                       class="form-control input-md" type="date" required>
                            </div>
                        </div>

                        <!-- Title -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="title">Nội dung thông báo *</label>

                            <div class="col-sm-7">
                                <textarea id="title" name="edit:title" class="form-control input-md"
                                          placeholder="Ví dụ: Gãy tay trái, chấn thương mô mềm"
                                          rows="4" maxlength="250"
                                          title="Vui lòng nhập nội dung thông báo">${accident.title}</textarea>
                            </div>
                        </div>

                        <!-- Attachment -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="attachment">Văn bản đính kèm *</label>

                            <div class="col-sm-6">
                                <input id="attachment" name="edit:attachment" type="hidden" required maxlength="255"
                                       value="${accident.attachment}">
                                <img id="imgAttachment" height="100px" src="${accident.attachment}" />
                                <script type="text/javascript" src="//api.filepicker.io/v2/filepicker.js"></script>
                                <input type="filepicker" data-fp-apikey="AEbPPQfPfRHqODjEl5AZ2z"
                                       onchange="$('#imgAttachment').attr('src', event.fpfile.url);$('#attachment').val(event.fpfile.url);">
                            </div>
                        </div>
                    </fieldset>
                    <br/>
                    <!-- Create new customer button -->
                    <div class="text-center">
                        <input type="hidden" name="edit:id" value="${accident.id}"/>
                        <input type="hidden" name="action" value="edit"/>
                        <button type="submit" id="btnEdit" class="btn btn-primary">
                            <i class="fa fa-pencil"></i> Cập nhật thông báo tai nạn
                        </button>
                        <br/><br/>
                        <a href="${pageContext.request.contextPath}/staff/contract?action=detail&code=${accident.contractCode}"
                           type="button" class="btn btn-default">
                            <i class="fa fa-arrow-left"></i> <strong>Xem chi tiết hợp đồng hiện tại</strong>
                        </a>
                    </div>
                </form>
            </div>
            <br/> <br/>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.page-wrapper -->
</div>
<!-- /#wrapper -->

<script type="text/javascript">
    $(document).ready(function () {
        if ($('#createdDate').val() == "") {
            $('#createdDate').val(getCurrentDate());
        }
        document.getElementById("createdDate").max = getCurrentDate();
    });
</script>

<%@ include file="_shared/footer.jsp" %>