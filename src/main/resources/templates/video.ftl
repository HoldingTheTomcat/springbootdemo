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

    <div class="float_lt txt">
        <span>图片地址</span>
        <input type="text" id="photoFilePath" />
    </div>
    <div class="float_lt txt">
        <span>选择图片</span>
        <input type="file" id="photoFile"/>
        <input type="button" id="submitPhoto" value="上传图片">
    </div>
    <script type="text/javascript">
        $(function () {
           /*上传图片*/
            $("#submitPhoto").click(function () {
                alert("点击事件触发");
               /* var formData = new FormData();
                formData.append("infoType", "uploadFaceImage");
                formData.append("applyId", 1569);
                formData.append("file", $("#videoFile").get(0).files[0]);
                alert(JSON.stringify(formData));
                upload(formData);*/
               
               
            })
        })
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