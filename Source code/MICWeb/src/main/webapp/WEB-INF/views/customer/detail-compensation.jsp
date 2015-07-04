<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">
    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal">
                    <fieldset>
                        <legend style="border-bottom: none">Thông tin hợp đồng</legend>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Mã hợp đồng</label>

                            <div class="col-sm-2">
                                <div class="text-value">
                                    <a href="${pageContext.request.contextPath}/staff/contract?action=detail&code=${compensation.contractCode}">
                                        <strong>${compensation.contractCode}</strong>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Tên khách hàng</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <a href="detail-customer.html">
                                        <b>${compensation.micContractByContractCode.micCustomerByCustomerCode.name}</b>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Contract information--%>
                    <br/>
                    <fieldset>
                        <legend>Thông tin yêu cầu bồi thường</legend>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Ngày gởi yêu cầu</label>

                            <div class="col-sm-3">
                                <div class="text-value">
                                    <fmt:formatDate value="${compensation.createdDate}" pattern="dd/MM/yyyy"/>
                                </div>
                            </div>
                            <label class="col-sm-2 control-label">Trạng thái</label>

                            <div class="col-sm-3">
                                <div class="text-value">
                                    <c:choose>
                                        <c:when test="${empty compensation.resolveDate}">
                                            <span class="label label-default">Chưa giải quyết</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="label label-success">Đã giải quyết</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Họ tên lái xe</label>

                            <div class="col-sm-6">
                                <div class="text-value">${compensation.driverName}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Địa chỉ liên hệ</label>

                            <div class="col-sm-8">
                                <div class="text-value">${compensation.driverAddress}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Số điện thoại</label>

                            <div class="col-sm-3">
                                <div class="text-value">${compensation.driverPhone}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Giấy phép lái xe số</label>

                            <div class="col-sm-3">
                                <div class="text-value">${compensation.licenseNumber}</div>
                            </div>
                            <label class="col-sm-2 control-label">Hạng</label>

                            <div class="col-sm-3">
                                <div class="text-value">${compensation.licenseType}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Biển số xe gây tai nạn</label>

                            <div class="col-sm-2">
                                <div class="text-value">${compensation.plate}</div>
                            </div>
                            <label class="col-sm-3 control-label">Trọng tải/số chỗ ngồi</label>

                            <div class="col-sm-2">
                                <div class="text-value">${compensation.vehicleCapacity}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Ngày xảy ra tai nạn</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:formatDate value="${compensation.accidentDate}" pattern="dd/MM/yyyy"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Nơi xảy ra tai nạn</label>

                            <div class="col-sm-8">
                                <div class="text-value">${compensation.accidentPlace}</div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Cơ quan CA giải quyết</label>

                            <div class="col-sm-8">
                                <div class="text-value">${compensation.controlDepartment}</div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Diễn biến và nguyên nhân</label>

                            <div class="col-sm-8">
                                <div class="text-value">${compensation.description}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thiệt hại về người</label>

                            <div class="col-sm-8">
                                <div class="text-value">${compensation.humanDamage}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thiệt hại về tài sản</label>

                            <div class="col-sm-8">
                                <div class="text-value">${compensation.assetDamage}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Người làm chứng</label>

                            <div class="col-sm-8">
                                <div class="text-value">${compensation.observer}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Địa chỉ của người làm chứng</label>

                            <div class="col-sm-8">
                                <div class="text-value">${compensation.observerAddress}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Yêu cầu bồi thường</label>

                            <div class="col-sm-8">
                                <div class="text-value">
                                    <c:choose>
                                        <c:when test="${empty compensation.compensationNote}">
                                            <span class="empty-value">Không có</span>
                                        </c:when>
                                        <c:otherwise>${compensation.compensationNote}</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Biên bản của cơ quan CA</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    <c:choose>
                                        <c:when test="${empty compensation.attachment}">
                                            <span class="empty-value">Không có</span>
                                        </c:when>
                                        <c:otherwise>${compensation.attachment}</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <br/>
                    <%--/Compensation information--%>
                    <c:if test="${not empty compensation.resolveDate}">
                        <fieldset>
                            <legend>Thông tin giải quyết yêu cầu bồi thường</legend>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Ngày giải quyết yêu cầu</label>

                                <div class="col-sm-4">
                                    <div class="text-value">
                                        <fmt:formatDate value="${compensation.resolveDate}" pattern="dd/MM/yyyy"/> lúc
                                        <fmt:formatDate value="${compensation.resolveDate}" type="time"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Quyết định bồi thường</label>

                                <div class="col-sm-6">
                                    <div class="text-value">${compensation.decision}</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Ghi chú của công ty bảo hiểm</label>

                                <div class="col-sm-8">
                                    <div class="text-value">
                                        <c:choose>
                                            <c:when test="${empty compensation.resolveNote}">
                                                <span class="empty-value">Không có</span>
                                            </c:when>
                                            <c:otherwise>${compensation.resolveNote}</c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </c:if>
                </form>
                <br/>

                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/customer/contract?action=detail&code=${compensation.contractCode}" type="button"
                       class="btn btn-default">
                        <i class="fa fa-arrow-left"></i> <strong>Quay về trang thông tin hợp đồng</strong>
                    </a>
                </div>
                <br/> <br/>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp" %>