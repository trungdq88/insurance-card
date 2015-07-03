<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
        <form action="${pageContext.request.contextPath}/customer/card"
              method="post" class="form-horizontal">
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header"> Yêu Cầu Thẻ Mới
                </h2>
            </div>
        </div>

            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Xác Nhân Mật Khẩu</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" name="request:password" id="password" >
                </div>
            </div>
            <div class="form-group">
                <label for="note" class="col-sm-3 control-label">Ghi Chú</label>
                <div class="col-sm-5">
                    <textarea name="request:note" id="note" class="form-control" cols="3"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">Thanh Toán: </label>
            </div>

            <div class="form-group">
                <div class="col-sm-3 control-label">
                    <input type="radio" id="payment" name="request:payment" value="direct" checked>
                </div>
                <div class="col-sm-5">
                    <p class="form-control-static">Thanh toán trực tiếp (tại công ty)</p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3 control-label">
                    <input type="radio" id="payment1" value="paypal">
                </div>
                <div class="col-sm-5">
                    <p class="form-control-static">Paypal</p>
                </div>
            </div>


            <div class="form-group newCardCost hide">
                <label class="col-sm-3 control-label">Phí Làm Thẻ Mới:</label>
                <div class="col-sm-5">
                    <p class="form-control-static">50000 VND   <input class="hide" id="newCard_Cost" name="newCardFee" value="50000" /></p>
                </div>
            </div>
            <div class="form-group tranformCost hide" >
                <label class="col-sm-3 control-label">Phí Vận Chuyển: </label>
                <div class="col-sm-5">
                    <p class="form-control-static">10000 VND <input class="hide" id="transform_Cost" name="trnsformFee" value="10000" /></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Thành Tiền: </label>
                <div class="col-sm-5">
                    <p class="form-control-static"> <input class="form-control-satic text-center" id="total_Cost" name="totalFee" value="0 VND" /></p>
                </div>
            </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"></label>
            <div class="col-sm-5">
                <input type="hidden" name="request:contractCode" value="${param.code}">
                <input type="hidden" name="action" value="createNewCardRequest">
                <button type="submit" class="btn btn-success">
                    <i class="fa fa-plus"></i>
                    Xác nhận
                </button>
                <%--<button type="submit" class="btn btn-cancel">Hủy Bỏ</button>--%>
            </div>
        </div>
        </form>
    </div>
    <!-- /#page-wrapper -->


</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>
