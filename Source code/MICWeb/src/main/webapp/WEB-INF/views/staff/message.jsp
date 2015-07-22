<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <c:if test="${requestScope.SUCCESS}">
        <meta http-equiv="refresh"
              content="3;url=${pageContext.request.contextPath}/staff/contract?action=detail&code=${requestScope.CODE}"/>
    </c:if>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group">
                    <br/> <br/>
                    <div class="text-center alert alert-info">
                        <p class="bs-example">${requestScope.MESSAGE}</p>
                    </div>
                    <br/>
                    <c:if test="${not empty requestScope.CODE}">
                        <div class="text-center">
                            <a href="${pageContext.request.contextPath}/staff/contract?action=detail&code=${requestScope.CODE}"
                               type="button" class="btn btn-success">
                                <i class="fa fa-arrow-left"></i> Quay về trang chi tiết hợp đồng
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
</div>
<!-- /#wrapper -->

<%@ include file="_shared/footer.jsp" %>