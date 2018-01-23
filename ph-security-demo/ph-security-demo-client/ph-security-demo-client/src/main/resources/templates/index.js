var urlPre = "http://localhost:8002/api/ph-security-demo-client"
$(function () {

    $.get(urlPre+"/admin/user/system",null,function(data){
        alert(data);
        var data = eval("("+data+")");
        alert(data);
        for(var i=0;i<data.length;i++){
            alert(data[i].title)
            $('#menuSys').append('<li class="layui-nav-item">' +
                '<a href="javascript:;">' +
                '<i class="fa '+data[i].icon+'" aria-hidden="false"></i>  '+data[i].title+'</a>' +
                '<dl class="layui-nav-child" id="'+data[i].id+'menu"> &lt;!&ndash; 二级菜单 &ndash;&gt;\n' +
                '</dl></li>');
            refreshMenu(data[i].id);
            //$('#menuSysMobile').prepend('<dd><a href="javascript:;" onclick="javascript:refreshMenu('+data[i].id+')"><i class="fa '+data[i].icon+'" aria-hidden="true"></i> '+data[i].title+'</a></dd>');
        }
    });
})

function refreshMenu(parentId) {
    $.get(urlPre+"/admin/user/menu?parentd="+parentId,null,function(data){
        var menuId = parentId + "menu";
        alert(data);
        var data = eval("("+data+")");
        alert(data);
        for(var i=0;i<data.length;i++){
            alert(data[i].title)
            $('#'+menuId).append('<dd><a href="javascript:;" onclick="javascript:refreshMenu('+data[i].id+')"><i class="fa '+data[i].icon+'" aria-hidden="false"></i>  '+data[i].title+'</a></dd>');
            //$('#menuSysMobile').prepend('<dd><a href="javascript:;" onclick="javascript:refreshMenu('+data[i].id+')"><i class="fa '+data[i].icon+'" aria-hidden="true"></i> '+data[i].title+'</a></dd>');

        }
    });
}