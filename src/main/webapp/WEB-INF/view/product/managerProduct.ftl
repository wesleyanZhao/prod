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
            <label class="layui-form-label">商品编号</label>
            <div class="layui-input-inline">
                <input type="text" id="prdNo" name="prdNo" value="${(cond.prdNo)!''}" placeholder="请输入商品编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-inline">
                <input type="text" id="prdName" name="prdName" value="${(cond.prdName)!''}" placeholder="请输入商品名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">参加活动</label>
            <div class="layui-input-inline">
                <select id="isSales" name="isSales" lay-filter="isSales">
                    <option value=" ">请选择</option>
                    <option value="y">是</option>
                    <option value="n">否</option>
                </select>
            </div>
        </div>
    </div>
    <div id="address" class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品简码</label>
            <div class="layui-input-inline">
                <input type="text" id="prdCode" name="prdCode" value="${(cond.prdCode)!''}" placeholder="请输入商品简码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">可预售</label>
            <div class="layui-input-inline">
                <select id="isPreSale" name="isPreSale" lay-filter="isPreSale">
                    <option value=" ">请选择</option>
                    <option value="y">是</option>
                    <option value="n">否</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">退换货</label>
            <div class="layui-input-inline">
                <select id="isReturn" name="isReturn" lay-filter="isReturn">
                    <option value=" ">请选择</option>
                    <option value="y">支持</option>
                    <option value="n">不支持</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品类别</label>
            <div class="layui-input-inline">
                <select id="prdType" name="prdType" lay-filter="prdType">
                    <option value=" ">请选择</option>
                <#list supAndType.type as type>
                    <option value="${type.tpNm}">${type.tpNm}</option>
                </#list>
                </select>
            </div>
        </div>
        <div class="layui-inline">
                <label class="layui-form-label">上架时间</label>
                <div class="layui-input-inline">
                    <input class="layui-input" name="shDateBegin" value="${(cond.shDateBegin)!''}" placeholder="开始日" id="LAY_demorange_s" onclick="layui.laydate({elem: this, festival: true})">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline">
                    <input class="layui-input" name="shDateEnd" value="${(cond.shDateEnd)!''}" placeholder="截止日" id="LAY_demorange_e" onclick="layui.laydate({elem: this, festival: true})">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">售卖方式</label>
            <div class="layui-input-inline">
                <select id="saleCode" name="saleCode" lay-filter="saleCode">
                    <option value=" ">请选择</option>
                    <option value="100">网站</option>
                    <option value="200">线下</option>
                    <option value="300">手机APP</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">是否使用</label>
            <div class="layui-input-inline">
                <select id="isUsed" name="isUsed" lay-filter="isUsed">
                    <option value=" ">请选择</option>
                    <option value="y">是</option>
                    <option value="n">否</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">供应商</label>
            <div class="layui-input-inline">
                <#--<input type="text" id="supName" name="supName" value="${(cond.supName)!''}" placeholder="请输入供应商名" autocomplete="off" class="layui-input">-->
                <select id="supName" name="supName" lay-filter="saleCode">
                    <option value=" ">请选择</option>
                <#list supAndType.sup as sup>
                    <option value="${sup.supName}">${sup.supName}</option>
                </#list>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">捆绑销售</label>
            <div class="layui-input-inline">
                <select id="isBind" name="isBind" lay-filter="isBind">
                    <option value=" ">请选择</option>
                    <option value="y">是</option>
                    <option value="n">否</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">销售方式</label>
            <div class="layui-input-inline">
                <select id="isGifts" name="isGifts" lay-filter="isGifts">
                    <option value=" ">请选择</option>
                    <option value="10">购买品</option>
                    <option value="20">赠品</option>
                    <option value="30">购买品或赠品</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"></label>
            <div class="layui-input-inline">
                <button class="layui-btn" lay-submit lay-filter="getProduct">查询</button>
            <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
            </div>
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
            <col width="30">
            <col width="95">
            <col width="95">
            <col width="95">
            <col width="95">
            <col width="120">
            <col width="110">
            <col width="95">
            <col width="95">
            <col width="95">
            <col width="95">
            <col width="95">
            <col width="110">
            <col width="100">
            <col width="200">
        </colgroup>
        <thead>
        <tr>
            <th>商品编号</th>
            <th>商品名称</th>
            <th>商品简码</th>
            <th>商品类别</th>
            <th>供应商</th>
            <th>供应商公司</th>
            <th>上架时间</th>
            <th>参与活动</th>
            <th>销售方式</th>
            <th>捆绑销售</th>
            <th>是否预售</th>
            <th>售卖形式</th>
            <th>支持退换货</th>
            <th>是否使用</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <#list list as item>
            <tr>
                <#--<input id="hidTpCd" type="hidden" value="${item.tpCd}">-->
                <td>${item.prdNo}</td>
                <td>${item.prdName}</td>
                <td>${item.prdCode}</td>
                <td>${item.tpNm}</td>
                <td>${item.supName}</td>
                <td>${item.supComp}</td>
                <td>${item.shDate}</td>
                <td>
                    <#if item.isSales='y'>参与
                        <#else >不参与
                    </#if>
                </td>
                    <td>
                        <#if item.isGifts='10'>购买品</#if>
                        <#if item.isGifts='20'>赠品</#if>
                        <#if item.isGifts='30'>购买品或赠品</#if>
                    </td>
                <td>
                    <#if item.isBind='y'>是
                        <#else >否
                    </#if>
                </td>
                <td>
                    <#if item.isPreSale='y'>是
                        <#else >否
                    </#if>
                </td>
                <td>
                    <#if item.saleCode='10'>网站</#if>
                    <#if item.saleCode='20'>线下</#if>
                    <#if item.saleCode='30'>手机APP</#if>
                </td>
                <td>
                    <#if item.isReturn='y'>支持
                        <#else >不支持
                    </#if>
                </td>
                <td>
                    <#if item.isUsed='y'>是</#if>
                    <#if item.isUsed='n'>否</#if>
                </td>
                <td>
                    <button class="layui-btn layui-btn-mini">修改</button>
                    <button class="layui-btn layui-btn-mini addDetailedProduct" prdNo="${item.prdNo}" val="${item.tpCd}" prdName="${item.prdName}" tpNm="${item.tpNm}">添加明细</button>
                    <#--<button class="layui-btn">默认按钮</button>-->
                    <#--<button class="layui-btn" prdNo="${item.prdNo}" val="${item.tpCd}" prdName="${item.prdName}" tpNm="${item.tpNm}">添加明细</button>-->
                    <#--<a class="update" val="${item.id}" href="javascript:;">修改</a>-->
                    <#--<a class="addDetailedProduct" prdNo="${item.prdNo}" val="${item.tpCd}" prdName="${item.prdName}" tpNm="${item.tpNm}" href="javascript:;">添加详细商品</a>-->
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
    <#--&lt;#&ndash;<input type="hidden" id="params" name="params" value="${(cond)!''}">&ndash;&gt;-->
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
                    $('#pageSubmit').submit();
                }
            }
        });

        var isSales = "${(cond.isSales)!''}";
        var isGifts = "${(cond.isGifts)!''}";
        var isBind = "${(cond.isBind)!''}";
        var isPreSale = "${(cond.isPreSale)!''}";
        var isReturn = "${(cond.isReturn)!''}";
        var prdType = "${(cond.prdType)!''}";
        var saleCode = "${(cond.saleCode)!''}";
        var isUsed = "${(cond.isUsed)!''}";
        var supName = "${(cond.supName)!''}"
        if(isSales!=''){
            if(isSales=='y'){
                $('#isSales option:eq(1)').attr('selected','selected');
            }else if(isSales=='n'){
                $('#isSales option:eq(2)').attr('selected','selected');
            }else{
                $('#isSales option:eq(0)').attr('selected','selected');
            }
            form.render();
        }
        if(isBind!=''){
            if(isBind=='y'){
                $('#isBind option:eq(1)').attr('selected','selected');
            }else if(isBind=='n'){
                $('#isBind option:eq(2)').attr('selected','selected');
            }else{
                $('#isBind option:eq(0)').attr('selected','selected');
            }
            form.render();
        }
        if(isPreSale!=''){
            if(isPreSale=='y'){
                $('#isPreSale option:eq(1)').attr('selected','selected');
            }else if(isPreSale=='n'){
                $('#isPreSale option:eq(2)').attr('selected','selected');
            }else{
                $('#isPreSale option:eq(0)').attr('selected','selected');
            }
            form.render();
        }
        if(isReturn!=''){
            if(isReturn=='y'){
                $('#isReturn option:eq(1)').attr('selected','selected');
            }else if(isReturn=='n'){
                $('#isReturn option:eq(2)').attr('selected','selected');
            }else{
                $('#isReturn option:eq(0)').attr('selected','selected');
            }
            form.render();
        }
        if(isUsed!=''){
            if(isUsed=='y'){
                $('#isUsed option:eq(1)').attr('selected','selected');
            }else if(isUsed=='n'){
                $('#isUsed option:eq(2)').attr('selected','selected');
            }else{
                $('#isUsed option:eq(0)').attr('selected','selected');
            }
            form.render();
        }
        if(saleCode!=''){
            if(saleCode=='100'){
                $('#saleCode option:eq(1)').attr('selected','selected');
            }else if(saleCode=='200'){
                $('#saleCode option:eq(2)').attr('selected','selected');
            }else if(saleCode=='300'){
                $('#saleCode option:eq(3)').attr('selected','selected');
            }else{
                $('#saleCode option:eq(0)').attr('selected','selected');
            }
            form.render();
        }
        if(isGifts!='') {
            if (isGifts == '10') {
                $('#isGifts option:eq(1)').attr('selected', 'selected');
            } else if (isGifts == '20') {
                $('#isGifts option:eq(2)').attr('selected', 'selected');
            } else if (isGifts == '30') {
                $('#isGifts option:eq(3)').attr('selected', 'selected');
            } else {
                $('#isGifts option:eq(0)').attr('selected', 'selected');
            }
            form.render();
        }
        if(prdType!=''){
            $("#prdType option[value='"+prdType+"']").attr('selected','selected');
            form.render();
        }
        if(supName!=''){
            $("#supName option[value='"+supName+"']").attr('selected','selected');
            form.render();
        }

//        $('.update').click(function () {
//            var val = $(this).attr('val');
//            layer.open({
//                title:'修改商品'
//                ,area:['400px','500px']
//                ,type:2
//                ,content:"/prd/modifyProduct.do?id="+val
//            })
//        })

        $('.addDetailedProduct').click(function () {
            var val = $(this).attr('val');
            var tpNm = $(this).attr('tpNm');
            var prdName = $(this).attr('prdName');
            var prdNo = $(this).attr('prdNo');
            layer.open({
                title:'添加商品明细'
                ,area:['400px','500px']
                ,type:2
                ,content:"/prd/addDetailedProduct.do?tpCd="+val+"&tpNm="+tpNm+"&prdName="+prdName+"&prdNo="+prdNo
            })
        })

        form.on('select(getProduct)',function () {
            return true;
        })
    });
</script>
</body>
</html>