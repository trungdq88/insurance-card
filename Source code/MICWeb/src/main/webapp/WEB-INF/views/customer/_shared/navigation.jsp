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
    <a class="navbar-brand" href="${pageContext.request.contextPath}/customer">
      Insurance Card
    </a>
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
          <a href="${pageContext.request.contextPath}/customer">
            <i class="fa fa-user fa-fw"></i> Thông tin cá nhân</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/contract">
            <i class="fa fa-file-text-o fa-fw"></i> Hợp đồng</a>
        </li>
        <%--<li>--%>
          <%--<a href="${pageContext.request.contextPath}/customer/punishment">Lịch Sử Vi Phạm</a>--%>
        <%--</li>--%>
        <%--<li>--%>
          <%--<a href="${pageContext.request.contextPath}/customer/compensation">Lịch Sử Bồi Thường </a>--%>
        <%--</li>--%>
        <li>
          <a href="${pageContext.request.contextPath}/customer/card">
            <i class="fa fa-credit-card fa-fw"></i> Thông tin thẻ</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/card?action=newCard">
            <i class="fa fa-exclamation fa-fw"></i> Yêu cầu thẻ mới</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/customer/payment">
            <i class="fa fa-dollar fa-fw"></i> Lịch sử giao dịch</a>
        </li>
      </ul>
    </div>
    <!-- /.sidebar-collapse -->
  </div>
  <!-- /.navbar-static-side -->
</nav>