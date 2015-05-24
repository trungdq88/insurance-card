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
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/customer"><label class="text-info">Insurance Card</label></a>
  </div>
  <!-- /.navbar-header -->

  <ul class="nav navbar-top-links navbar-right">
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <i class="fa fa-user fa-fw"></i>
        Phúc Nguyễn
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
  <!-- /.navbar-top-links -->

  <div class="navbar-default sidebar" role="navigation">

    <div class="sidebar-nav navbar-collapse">
      <ul class="nav" id="side-menu">
        <li>
          <a href="${pageContext.request.contextPath}/customer">Thông Tin Cá Nhân</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/contract">Thông Tin Bảo Hiểm</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/punishment">Lịch Sử Vi Phạm</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/compensation">Lịch Sử Bồi Thường </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/card">Thông Tin Thẻ</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/card?action=newCard">Yêu Cầu Thẻ Mới</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/payment">Lịch Sử Giao Dịch</a>
        </li>
      </ul>
    </div>
    <!-- /.sidebar-collapse -->
  </div>
  <!-- /.navbar-static-side -->
</nav>