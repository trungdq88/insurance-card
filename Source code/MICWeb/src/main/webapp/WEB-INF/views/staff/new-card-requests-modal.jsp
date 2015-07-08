<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- model for add new payment -->
<div class="modal fade" id="add-payment-modal">
  <div class="modal-dialog">
    <form action="${pageContext.request.contextPath}/staff/card" method="post" class="form-horizontal">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                  aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">Thêm thông tin thanh toán</h4>
        </div>
        <div class="modal-body">
          <%--<jsp:include page="contract-detail-general.jsp" flush="true"/>--%>
          <%--/Contract information--%>
          <br/>
          <fieldset>
            <legend>Thông tin thanh toán</legend>
            <!-- Paid date -->
            <div class=" form-group">
              <label class="col-sm-4 control-label" for="addPaidDate">Ngày nộp phí *</label>

              <div class="col-sm-4">
                <input id="addPaidDate" name="createNewCardPayment:paidDate" class="form-control input-md"
                       type="date" required>
              </div>
            </div>

            <!-- Content -->
            <div class="form-group">
              <label class="col-sm-4 control-label" for="addContent">Dịch vụ *</label>

              <div class="col-sm-7">
                <input id="addContent" name="createNewCardPayment:content" class="form-control input-md"
                       type="text" required maxlength="250"
                       title="Vui lòng nhập dịch vụ cần thanh toán"
                       placeholder="Ví dụ: Đăng ký hợp đồng mới KH0001">
              </div>
            </div>

            <!-- Amount -->
            <div class=" form-group">
              <label class="col-sm-4 control-label" for="addAmount">Số tiền *</label>

              <div class="col-sm-5">
                <div class="input-group">
                  <input id="addAmount" name="createNewCardPayment:amount" class="form-control input-md"
                         type="number" required min="0" max="1000000000"
                         title="Vui lòng nhập số tiền" placeholder="Ví dụ: 80000">

                  <div class="input-group-addon">VNĐ</div>
                </div>
              </div>
            </div>
          </fieldset>
          <%--/Payment information--%>
        </div>
        <div class="modal-footer">
          <input id="contractCode" type="hidden" name="createNewCardPayment:contractCode" />
          <input id="delivery" name="createNewCardPayment:delivery">
          <input type="hidden" name="action" value="createNewCardPayment"/>
          <button type="submit" class="btn btn-success">
            <i class="fa fa-arrow-right"></i> Thêm thông tin thanh toán
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