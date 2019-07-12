<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/7/12
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理</title>
    <link href="../backPage/ui/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../backPage/ui/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
    <link href="../backPage/ui/waves-0.7.5/waves.css" rel="stylesheet"/>
    <link href="../backPage/ui/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>

    <script src="../backPage/ui/js/jquery-3.3.1.min.js"></script>
    <script src="../backPage/ui/waves-0.7.5/waves.js"></script>
    <script src="../backPage/ui/jquery-confirm/jquery-confirm.min.js"></script>
    <script src="../backPage/ui/bootstrap-3.3.0/js/bootstrap.min.js"></script>
    <script src="../backPage/ui/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <button class="btn btn-default" href="javascript:;" onclick="createAction()">新增</button>
        <button class="btn btn-default" href="javascript:;" onclick="updateAction()">编辑</button>
        <button class="btn btn-default" href="javascript:;" onclick="deleteAction()">删除</button>
        <br/>
    </div>
    <table id="table"></table>
</div>

<script>

    var $table = $('#table');

    $(function() {
        //新增
        $("#submitbutton").click(function() {
            var id = $("#typeId").val()
            var typeName = $("#typeName").val()

            var url = "/carType?method=";

            if (id != null && id != "")
            {
                url += "update&id=" + id + "&typeName=" + typeName;
            }else{
                url += "create" + "&typeName=" + typeName;
            }

            $.ajax({
                url:url,
                type:"post",
                contentType: "application/json;charset=UTF-8",
                success:function(data){
                    if(data.success == true){
                        alert("保存成功");
                        $table.bootstrapTable('refresh');
                        $('form')[0].reset();
                        $("#createModal").modal('hide');
                    }else{
                        alert(data.errMsg);
                    }
                }
            })

        })

        // bootstrap table初始化
        $table.bootstrapTable({
            url: '/carType?method=find',
            height: getHeight(),
            toolbar: '#toolbar',
            clickToSelect: true,
            striped: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            sortOrder: "desc",
            smartDisplay: false,
            pagination: true,
            escape: true,
            idField: 'id',
            maintainSelected: true,
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'id', title: '编号', align: 'center'},
                {field: 'typeName', title: '类型名称',align: 'center'},
                {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
            ],
            onLoadError:function (status, res){
                console.info(status);
                console.info(res);
                var url = window.location.protocol + "//" + window.location.host + window.location.port + "/";
                parent.window.location.href = url;
            },
            onLoadSuccess:function (data){
                console.info(data);
            },
        });
    });

    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="编辑"><i class="glyphicon glyphicon-edit"></i></a>　',
            '<a class="delete" href="javascript:;" onclick="deleteAction()" data-toggle="tooltip" title="删除"><i class="glyphicon glyphicon-remove"></i></a>'
        ].join('');
    }
    // 格式化时间
    function timeFormatter(value , row, index) {
        return new Date(value).toLocaleString().split(' ')[0];
    }

    function getHeight() {
        return $(window).height() - 20;
    }

    // 删除
    var deleteDialog;
    function deleteAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            deleteDialog = $.confirm({
                type: 'red',
                animationSpeed: 300,
                title: false,
                content: '确认删除该吗？',
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            for (var i in rows) {
                                ids.push(rows[i].id);
                            }

                            $.ajax({
                                url:"/carType?method=delete&ids="+ids.join("-"),
                                type:"post",
                                contentType: "application/json;charset=UTF-8",
                                success:function(data){
                                    if(data.success == true){
                                        alert("成功");
                                        $table.bootstrapTable('refresh');
                                        $('form')[0].reset();
                                        $("#createModal").modal('hide');
                                    }else{
                                        alert("失败");
                                    }
                                },
                                error: function(XMLHttpRequest, textStatus, errorThrown) {
                                    $.confirm({
                                        theme: 'dark',
                                        animation: 'rotateX',
                                        closeAnimation: 'rotateX',
                                        title: false,
                                        content: textStatus,
                                        buttons: {
                                            confirm: {
                                                text: '确认',
                                                btnClass: 'waves-effect waves-button waves-light'
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }

    //创建
    function createAction(){
        $("#createModal").modal("show");
    }

    //编辑
    function updateAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            //赋初值
            var obj = rows[0];
            var typeId = obj["id"];
            var typeName = obj["typeName"];

            $("#typeId").val(typeId);
            $("#typeName").val(typeName);

            $("#createModal").modal("show");
            $('#createModal').on('hide.bs.modal', function () {
                $('form')[0].reset();
            });
        }
    }

</script>

<!-- 模态框（Modal） -->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="createModalTitle">
                    保存
                </h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <input hidden id="typeId" />

                    <div class="form-group">
                        <label for="typeName">类型名称</label>
                        <input type="text" class="form-control" id="typeName" placeholder="类型名称">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="submitbutton">
                    保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>