<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/common/jsp/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/common/css/pagination.css"/>
    <script src="${ctx}/common/js/jquery.twbsPagination.js"></script>
    <script src="${ctx}/common/js/pageGrid.js"></script>
</head>
<body>
<div class="site-new-body">
    <div class="layui-form-item">
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal" href="javascript:void(0)" data-type="add"><i class="layui-icon"></i>新增</a>
            <a class="layui-btn" href="javascript:void(0)" data-type="edit"><i class="layui-icon"></i>修改</a>
            <a class="layui-btn layui-btn-danger" href="javascript:void(0)" data-type="remove"><i class="layui-icon"></i>删除</a>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input name="username" lay-verify="" autocomplete="off" placeholder="请输入登录账号"
                       class="layui-input" type="text">
            </div>
            <div class="layui-input-inline">
                <input name="name" lay-verify="" autocomplete="off" placeholder="请输入用户姓名" class="layui-input"
                       type="text">
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn" data-type="search">查询</button>
            </div>
        </div>
    </div>
    <div id="pageGrid"></div>
</div>
</body>
<script>
    layui.use(["layer", "form"], function () {
        var $ = layui.jquery, layer = layui.layer;
        var grid = pageGrid({
            columns: [
                {field: "username", name: "登录账号"},
                {field: "name", name: "用户姓名"},
                {
                    field: "sex", name: "性别", renderData: function (rowindex, data, rowdata, colkey) {
                    if (data == "1") {
                        return "男";
                    } else if (data == "2") {
                        return "女";
                    } else {
                        return "未知";
                    }
                }
                },
                {field: "tel", name: "手机号码"},
                {field: "email", name: "电子邮箱"},
                {
                    field: "status", name: "状态", renderData: function (rowindex, data, rowdata, colkey) {
                    return data == "1" ? "有效" : "无效";
                }
                },
                {
                    field: "", name: "操作", renderData: function (rowindex, data, rowdata, colkey) {
                    return "<a href='javascript:void(0);' data-id=" + rowdata.id + " data-type='info' class='layui-btn layui-btn-mini layui-btn-danger'>查看</a>" +
                        "<button class='layui-btn layui-btn-mini sq_btn' data-id=" + rowdata.id + " data-type='grantRole'>授权角色</button>"
                }
                },
            ],
            jsonUrl: '${ctx}/user/queryUsers.do',
            checkbox: true,
            index: true,
            fixed:true
        });
        grid.load();

        var active = {
            search: function () {
                var data = {
                    username: $('input[name="username"]').val(),
                    name: $('input[name="name"]').val()
                };
                grid.load({data: data});
            },
            add: function () {
                location.href = "edit.shtml";
            },
            edit: function () {
                var datas = grid.getSelecteds();
                if (datas == null || datas.length == 0) {
                    layer.open({
                        title: '系统提示'
                        ,content: '请先选择一条记录!'
                        ,closeBtn: 0
                        ,icon: 6
                    });
                } else if (datas.length > 1) {
                    layer.open({
                        title: '系统提示'
                        ,content: '只能选择一条记录修改!'
                        ,closeBtn: 0
                        ,icon: 6
                    });
                } else {
                    var id = datas[0].id;
                    location.href = "edit.shtml?id=" + id;
                }
            },
            operation: function () {
                var datas = grid.getSelecteds();
                if (datas == null || datas.length == 0) {
                    layer.open({
                        title: '系统提示'
                        ,content: '请先选择记录!'
                        ,closeBtn: 0
                        ,icon: 6
                    });
                } else {
                    var ope = this.innerText, msg = "";
                    if (ope.indexOf("启用") != "-1") {
                        msg = "确定要启用这些用户?";
                        layer.confirm(msg, {icon: 3,title: '提示',closeBtn: 0}, function (index) {
                            var ids = [];
                            for (var i = 0; i < datas.length; i++) {
                                ids.push(datas[i].id);
                            }
                            $.ajax({
                                url: "${ctx}/user/enable.do",
                                traditional: "true",
                                data: {"ids": ids},
                                type: "POST",
                                success: function (data) {
                                    if (data == "success") {
                                        layer.open({
                                            title: '系统提示'
                                            ,content: '操作成功!'
                                            ,closeBtn: 0
                                            ,icon: 6
                                            ,yes:function(index){
                                                grid.reload();
                                                layer.close(index);
                                            }
                                        });
                                    }
                                },
                                error: function () {
                                    layer.alert("出错了!");
                                }
                            });
                            layer.close(index);
                        });
                    } else {
                        msg = "确定要禁用这些用户?";
                        layer.confirm(msg, {icon: 3,title: '提示',closeBtn: 0}, function (index) {
                            var ids = [];
                            for (var i = 0; i < datas.length; i++) {
                                if (datas[i].canDelete == 1) {
                                    ids.push(datas[i].id);
                                } else {
                                    layer.open({
                                        title: '系统提示'
                                        ,content: datas[i].username + "用户不能被禁用!"
                                        ,closeBtn: 0
                                        ,icon: 5
                                    });
                                    return;
                                }
                            }
                            $.ajax({
                                url: "${ctx}/user/disable.do",
                                traditional: "true",
                                data: {"ids": ids},
                                type: "POST",
                                success: function (data) {
                                    if (data == "success") {
                                        layer.open({
                                            title: '系统提示'
                                            ,content: '操作成功!'
                                            ,closeBtn: 0
                                            ,icon: 6
                                            ,yes:function(index){
                                                grid.reload();
                                                layer.close(index);
                                            }
                                        });
                                    }
                                },
                                error: function () {
                                    layer.alert("出错了!");
                                }
                            });
                            layer.close(index);
                        });
                    }
                }
            },
            grantRole: function (othis) {
                layer.open({
                    type: 2,
                    title: '授权角色',
                    area: ['700px', '350px'],
                    shade: 0.1,
                    content: 'sqjs.shtml?userId=' + othis.attr("data-id")
                })
            },
            info: function (othis) {
                location.href = 'edit.shtml?id=' + othis.attr("data-id")+"&flag=1";

            },

        };
        $('.layui-btn').live('click', function () {
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });
    });
</script>
</html>
