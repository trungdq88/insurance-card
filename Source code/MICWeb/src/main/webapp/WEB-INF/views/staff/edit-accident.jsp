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

                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input id="contractCode" name="edit:contractCode"
                                           class="form-control input-md"
                                           value="${not empty submitted.contractCode ? submitted.contractCode : accident.contractCode}"
                                           readonly title="Ví dụ: HD2703"
                                           type="text" required pattern="^HD([0-9A-Z]{4,8})$">
                                    <span class="input-group-btn" data-toggle="tooltip" data-placement="top"
                                          id="btnTooltip" title="Chọn hợp đồng có sẵn trong hệ thống">
                                    <button id="contract-select-btn" type="button" class="btn btn-primary"
                                            data-toggle="modal" data-target="#select-contract-modal"
                                            onclick="loadContracts()">
                                        <i class="fa fa-search"></i> Chọn
                                    </button>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <!-- Created date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="createdDate">Ngày gởi thông báo *</label>

                            <div class="col-sm-3">
                                <input id="createdDate" name="edit:createdDate"
                                       value="<fmt:formatDate value="${not empty submitted.createdDate ? submitted.createdDate : accident.createdDate}" pattern="yyyy-MM-dd" />"
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
                                          title="Vui lòng nhập nội dung thông báo">${not empty submitted.title ? submitted.title : accident.title}</textarea>
                            </div>
                        </div>

                        <!-- Attachment -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="attachment">Văn bản đính kèm *</label>

                            <div class="col-sm-6">
                                <input id="attachment" name="edit:attachment" type="hidden" required maxlength="255"
                                       value="${accident.attachment}">
                                <img id="imgAttachment" height="100px" src="${accident.attachment}"/>
                                <script type="text/javascript" src="//api.filepicker.io/v2/filepicker.js"></script>
                                <input type="filepicker" data-fp-apikey="AEbPPQfPfRHqODjEl5AZ2z"
                                       onchange="$('#imgAttachment').attr('src', event.fpfile.url);$('#attachment').val(event.fpfile.url);">
                            </div>
                        </div>
                    </fieldset>
                    <br/>
                    <!-- Create new accident button -->
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

<!-- model for select contract -->
<div class="modal fade" id="select-contract-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Chọn hợp đồng đã có sẵn trong hệ thống</h4>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="select-contract-keyword"
                       placeholder="Tìm theo mã hợp đồng hoặc tên khách hàng"/>
                <br/>

                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Mã hợp đồng</th>
                            <th>Tên khách hàng</th>
                            <th>Chọn</th>
                        </tr>
                        </thead>
                        <tbody id="list-items">
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">
    $(document).ready(function () {
        if ($('#createdDate').val() == "") {
            $('#createdDate').val(getCurrentDate());
        }
        document.getElementById("createdDate").max = getCurrentDate();
    });

    function escapeHtml(unsafe) {
        return unsafe
                .replace(/&/g, "&amp;")
                .replace(/</g, "&lt;")
                .replace(/>/g, "&gt;")
                .replace(/"/g, "&quot;")
                .replace(/'/g, "&#039;");
    }

    function showContractInfo(info) {
        $('#contractCode').val(info.contractCode);
    }

    function loadContracts() {
        var keyword = $('#select-contract-keyword').val();

        var updateList = function (items) {
            var html = '';
            if (!items || items.length == 0) {
                html = '<tr><td class="text-center" colspan="4">Không có hợp đồng nào</td></tr>'
            } else {
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    html += '<tr>' +
                            '<td>' + (i + 1) + '</td>' +
                            '<td>' + item.contractCode + '</td>' +
                            '<td>' + item.micCustomerByCustomerCode.name + '</td>' +
                            '<td><button data-dismiss="modal" type="button" class="btn btn-primary btn-xs"' +
                            'onclick="showContractInfo(' + escapeHtml(JSON.stringify(item)) + ')">' +
                            '<i class="fa fa-check"></i> Chọn</button></td>' +
                            '</tr>';
                }
            }
            $('#list-items').html(html);
        };

        $('#list-items').html('<tr><td class="text-center" colspan="4">Đang tìm kiếm...</td></tr>');
        $.ajax({
            url: '/ajax',
            method: 'get',
            dataType: 'json',
            data: {
                action: 'loadContracts',
                keyword: keyword
            }
        }).done(function (contracts) {
            updateList(contracts);
        }).fail(function () {
            updateList([]);
        });
    }
</script>

<%@ include file="_shared/footer.jsp" %>