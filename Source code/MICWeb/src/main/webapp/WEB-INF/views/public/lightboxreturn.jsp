<%@ page import="java.util.Map" %>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";

    url = url + "/public/return?action=return&page=return&";


    Map<String, String[]> parameters = request.getParameterMap();
    for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
        url += entry.getKey() + "=" + request.getParameter(entry.getKey()) + "&";

    }
    System.out.println(url);
%>
<html>
<head>

    <title>Mic- Thẻ Bảo Hiểm</title>
    <!--Including Bootstrap style files-->

</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span4">
        </div>
        <div class="span5">
            <div class="row text-center"><h3>Loading...</h3></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    if (window != top) {
        top.location.href = '<%=url%>';
    } //lightbox return
    else
        window.location.href = '<%=url%>'; //return from full page paypal flow
</script>
</body>
</html>