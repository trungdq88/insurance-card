<%@ include file="_shared/header.jsp"%>

    <div id="wrapper">

        <%@ include file="_shared/navigation.jsp"%>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Thông Tin Cá Nhân
                        <span class="pull-right">
                            <button type="submit" class="btn btn-primary" id="btn_Modify">Chỉnh Sửa Thông Tin</button>
                            <button type="submit" class="btn btn-primary hide" id="btn_Save">Lưu Thay Đổi</button>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Đổi Mật Khẩu</button>
                        </span>
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-3 control-label">Tên Đăng Nhập</label>
                    <div class="col-sm-5">
                        <p class="form-control-static">${customer.customerCode}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">Họ Và Tên</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" placeholder="Họ Và Tên" disabled="disabled"
                               value="${customer.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">Địa Chỉ</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control textInFormation" placeholder="Địa Chỉ" disabled="disabled"
                               value="${customer.address}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">Email</label>
                    <div class="col-sm-5">
                        <input type="email" class="form-control textInFormation" placeholder="Email" disabled="disabled"
                               value="${customer.email}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">Số Điện Thoại</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control textInFormation" disabled="disabled"
                               value="${customer.phone}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">CMND/Hộ Chiếu</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control textInFormation" disabled="disabled"
                               value="${customer.personalId}">
                    </div>
                </div>

            </form>

        </div>
        <!-- /#page-wrapper -->

    </div>


<%@ include file="_shared/footer.jsp"%>