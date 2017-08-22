<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/jsp/common.jsp" %>
</head>
<body class="gray-bg">
<div class="middle-box">

    <h1>403</h1>

    <h3 class="font-bold">您的请求被拒绝</h3>

    <div class="error-desc">
        <br/>您可以返回主页看看
        <br/><br/>
        <a class="layui-btn" href="javascript:;" onclick="javascript:top.location = '${ctx}'">首页</a>
    </div>
</div>
</body>
</html>