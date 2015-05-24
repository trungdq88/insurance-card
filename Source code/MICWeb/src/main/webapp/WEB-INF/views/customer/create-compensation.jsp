<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp"%>


    <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Yêu cầu bồi thường COM384
                    <div class="pull-right">
                        <span style="font-size: 40%" class="label label-warning">Đang giải quyết</span>
                    </div>
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">

                    <form class="form-horizontal">
                        <fieldset>
                            <legend>Thông tin hợp đồng
                            </legend>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Mã hợp đồng</label>

                                <div class="col-sm-6">
                                    <div class="text-value text-primary">
                                        <b><a href="detail-contract.html">BHBB374</a></b>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Tên khách hàng</label>

                                <div class="col-sm-6">
                                    <div class="text-value">
                                        <b><a href="detail-customer.html">Đinh Quang Trung</a></b>
                                    </div>
                                </div>
                            </div>

                        </fieldset>

                        <br />
                        <br />

                        <fieldset>
                            <legend>Thông tin yêu cầu bồi thường
                            </legend>
                            <br />

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Họ tên lái xe</label>

                                <div class="col-sm-6">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Địa chỉ liên hệ</label>

                                <div class="col-sm-6">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Biển số xe gây tai nạn</label>

                                <div class="col-sm-6">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Giấy phép lái xe số</label>

                                <div class="col-sm-3">
                                    <input type="text" class="form-control" />
                                </div>
                                <label class="col-sm-1 control-label">Hạng</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Trọng tải / số chỗ ngồi</label>

                                <div class="col-sm-3">
                                    <input type="text" class="form-control" />
                                </div>
                                <label class="col-sm-1 control-label">Điện thoại</label>


                                <div class="col-sm-2">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-4 control-label">Thời gian</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Địa điểm</label>

                                <div class="col-sm-6">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Cơ quan CA giải quyết</label>

                                <div class="col-sm-6">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Diễn biến và nguyên nhân</label>

                                <div class="col-sm-6">
                                    <textarea class="form-control" rows="7">

                                   </textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Thiệt hại về người</label>

                                <div class="col-sm-6">

                                    <textarea class="form-control" rows="5">

                                   </textarea>

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Thiệt hại về tài sản</label>

                                <div class="col-sm-6">
                                   <textarea class="form-control" rows="5">

                                   </textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Người làm chứng</label>

                                <div class="col-sm-6">
                                    <input type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Yêu cầu bồi thường</label>

                                <div class="col-sm-6">
                                     <textarea class="form-control" rows="3">

                                   </textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Biên bản của cơ quan CA</label>

                                <div class="col-sm-6">
                                    <input type="file" />
                                </div>
                            </div>

                        </fieldset>
                        <br />
                        <br />
                        <fieldset>
                            <legend>Giải quyết yêu cầu bồi thường
                            </legend>

                            <!-- Text input-->
                            <div class="form-group">
                                <div class="col-sm-1">&nbsp;</div>
                                <div class="col-sm-10">
                                    <div class="text-value">
                                        <label for="note">Ghi chú của công ty bảo hiểm</label>
                                        <textarea id="note" class="form-control" rows="7"></textarea>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Quyết định bồi thường</label>

                                <div class="col-sm-6">
                                    <select class="form-control">
                                        <option>Chưa quyết định</option>
                                        <option>Chấp nhận bồi thường</option>
                                        <option>Từ chối bồi thường</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Trạng thái</label>

                                <div class="col-sm-6">
                                    <label class="text-value" style="font-weight: normal;">
                                        <input type="checkbox" />
                                        Đã giải quyết xong
                                    </label>
                                </div>
                            </div>
                        </fieldset>
                        <br />
                        <br />
                        <p class="text-center">
                            <a href="#" class="btn btn-primary">Lưu thay đổi</a>
                        </p>
                    </form>

                </div>
            </div>



        </div>
    </div>
    <!-- /#page-wrapper -->


    <!-- /#wrapper -->


    <%@ include file="_shared/footer.jsp" %>