<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>商品管理系统 惠买ivalue后台管理</title>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>

<body>
<form id="pageSubmit" class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-inline">
                <input type="text" id="prdName" name="prdName" value="${(params.prdName)!''}" placeholder="请输入商品名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">明细商品名</label>
            <div class="layui-input-inline">
                <input type="text" id="prdDtlName" name="prdDtlName" value="${(params.prdDtlName)!''}" placeholder="请输入明细商品名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">销售价格</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="salePriceBegin" value="${(params.salePriceBegin)!''}" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="salePriceEnd" value="${(params.salePriceEnd)!''}" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">仓储名称</label>
            <div class="layui-input-inline">
                <input type="text" id="wAddrName" name="wAddrName" value="${(params.wAddrName)!''}" placeholder="请输入仓储名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">库存数量</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="wCountBegin" value="${(params.wCountBegin)!''}" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="wCountEnd" value="${(params.wCountEnd)!''}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">是否可用</label>
            <div class="layui-input-inline">
                <select id="isUsed" name="isUsed" lay-filter="isUsed">
                    <option value=" ">请选择</option>
                    <option value="y">是</option>
                    <option value="n">否</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label"></label>
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="getDetailed">查询</button>
        <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
        </div>
    </div>
    <input type="hidden" id="currentPage" name="currentPage" >
</form>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>所有菜单</legend>
</fieldset>

<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="40">
            <col width="30">
            <col width="30">
            <col width="60">
            <col width="60">
            <col width="60">
            <col width="70">
            <col width="70">
            <col width="50">
            <col width="80">
        </colgroup>
        <thead>
        <tr>
            <th>商品</th>
            <th>商品名称</th>
            <th>明细编号</th>
            <th>明细名称</th>
            <th>成本价格</th>
            <th>销售价格</th>
            <th>是否可用</th>
            <th>仓储名称</th>
            <th>库存数量</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <#list list as item>
            <tr>
                <#--<input id="hidTpCd" type="hidden" value="${item.tpCd}">-->
                <td><img src="${item.imgUrl}" style="max-width: 60%"></td>
                <td>${item.prdName}</td>
                <td>${item.prdDtlNo}</td>
                <td>${item.prdDtlName}</td>
                <td>${item.iptPrice}</td>
                <td>${item.salePrice}</td>
                <td>
                    <#if item.isUsed='y'>可用
                        <#else >不可用
                    </#if>
                </td>
                <td>${item.wAddr}.${item.wName}</td>
                <td>${item.wCount}</td>
                <td>
                    <button class="layui-btn layui-btn-mini addDetailedProduct" val="${item.prdDtlNo}" wNo="${item.wNo}">管理</button>
                    <#--<button class="layui-btn layui-btn-mini addDetailedProduct" prdNo="${item.prdNo}" val="${item.tpCd}" prdName="${item.prdName}" tpNm="${item.tpNm}">添加明细</button>-->
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
<div class="layui-form">
    <span id="form_page"></span>&nbsp;共${page.total}条数据
</div>
<#--<form id="pageSubmit">-->
    <#---->
<#--</form>-->
<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script type="text/javascript">



    layui.define([ 'element', 'form', 'layer', 'laypage','laydate'], function(exports) {

        var element = layui.element();
        var form = layui.form();
        var layer = layui.layer;
        var laypage = layui.laypage;
        var $ = layui.jquery;

        var pindex = "${page.pageNum}";// 当前页
        var ptotalpages = "${page.pages}";// 总页数
        var pcount = "${page.total}";// 数据总数

        // 分页
        laypage({
            cont : 'form_page', // 页面上的id
            pages : ptotalpages,//总页数
            curr : pindex,//当前页。
            skip : true,
            jump : function(obj, first) {
                $("#currentPage").val(obj.curr);//设置当前页
                //$("#size").val(psize);
                //防止无限刷新,
                //只有监听到的页面index 和当前页不一样是才出发分页查询
                if (obj.curr != pindex ) {
                    $("#pageSubmit").submit();
                }
            }
        });

        var isUsed = "${(params.isUsed)!''}";

        if(isUsed!=''){
            if(isUsed=='y'){
                console.log(isUsed);
                $('#isUsed option:eq(1)').attr('selected','selected');
            }else if(isUsed=='n'){
                $('#isUsed option:eq(2)').attr('selected','selected');
            }else{
                $('#isUsed option:eq(0)').attr('selected','selected');
            }
            form.render();
        }

        $('.addDetailedProduct').click(function () {
            var val = $(this).attr('val');
            var wNo = $(this).attr('wNo');
//            var tpNm = $(this).attr('tpNm');
//            var prdName = $(this).attr('prdName');
//            var prdNo = $(this).attr('prdNo');
            layer.open({
                title:'修改商品明细'
                ,area:['800px','500px']
                ,type:2
                ,content:"/prd/modifyDetailedShow.do?prdDtlNo="+val+"&wNo="+wNo
            })
        })

        form.on('select(getDetailed)',function () {
            return true;
        })
    });
</script>
</body>
</html>