﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Hợp đồng

                    <div class="pull-right">
                        <a href="${pageContext.request.contextPath}/customer/contract?action=NewContract" class="btn btn-success">
                            <i class="fa fa-plus"></i>
                            Hợp đồng mới
                        </a>
                    </div></h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <div>

        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="pull-left center-dropdown-button">
                    <!--<input type="checkbox" class="check-all"/>-->
                    <b>Có 173 hợp đồng</b>
                </div>
                <div class="pull-right no-wrap">
                    <input type="text" class="form-control long-text-box"
                           placeholder="Tìm kiếm theo tên, mã hợp đồng"/>
                    <input type="button" class="btn btn-default" value="Tìm kiếm"/>

                </div>
                <div class="clearfix"></div>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-hover table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Mã hợp đồng</th>
                            <th>Ngày bắt đầu</th>
                            <th>Thời hạn</th>
                            <th>Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/customer/contract?action=ContractDetail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-success">Sẵn sàng</span> </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-warning">Sắp hết hạn</span> </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-danger">Hết hạn</span> </td>
                        </tr>

                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-gray">Chờ thanh toán</span> </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-primary">Chưa có thẻ</span> </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-dark">Đã huỷ hợp đồng</span> </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-success">Sẵn sàng</span> </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-success">Sẵn sàng</span> </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-success">Sẵn sàng</span> </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/contract?action=detail">BHBB374</a></td>
                            <td>04/05/2015</td>
                            <td>1 năm</td>
                            <td><span class="label label-success">Sẵn sàng</span> </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
                <nav class="text-right">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->


        <div class="panel panel-success">
            <div class="panel-heading">
                Các trạng thái của hợp đồng:
            </div>
            <div class="panel-body">
                <ul>
                    <li>
                        <span class="label label-gray">Chờ thanh toán</span>
                        Khách hàng đăng ký online nhưng chưa thanh toán.
                    </li>
                    <li>
                        <span class="label label-primary">Chưa có thẻ</span>
                        Khách hàng đã thanh toán cho hợp đồng nhưng chưa được in thẻ để sử dụng.
                    </li>
                    <li>
                        <span class="label label-success">Sẵn sàng</span>
                        Khánh hàng đã hoàn thành hợp đồng và đã có thẻ để sẵn sàng sử dụng.
                    </li>
                    <li>
                        <span class="label label-warning">Sắp hết hạn</span>
                        Thời hạn của hợp đồng sắp hết, cần được gia hạn.
                    </li>
                    <li>
                        <span class="label label-danger">Hết hạn</span>
                        Hợp đồng đã hết hạn và không có giá trị. Khánh hàng phải gia hạn để tiếp tục sử dụng chương trình bảo hiểm.
                    </li>
                    <li>
                        <span class="label label-dark">Đã huỷ hợp đồng</span>
                        Hợp đồng bị huỷ. Khánh hàng muốn tiếp tục sử dụng cần phải đăng ký hợp đồng mới.
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp"%>