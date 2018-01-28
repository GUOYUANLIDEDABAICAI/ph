var role = {
    baseUrl: "role",
    entity: "role",
    tableId: "roleTable",
    toolbarId: "roleToolbar",
    unique: "id",
    order: "asc",
    currentItem: {}
};
role.columns = function () {
    return [
        {
            checkbox:true
        }, {
            field: 'name',
            title: '角色'
        }, {
            field: 'code',
            title: '编码'
        }, {
            field: 'path',
            title: 'url'
        }];
};

role.init = function () {

    role.table = $('#' + role.tableId).bootstrapTable({
        url: role.baseUrl + '/list', //请求后台的URL（*）
        method: 'get', //请求方式（*）
        toolbar: '#' + role.toolbarId, //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: false, //是否启用排序
        sortOrder: role.order, //排序方式
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false, //是否显示所有的列
        showRefresh: true, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: true, //是否启用点击选中行
        uniqueId: role.unique, //每一行的唯一标识，一般为主键列
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        columns: role.columns()
    });
};
role.select = function (layerTips) {
    var rows = role.table.bootstrapTable('getSelections');
    if (rows.length == 1) {
        role.currentItem = rows[0];
        return true;
    } else {
        layerTips.msg("请选中一行");
        return false;
    }
};

role.refresh = function(){
    user.select();
    role.table.bootstrapTable("refresh");
}
layui.use(['form', 'layedit', 'laydate'], function () {
    role.init();
    var editIndex;
    var layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        form = layui.form(),
        layedit = layui.layedit,
        laydate = layui.laydate;
    var addBoxIndex = -1;
    //初始化页面上面的按钮事件
    $('#btn_role_add').on('click', function () {
        if(user.select(layerTips)) {
            //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
            $.get(role.entity + '/edit', null, function (form) {
                addBoxIndex = layer.open({
                    type: 1,
                    title: '添加按钮',
                    content: form,
                    btn: ['保存', '取消'],
                    shade: false,
                    offset: ['20px', '20%'],
                    area: ['600px', '400px'],
                    maxmin: true,
                    yes: function (index) {
                        layedit.sync(editIndex);
                        //触发表单的提交事件
                        $('form.layui-form').find('button[lay-filter=edit]').click();
                    },
                    full: function (elem) {
                        var win = window.top === window.self ? window : parent.window;
                        $(win).on('resize', function () {
                            var $this = $(this);
                            elem.width($this.width()).height($this.height()).css({
                                top: 0,
                                left: 0
                            });
                            elem.children('div.layui-layer-content').height($this.height() - 95);
                        });
                    },
                    success: function (layero, index) {
                        var form = layui.form();
                        editIndex = layedit.build('description_editor');
                        layero.find("#userId").val(user.currentItem.id);
                        form.render();
                        form.on('submit(edit)', function (data) {
                            $.ajax({
                                url: role.baseUrl,
                                type: 'post',
                                data: data.field,
                                dataType: "json",
                                success: function () {
                                    layerTips.msg('保存成功');
                                    layer.close(addBoxIndex);
                                    role.refresh();
                                }

                            });
                            //这里可以写ajax方法提交表单
                            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                        });
                    },
                    end: function () {
                        addBoxIndex = -1;
                    }
                });
            });
        }
    });
    $('#btn_role_del').on('click', function () {
        if (role.select(layerTips)) {
            var id = role.currentItem.id;
            layer.confirm('确定删除数据吗？', null, function (index) {
                $.ajax({
                    url: role.baseUrl + "/" + id,
                    type: "DELETE",
                    success: function (data) {
                        if (data.rel == true) {
                            layerTips.msg("移除成功！");
                            location.reload();
                        } else {
                            layerTips.msg("移除失败！")
                            location.reload();
                        }
                    }
                });
                layer.close(index);
            });
        }
    });
});