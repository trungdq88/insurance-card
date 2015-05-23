<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thẻ đã phát hành</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="pull-left center-dropdown-button">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có 435 thẻ đã phát hành</b>
                        </div>
                        <div class="pull-right no-wrap">
                            <input type="text" class="form-control long-text-box"
                                   placeholder="Tìm kiếm theo mã thẻ, tên chủ thẻ"/>
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
                                    <th>Mã thẻ</th>
                                    <th>Chủ thẻ</th>
                                    <th>Lần cuối truy cập</th>
                                    <th>Trạng thái</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">FC3-DDA-4B6-773</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>8/9/2014 lúc 08:12:03</td>
                                    <td><span class="label label-danger">Ngưng hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="${pageContext.request.contextPath}/staff/card?action=detail">AC7-37F-E8E-DDC</a></td>
                                    <td>Đinh Quang Trung</td>
                                    <td>6/5/2015 lúc 10:23:30</td>
                                    <td><span class="label label-success">Hoạt động</span></td>
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
            </div>
        </div>
    </div>
</div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp"%>