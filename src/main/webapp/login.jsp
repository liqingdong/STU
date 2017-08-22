<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>XX-管理平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/common/jsp/common.jsp" %>
    <link rel="stylesheet" href="${ctx}/common/css/login.css">
</head>
<body>
<div class="login login_box">
    <div class="message" title="XX-管理平台"></div>
    <div id="darkbannerwrap"></div>
    <form id="loginForm" action="${ctx}/j_spring_security_check" method="post">
        <input id="j_username" name="j_username" placeholder="用户名" type="text"><hr class="hr15">
        <input id="j_password" name="j_password" placeholder="密码" type="password"><hr class="hr15">
        <input name="yzm" placeholder="验证码" type="text" class="yzm_text">
        <img alt="请点击刷新验证码" title="请点击刷新验证码" class="yzm_img"><hr class="hr15">
        <label class="fl" for="check_input">
            <input id="check_input" name="_spring_security_remember_me" type="checkbox" class="check_input">记住账号</label>
        <a class="fr" href="${ctx}/retrievePassword/index.shtml">忘记密码？</a><hr class="hr15">
        <input value="登录" style="width:100%;" type="button" onclick="login()">
    </form>
    <div class="error_text">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        document.onkeydown = function (e) {
            var ev = (typeof event != 'undefined') ? window.event : e;
            if (ev.keyCode == 13) {
                login();
            }
        };
        yzm();
        $(".yzm_img").on("click", function () {
            yzm();
        });
    });

    function yzm() {
        var params = {
            width: "130",
            height: "60"
        };
        $.ajax({
            url: '${ctx}/randomCode.do',
            type: 'POST',
            dataType: 'json',
            data: params,
            success: function (data) {
                $(".yzm_img").attr("src", "data:image/png;base64," + data.imageUrl);
                $(".yzm_img").attr("name", data.imageCont);
            }
        })
    }
    function login() {
        if (!$("#j_username").val()) {
            alert('请输入用户名');
            return;
        }
        if (!$("#j_password").val()) {
            alert('请输入密码');
            return;
        }
//        if (!$(".yzm_text").val()) {
//            alert('请输入验证码');
//            return;
//        }
//        if ($(".yzm_text").val() != $(".yzm_img").attr("name")) {
//            alert('验证码不正确');
//            yzm();
//            return;
//        }
        $("#loginForm").submit();
    }
</script>
</html>
