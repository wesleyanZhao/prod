<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field" >
    <legend>修改商品信息</legend>
<div class="layui-field-box" >
    <form id="form" class="layui-form">
        <input type="hidden" name="prdDtlNo" value="${(map.prdDtlNo)!''}">
        <div class="layui-form-item">
            <label class="layui-form-label">明细名称</label>
            <div class="layui-input-inline">
                <input type="text" id="prdDtlName" name="prdDtlName" value="${(map.prdDtlName)!''}" required  lay-verify="required" placeholder="请输入明细名称" autocomplete="off" class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">图片</label>
            <div class="layui-input-inline">
                <input type="text" id="imgUrl" name="imgUrl" value="${(map.imgUrl)!''}" required  lay-verify="required" placeholder="请输入图片" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">毛利率</label>
            <div class="layui-input-inline">
                <input type="text" id="ctrlRate" name="ctrlRate" value="${(map.ctrlRate)!''}" required  lay-verify="required" placeholder="请输入毛利率" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">销售价格</label>
            <div class="layui-input-inline">
                <input type="text" id="salePrice" name="salePrice" value="${(map.salePrice)!''}" required  lay-verify="required" placeholder="请输入销售价格" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否可用</label>
            <div class="layui-input-inline">
                <input id="isUsedYes" type="radio" name="isUsed" value="y" title="使用">
                <input id="isUsedNo" type="radio" name="isUsed" value="n" title="禁用">
            </div>
        </div>
        <div class="layui-form-item">
            <#--<#if (length>0)>-->
                <#--<#list list as wa>-->
                    <div class="layui-inline">
                        <input type="hidden" name="wNo" value="${map.wNo}">
                        <label class="layui-form-label">${map.wAddr}.${map.wName}</label>
                        <#--<div class="layui-input-inline">-->
                            <#--<input type="checkbox" name="wNo" value="${map.wNo}" class="layui-input">-->
                        <#--</div>-->
                        <label class="layui-form-label">库存数量</label>
                        <div class="layui-input-inline">
                            <input type="text" id="wCount" name="wCount" value="${(map.wCount)!''}" placeholder="请输入库存数量" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                <#--</#list>-->
            <#--</#if>-->
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="saveDetailed">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</fieldset>
<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script>

    layui.use(['form','jquery','layer'],function () {
        var $ = layui.jquery;
        var form = layui.form();
        var layer = layui.layer;

        var isUsed = "${(map.isUsed)!''}";
        if(isUsed!=''){
            if(isUsed=='y'){
                $('#isUsedYes').attr('checked','checked');
            }
            if(isUsed=='n'){
                $('#isUsedNo').attr('checked','checked');
            }
            form.render();
        }


        form.on('submit(saveDetailed)',function () {
            var data = $('form').serializeArray();
            $.ajax({
                url:"/prd/saveModifyDetailed.do",
                type:"post",
                data:data,
                success:function(data){
                    if(data.result=="success"){
                        layer.msg('修改成功',{
                            icon:1,
                            time:1000
                        },function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                    }else{
                        layer.msg('修改失败',{
                            icon:0,
                            time:1000
                        })
                    }
                }
            })

            return false;
        })
    })

</script>
</body>
</html>