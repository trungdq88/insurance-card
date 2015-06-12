<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/staff/contract?action=detail&code=${requestScope.CODE}"/>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group">
                    <br/>
                    <br/>
                    <div class="text-center alert alert-info">
                        <p class="bs-example text-capitalize">
                            ${requestScope.MESSAGE}
                        </p>
                    </div>

                    <br/>
                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/staff/contract?action=detail&code=${requestScope.CODE}"
                           type="button" class="btn btn-success">
                            <i class="fa fa-arrow-left"></i>
                            Quay về trang thông tin khách hàng
                        </a>
                    </div>
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
</div>
</div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>