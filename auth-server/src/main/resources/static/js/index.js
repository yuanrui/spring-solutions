var element, layer;
//TODO:让浏览器后退按钮失效。
history.pushState(null, null, document.URL);
window.addEventListener('popstate', function () {
    history.pushState(null, null, document.URL);
});

layui.use(['element', 'layer'], function () {
    element = layui.element;
    layer = layui.layer;
});

$(function () {
    $(".au-menu a").on("click", function () {
        var url = $(this).attr("lay-href") + "?_=" + new Date().getTime();
        if (url == undefined) {
            url = "about:blank";
        }
        $(".au-profile .layui-nav-child dd").removeClass("layui-this");
        setMainUrl(url);
    });
});

function setMainUrl(url) {
    var frames = $("#mainFrame").contents().find("iframe");
    if (frames.length > 0) {

        layer.confirm('页面内容可能未保存, 是否跳转？', {
            icon: 3,
            title:"操作提示",
            btn: ['是', '否']
        }, function () {
            layer.closeAll();
            $("#mainFrame").attr("src", url);
        }, function () {

        });

        return;
    }

    //$("#mainFrame").attr("src", url);
}

function showTool(url) {
    setMainUrl(url);
    $(".au-menu .layui-nav-item").removeClass("layui-this");
}