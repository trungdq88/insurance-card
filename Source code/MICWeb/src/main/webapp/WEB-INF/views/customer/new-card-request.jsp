<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:if test="${not empty validateErrors}">
                <div class="text-danger">
                    <ul>
                        <c:forEach var="error" items="${validateErrors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Xác nhân mật khẩu</label>

                <div class="col-sm-5">
                    <input type="password" class="form-control" name="request:password" id="password"
                           required title="Vui lòng nhập mật khẩu" value="${submitted.password}">
                </div>
            </div>
            <div class="form-group">
                <label for="note" class="col-sm-3 control-label">Ghi chú</label>

                <div class="col-sm-5">
                    <textarea name="request:note" id="note" class="form-control" cols="3"
                              required title="Vui lòng nhập ghi chú" >${submitted.note}</textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">Thanh toán: </label>
            </div>

            <div class="form-group">
                <div class="col-sm-3 control-label">
                    <input type="radio" name="request:payment" value="direct" onclick="{
                        $('.tranformCost').removeClass('hide');
                        $('.newCardCost').removeClass('hide');
                        $('#total_Cost').val(parseFloat($('#newCard_Cost').val()) + parseFloat($('#transform_Cost').val()) + ' VND');
                    }" checked>
                </div>
                <div class="col-sm-5">
                    <p class="form-control-static">Thanh toán trực tiếp (tại công ty)</p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3 control-label">
                    <input type="radio" name="request:payment" value="paypal" onclick="{
                    $('.tranformCost').addClass('hide');
                    $('#total_Cost').val(parseFloat($('#newCard_Cost').val()) + ' VND');
                    }">
                </div>
                <div class="col-sm-5">
                    <p class="form-control-static">Paypal</p>
                </div>
            </div>


            <div class="form-group newCardCost hide">
                <label class="col-sm-3 control-label">Phí Làm Thẻ Mới:</label>

                <div class="col-sm-5">
                    <p class="form-control-static">${newCardFee} VND <input class="hide" id="newCard_Cost" name="newCardFee"
                                                                    value="${newCardFee}"/></p>
                </div>
            </div>
            <div class="form-group tranformCost hide">
                <label class="col-sm-3 control-label">Phí Vận Chuyển: </label>

                <div class="col-sm-5">
                    <p class="form-control-static">${transformFee} VND <input class="hide" id="transform_Cost" name="transformFee"
                                                                    value="${transformFee}"/></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Thành Tiền: </label>

                <div class="col-sm-5">
                    <p class="form-control-static"><span id="total_Cost"></span> VND</p>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label"></label>

                <div class="col-sm-5">
                    <input type="hidden" name="contractCode" value="${contractCode}">
                    <input type="hidden" name="request:contractCode" value="${contractCode}">
                    <input type="hidden" name="request:customerCode" value="${customerCode}">
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
<script language="JavaScript">
    $('.tranformCost').removeClass('hide');
    $('.newCardCost').removeClass('hide');
    $('#total_Cost').text(parseFloat($('#newCard_Cost').val()) + parseFloat($('#transform_Cost').val()));
</script>