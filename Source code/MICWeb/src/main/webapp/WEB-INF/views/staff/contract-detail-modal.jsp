<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- model for detail payment -->
<div class="modal fade" id="detail-payment-modal">
    <div class="modal-dialog">
        <form class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Chi tiết thông tin thanh toán</h4>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <!-- Payment ID & Paid date -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Mã giao dịch</label>

                            <div class="col-sm-1">
                                <div class="text-value">
                                    <strong id="payment-id-value"></strong>
                                </div>
                            </div>

                            <label class="col-sm-3 control-label">Mã hợp đồng</label>

                            <div class="col-sm-2">
                                <div class="text-value" id="contract-code-value"></div>
                            </div>
                        </div>

                        <!-- Amount & Content -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Số tiền</label>

                            <div class="col-sm-2">
                                <div class="text-value" id="amount-value"></div>
                            </div>

                            <label class="col-sm-2 control-label">Dịch vụ</label>

                            <div class="col-sm-5">
                                <div class="text-value" id="content-value"></div>
                            </div>
                        </div>

                        <!-- Payment method & Paid date -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Hình thức</label>

                            <div class="col-sm-2">
                                <div class="text-value" id="payment-method-value"></div>
                            </div>

                            <label class="col-sm-2 control-label">Thời gian</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="paid-date-value"></div>
                            </div>
                        </div>

                        <!-- Paypal ID -->
                        <div id="ppIDCtrl" class="form-group hide">
                            <label class="col-sm-3 control-label">Mã Paypal</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="paypal-id-value"></div>
                            </div>
                        </div>

                        <!-- Receiver -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Người nhận</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="receiver-value"></div>
                            </div>
                        </div>

                        <!-- Start date -->
                        <div id="stDateCtrl" class="form-group hide">
                            <label class="col-sm-5 control-label">Ngày bắt đầu gia hạn</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="start-date-value"></div>
                            </div>
                        </div>

                        <!-- Expired date -->
                        <div id="expDateCtrl" class="form-group hide">
                            <label class="col-sm-5 control-label">Ngày hết hạn hợp đồng</label>

                            <div class="col-sm-4">
                                <div class="text-value" id="expired-date-value"></div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Payment information--%>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- model for complete payment -->
<div class="modal fade" id="complete-payment-modal">
    <div class="modal-dialog">
        <form action="${pageContext.request.contextPath}/staff/contract" method="post" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Hoàn tất thông tin thanh toán</h4>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <legend>Thông tin hợp đồng bảo hiểm</legend>

                        <!-- Contract code -->
                        <input type="hidden" name="payment:contractCode" value="${contract.contractCode}"/>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Loại hợp đồng</label>

                            <div class="col-sm-6">
                                <div class="text-value">
                                    ${contract.micContractTypeByContractTypeId.name}
                                </div>
                            </div>
                        </div>

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Thời điểm có hiệu lực</label>

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
                            <label class="col-sm-5 control-label">Thời điểm hết hiệu lực</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:formatDate value="${contract.expiredDate}" pattern="dd/MM/yyyy"/>
                                    lúc
                                    <fmt:formatDate value="${contract.expiredDate}" type="time"/>
                                </div>
                            </div>
                        </div>

                        <!-- Contract fee -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Phí bảo hiểm</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <fmt:setLocale value="vi_VN"/>
                                    <fmt:formatNumber value="${contract.contractFee}" type="currency"
                                                      maxFractionDigits="0"/>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Contract information--%>
                    <br/>

                    <fieldset>
                        <legend>Thông tin thanh toán</legend>

                        <!-- Paid date -->
                        <div class=" form-group">
                            <label class="col-sm-5 control-label" for="paidDate">Ngày nộp phí *</label>

                            <div class="col-sm-4">
                                <input id="paymentDate" name="payment:paidDate" class="form-control input-md"
                                       type="date" required>
                                <input type="hidden" name="payment:amount" value="${contract.contractFee}"/>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Payment information--%>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="action" value="completePayment"/>
                    <button type="submit" class="btn btn-success">
                        <i class="fa fa-arrow-right"></i> Hoàn tất thanh toán
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- model for renew contract -->
<div class="modal fade" id="renew-contract-modal">
    <div class="modal-dialog">
        <form action="${pageContext.request.contextPath}/staff/contract" method="post" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Gia hạn hợp đồng</h4>
                </div>
                <div class="modal-body">

                    <!-- Contract code -->
                    <input type="hidden" name="renew:contractCode" value="${contract.contractCode}"/>

                    <!-- Contract type -->
                    <div class="form-group">
                        <label class="col-sm-5 control-label">Loại hợp đồng</label>

                        <div class="col-sm-7">
                            <div class="text-value">
                                ${contract.micContractTypeByContractTypeId.name}
                            </div>
                        </div>
                    </div>

                    <!-- Start date -->
                    <div class="form-group">
                        <label class="col-sm-5 control-label">Bắt đầu có hiệu lực từ</label>

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
                        <label class="col-sm-5 control-label">Thời điểm hết hiệu lực</label>

                        <div class="col-sm-4">
                            <div class="text-value">
                                <fmt:formatDate value="${contract.expiredDate}" pattern="dd/MM/yyyy"/>
                                lúc
                                <fmt:formatDate value="${contract.expiredDate}" type="time"/>
                            </div>
                        </div>
                    </div>

                    <!-- New expired date -->
                    <div class="form-group">
                        <label class="col-sm-5 control-label" for="expiredDate">Gia hạn đến *</label>

                        <div class="col-sm-4">
                            <input id="expiredDate" name="renew:expiredDate" class="form-control input-md"
                                   type="date" required>
                        </div>
                    </div>

                    <!-- Renew fee -->
                    <div class="form-group">
                        <label class="col-sm-5 control-label">Phí gia hạn</label>

                        <div class="col-sm-4">
                            <div class="text-value">
                                    <span id="renewFee"
                                          style="color:deepskyblue; font-weight: bolder; font-size: large"></span> đ
                                <input type="hidden" id="contractFee" name="renew:contractFee"/>
                            </div>
                        </div>
                    </div>

                    <!-- New card -->
                    <div class="form-group">
                        <label class="col-sm-5 control-label">Cấp thẻ mới</label>

                        <div class="col-sm-4">
                            <div class="text-value">
                                <a data-toggle="collapse" href="#collapseNewCard" data-parent="#accordion">
                                    <input type="checkbox" id="newCard" name="renew:newCard">
                                </a>
                            </div>
                        </div>
                    </div>

                    <div id="collapseNewCard" class="panel-collapse collapse in">
                        <!-- New card fee -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Phí thẻ mới</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <span id="newCardFee"
                                          style="color:navy; font-weight: bolder; font-size: large"></span> đ
                                </div>
                            </div>
                        </div>

                        <!-- Total fee -->
                        <div class="form-group">
                            <label class="col-sm-5 control-label">Tổng chi phí</label>

                            <div class="col-sm-4">
                                <div class="text-value">
                                    <span id="totalFee"
                                          style="color:red; font-weight: bolder; font-size: large"></span> đ
                                    <input type="hidden" id="amount" name="renew:amount"/>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Paid date -->
                    <div class=" form-group">
                        <label class="col-sm-5 control-label" for="paidDate">Ngày nộp phí *</label>

                        <div class="col-sm-4">
                            <input id="paidDate" name="renew:paidDate" class="form-control input-md"
                                   type="date" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="action" value="renew"/>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-arrow-right"></i> Gia hạn hợp đồng
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- model for cancel contract -->
<div class="modal fade" id="cancel-contract-modal">
    <div class="modal-dialog">
        <form action="${pageContext.request.contextPath}/staff/contract" method="post" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Hủy hợp đồng</h4>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <!-- Contract code -->
                        <input type="hidden" name="cancel:contractCode" value="${contract.contractCode}"/>

                        <!-- Cancel date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelDate">Ngày hủy hợp đồng *</label>

                            <div class="col-sm-4">
                                <input id="cancelDate" name="cancel:cancelDate" type="date" required
                                       class="form-control input-md"
                                       value="<fmt:formatDate value="${contract.cancelDate}" pattern="yyyy-MM-dd"/>">
                            </div>
                        </div>

                        <!-- Cancel reason -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelReason">Lý do hủy hợp đồng *</label>

                            <div class="col-sm-7">
                                <c:if test="${contract.status eq 'Request cancel'}">
                                    <input type="hidden" name="cancel:cancelReason" value="${contract.cancelReason}"/>
                                </c:if>
                                <input id="cancelReason" name="cancel:cancelReason" class="form-control input-md"
                                       type="text" required maxlength="250" pattern="^\w.+$"
                                       title="Vui lòng nhập lý do hủy hợp đồng" placeholder="Ví dụ: Mất xe"
                                       value="${contract.cancelReason}">
                            </div>
                        </div>

                        <!-- Cancel note -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cancelNote">Ghi chú</label>

                            <div class="col-sm-7">
                                <textarea id="cancelNote" name="cancel:cancelNote" rows="4" maxlength="2000"
                                          class="form-control input-lg"></textarea>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="action" value="cancel"/>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-check"></i> Đồng ý hủy
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->