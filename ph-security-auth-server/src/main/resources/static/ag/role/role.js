var role = {
    baseUrl: "role",
    entity: "role",
    tableId: "roleTable",
    toolbarId: "toolbar",
    unique: "id",
    order: "asc",
    currentItem: {},
    code: "id",
    parentCode: "parentId",
    rootValue: -1,
    explandColumn: 1
};
role.columns = function () {
    return [
        {
            field: 'selectItem',
            radio: true
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
//得到查询的参数
role.queryParams = function () {
    var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        name: $("#name").val()
    };
    return temp;
};
role.init = function () {
    role.table = $('#' + role.tableId).bootstrapTreeTable({
        id: role.unique,// 选取记录返回的值
        code: role.code,// 用于设置父子关系
        parentCode: role.parentCode,// 用于设置父子关系
        rootCodeValue: role.rootValue,
        url: role.baseUrl + '/list', //请求后台的URL（*）
        method: 'get', //请求方式（*）
        toolbar: '#' + role.toolbarId, //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        ajaxParams: role.queryParams,//传递参数（*）
        expandColumn: role.explandColumn,//在哪一列上面显示展开按钮,从0开始
        expandAll: true,
        columns: role.columns(),
        clickRow: role.clickRow
    });
};
role.select = function (layerTips) {
    var rows = role.table.bootstrapTreeTable('getSelections');
    if (rows.length == 1) {
        role.currentItem = rows[0];
        return true;
    } else {
        layerTips.msg("请选中一行");
        return false;
    }
};
role.clickRow = function () {
    alert("点击成功")
    element.refresh();
};
role.refresh = function () {
    role.table.bootstrapTreeTable("refresh");
};


layui.use(['form', 'layedit', 'laydate'], function () {
    role.init();
    $('#' + role.tableId + '>.treegrid-tbody>tr').click(function () {
        var rows = role.table.bootstrapTreeTable('getSelections');
        role.currentItem = rows[0];
        alert();
    });
    var allrole = null;
    var editIndex;
    $.get(role.baseUrl + '/list', null, function (data) {
        allrole = data;
    });
    var layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        form = layui.form(),
        layedit = layui.layedit;
//初始化页面上面的按钮事件
    $('#btn_query').on('click', function () {
        role.table.bootstrapTreeTable('refresh', role.queryParams());
    });
    var addBoxIndex = -1;
    $('#btn_add').on('click', function () {
        if (addBoxIndex !== -1)
            return;
        var rows = role.table.bootstrapTreeTable('getSelections');
        var id = "-1";
        if (rows.length == 1) {
            id = rows[0].id;
        }
        //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
        $.get(role.entity + '/edit', null, function (form) {
            addBoxIndex = layer.open({
                type: 1,
                title: '添加菜单',
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
                    form.render();
                    for (var i = 0; i < allrole.length; i++)
                        layero.find('#parentId').append('<option value="' + allrole[i].id + '" >' + allrole[i].title + '</option>');
                    layero.find("select[name='parentId']").val(id);
                    form.render('select');
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
    });
    $('#btn_edit').on('click', function () {
        if (addBoxIndex !== -1)
            return;
        var rows = role.table.bootstrapTreeTable('getSelections');
        if (role.select(layerTips)) {
            var id = role.currentItem.id;
            $.get(role.baseUrl + '/' + id, null, function (data) {
                console.log(data);
                var result = data.result;
                $.get(role.entity + '/edit', null, function (form) {
                    addBoxIndex = layer.open({
                        type: 1,
                        title: '编辑菜单',
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
                            setFromValues(layero, result);
                            layero.find('#description_editor').val(result.description);
                            editIndex = layedit.build('description_editor');
                            for (var i = 0; i < allrole.length; i++)
                                layero.find('#parentId').append('<option value="' + allrole[i].id + '" >' + allrole[i].title + '</option>');
                            layero.find("select[name='parentId']").val(result['parentId']);
                            form.render('select');
                            layero.find(":input[name='code']").attr("disabled", "");
                            form.render();

                            form.on('submit(edit)', function (data) {
                                $.ajax({
                                    url: role.baseUrl + '/' + result.id,
                                    type: 'put',
                                    data: data.field,
                                    dataType: "json",
                                    success: function () {
                                        layerTips.msg('更新成功');
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
            });
        }
    });
    $('#btn_del').on('click', function () {
        if (role.select(layerTips)) {
            var id = role.currentItem.id;
            layer.confirm('确定删除数据吗？', null, function (index) {
                $.ajax({
                    url: role.baseUrl + "/" + id,
                    type: "DELETE",
                    success: function (data) {
                        console.log(data);
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
})
;
