<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thêm hợp đồng mới</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="well text-center text-primary">
                    <i class="fa fa-check"></i> Hợp đồng đã được khởi tạo thành công!
                </div>


                <div class="table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>Mã hợp đồng</th>
                            <th>Tên khách hàng</th>
                            <th>Ngày bắt đầu</th>
                            <th>Thời hạn</th>
                            <th>Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><a href="detail-contract.html">BHBB374</a></td>
                            <td><a href="detail-customer.html">Đinh Quang Trung</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-success">Sẵn sàng</span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <br/>

                <div class="text-center">

                    <a href="${pageContext.request.contextPath}/staff/contract" type="button" class="btn btn-primary">
                        <i class="fa fa-arrow-left"></i>
                        Quay lại trang hợp đồng
                    </a>
                </div>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp"%>