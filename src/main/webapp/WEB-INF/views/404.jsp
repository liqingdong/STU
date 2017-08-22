<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/jsp/common.jsp" %>
</head>
<body class="gray-bg">
<div class="middle-box">
    <h1>404</h1>

    <h3 class="font-bold">页面未找到！</h3>

    <div class="error-desc">
        抱歉，页面好像去火星了~
        <br/><br/>
        <a class="layui-btn" href="javascript:void(0);" onclick="javascript:top.location = '${ctx}'">首页</a>
    </div>
</div>
</body>
</html>