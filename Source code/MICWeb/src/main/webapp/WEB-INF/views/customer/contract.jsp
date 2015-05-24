<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>


    <div id="page-wrapper">
        <div class="row">

            <div class="col-lg-12">
                <h2 class="page-header text-info">Thông Tin Bảo Hiểm
                     <span class="pull-right">
                         <button type="submit" class="btn btn-default">Gia Hạn</button>
                         <button type="button" class="btn btn-cancel" data-toggle="modal"
                                 data-target=".bs-example-modal-lg">Hủy Hợp Đồng
                         </button>

                     </span>
                </h2>
            </div>
            <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">
                                <label class="text-danger">Hủy Hợp Đồng</label></h4>
                        </div>
                        <div class="modal-body">
                            <div class="text-info">
                                <label>
                                    Quý khách vui lòng cung cấp lý do hoặc trường hợp hủy hợp đồng!
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="radio" value="">
                                    Xe cơ giới bị thu hồi đăng ký
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="radio" value="">
                                    Xe cơ giới hết niên hạn
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="radio" value="">
                                    Lý do khác
                                </label>
                            </div>
                            <div class="form-group">
                                <label>Văn Bản Đính Kèm (Nếu có)</label>
                                <input type="file"/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary">Xác Nhận</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Hủy Bỏ</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">Tình Trạng Hợp Đồng:</label>

                <div class="col-sm-10">
                    <p class="form-control-static" style="color: orange">Sắp Hết Hạn (Còn 7 Ngày)</p>
                </div>

            </div>
        </form>
        <table class="table table-bordered">
            <thead>
            <tr class="active">
                <th class="text-center">Sổ Hợp Đồng
                </th>
                <th class="text-center">Ngày Tham Gia
                </th>
                <th class="text-center">Ngày Hết Hạn
                </th>
                <th class="text-center">Quyền Lợi Bảo Hiểm
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="text-center">123456789
                </td>
                <td class="text-center">11/02/2011
                </td>
                <td class="text-center">11/02/2012
                </td>
                <td class="text-center">Trên 50 CC có bảo hiểm
                </td>
            </tr>
            </tbody>
        </table>
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">Ngày Cấp:</label>

                <div class="col-sm-10">
                    <p class="form-control-static">10/11/2011</p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Nơi Cấp: </label>

                <div class="col-sm-10">
                    <p class="form-control-static">24/02 Lê Quan Định, Bình Thạnh TPHCM</p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">NGười Cấp: </label>

                <div class="col-sm-10">
                    <p class="form-control-static">Huỳnh Văn Thái</p>
                </div>
            </div>
        </form>
        <div class="row">

            <div class="col-lg-12">
                <h2 class="page-header text-info">Thông Tin Xe

                </h2>
            </div>
        </div>
        <form class="form-horizontal">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Biển Số Đăng Ký: </label>

                    <div class="col-sm-8">
                        <p class="form-control-static">
                            <label>59Y1-3979</label>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Loại Xe:</label>

                    <div class="col-sm-8">
                        <p class="form-control-static">
                            <label>Nữ</label>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Màu Sơn:</label>

                    <div class="col-sm-8">
                        <p class="form-control-static">
                            <label>Đen</label>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Số Máy:</label>

                    <div class="col-sm-8">
                        <p class="form-control-static">
                            <label>241270</label>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Nhãn Hiệu: </label>

                    <div class="col-sm-8">
                        <p class="form-control-static">
                            <label>Air Blade</label>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Dung Tích:</label>

                    <div class="col-sm-8">
                        <p class="form-control-static">
                            <label>108</label>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Năm Sản Xuất:</label>

                    <div class="col-sm-8">
                        <p class="form-control-static">
                            <label>2011</label>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Số Khung:</label>

                    <div class="col-sm-8">
                        <p class="form-control-static">
                            <label>241270222</label>
                        </p>
                    </div>
                </div>
            </div>
        </form>
        <div class="clearfix"></div>
    </div>
    <!-- /#page-wrapper -->


</div>
<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp" %>
