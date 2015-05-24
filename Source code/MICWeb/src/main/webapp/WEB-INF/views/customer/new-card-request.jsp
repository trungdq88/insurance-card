<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-header text-info"> Yêu Cầu Thẻ Mới             
                    </h2>
                </div>
            </div>
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-3 control-label">Xác Nhân Mật Khẩu</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">Ghi Chú</label>
                    <div class="col-sm-5">
                        <textarea class="form-control" cols="3"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">Thanh Toán: </label>
                </div>
                <div class="form-group">
                    <div class="col-sm-3 control-label">
                        <input type="radio" id="radio_Paypal" value="">
                    </div>
                    <div class="col-sm-5">
                        <p class="form-control-static">Paypal</p>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3 control-label">
                        <input type="radio" id="radio_CDO" value="">
                    </div>
                    <div class="col-sm-5">
                        <p class="form-control-static">Thanh toán trực tiếp (tại công ty)</p>
                    </div>
                </div>
            </form>
            <form class="form-horizontal">
                <div class="form-group newCardCost hide">
                    <label class="col-sm-3 control-label">Phí Làm Thẻ Mới:</label>
                    <div class="col-sm-5">
                        <p class="form-control-static">50000 VND   <input class="hide" id="newCard_Cost" value="50000" /></p>
                    </div>
                </div>
                <div class="form-group tranformCost hide" >
                    <label class="col-sm-3 control-label">Phí Vận Chuyển: </label>
                    <div class="col-sm-5">
                        <p class="form-control-static">10000 VND <input class="hide" id="transform_Cost" value="10000" /></p>
                    </div>
                </div>
               <div class="form-group">
                    <label class="col-sm-3 control-label">Thành Tiền: </label>
                    <div class="col-sm-5">
                        <p class="form-control-static"> <input class="form-control-satic text-center" id="total_Cost" value="0 VND" /></p>
                    </div>
                </div>
            </form>


            

            <div class="form-group">
                <label class="col-sm-3 control-label"></label>
                <div class="col-sm-5">
                    <button type="submit" class="btn btn-primary">Xác Nhận</button>
                    <button type="submit" class="btn btn-cancel">Hủy Bỏ</button>
                </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->


<%@ include file="_shared/footer.jsp" %>
