<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/common/jsp/common.jsp" %>
    <link rel="stylesheet" href="${ctx}/common/bootstrap/css/bootstrap.min.css">
    <script src="${ctx}/common/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div>
    <div style="margin-top: 5px;margin-left: 5px">
        <input id="file" type="file" onchange="upload()">
    </div>
    <div id="view" style="display: none">
        <progress id="progress" value="0" max="100" style="width: 200px;height: 10px;"></progress>
        <span id="percentage" style="color:blue;"></span><br>
    </div>
</div>
</body>
<script type="text/javascript">
    function upload() {
        $("#view").removeAttr("style");
        var file = document.getElementById("file").files[0];
        var form = new FormData();
        form.append("file", file);
        var xhr = new XMLHttpRequest();
        xhr.open("post", "${ctx}/attachment/upload.do", true);
        xhr.onload = function () {
            $("#view").attr("style", "display:none");
        };
        //监听progress事件
        xhr.upload.addEventListener("progress", function (event) {
            if (event.lengthComputable) {
                $("#progress").attr("max", event.total).attr("value", event.loaded);
                $("#percentage").text(Math.round(event.loaded / event.total * 100) + "%");
            }
        }, false);
        xhr.send(form);
    }
</script>
</html>