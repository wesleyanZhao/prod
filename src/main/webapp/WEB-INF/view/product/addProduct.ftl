<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field" >
    <legend>添加新商品</legend>
<div class="layui-field-box" >
    <form id="form" class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="prdName" name="prdName" required  lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">明细商品图片</label>
                <div class="layui-input-inline">
                    <input type="file" name="file" class="layui-upload-file">
                    <input type="hidden" id="prdImg" name="prdImg">
                <#--<input type="text" id="imgUrl" name="imgUrl" required  lay-verify="required" placeholder="请输入明细商品图片" autocomplete="off" class="layui-input">-->
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">参加活动</label>
                <div class="layui-input-block">
                    <input type="radio" name="isSales" value="y" title="是" checked>
                    <input type="radio" name="isSales" value="n" title="否">
                </div>
            </div>
        </div>
        <div id="address" class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品简码</label>
                <div class="layui-input-inline">
                    <input type="text" id="prdCode" name="prdCode" placeholder="请输入商品简码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">捆绑销售</label>
                <div class="layui-input-block">
                    <input type="radio" name="isBind" value="y" title="是" checked>
                    <input type="radio" name="isBind" value="n" title="否">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">销售方式</label>
                <div class="layui-input-inline">
                    <select id="isGifts" name="isGifts" lay-filter="isGifts" lay-verify="required" >
                        <option value="10" selected>购买品</option>
                        <option value="20">赠品</option>
                        <option value="30">赠品或购买品</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品类别</label>
                <div class="layui-input-inline">
                    <select id="prdType" name="prdType" lay-filter="prdType" lay-verify="required" >
                        <#list supAndType.type as type>
                            <option value="${type.tpNm}">${type.tpNm}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">可预售</label>
                <div class="layui-input-block">
                    <input type="radio" name="isPreSale" value="y" title="是" checked>
                    <input type="radio" name="isPreSale" value="n" title="否">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">上架时间</label>
                <div class="layui-input-inline">
                    <input name="shDate" class="layui-input" placeholder="请选择上架时间" onclick="layui.laydate({elem: this})">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">退换货</label>
                <div class="layui-input-block">
                    <input type="radio" name="isReturn" value="y" title="支持" checked>
                    <input type="radio" name="isReturn" value="n" title="不支持">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">售卖形式</label>
                <div class="layui-input-inline">
                    <select id="saleCode" name="saleCode" lay-filter="saleCode" lay-verify="required" >
                        <option value="100" selected>网站</option>
                        <option value="200">线下</option>
                        <option value="300">手机APP</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">是否使用</label>
                <div class="layui-input-block">
                    <input type="radio" name="isUsed" value="y" title="是" checked>
                    <input type="radio" name="isUsed" value="n" title="否">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">供应商</label>
            <div class="layui-input-inline">
                <#--<input type="text" id="supName" name="supName" placeholder="请输入供应商" autocomplete="off" class="layui-input">-->
                <select id="supName" name="supName" lay-filter="saleCode" lay-verify="required" >
                    <#list supAndType.sup as sup>
                        <option value="${sup.supName}">${sup.supName}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="addProduct">提交</button>
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
                $('#prdImg').val(res.imgUrl);
                form.render();
            }
        });

        form.on('submit(addProduct)',function () {
            var data = $('form').serializeArray();

            $.ajax({
                url:"/prd/addProductForm.do",
                data:data,
                type:"post",
                success:function (data) {
                    if(data.result == "success"){
                        layer.msg('添加成功',{
                            icon: 1,
                            time: 1000
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