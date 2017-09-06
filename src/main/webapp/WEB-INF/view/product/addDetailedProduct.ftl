<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field" >
    <legend>添加商品明细</legend>
<div class="layui-field-box" >
    <form id="form" class="layui-form">
        <input type="hidden" name="prdNo" value="${params.prdNo}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="prdName" value="${params.prdName}" disabled autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品类别</label>
                <div class="layui-input-inline">
                    <input type="text" name="prdType" value="${params.tpNm}" disabled autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">明细商品名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="prdDtlName" name="prdDtlName" required  lay-verify="required" placeholder="请输入明细商品名称" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">明细商品图片</label>
                <div class="layui-input-inline">
                    <input type="file" name="file" class="layui-upload-file">
                    <input type="hidden" id="imgUrl" name="imgUrl">
                    <#--<input type="text" id="imgUrl" name="imgUrl" required  lay-verify="required" placeholder="请输入明细商品图片" autocomplete="off" class="layui-input">-->
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">明细商品成本</label>
                <div class="layui-input-inline">
                    <input type="text" id="iptPrice" name="iptPrice" required  lay-verify="required" placeholder="请输入明细商品成本" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">明细商品售价</label>
                <div class="layui-input-inline">
                    <input type="text" id="salePrice" name="salePrice" required  lay-verify="required" placeholder="请输入明细商品售价" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否使用</label>
                <div class="layui-input-block">
                    <input type="radio" name="isUsed" value="y" title="是" checked>
                    <input type="radio" name="isUsed" value="n" title="否">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <#if (length>0)>
                <#list ware as wa>
                    <div class="layui-inline">
                        <#--<div class="layui-input-inline">-->
                            <label class="layui-form-label">${wa.wAddr}.${wa.wName}</label>
                            <div class="layui-input-inline">
                                <input type="checkbox" name="wNo" value="${wa.wNo}" class="layui-input">
                            </div>
                        <#--</div>-->
                        <#--<div class="layui-input-inline">-->
                            <label class="layui-form-label">库存数量</label>
                            <div class="layui-input-inline">
                                <input type="text" id="wCount${wa.wNo}" name="wCount${wa.wNo}" placeholder="请输入库存数量" autocomplete="off" class="layui-input">
                            </div>
                        <#--</div>-->
                    </div>
                    <#--<div class="layui-inline">-->
                        <label class="layui-form-label">仓库使用</label>
                        <div class="layui-input-inline">
                            <input type="radio" name="wIsUsed${wa.wNo}" value="y" title="使用" checked>
                            <input type="radio" name="wIsUsed${wa.wNo}" value="n" title="禁用">
                        </div>
                    <#--</div>-->
                </#list>
            </#if>
            <#if (length<=0)>
                <div class="layui-inline">
                    <input type="text" id="wCount" name="wCount" placeholder="请输入库存数量" autocomplete="off" class="layui-input">
                    <#--<select id="wName" name="wName" lay-filter="wName" disabled>-->
                        <#--<option value=" ">无</option>-->
                    <#--</select>-->
                </div>
            </#if>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="addWare">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</fieldset>
<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script>

    layui.use(['laydate','form','jquery','layer','upload'],function () {
        var $ = layui.jquery;
        var form = layui.form();
        var layer = layui.layer;

        layui.upload({
            url: '/base/upload.do' //上传接口
            ,success: function(res){ //上传成功后的回调
                $('#imgUrl').val(res.imgUrl);
                form.render();
            }
        });

        form.on('submit(addWare)',function () {
            var data = $('form').serializeArray();

            $.ajax({
                url:"/prd/addDetailedProductForm.do",
                data:data,
                type:"post",
                success:function (data) {
                    if(data.result == "success"){
                        layer.msg('添加成功',{
                            icon: 1,
                            time: 1000
                        },function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }else if(data.result == "error"){
                        layer.msg('添加失败',{
                            icon:0,
                            time:1000
                        });
                    }
                }
            })
            return false;
        })
    })

</script>
</body>
</html>