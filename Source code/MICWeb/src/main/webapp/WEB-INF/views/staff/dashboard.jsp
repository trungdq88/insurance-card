
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thông báo (${unreadCount})</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <c:choose>
                            <c:when test="${notifications.size() == 0}">
                                <tr>
                                    <td colspan="6" style="vertical-align: middle; text-align: center;">
                                        Không có thông báo mới nào
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="notification" items="${notifications}" varStatus="counter">
                                    <tr class="notif-item" data-id="${notification.id}"
                                        data-is-read="${notification.isRead != null ? 'true' : 'false'}">
                                        <td>
                                            <a class="notif-link" href="/notif?action=markAsRead&id=${notification.id}&redirect=true">
                                                <i class="fa fa-bell-o"></i> ${notification.content}
                                            </a>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${notification.createdDate}" pattern="dd/MM/yyyy"/>
                                            lúc
                                            <fmt:formatDate value="${notification.createdDate}" type="time"/>
                                        </td>
                                        <td>
                                            <a class="notif-control mark-as-read" href="javascript:markAsRead(${notification.id})"
                                               data-toggle="tooltip" title="Đánh dấu đã đọc">
                                                <i class="fa fa-check"></i>
                                            </a>
                                            <a class="notif-control mark-as-unread" href="javascript:markAsUnread(${notification.id})"
                                               data-toggle="tooltip" title="Đánh dấu chưa đọc">
                                                <i class="fa fa-bell"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thông tin chung</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-sm-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-file-text fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">26</div>
                                <div>Hợp đồng mới</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">Xem danh sách</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-green">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-gavel fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">12</div>
                                <div>Yêu cầu bồi thường</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">Xem danh sách</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-yellow">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-exclamation fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">2</div>
                                <div>Yêu cầu cấp thẻ mới</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">Xem danh sách</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-red">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-exclamation-circle fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">1</div>
                                <div>Huỷ hợp đồng</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">Xem danh sách</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>

        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>