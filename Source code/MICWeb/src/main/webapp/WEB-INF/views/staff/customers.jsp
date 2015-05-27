<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp"%>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Khách hàng

                    <div class="pull-right">
                        <a href="${pageContext.request.contextPath}/staff/customer?action=create" class="btn btn-success">
                            <i class="fa fa-plus"></i>
                            Khách hàng mới
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
                    <b>Có 173 khách hàng</b>
                </div>
                <div class="pull-right no-wrap">
                    <input type="text" class="form-control long-text-box"
                           placeholder="Tìm kiếm theo tên, mã hợp đồng, mã thẻ"/>
                    <input type="button" class="btn btn-default" value="Tìm kiếm"/>
                    <!--<div class="btn-group">-->
                        <!--<button type="button" class="btn btn-default dropdown-toggle"-->
                                <!--data-toggle="dropdown"-->
                                <!--aria-expanded="false">-->
                            <!--Tên <span class="caret"></span>-->
                        <!--</button>-->
                        <!--<ul class="dropdown-menu left-grow" role="menu">-->
                            <!--<li>-->
                                <!--<label>Tên:-->
                                    <!--<input type="text" class="form-control typeahead"/>-->
                                <!--</label>-->
                            <!--</li>-->
                            <!--<li class="divider"></li>-->
                            <!--<li><a href="#">Action</a></li>-->
                            <!--<li><a href="#">Another action</a></li>-->
                            <!--<li><a href="#">Something else here</a></li>-->
                        <!--</ul>-->
                    <!--</div>-->
                    <!--<div class="btn-group">-->
                        <!--<button type="button" class="btn btn-default dropdown-toggle"-->
                                <!--data-toggle="dropdown"-->
                                <!--aria-expanded="false">-->
                            <!--Số điện thoại <span class="caret"></span>-->
                        <!--</button>-->
                        <!--<ul class="dropdown-menu left-grow" role="menu">-->
                            <!--<li>-->
                                <!--<label>Số điện thoại:-->
                                    <!--<input type="text" class="form-control typeahead"/>-->
                                <!--</label>-->
                            <!--</li>-->
                            <!--<li class="divider"></li>-->
                            <!--<li><a href="#">Action</a></li>-->
                            <!--<li><a href="#">Another action</a></li>-->
                            <!--<li><a href="#">Something else here</a></li>-->
                        <!--</ul>-->
                    <!--</div>-->
                    <!--<div class="btn-group">-->
                        <!--<button type="button" class="btn btn-default dropdown-toggle"-->
                                <!--data-toggle="dropdown"-->
                                <!--aria-expanded="false">-->
                            <!--Mã hợp đồng <span class="caret"></span>-->
                        <!--</button>-->
                        <!--<ul class="dropdown-menu left-grow" role="menu">-->
                            <!--<li>-->
                                <!--<label>Mã hợp đồng:-->
                                    <!--<input type="text" class="form-control typeahead"/>-->
                                <!--</label>-->
                            <!--</li>-->
                            <!--<li class="divider"></li>-->
                            <!--<li><a href="#">Action</a></li>-->
                            <!--<li><a href="#">Another action</a></li>-->
                            <!--<li><a href="#">Something else here</a></li>-->
                        <!--</ul>-->
                    <!--</div>-->
                    <!--<div class="btn-group">-->
                        <!--<button type="button" class="btn btn-default dropdown-toggle"-->
                                <!--data-toggle="dropdown"-->
                                <!--aria-expanded="false">-->
                            <!--Mã thẻ <span class="caret"></span>-->
                        <!--</button>-->
                        <!--<ul class="dropdown-menu left-grow" role="menu">-->
                            <!--<li>-->
                                <!--<label>Mã thẻ:-->
                                    <!--<input type="text" class="form-control typeahead"/>-->
                                <!--</label>-->
                            <!--</li>-->
                            <!--<li class="divider"></li>-->
                            <!--<li><a href="#">Action</a></li>-->
                            <!--<li><a href="#">Another action</a></li>-->
                            <!--<li><a href="#">Something else here</a></li>-->
                        <!--</ul>-->
                    <!--</div>-->
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
                            <th>Mã KH</th>
                            <th>Tên</th>
                            <th>Số điện thoại</th>
                            <th>Mã hợp đồng</th>
                            <th>Mã thẻ</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td><a href="${pageContext.request.contextPath}/staff/customer?action=detail">HDTRU839</a></td>
                            <td>Đinh Quang Trung</td>
                            <td>0987.654.321</td>
                            <td><a href="#">0123654789</a></td>
                            <td><a href="#">AC7-37F-E8E-DDC</a></td>
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
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp"%>