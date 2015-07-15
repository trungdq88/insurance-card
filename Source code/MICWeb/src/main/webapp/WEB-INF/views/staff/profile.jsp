<%--
  Created by IntelliJ IDEA.
  User: TriPQM
  Date: 07/02/2015
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thông tin nhân viên
                    <div class="pull-right">
                        <a href="${pageContext.request.contextPath}/staff/profile?action=viewEditProfile" class="btn btn-primary">
                            <i class="fa fa-edit"></i>
                            Sửa thông tin
                        </a>
                    </div>
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <c:if test="${param.info eq 'success'}">
            <div class="text-success">
                Sửa thông tin thành công
            </div>
        </c:if>
        <c:if test="${param.info eq 'fail'}">
            <div class="text-danger">
                Có lỗi xảy ra. Xin thử lại
            </div>
            </c:if>
        <div class="row">
            <div class="col-lg-12">
                <br/>

                <form class="form-horizontal">
                    <fieldset>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" >Mã nhân viên :</label>

                            <div class="col-sm-6">
                                <p class="text-value">
                <span class="label label-info"
                      style="font-size: 16px">${staff.staffCode}</span>
                                </p>

                            </div>
                        </div>
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" >Họ tên :</label>

                            <div class="col-sm-6">
                                <p class="text-value">
                                    ${staff.name}
                                </p>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" >Email :</label>

                            <div class="col-sm-6">
                                <p class="text-value">
                                    ${staff.email}
                                </p>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" >Số điện thoại :</label>

                            <div class="col-sm-6">
                                <p class="text-value">
                                    ${staff.phone}
                                </p>
                            </div>
                        </div>
                    </fieldset>
                </form>

                <div class="text-left">

                    <a href="${pageContext.request.contextPath}/staff" type="button"
                       class="btn btn-success">
                        <i class="fa fa-arrow-left"></i>
                        Trở về trang chủ
                    </a>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp" %>
