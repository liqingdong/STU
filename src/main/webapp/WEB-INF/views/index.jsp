<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>XX-管理平台</title>
    <link rel="stylesheet" href="${ctx}/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/common/css/common.css">
    <script src="${ctx}/layui/layui.js"></script>
    <style type="text/css">
        .layui-tab .notallowclose i {
            display: none;
        }
    </style>
</head>

<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header header-new">
        <a class="logo">
            <img src="${ctx}/common/images/logo.png" alt="外包服务管理平台"/>
        </a>
        <ul class="layui-nav" style="float:right">
            <li class="layui-nav-item">
                <a href="javascript:;"><img src="${ctx}/common/images/head.png" class="user_head" alt=""/></a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:void(0);" id="modify">密码修改</a></dd>
                    <dd><a href="${ctx}/j_spring_security_logout">退出登录</a></dd>
                </dl>
            </li>
        </ul>

    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree site-new-nav">
                <c:forEach items="${menus}" var="menu" varStatus="i">
                    <li class="layui-nav-item <c:if test='${i.index == 0}'>layui-nav-itemed</c:if>">
                        <a href="javascript:;">${menu.menuName}</a>
                        <dl class="layui-nav-child">
                            <c:forEach items="${menu.childMenus}" var="childMenu">
                                <dd>
                                    <a data-id="${childMenu.id}" class="site-new-active" data-url="${ctx}${childMenu.url}">${childMenu.menuName}</a>
                                </dd>
                            </c:forEach>
                        </dl>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="layui-tab layui-tab-brief" lay-filter="tabs" lay-allowclose="true">
        <ul class="layui-tab-title site-new-title">
            <li class="layui-this notallowclose">首页</li>
        </ul>
        <div class="layui-body layui-tab-content site-new">

            <div class="layui-tab-item layui-show">
                <iframe frameborder="0" src="${ctx}/welcome.shtml" class="frame_page"></iframe>
            </div>

        </div>
    </div>

</div>

<script type="text/javascript">
    layui.use(['layer', 'element'], function () {
        var $ = layui.jquery, layer = layui.layer
                , element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabAdd: function (tabid, tab) {
                var laytab = $("div.layui-tab[lay-filter='" + tabid + "']"),
                        tabitem = laytab.find("li[lay-id='" + tab.id + "']");
                if (tabitem.length == 0) {
                    //新增一个Tab项
                    element.tabAdd(tabid, {
                        title: tab.title
                        , content: '<iframe frameborder="0" src="' + tab.url + '" class="frame_page"></iframe>'
                        , id: tab.id
                    })
                }
                this.tabChange(tabid, tab.id);
                if (tabitem.length > 0) {
                    //是否刷新tab页
                    layer.confirm('标签页已经打开,是否重新加载？', {
                        title: '系统提示'
                        , icon: 3
                    }, function (i) {
                        var index = tabitem.index();
                        var tab_item = laytab.find(".layui-tab-item:eq(" + index + ")");
                        if (tab_item.hasClass("layui-show")) {
                            tab_item.html('<iframe frameborder="0" src="' + tab.url + '" class="frame_page"></iframe>');
                        }
                        layer.close(i);
                    });
                }
            }
            , tabChange: function (tabid, id) {
                //切换到指定Tab项
                element.tabChange(tabid, id);
            }
        };

        $("#modify").click(function () {
            layer.open({
                type: 2,
                title: '修改密码',
                area: ['500px', '350px'],
                shade: 0.1,
                content: '${ctx}/user/modify.shtml'
            })
        });

        $(".site-new-active").on("click", function () {
            var id = $(this).attr("data-id");
            var href = $(this).attr("data-url");

            href += "?time=" + new Date().getTime();
            var title = $(this).text();

            active.tabAdd('tabs', {id: id, title: title, url: href});
        });
    });
</script>

</body>
</html>