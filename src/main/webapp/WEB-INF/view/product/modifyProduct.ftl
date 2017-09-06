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
        <div class="layui-form-item">
            <#--<input id="hidPrdName" type="hidden" value="${product.prdName}">-->
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-inline">
                <input type="text" id="prdName" name="prdName" required  lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">参加活动</label>
            <div class="layui-input-block">
                <input type="radio" name="isSales" value="y" title="是" checked>
                <input type="radio" name="isSales" value="n" title="否">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品简码</label>
            <div class="layui-input-inline">
                <input type="text" id="prdCode" name="prdCode" placeholder="请输入商品简码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">捆绑销售</label>
            <div class="layui-input-block">
                <input type="radio" name="isBind" value="y" title="是" checked>
                <input type="radio" name="isBind" value="n" title="否">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品类别</label>
            <div class="layui-input-inline">
                <select id="prdType" name="prdType" lay-filter="prdType" lay-verify="required" >
                    <#list supAndType.type as type>
                        <option value="${type.tpNm}">${type.tpNm}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">可预售</label>
            <div class="layui-input-block">
                <input type="radio" name="isPreSale" value="y" title="是" checked>
                <input type="radio" name="isPreSale" value="n" title="否">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上架时间</label>
            <div class="layui-input-inline">
                <input name="shDate" class="layui-input" placeholder="请选择上架时间" onclick="layui.laydate({elem: this})">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">退换货</label>
            <div class="layui-input-block">
                <input type="radio" name="isReturn" value="y" title="支持" checked>
                <input type="radio" name="isReturn" value="n" title="不支持">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">售卖方式</label>
            <div class="layui-input-inline">
                <select id="saleCode" name="saleCode" lay-filter="saleCode" lay-verify="required" >
                    <option value="100" selected>网站</option>
                    <option value="200">线下</option>
                    <option value="300">手机APP</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否使用</label>
            <div class="layui-input-block">
                <input type="radio" name="isUsed" value="y" title="是" checked>
                <input type="radio" name="isUsed" value="n" title="否">
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

    layui.use(['laydate','form','jquery','layer'],function () {
        var $ = layui.jquery;
        var form = layui.form();
        var layer = layui.layer;

        var prdName = "${product.prdName}";
        var isSales = "${product.isSales}";
        var prdCode = "${product.prdCode}";
        var isBind = "${product.isBind}";
        var prdType = "${product.tpCd}";
        var isPreSale = "${product.isPreSale}";
        var shDate = "${product.shDate}";
        var isReturn = "${product.isReturn}";
        var saleCode = "${product.saleCode}";
        var isUsed = "${product.isUsed}";
        var supName = "${product.supName}";


        form.on('submit(addProduct)',function () {
            var data = $('form').serializeArray();


            return false;
        })
    })

</script>
</body>
</html>