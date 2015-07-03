<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">

    <c:set var="staff" value="${requestScope.PROFILE}" scope="request"/>

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    ${staff.staffCode}
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal">
                    <fieldset>
                        <legend>Thông tin cá nhân
                            <div class="pull-right" style="margin-top: -5px;">
                                <button type="button" class="btn btn-xs btn-primary"
                                        data-toggle="modal" data-target="#edit-profile-modal">
                                    <i class="fa fa-pencil"></i> Chỉnh sửa
                                </button>
                            </div>
                        </legend>

                        <!-- Staff name & Staff code -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Họ tên</label>

                            <div class="col-sm-8">
                                <div class="text-value">
                                    <strong>${staff.name}</strong>
                                </div>
                            </div>
                        </div>

                        <!-- Staff email -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Email</label>

                            <div class="col-sm-8">
                                <div class="text-value">
                                    ${staff.email}
                                </div>
                            </div>
                        </div>

                        <!-- Email & personal ID -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Số điện thoại</label>

                            <div class="col-sm-2">
                                <div class="text-value">
                                    ${staff.phone}
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Profile information--%>
                    <br/>
                </form>
                <br/>
                <br/>
            </div>
            <!-- col -->
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>