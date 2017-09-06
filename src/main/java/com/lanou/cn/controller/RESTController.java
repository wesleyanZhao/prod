package com.lanou.cn.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.lanou.cn.service.RESTService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by landfash on 2017/7/20.
 */
@RestController
@RequestMapping("/rest")
public class RESTController {

    @Resource
    private RESTService restService;

    @RequestMapping("getProduct")
    public Map<String,Object> getProduct(@RequestParam Map<String,Object> params){
        System.out.println(params);
        Map<String,Object> map = restService.getProduct(params);
        System.out.println(params);
        PageInfo<Map<String,Object>> pageInfo = (PageInfo<Map<String,Object>>) map.get("page");
        Integer pages = pageInfo.getPages();//总页数
        List<Map<String,Object>> list = pageInfo.getList();
        Integer pageNum = pageInfo.getPageNum();//当前页
        Long total = pageInfo.getTotal();//总数
        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("pages",pages);
        pageMap.put("list",list);
        pageMap.put("pageNum",pageNum);
        pageMap.put("total",total);
        pageMap.put("tp",map.get("tpList"));
        return pageMap;
    }

    @RequestMapping("getDetailedProduct")
    public List<Map<String,Object>> getDetailedProduct(@RequestParam Map<String,Object> params){
        return restService.getDetailedProduct(params);
    }

    @RequestMapping("produceOrder")
    public Map<String,Object> produceOrder(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try{
            List<Map<String,Object>> ware = restService.produceOrder(params);
            result.put("ware",ware);
            result.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
    }

    @RequestMapping("barterGoods")
    public Map<String,Object> barterGoods(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try{
            restService.barterGoods(params);
            result.put("result","success");
        }catch(Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    //参数：商品明细编号prdDtlNo 库存占用数occCount 库存编号wNo
    @RequestMapping("completePay")
    public Map<String,Object> completePay(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try{
            restService.completePay(params);
            result.put("result","success");
        }catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    @RequestMapping("getGifts")
    public List<Map<String,Object>> getGifts(@RequestParam Map<String,Object> params){
        return restService.getGifts(params);
    }

    @RequestMapping("getType")
    public List<Map<String,Object>> getType(@RequestParam Map<String,Object> params){
        return restService.getType(params);
    }

    @RequestMapping("getPrd")
    public Map<String,Object> getPrd(@RequestParam Map<String,Object> params){
        List<Map<String,Object>> list = restService.getPrd(params);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @RequestMapping("getDetailedPrdForPlan")
    public Map<String,Object> getDetailedPrdPlan(@RequestParam Map<String,Object> params){
        String product = (String) params.get("product");
//        List arr = JSON.parseArray(product,Map.class);
        List<Map<String,Object>> lsit = (List<Map<String,Object>>)JSONArray.parse(product);

        params.put("product",lsit);
        return restService.getDetailedForPlan(params);
    }

    @RequestMapping("getPrdDetailed")
    public Map<String,Object> getPrdDetailed(@RequestParam Map<String,Object> params){
        List<Map<String,Object>> result = restService.getPrdDetailed(params);
//        if(null != result ){
        return result.get(0);
//        }else{
//            return null;
//        }
    }

    @RequestMapping("getPrdDetailedForList")
    public List<Map<String,Object>> getPrdDetailedForList(@RequestParam Map<String,Object> params){
        List<Map<String,Object>> result = new ArrayList<>();
        String prdDtlListStr = (String) params.get("prdDtlList");
        List<Map<String,Object>> prdDtlList = (List<Map<String,Object>>)JSONArray.parse(prdDtlListStr);
        for(int i = 0 ; i < prdDtlList.size() ; i ++){
            List<Map<String,Object>> list = restService.getPrdDetailed(prdDtlList.get(i));
            list.get(0).put("num",prdDtlList.get(i).get("num"));
            result.add(list.get(0));
        }
        return result;
    }

    @RequestMapping("backWareCount")
    public Map<String,Object> addCount(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try {
            restService.addCount(params);
            result.put("result","success");
        }catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    @RequestMapping("getWare")
    public List<Map<String,Object>> getWare(@RequestParam Map<String,Object> params){
        return restService.getWare(params);
    }

    @RequestMapping("cancelPayWCount")
    public Map<String,Object> cancelPayWCount(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try{
            restService.cancelPayWCount(params);
            result.put("result","success");
        }catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    @RequestMapping("getPrdForPrdDtl")
    public List<Map<String,Object>> getPrdForPrdDtl(@RequestParam Map<String,Object> params){
        return restService.getPrdForPrdDtl(params);
    }

    /**
     * 获取商品品论前的通过商品id获取商品编号明细名称等信息
     * @param params
     * @return
     */
    @RequestMapping("getPrdNoPrdDtlName")
    public List<Map<String,Object>> getPrdNoPrdDtlName(@RequestParam Map<String,Object> params){
        //System.out.println("------"+params);
        List<Map<String, Object>> result = restService.getPrdNoPrdDtlName(params);
        //System.out.println("商品编号"+result);
        return result;
    }

    /**
     * 查询购物车的商品明细和数量
     * @param params
     * @return
     */
    @RequestMapping("findShopCar")
    public Map<String,Object> findShopCar(@RequestParam Map<String,Object> params) {
        return restService.findShopCar(params);
    }

    /**
     * 更新购物车数量
     * @param params
     */
    @RequestMapping("updateShopCar")
    public Map<String,Object> updateShopCar(@RequestParam Map<String,Object> params) {
        Map<String,Object> result = new HashMap<>();
        try {
            restService.updateShopCar(params);
            result.put("result","success");
        }catch (Exception e) {
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    /**
     * 添加购物车
     * @param params
     */
    @RequestMapping("addShopCar")
    public Map<String,Object> addShopCar(@RequestParam Map<String,Object> params) {
        Map<String,Object> result = new HashMap<>();
        String imgUrl = (String) params.get("phone");
        params.put("imgUrl",imgUrl);
        try {
            restService.addShopCar(params);
            result.put("result","success");
        }catch (Exception e) {
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    /**
     * 根据登陆的人的编号查询所有的购物车
     * @param params
     * @return
     */
    @RequestMapping("getAllShop")
    public List<Map<String,Object>> getAllShop(@RequestParam Map<String,Object> params) {
        return restService.getAllShop(params);
    }

    /**
     * 根据id删除购物车数据
     * @param params
     * @return
     */
    @RequestMapping("deleteShopCar")
    public Map<String,Object> deleteShopCar(@RequestParam Map<String,Object> params) {
        Map<String,Object> result = new HashMap<>();
        try {
            restService.deleteShopCar(params);
            result.put("result","success");
        }catch (Exception e) {
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    /**
     * 根据vipNo删除购物车所有数据
     * @param params
     * @return
     */
    @RequestMapping("deleteAllShopCar")
    public Map<String,Object> deleteAllShopCar(@RequestParam Map<String,Object> params) {
        Map<String,Object> result = new HashMap<>();
        try {
            restService.deleteAllShopCar(params);
            result.put("result","success");
        }catch (Exception e) {
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }


}
