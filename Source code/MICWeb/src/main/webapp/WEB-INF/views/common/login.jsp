<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Đăng nhập</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <style>
        label {
            width: 100%;
        }
    </style>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Đăng nhập</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="${pageContext.request.contextPath}/user" method="post">
                        <fieldset>

                            <c:if test="${not empty validateErrors}">
                                <div class="text-danger">
                                    <ul>
                                        <c:forEach var="error" items="${validateErrors}">
                                            <li>${error}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>

                            <div class="text-danger" id="authorize-failed" style="display: none">
                                <p>
                                    Bạn không có quyền truy cập vào trang này
                                </p>
                            </div>

                            <div class="form-group">
                                <label>Email hoặc mã số
                                    <input class="form-control" name="login:emailorcode"
                                           required autofocus title="Vui lòng nhập email hoặc mã khách hàng"
                                            value="${submitted.emailorcode}">
                                </label>
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu
                                    <input class="form-control" name="login:password" type="password"
                                           required title="Vui lòng nhập mật khẩu"
                                           value="${submitted.password}">
                                </label>
                            </div>
                            <div class="form-group">
                                <label>Đăng nhập với tư cách:
                                    <select id="selectRole" name="login:role" class="form-control">
                                        <option value="customer"
                                         ${submitted.role == "customer" ? "selected" : ""}>Khách hàng</option>
                                        <option value="staff"
                                         ${submitted.role == "staff" ? "selected" : ""}>Nhân viên MIC</option>
                                    </select>
                                </label>
                            </div>
                            <input type="hidden" id="redirectId" name="login:redirect" value="${submitted.redirect}"/>
                            <input type="hidden" name="action" value="login"/>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="submit" class="btn btn-lg btn-success btn-block">Đăng nhập</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script>
    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return false;
    }

    $(function () {

        // Fill redirect value
        var $redirect = $('#redirectId');
        if ($redirect.val() == "" && getQueryVariable('redirect')) {
            $redirect.val(getQueryVariable('redirect'));
        }


        // Show authorize message
        if (getQueryVariable("authorize")) {
            $('#authorize-failed').show();
        }

        // Auto select staff if possible
        var refrole = getQueryVariable("refrole");
        if (refrole) {
            $('#selectRole').val(refrole);
        }
    });
</script>

</body>

</html>
