<%@ page import="java.util.Date" %>
<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header text-info">Lịch Sử Vi Phạm Luật ATGT
                </h2>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel panel-heading">
                <div class="pull-left">
                    <!--<input type="checkbox" class="check-all"/>-->
                    <b>Có 173 hợp đồng</b>
                </div>
                <div class="pull-right ">
                    <input type="text" class="form-control long-text-box"
                           placeholder="Tìm kiếm theo tên, mã hợp đồng"/>
                    <input type="button" class="btn btn-default" value="Tìm kiếm"/>

                </div>
                <div class="clearfix"></div>
            </div>
            <div class="panel panel-body">
                <div class="table-responsive">
                    <table class="table table-hover table-striped">
                        <tbody>
                        <tr>
                            <th>
                                Ngày Vi Phạm
                            </th>
                            <th>
                                Nội Dung Vi Phạm
                            </th>
                            <th>
                                Biên Bản
                            </th>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td>
                                <%= new Date() %>
                            </td>
                            <td>
                                AAAAAAAAAAAAAAAAAA
                            </td>
                            <td>

                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="panel panel-footer">

            </div>
        </div>

    </div>
</div>
<!-- /#page-wrapper -->

<%@ include file="_shared/footer.jsp" %>
