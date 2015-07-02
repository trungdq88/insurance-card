<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fieldset>
    <legend>Thông tin về dịch vụ bảo hiểm
        <div class="pull-right" style="margin-top: -5px;">
            <button type="button" class="btn btn-xs btn-primary"
                    data-toggle="modal" data-target="#edit-contract-modal">
                <i class="fa fa-pencil"></i> Chỉnh sửa
            </button>
        </div>
    </legend>

    <!-- Contract code & Contract status -->
    <div class="form-group">
        <label class="col-sm-4 control-label">Mã hợp đồng</label>

        <div class="col-sm-2">
            <div class="text-value text-primary">
                <b>${contract.contractCode}</b>
            </div>
        </div>

        <label class="col-sm-2 control-label">Trạng thái</label>

        <div class="col-sm-3">
            <div class="text-value">
                <c:set var="status" value="${contract.status}"/>
                <c:choose>
                    <c:when test="${status.equalsIgnoreCase('Pending')}">
                        <span class="label label-gray">Chưa kích hoạt</span>
                    </c:when>
                    <c:when test="${status.equalsIgnoreCase('No card')}">
                        <span class="label label-primary">Chưa có thẻ</span>
                    </c:when>
                    <c:when test="${status.equalsIgnoreCase('Ready')}">
                        <span class="label label-success">Sẵn sàng</span>
                    </c:when>
                    <c:when test="${status.equalsIgnoreCase('Request cancel')}">
                        <span class="label label-warning">Yêu cầu hủy</span>
                    </c:when>
                    <c:when test="${status.equalsIgnoreCase('Expired')}">
                        <span class="label label-danger">Hết hạn</span>
                    </c:when>
                    <c:when test="${status.equalsIgnoreCase('Cancelled')}">
                        <span class="label label-dark">Đã huỷ</span>
                    </c:when>
                    <c:otherwise>
                        <span class="label label-default">Không trạng thái</span>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <!-- Contract type -->
    <div class="form-group">
        <label class="col-sm-4 control-label">Loại hợp đồng</label>

        <div class="col-sm-7">
            <div class="text-value">
                ${contract.micContractTypeByContractTypeId.name}
            </div>
        </div>
    </div>

    <!-- Start date -->
    <div class="form-group">
        <label class="col-sm-4 control-label">Bắt đầu có hiệu lực từ</label>

        <div class="col-sm-4">
            <div class="text-value">
                <fmt:formatDate value="${contract.startDate}" pattern="dd/MM/yyyy"/>
                lúc
                <fmt:formatDate value="${contract.startDate}" type="time"/>
            </div>
        </div>
    </div>

    <!-- Expired date -->
    <div class="form-group">
        <label class="col-sm-4 control-label">Thời điểm hết hiệu lực</label>

        <div class="col-sm-4">
            <div class="text-value">
                <fmt:formatDate value="${contract.expiredDate}" pattern="dd/MM/yyyy"/>
                lúc
                <fmt:formatDate value="${contract.expiredDate}" type="time"/>
            </div>
        </div>
    </div>

    <!-- Remaining days -->
    <div class="form-group">
        <label class="col-sm-4 control-label">Thời gian đến ngày hết hạn</label>

        <div class="col-sm-2">
            <div class="text-value">
                <span id="remain" style="color:deepskyblue; font-weight: bolder; font-size: large"></span> ngày
            </div>
        </div>

        <label class="col-sm-2 control-label">Phí bảo hiểm</label>

        <div class="col-sm-2">
            <div class="text-value">
                <span style="color:hotpink; font-weight: bolder; font-size: large">
                    <fmt:setLocale value="vi_VN"/>
                    <fmt:formatNumber value="${contract.contractFee}" type="currency" maxFractionDigits="0"/>
                </span>
            </div>
        </div>
    </div>
</fieldset>