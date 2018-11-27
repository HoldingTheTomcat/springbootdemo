F:\springbootdemo\src\main\resources\templates\js
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- bootstrap -->
    <link rel="stylesheet" href="/resource/admin/wskh/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/resource/admin/wskh/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/resource/admin/wskh/bower_components/Ionicons/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet"
          href="/resource/admin/wskh/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="/resource/admin/wskh/bower_components/select2/dist/css/select2.min.css">
    <link rel="stylesheet" href="/resource/admin/wskh/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins -->
    <link rel="stylesheet" href="/resource/admin/wskh/dist/css/skins/_all-skins.css">
    <!-- 提示框toastr -->
    <link rel="stylesheet" href="/resource/admin/wskh/bower_components/toastr/toastr.min.css">
    <!-- bootstrapValidator -->
    <link rel="stylesheet"
          href="/resource/admin/wskh//bower_components/bootstrapvalidator/dist/css/bootstrapValidator.min.css">
    <link href="/resource/admin/wskh/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css"
          rel="stylesheet">
    <link href="/resource/admin/wskh/bower_components/fileinput/fileinput.min.css" rel="stylesheet" type="text/css" />

    <!-- 页面样式 -->
    <link rel="stylesheet" href="/resource/admin/wskh/css/public.css">
    <link rel="stylesheet" href="/resource/admin/wskh/css/management.css">
    
    <style>
        .form-inline .form-control {
            margin-bottom: 5px;
            width: 100%;
        }

        .form-group {
            width: 100%;
        }

    </style>
</head>

<body>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="txt_departmentname">部门名称</label>
                    <input type="text" name="txt_departmentname" class="form-control" id="txt_departmentname" placeholder="部门名称">
                </div>
                <div class="form-group">
                    <label for="txt_parentdepartment">上级部门</label>
                    <input type="text" name="txt_parentdepartment" class="form-control" id="txt_parentdepartment" placeholder="上级部门">
                </div>
                <div class="form-group">
                    <label for="txt_departmentlevel">部门级别</label>
                    <input type="text" name="txt_departmentlevel" class="form-control" id="txt_departmentlevel" placeholder="部门级别">
                </div>
                <div class="form-group">
                    <label for="txt_statu">描述</label>
                    <input type="text" name="txt_statu" class="form-control" id="txt_statu" placeholder="状态">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    //注册新增按钮的事件
    $("#btn_add").click(function () {
        $("#myModalLabel").text("新增");
        $('#myModal').modal();
    });
</script>
    <!-- jQuery 3 -->
    <script src="/resource/admin/wskh/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="/resource/admin/wskh/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- AdminLTE App -->
    <script src="/resource/admin/wskh/dist/js/adminlte.min.js"></script>
    <!-- Select2 -->
    <script src="/resource/admin/wskh/bower_components/select2/dist/js/select2.full.min.js"></script>
    <!-- DataTables -->
    <script src="/resource/admin/wskh/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="/resource/admin/wskh/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="/resource/admin/wskh/bower_components/colresizable/colResizable-1.6.min.js"></script>
    <!-- 提示框toastr -->
    <script src="/resource/admin/wskh/bower_components/toastr/toastr.min.js"></script>
    <script src="/resource/admin/wskh/bower_components/toastr/options.js"></script>
    <!-- iCheck -->
    <script src="/resource/admin/wskh/plugins/iCheck/icheck.min.js"></script>
    <script src="/resource/admin/wskh/plugins/handlebars/handlebars-v4.0.12.js"></script>
    <script src="/resource/admin/wskh/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
    <script src="/resource/admin/wskh/bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <script src="/resource/admin/wskh/bower_components/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="/resource/admin/wskh/bower_components/fileinput/fileinput.min.js"></script>
    <script type="text/javascript" src="/resource/admin/wskh/bower_components/fileinput/locales/zh.js"></script>
    <!-- bootstrapValidator -->
    <script src="/resource/admin/wskh/bower_components/bootstrapvalidator/dist/js/bootstrapValidator.min.js"></script>
    <script src="/resource/admin/wskh/bower_components/layer/2.4/layer.js"></script>
    <!-- 公共js -->
    <script src="/resource/admin/wskh/js/public.js"></script>
    <!-- 页面js -->
    <script src="/resource/admin/wskh/js/banner.js"></script>
</body>
</html>
                                                                                                                                                                                                                                                                                                                                                                      0