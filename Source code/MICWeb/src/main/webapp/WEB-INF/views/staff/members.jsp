<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Người quản lý
                    <div class="pull-right">
                        <a href="${pageContext.request.contextPath}/staff/member?action=create" class="btn btn-success">
                            <i class="fa fa-plus"></i>
                            Thêm nhân viên
                        </a>
                    </div>
                </h1>
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
                    <b>Có 12 nhân viên trong hệ thống</b>
                </div>
                <div class="pull-right no-wrap">
                    <input type="text" class="form-control long-text-box"
                           placeholder="Tìm kiếm theo tên, mã nhân viên, email"/>
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
                            <th><input type="checkbox"/></th>
                            <th>#</th>
                            <th>Mã nhân viên</th>
                            <th>Tên nhân viên</th>
                            <th>Email</th>
                            <th>Số điện thoại</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>STAFF01</td>
                            <td>Đinh Quang Trung</td>
                            <td>trungdq88@gmail.com</td>
                            <td>0987010238</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->

                <p>
                    <a href="#" class="btn btn-danger">
                        <i class="fa fa-trash"></i>
                        Xoá nhân viên
                    </a>
                </p>

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
<!-- /#wrapper -->



<%@ include file="_shared/footer.jsp"%>