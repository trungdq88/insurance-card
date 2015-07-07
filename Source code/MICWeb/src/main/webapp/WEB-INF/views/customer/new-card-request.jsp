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
                    <input type="radio" name="request:payment" value="paypal" onclick="{
                        $('.tranformCost').removeClass('hide');
                        $('.newCardCost').removeClass('hide');
                        $('#total_Cost').text((parseFloat($('#newCard_Cost').val()) + parseFloat($('#transform_Cost').val())).formatMoney(0));
                    }" checked>
                </div>
                <div class="col-sm-5">
                    <p class="form-control-static">Thanh toán qua PayPal</p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3 control-label">
                    <input type="radio" name="request:payment" value="direct" onclick="{
                    $('#total_Cost').text(parseFloat($('#newCard_Cost').val()).formatMoney(0));
                    $('.tranformCost').addClass('hide');
                    }">
                </div>
                <div class="col-sm-5">
                    <p class="form-control-static">Thanh toán trực tiếp (tại công ty)</p>
                </div>
            </div>


            <div class="form-group newCardCost hide">
                <label class="col-sm-3 control-label">Phí Làm Thẻ Mới:</label>

                <div class="col-sm-2">
                    <p class="form-control-static"><span id="newCard_Cost1"></span> VND <input class="hide" id="newCard_Cost" name="newCardFee"
                                                                    value="${newCardFee}"/></p>
                </div>
                <div class="col-sm-3">
                    <p class="text-value">
                        <input id="deactiveCard" type="hidden" name="request:deactiveCardRequested" value="${empty submitted.deactiveCardRequested?false:submitted.deactiveCardRequested}">
                        <input id="deactiveCardChk" type="checkbox"  onclick="{
                    if( this.checked){
                        $('#deactiveCard').attr('value','true');
                    } else {
                        $('#deactiveCard').attr('value','false');
                    };
                    }"
                        <c:if test="${submitted.deactiveCardRequested == true}"> checked</c:if> >
                        <label for="deactiveCardChk">Hủy bỏ thẻ cũ</label>
                    </p>

                </div>
            </div>
            <div class="form-group tranformCost hide">
                <label class="col-sm-3 control-label">Phí Vận Chuyển: </label>

                <div class="col-sm-2">
                    <p class="form-control-static"><span id="transform_Cost1"></span> VND <input class="hide" id="transform_Cost" name="transformFee"
                                                                    value="${transformFee}"/></p>

                </div>
                <div class="col-sm-3">
                    <p class="text-value">
                        <input id="delivery" type="hidden" name="request:deliveryRequested" value="${empty submitted.deliveryRequested?true:submitted.deliveryRequested}">
                        <input id="deliveryChk" type="checkbox" onclick="{
                    if( this.checked){
                        $('#delivery').attr('value','false');
                        $('#total_Cost').text(parseFloat($('#newCard_Cost').val()).formatMoney(0));
                    } else {
                        $('#delivery').attr('value','true');
                        $('#total_Cost').text((parseFloat($('#newCard_Cost').val()) + parseFloat($('#transform_Cost').val())).formatMoney(0));
                    };
                    }"
                        <c:if test="${submitted.deliveryRequested == true}"> checked</c:if> >
                        <label for="deliveryChk">Nhận thẻ tại công ty</label>
                    </p>

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
                    <button type="submit" class="btn btn-primary">
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
    Number.prototype.formatMoney = function(c, d, t){
        var n = this,
                c = isNaN(c = Math.abs(c)) ? 2 : c,
                d = d == undefined ? "." : d,
                t = t == undefined ? "," : t,
                s = n < 0 ? "-" : "",
                i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "",
                j = (j = i.length) > 3 ? j % 3 : 0;
        return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
    };
    $('.tranformCost').removeClass('hide');
    $('.newCardCost').removeClass('hide');
    if ($('#deactiveCard').attr('value') == true) {
        $('#deactiveCardChk').checked;
    }

    if($('#delivery').attr('value') == false) {
        $('#total_Cost').text(parseFloat($('#newCard_Cost').val()).formatMoney(0));
        $('#deliveryChk').checked;
    } else {
        $('#total_Cost').text((parseFloat($('#newCard_Cost').val()) + parseFloat($('#transform_Cost').val())).formatMoney(0));
    }

    $('#newCard_Cost1').text(parseFloat($('#newCard_Cost').val()).formatMoney(0));
    $('#transform_Cost1').text(parseFloat($('#transform_Cost').val()).formatMoney(0));
</script>