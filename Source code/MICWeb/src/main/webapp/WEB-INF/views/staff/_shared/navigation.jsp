<%--
  Created by IntelliJ IDEA.
  User: dinhquangtrung
  Date: 5/23/15
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
  <div class="navbar-header">
    <button type="button"
            class="navbar-toggle"
            data-toggle="collapse"
            data-target=".navbar-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="index.html">Insurance Card</a>
  </div>
  <!-- /.navbar-header -->

  <ul class="nav navbar-top-links navbar-right">
    <!-- /.dropdown -->
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <i class="fa fa-user fa-fw"></i>
        Đinh Quang Trung
        <i class="fa fa-caret-down"></i>
      </a>
      <ul class="dropdown-menu dropdown-user">
        <li><a href="#"><i class="fa fa-user fa-fw"></i> Thông tin cá nhân</a>
        </li>
        <li class="divider"></li>
        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Đăng xuất</a>
        </li>
      </ul>
      <!-- /.dropdown-user -->
    </li>
    <!-- /.dropdown -->
  </ul>

  <div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
      <ul class="nav" id="side-menu">
        <li>
          <a href="${pageContext.request.contextPath}/staff"><i class="fa fa-dashboard fa-fw"></i> Thông tin chung</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/staff/customers"><i class="fa fa-users fa-fw"></i> Khách hàng</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/staff/cards"><i class="fa fa-credit-card fa-fw"></i> Thẻ đã phát hành</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/staff/new-card-requests"><i class="fa fa-exclamation fa-fw"></i> Yêu cầu cấp thẻ mới</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/staff/contracts"><i class="fa fa-file-text-o fa-fw"></i> Hợp đồng</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/staff/compensations"><i class="fa fa-gavel fa-fw"></i> Bồi thường</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/staff/staffs"><i class="fa fa-key fa-fw"></i> Người quản lý</a>
        </li>
      </ul>
    </div>
    <!-- /.sidebar-collapse -->
  </div>
  <!-- /.navbar-static-side -->
</nav>