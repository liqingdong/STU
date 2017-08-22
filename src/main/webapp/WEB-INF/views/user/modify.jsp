<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/common/jsp/common.jsp" %>
</head>
<body>
<form class="layui-form" action="modify.do" method="post">

    <div style="padding:30px 0 0 20px">
        <div class="layui-form-item">
            <label class="layui-form-label"><span class="red">*</span>原密码</label>
            <div class="layui-input-inline">
                <input name="" lay-verify="required|password|validate" autocomplete="off"
                       class="layui-input" type="password" value="">
            </div>
            <div class="layui-form-mid layui-word-aux">请填写6到20位密码</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span class="red">*</span>新密码</label>
            <div class="layui-input-inline">
                <input name="password" lay-verify="required|password" autocomplete="off"
                       class="layui-input" type="password" value="">
            </div>
            <div class="layui-form-mid layui-word-aux">请填写6到20位密码</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span class="red">*</span>确认密码</label>
            <div class="layui-input-inline">
                <input name="" lay-verify="required|confirm" autocomplete="off"
                       class="layui-input" type="password" value="">
            </div>
            <div class="layui-form-mid layui-word-aux">请填写6到20位密码</div>
        </div>
    </div>
    <div class="layui-form-item set_bar tc" style="position: fixed; bottom: 0;width: 100%;">
        <button class="layui-btn" lay-submit="" lay-filter="save">保存</button>
        <button class="layui-btn event_btn" data-type='cancel'>取消</button>
    </div>
</form>
<script>
    layui.use('form', function () {
        var form = layui.form();
        form.verify({
            validate: function (value) {
                var flag = true;
                $.ajax({
                    url: "${ctx}/user/validate.do",
                    type: "POST",
                    async: false,
                    data: {password: value},
                    success: function (data) {
                        if (data.success = false) {
                            flag = false;
                        }
                    }
                });
                if (flag == false) {
                    return "原密码不正确!";
                }
            },
            password: function (value) {
                if (/[\u4e00-\u9fa5]{1,}/.test(value)) {
                    return "密码不能包含中文";
                }
                if (!/^[\s\S]{6,20}$/.test(value)) {
                    return "密码必须是6到20位";
                }
            },
            confirm: function (value) {
                var pwd = $("input[name='password']").val();
                if (value != pwd) {
                    return "两次输入密码不一致";
                }
            }
        });
        form.on('submit(save)', function () {
            var password = $("input[name='password']").val();
            $.ajax({
                url: "${ctx}/user/saveChange.do",
                type: "POST",
                traditional: "true",
                data: {password: password},
                success: function (data) {
                    if (data.success = true) {
                        layer.open({
                            title: '系统提示',
                            content: '密码修改成功!',
                            closeBtn: 0,
                            yes: function () {
                                parent.layer.closeAll();
                                location.href = '${ctx}/j_spring_security_logout';
                            }
                        });
                    }
                },
                error: function () {
                    layer.open({
                        title: '系统提示',
                        closeBtn: 0,
                        content: '系统错误!'
                    });
                }
            });
            return false;
        });
        var active = {
            cancel: function (othis) {
                parent.layer.closeAll();
            }
        };

        $('.event_btn').on('click', function () {
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });
    })
</script>
</body>
</html>
