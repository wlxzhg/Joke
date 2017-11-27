<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/19
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getContextPath();
%>
<html>
<head>
    <title>笑话</title>
</head>
<body>
    <%--输入id:<input type="text" id="joke-id">--%>
    <input id="min-id" type="hidden" value="${minId}">
    <input id="max-id" type="hidden" value="${maxId}">
    <button id="joke-ok">讲个笑话</button>
    <p id="joke-p"></p>
</body>
<script type="text/javascript" src="<%=basePath%>/static/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $(function() {
        $('#joke-ok').on('click',function() {
            var min = $('#min-id').val();
            var range = $('#max-id').val() - min;
            var id = Math.round(min + Math.random() * range);
            $.ajax('query/joke/' + id,{
                dataType:'JSON'
            }).done(function(data) {
                $('#joke-p').html(data.joke);
            })
        });
    });
</script>
</html>
