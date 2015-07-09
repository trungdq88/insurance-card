<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="compensation" value="${requestScope.COMPENSATION}"/>

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Chỉnh sửa yêu cầu bồi thường ${compensation.compensationCode}</h1>
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

                <div class="alert alert-info">
                    <div class="text text-field" style="font-size: medium">
                        <strong>Lưu ý quan trọng</strong>:
                        Người kê khai phải kê khai đầy đủ và trung thực các nội dung dưới đây.
                        Doanh nghiệp bảo hiểm có thể từ chối một phần số tiền bồi thường nếu nhận được nội dung kê
                        khai thiếu trung thực
                    </div>
                </div>
                <br/>
                <!-- Form to edit compensation -->
                <form id="editForm" action="${pageContext.request.contextPath}/staff/compensation" method="post"
                      class="form-horizontal">
                    <fieldset>
                        <!-- Contract input & Created date-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="contractCode">Mã hợp đồng *</label>

                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input id="contractCode" name="edit:contractCode"
                                           class="form-control input-md" value="${compensation.contractCode}"
                                           type="text" required pattern="^HD([0-9A-Z]{4,8})$">
                                    <span class="input-group-btn" data-toggle="tooltip" data-placement="top"
                                          id="btnTooltip" title="Chọn hợp đồng có sẵn trong hệ thống">
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#select-customer-modal">
                                        <i class="fa fa-search"></i> Chọn
                                    </button>
                                    </span>
                                </div>
                            </div>

                            <label class="col-sm-3 control-label" for="createdDate">Ngày gởi yêu cầu *</label>

                            <div class="col-sm-3">
                                <input id="createdDate" name="edit:createdDate"
                                       value="<fmt:formatDate value="${compensation.createdDate}" pattern="yyyy-MM-dd" />"
                                       class="form-control input-md" type="date" required>
                            </div>
                        </div>

                        <!-- Driver name & Phone number -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="driverName">Họ tên lái xe *</label>

                            <div class="col-sm-4">
                                <input id="driverName" name="edit:driverName" class="form-control input-md"
                                       type="text" required minlength="3" maxlength="80"
                                       pattern="^([^0-9`~!@#$%^&*,.<>;':/|{}()=_+-]+)$"
                                       value="${compensation.driverName}" title="Vui lòng nhập họ tên"
                                       placeholder="Ví dụ: Nguyễn Văn A">
                            </div>

                            <label class="col-sm-2 control-label" for="driverPhone">Điện thoại *</label>

                            <div class="col-sm-3">
                                <input id="driverPhone" name="edit:driverPhone" class="form-control input-md"
                                       type="tel" required minlength="8" maxlength="15"
                                       pattern="[0-9]+" title="Vui lòng chỉ nhập số"
                                       value="${compensation.driverPhone}" placeholder="Ví dụ: 0933270393">
                            </div>
                        </div>

                        <!-- Address input -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="driverAddress">Địa chỉ *</label>

                            <div class="col-sm-8">
                                <input id="driverAddress" name="edit:driverAddress"
                                       class="form-control input-md"
                                       type="text" required minlength="3" maxlength="250"
                                       value="${compensation.driverAddress}" onfocus="geolocate()"
                                       title="Vui lòng nhập địa chỉ"
                                       placeholder="Ví dụ: 123A, Điện Biên Phủ, Quận 1, TP.HCM">
                            </div>
                        </div>

                        <!-- Driver license -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="licenseNumber">Giấy phép lái xe số *</label>

                            <div class="col-sm-3">
                                <input id="licenseNumber" name="edit:licenseNumber" type="text"
                                       class="form-control input-md"
                                       required minlength="10" maxlength="15" placeholder="Ví dụ: 740118000357"
                                       value="${compensation.licenseNumber}" title="Vui lòng nhập số GPLX">
                            </div>

                            <label class="col-sm-3 control-label" for="licenseType">Hạng *</label>

                            <div class="col-sm-2">
                                <input id="licenseType" name="edit:licenseType"
                                       class="form-control input-md" type="text"
                                       required minlength="1" maxlength="15" placeholder="Ví dụ: A1"
                                       value="${compensation.licenseType}" title="Vui lòng nhập hạng GPLX">
                            </div>
                        </div>

                        <!-- Plate & Capacity -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="plate">Biển số xe gây tai nạn *</label>

                            <div class="col-sm-3">
                                <input id="plate" name="edit:plate" class="form-control input-md"
                                       type="text" required minlength="4" maxlength="15"
                                       title="Vui lòng nhập biển số xe!" placeholder="Ví dụ: 78Y9-15383"
                                       value="${compensation.plate}">
                            </div>
                            <label class="col-sm-3 control-label" for="vehicleCapacity">
                                Trọng tải/số chỗ ngồi *
                            </label>

                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input id="vehicleCapacity" name="edit:vehicleCapacity"
                                           class="form-control input-md"
                                           type="text" required minlength="2" maxlength="20"
                                           title="Vui lòng nhập dung tích xe!" placeholder="Ví dụ: 7"
                                           value="${compensation.vehicleCapacity}">
                                    <span class="input-group-addon" id="basic-addon">(tấn/chỗ)</span>
                                </div>
                            </div>
                        </div>

                        <!-- Accident date -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="accidentDate">Ngày xảy ra tai nạn *</label>

                            <div class="col-sm-3">
                                <input id="accidentDate" name="edit:accidentDate"
                                       value="<fmt:formatDate value="${compensation.accidentDate}" pattern="yyyy-MM-dd" />"
                                       class="form-control input-md" type="date" required>
                            </div>
                        </div>

                        <!-- Accident place -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="accidentPlace">Nơi xảy ra tai nạn *</label>

                            <div class="col-sm-8">
                                <input id="accidentPlace" name="edit:accidentPlace"
                                       class="form-control input-md"
                                       type="text" required minlength="3" maxlength="250"
                                       value="${compensation.accidentPlace}" onfocus="geolocate()"
                                       title="Vui lòng nhập nơi xảy ra tai nạn"
                                       placeholder="Ví dụ: 405, Hoàng Văn Thụ, Quận Tân Bình, TP.HCM">
                            </div>
                        </div>

                        <!-- Control department -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="controlDepartment">
                                CQCA giải quyết *
                            </label>

                            <div class="col-sm-4">
                                <input id="controlDepartment" name="edit:controlDepartment"
                                       class="form-control input-md"
                                       type="text" required minlength="3" maxlength="250"
                                       value="${compensation.controlDepartment}"
                                       title="Vui lòng nhập cơ quan công an giải quyết tai nạn"
                                       placeholder="Ví dụ: Đội CSGT Bình Triệu">
                            </div>
                        </div>

                        <!-- Description -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="description">Diễn biến tai nạn *</label>

                            <div class="col-sm-8">
                                <textarea id="description" name="edit:description" rows="2"
                                          required maxlength="2000"
                                          title="Vui lòng nhập diễn biến và nguyên nhân tai nạn"
                                          class="form-control input-md">${compensation.description}</textarea>
                            </div>
                        </div>

                        <!-- Human damage -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="humanDamage">Thiệt hại về người *</label>

                            <div class="col-sm-8">
                                <textarea id="humanDamage" name="edit:humanDamage" rows="2"
                                          required maxlength="2000" title="Vui lòng nhập tình hình thiệt hại về người"
                                          class="form-control input-md">${compensation.humanDamage}</textarea>
                            </div>
                        </div>

                        <!-- Asset damage -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="assetDamage">Thiệt hại về tài sản *</label>

                            <div class="col-sm-8">
                                <textarea id="assetDamage" name="edit:assetDamage" rows="2"
                                          required maxlength="2000" title="Vui lòng nhập tình hình thiệt hại về tài sản"
                                          class="form-control input-md">${compensation.assetDamage}</textarea>
                            </div>
                        </div>

                        <!-- Observer -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="observer">Người làm chứng *</label>

                            <div class="col-sm-4">
                                <input id="observer" name="edit:observer" class="form-control input-md"
                                       type="text" required minlength="3" maxlength="80"
                                       pattern="^([^0-9`~!@#$%^&*,.<>;':/|{}()=_+-]+)$"
                                       value="${compensation.observer}" title="Vui lòng nhập tên người làm chứng"
                                       placeholder="Ví dụ: Đặng Thu Thảo">
                            </div>
                        </div>

                        <!-- Observer address -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="observerAddress">
                                Địa chỉ liên hệ *
                            </label>

                            <div class="col-sm-8">
                                <input id="observerAddress" name="edit:observerAddress"
                                       class="form-control input-md"
                                       type="text" required minlength="3" maxlength="250"
                                       value="${compensation.observerAddress}" onfocus="geolocate()"
                                       title="Vui lòng nhập địa chỉ của người làm chứng"
                                       placeholder="Ví dụ: 73B, Nguyễn Trãi, Quận 5, TP.HCM">
                            </div>
                        </div>

                        <!-- Compensation note -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="compensationNote">
                                Yêu cầu bồi thường
                            </label>

                            <div class="col-sm-8">
                                <textarea id="compensationNote" name="edit:compensationNote" maxlength="2000"
                                          title="Vui lòng nhập yêu cầu bồi thường"
                                          class="form-control input-md"
                                          rows="2">${compensation.compensationNote}</textarea>
                            </div>
                        </div>

                        <!-- Attachment -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="attachment">
                                Biên bản của CQCA
                            </label>

                            <div class="col-sm-6">
                                <input id="attachment" name="edit:attachment" type="hidden" maxlength="255">
                                <img id="imgAttachment" height="100px" src="${compensation.attachment}"/>
                                <script type="text/javascript" src="//api.filepicker.io/v2/filepicker.js"></script>
                                <input type="filepicker" data-fp-apikey="AEbPPQfPfRHqODjEl5AZ2z"
                                       onchange="$('#imgAttachment').attr('src', event.fpfile.url);$('#attachment').val(event.fpfile.url);">
                            </div>
                        </div>
                    </fieldset>
                    <br/>
                    <%--/Compensation information--%>
                    <c:if test="${not empty compensation.resolveDate}">
                        <fieldset>
                            <legend>Thông tin giải quyết yêu cầu bồi thường</legend>

                            <!-- Resolve date -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="resolveDate">Ngày giải quyết yêu cầu</label>

                                <div class="col-sm-3">
                                    <input id="resolveDate" name="edit:resolveDate" type="date"
                                           value="<fmt:formatDate value="${compensation.resolveDate}" pattern="yyyy-MM-dd" />"
                                           class="form-control input-md">
                                </div>
                            </div>

                            <!-- Decision -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="decision">Quyết định của công ty</label>

                                <div class="col-sm-4">
                                    <select class="form-control" id="decision" name="edit:decision">
                                        <option value="Chưa quyết định">Chưa quyết định</option>
                                        <option value="Đồng ý bồi thường">Đồng ý bồi thường</option>
                                        <option value="Từ chối bồi thường">Từ chối bồi thường</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Resolve note -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="resolveNote">Ghi chú của công ty</label>

                                <div class="col-sm-8">
                                <textarea id="resolveNote" name="edit:resolveNote" rows="3" maxlength="2000"
                                          title="Vui lòng nhập ghi chú của công ty"
                                          class="form-control input-lg">${compensation.resolveNote}</textarea>
                                </div>
                            </div>
                        </fieldset>
                    </c:if>
                    <%--/Resolve information --%>
                    <!-- Update compensation information button -->
                    <div class="text-center">
                        <input type="hidden" name="edit:compensationCode" value="${compensation.compensationCode}"/>
                        <input type="hidden" name="action" value="edit"/>
                        <button type="submit" id="btnEdit" class="btn btn-primary hide">
                            <i class="fa fa-pencil"></i> Cập nhật yêu cầu bồi thường
                        </button>
                        <br/> <br/>
                        <a href="${pageContext.request.contextPath}/staff/compensation?action=detail&code=${compensation.compensationCode}"
                           type="button" class="btn btn-default">
                            <i class="fa fa-arrow-left"></i> <strong>Xem chi tiết yêu cầu này</strong>
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

<script src="${pageContext.request.contextPath}/js/geolocation.js" type="text/javascript"></script>
<!-- Google API Autocomplete for address-->
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<script type="text/javascript">
    initialize1();
    initialize2();
    initialize3();
    $(document).ready(function () {
        document.getElementById("createdDate").max = getCurrentDate();
        document.getElementById("accidentDate").max = getCurrentDate();
        $('#createdDate').change(function () {
            document.getElementById("accidentDate").max = $('#createdDate').val();
        });
        var decision = '${compensation.decision}';
        if (decision != null) {
            $('#decision').val(decision)
        }
        $('input').change(function () {
            $('#btnEdit').removeClass('hide');
        });
        $('select').change(function () {
            $('#btnEdit').removeClass('hide');
        });
        $('textarea').change(function () {
            $('#btnEdit').removeClass('hide');
        });
    });
</script>

<%@ include file="_shared/footer.jsp" %>