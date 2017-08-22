<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/jsp/common.jsp" %>
</head>
<body class="gray-bg">
<div class="middle-box">

    <h1>500</h1>

    <h3 class="font-bold">服务器内部错误</h3>

    <div class="error-desc">
        服务器好像出错了...
        <br/>您可以返回主页看看
        <br/><br/>
        <a class="layui-btn" href="javascript:;" onclick="javascript:top.location = '${ctx}'">首页</a>
    </div>
</div>
</body>
</html>