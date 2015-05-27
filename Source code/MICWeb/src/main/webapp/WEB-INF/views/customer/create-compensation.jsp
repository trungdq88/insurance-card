<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>


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

                    <br/>
                    <br/>

                    <fieldset>
                        <legend>Thông tin yêu cầu bồi thường
                        </legend>
                        <br/>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Họ tên lái xe</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Địa chỉ liên hệ</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Địa điểm</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Cơ quan CA giải quyết</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời gian</label>

                            <div class="input-group col-md-3" style="padding-left: 15px">
                                    <input type="text" class="form-control"/>
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </span>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Điện thoại</label>

                            <div class="col-sm-3">
                                <input type="number" class="form-control"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-4 control-label">Biển số xe gây tai nạn</label>

                            <div class="col-sm-3">
                                <input type="text" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Giấy phép lái xe số</label>

                            <div class="col-sm-3">
                                <input type="text" class="form-control"/>
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Trọng tải / số chỗ ngồi</label>

                            <div class="col-sm-2">
                                <input type="text" class="form-control"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Hạng</label>

                            <div class="col-sm-2">
                                <input type="text" class="form-control"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-4 control-label">Diễn biến và nguyên nhân</label>

                            <div class="col-sm-6">
                                    <textarea class="form-control" rows="5">

                                   </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thiệt hại về người</label>

                            <div class="col-sm-6">

                                    <textarea class="form-control" rows="3">

                                   </textarea>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thiệt hại về tài sản</label>

                            <div class="col-sm-6">
                                   <textarea class="form-control" rows="3">

                                   </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Người làm chứng</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control"/>
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
                                <input type="file"/>
                            </div>
                        </div>

                    </fieldset>
                    <br/>
                    <br/>

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