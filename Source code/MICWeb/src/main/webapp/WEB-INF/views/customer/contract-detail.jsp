<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: PhucNguyen
  Date: 26/05/2015
  Time: 10:09 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<div id="wrapper">
    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">

            <div class="col-lg-12">
                <h2 class="page-header ">Hợp Đồng ${contract.contractCode}
                     <span class="pull-right">
                         <button type="submit" class="btn btn-info"><i class="fa fa-refresh"></i> Gia Hạn</button>
                         <button type="button" class="btn btn-danger" data-toggle="modal"
                                 data-target=".bs-example-modal-lg"><i class="fa fa-times"></i> Hủy Hợp Đồng
                         </button>

                     </span>
                </h2>
            </div>
            <form action="/customer/contract" method="post">
                <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel"
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
                                        <input type="radio" value="" name="rdbReason1" class="check" id="rdbReason1">
                                        Xe cơ giới bị thu hồi đăng ký
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="radio" value="" name="rdbReason2" id="rdbReason2" class="check">
                                        Xe cơ giới hết niên hạn
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="radio" value="" name="rdbAnother" id="rdbAnother" class="check">
                                        Lý do khác
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label></label>
                               <textarea name="txtAnotherReason" rows="3" cols="95" id="anotherReason" class="hide">
                               </textarea>

                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" name="action" value="CancelContract"/>
                                <input class="hide" name="txtReason" id="reason">
                                <input type="submit" class="btn btn-primary" name="Xác Nhận"/>
                                <input type="hidden" name="contractcode" value="${contract.contractCode}"/>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Hủy Bỏ</button>
                            </div>
                        </div>

                    </div>
                </div>
            </form>
        </div>

        <div class="form-horizontal">
            <c:if test="${contract.status.equalsIgnoreCase('Request cancel')}">
                <div class="well well-lg text-center text-danger">
                    Hợp đồng đã được yêu cầu hủy vui lòng chờ xác nhận của nhân viên
                </div>
            </c:if>
            <fieldset>
                <legend>
                    Thông tin chung
                </legend>
            </fieldset>
            <div class="form-group">
                <label class="col-sm-5 text-right control-label">Mã hợp đồng</label>

                <div class="col-sm-5">
                    <div class="text-value">
                        ${contract.contractCode}

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-5 text-right control-label">Ngày Tham Gia</label>

                <div class="col-sm-5">
                    <div class="text-value ">
                        ${contract.startDate}

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-5 text-right control-label">Ngày Hết Hạn</label>

                <div class="col-sm-5">
                    <div class="text-value">
                        ${contract.expiredDate}
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-5 text-right control-label">Quyền Lợi Bảo Hiểm</label>

                <div class="col-sm-5">
                    <div class="text-value ">
                        Trên 50 CC có bảo hiểm
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-5 text-right control-label"> Tình Trạng Hợp Đồng</label>

                <div class="col-sm-5">
                    <div class="text-value ">
                        ${contract.status}

                    </div>
                </div>
            </div>
        </div>

        <div class="form-horizontal">
            <fieldset>
                <legend>
                    Thông tin xe
                </legend>
            </fieldset>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Biển Số Đăng Ký: </label>

                    <div class="col-sm-8">
                        <div class="text-value ">
                            ${contract.plate}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Loại Xe:</label>

                    <div class="col-sm-8">
                        <div class="text-value ">
                            ${contract.vehicleType}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Màu Sơn:</label>

                    <div class="col-sm-8">
                        <div class="text-value ">
                            ${contract.color}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Số Máy:</label>

                    <div class="col-sm-8">
                        <div class="text-value ">
                            ${contract.engine}
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Nhãn Hiệu: </label>

                    <div class="col-sm-8">
                        <div class="text-value ">
                            ${contract.modelCode}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Dung Tích:</label>

                    <div class="col-sm-8">
                        <div class="text-value ">
                            ${contract.capacity}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Năm Sản Xuất:</label>

                    <div class="col-sm-8">
                        <div class="text-value ">
                            ${contract.yearOfManufacture}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Số Khung:</label>

                    <div class="col-sm-8">
                        <div class="text-value ">
                            ${contract.chassis}
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-12">
            <h2 class="page-header ">Thông Tin Khác

            </h2>
        </div>
        <div role="tabpanel">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#compensations" aria-controls="profile" role="tab" data-toggle="tab">Lịch sử bồi thường</a>
                </li>
                <li role="presentation">
                    <a href="#punishments" aria-controls="messages" role="tab" data-toggle="tab">Lịch sử vi phạm luật
                        GT</a>
                </li>
            </ul>
        </div>
        <br/>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="compensations">
                <div class="row">
                    <div class="col-lg-12">
                          <span class="pull-right">
                              <a href="${pageContext.request.contextPath}/customer/compensation?action=create"
                                 class="btn btn-primary">Yêu Cầu Bồi Thường</a>
                          </span>
                    </div>

                </div>

                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <div class="pull-left">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có 200 Vụ Bồi Thường</b>
                        </div>
                        <div class="pull-right ">
                            <input type="text" class="form-control long-text-box"
                                   placeholder="Tìm kiếm theo tên, mã hợp đồng"/>
                            <input type="button" class="btn btn-default" value="Tìm kiếm"/>

                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel panel-body">
                        <div class="table table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr class="success">
                                    <th class="text-center ">#
                                    </th>
                                    <th class="text-center ">Mã yêu Cầu
                                    </th>
                                    <th class="text-center ">Mã Hợp Đồng
                                    </th>
                                    <th class="text-center ">Tên Khách Hàng
                                    </th>
                                    <th class="text-center ">Ngày Yêu Cầu
                                    </th>
                                    <th class="text-center ">Ngày Giải Quyết
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="active">
                                    <td class="text-center">1</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span class="label label-warning">Đang giải quyết</span>
                                    </td>
                                </tr>
                                <tr class="">
                                    <td class="text-center">2</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span class="label label-warning">Đang giải quyết</span>
                                    </td>
                                </tr>
                                <tr class="active">
                                    <td class="text-center">3</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span class="label label-warning">Đang giải quyết</span>
                                    </td>
                                </tr>
                                <tr class="">
                                    <td class="text-center">4</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span class="label label-warning">Đang giải quyết</span>
                                    </td>
                                </tr>
                                <tr class="active">
                                    <td class="text-center">5</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span>10/10/2015</span></td>
                                </tr>
                                <tr class="">
                                    <td class="text-center">6</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span>10/10/2015</span></td>
                                </tr>
                                <tr class="active">
                                    <td class="text-center">7</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span>10/10/2015</span></td>
                                </tr>
                                <tr class="">
                                    <td class="text-center">8</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span>10/10/2015</span></td>
                                </tr>
                                <tr class="active">
                                    <td class="text-center">9</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span>10/10/2015</span></td>
                                </tr>
                                <tr class="">
                                    <td class="text-center">10</td>
                                    <td class="text-center"><a
                                            href="${pageContext.request.contextPath}/customer/compensation?action=detail">COM736</a>
                                    </td>
                                    <td class="text-center"><a href="detail-contract.html">BHBB374</a></td>
                                    <td class="text-center"><a href="detail-customer.html">Đinh Quang Trung</a></td>
                                    <td class="text-center">04/05/2015</td>
                                    <td class="text-center"><span>10/10/2015</span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-footer">
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


                    </div>
                </div>


            </div>

            <div role="tabpanel" class="tab-pane" id="punishments">

                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <div class="pull-left">
                            <!--<input type="checkbox" class="check-all"/>-->
                            <b>Có 10 trường hợp vi phạm</b>
                        </div>
                        <div class="pull-right ">
                            <input type="text" class="form-control long-text-box"
                                   placeholder="Tìm kiếm theo tên, mã hợp đồng"/>
                            <input type="button" class="btn btn-default" value="Tìm kiếm"/>

                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel panel-body">

                        <div class="table table-responsive">
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <th class="col-md-2 text-center">
                                        Ngày Vi Phạm
                                    </th>
                                    <th class="col-md-9 text-center">
                                        Nội Dung Vi Phạm
                                    </th>
                                    <th class="col-md-1 text-center">
                                        Biên Bản
                                    </th>
                                </tr>
                                </tbody>
                                <tbody>
                                <% for (int i = 1; i <= 10; i++) {
                                    out.print("   <tr>\n" +
                                            "                            <td class=\"text-center\">\n" +
                                            "                                12/02/2015\n" +
                                            "                            </td>\n" +
                                            "                            <td class=\"text-left\">\n" +
                                            "                                Sử dụng Giấy đăng ký xe bị tẩy xóa; Không đúng số khung, số máy hoặc không do cơ quan có\n" +
                                            "                                thẩm quyền cấp.\n" +
                                            "                                Phạt tiền từ 300.000 đến 400.000. Đồng thời tịch thu Giấy đăng ký không hợp lệ\n" +
                                            "                            </td>\n" +
                                            "                            <td class=\"text-center\">\n" +
                                            "                                <a href=\"#\" id=\"showPunishment_" + i + "\" class=\"fa-lg\"><i class=\"fa fa-file-image-o\"></i></a>\n" +
                                            "                            </td>\n" +

                                            "     </tr> ");
                                }

                                %>

                                </tbody>
                            </table>

                        </div>
                    </div>
                    <div class="panel panel-footer">
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
                </div>

            </div>
        </div>
    </div>


</div>
<!-- /#page-wrapper -->


</div>
<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp" %>

