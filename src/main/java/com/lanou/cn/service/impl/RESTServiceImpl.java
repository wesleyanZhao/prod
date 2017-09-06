package com.lanou.cn.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cn.Utils.calculateUtils;
import com.lanou.cn.mapper.RESTMapper;
import com.lanou.cn.service.RESTService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by landfash on 2017/7/21.
 */
@Service
public class RESTServiceImpl implements RESTService{

    @Resource
    private RESTMapper restMapper;

    @Override
    public Map<String, Object> getProduct(Map<String, Object> params) {
        Map<String,Object> map = new HashMap<>();
        Integer currentPage = params.get("currentPage") == null ? 1 : Integer.parseInt((String)params.get("currentPage"));
        PageHelper.startPage(currentPage,16);
        List<Map<String,Object>> list = restMapper.getProduct(params);
        List<Map<String,Object>> tpList = restMapper.getAllType();
        PageInfo<Map<String,Object>> page = new PageInfo<>(list);
        map.put("page",page);
        map.put("tpList",tpList);
        return map;
    }

    @Override
    public List<Map<String,Object>> getDetailedProduct(Map<String,Object> params){
        return restMapper.getDetailedProduct(params);
    }

    @Override
    @Transactional
    public List<Map<String,Object>> produceOrder(Map<String, Object> params) throws Exception{
        System.out.println("produceOrder>>>>>>>>>"+params);
        List<Map<String,Object>> resultList = new ArrayList<>();
        String product = (String) params.get("prdDtlList");

        List<Map<String,Object>> wList = (List<Map<String,Object>>)JSONArray.parse(product);
        System.out.println("wList>>>>>>>>>>>>>>>>>"+wList);
        if(!CollectionUtils.isEmpty(wList)){
            int sum1 = 0;
            int sum2 = 0 ;
            for(int j = 0 ; j < wList.size() ; j ++){
                Map<String,Object> result = new HashMap<>();
                restMapper.lock(wList.get(j));
                List<Map<String,Object>> ware = restMapper.getWare(wList.get(j));
                List<Map<String,Object>> w = new ArrayList<>();
                System.out.println("ware>>>>>>>" + ware);
                for(int s = 0 ; s < ware.size() ; s ++){
                    sum1 += (Integer) ware.get(s).get("wCount");
                    if(null != ware.get(s).get("wOccupyCount")){
                        sum2 += (Integer) ware.get(s).get("wOccupyCount");
                    }
                }
                System.out.println("sum1>>>>>>>>>>>>>>"+sum1);
                System.out.println("sum2>>>>>>>>>>>>>>"+sum2);
                if(Integer.parseInt((String)wList.get(j).get("num"))<=sum1-sum2){
                    for(int i = 0 ; i < ware.size() ; i ++){
                        int num1 = (Integer) ware.get(i).get("wCount");
                        int num2 = 0;
                        if(null != ware.get(i).get("wOccupyCount")){
                            num2 = (Integer) ware.get(i).get("wOccupyCount");
                        }
                        int productOrderNumber = Integer.parseInt((String)wList.get(j).get("num"));
                        if(num1>0){
                            if(productOrderNumber<=num1-num2){
                                Map<String,Object> mapOrder = new HashMap<>();
                                mapOrder.put("id",ware.get(i).get("id"));
                                int temp_num = Integer.parseInt((String)wList.get(j).get("num"));
                                mapOrder.put("num",num2+temp_num);
                                restMapper.addOccupy(mapOrder);
                                Map<String,Object> wareNum = new HashMap<>();
                                wareNum.put("id",ware.get(i).get("id"));
                                wareNum.put("wNo",ware.get(i).get("wNo"));
                                wareNum.put("num",wList.get(j).get("num"));
                                w.add(wareNum);
                                break;
                            }else {
                                Integer num = (Integer) (ware.get(i).get("wCount")) - num2;
                                Map<String, Object> mapOrder = new HashMap<>();
                                mapOrder.put("id", ware.get(i).get("id"));
                                mapOrder.put("num", num1);
                                restMapper.addOccupy(mapOrder);
                                wList.get(j).put("num", productOrderNumber - num + "");
                                Map<String, Object> wareNum = new HashMap<>();
                                wareNum.put("id", ware.get(i).get("id"));
                                wareNum.put("wNo",ware.get(i).get("wNo"));
                                wareNum.put("num", num);
                                w.add(wareNum);
                            }
                        }

                    }
                }else{
                    throw new Exception();
                }
                result.put("prdDtlNo",wList.get(j).get("prdDtlNo"));
                result.put("prdNo",ware.get(0).get("prdNo"));
                result.put("wareInfo",w);
                resultList.add(result);
            }
        }else{
            throw new Exception();
        }
        return resultList;
    }

    @Override
    public void barterGoods(Map<String,Object> params) throws Exception{
        List<Map<String,Object>> wareInfo = restMapper.getWare(params);
        for(int i = 0 ; i < wareInfo.size() ; i ++){
            int wCount = Integer.parseInt(wareInfo.get(i).get("wCount").toString());
            if(wCount>=1){
                int num = Integer.parseInt((String)params.get("prdNum"));
                wCount = wCount - num;
                wareInfo.get(i).put("wCount",wCount);
                restMapper.backCount(wareInfo.get(i));
                break;
            }
        }
    }



//    @Override
//    public void updateWOccupyCount(Map<String,Object> map) throws Exception{
//        List<Map<String,Object>> list = (List<Map<String,Object>>) JSONArray.parse((String)map.get("list"));
//        for(int i = 0 ; i < list.size() ; i ++){
//            List<Map<String,Object>> ware = restMapper.getWare(list.get(i));
//            map.put("id",ware.get(0).get("id"));
//            restMapper.updateWOccupyCount(map);
//        }
////        restMapper.updateWOccupyCount(map);
//
//    }

    @Override
    public List<Map<String, Object>> getGifts(Map<String, Object> params) {
        return restMapper.getGifts(params);
    }

    @Override
    public List<Map<String, Object>> getType(Map<String, Object> params) {
        return restMapper.getType(params);
    }

    @Override
    public List<Map<String,Object>> getPrd(Map<String,Object> params){
        return restMapper.getPrd(params);
    }

    @Override
    public List<Map<String,Object>> getAllType(){
        return restMapper.getAllType();
    }

    @Override
    public Map<String,Object> getDetailedForPlan(Map<String,Object> params){
        List<Map<String,Object>> product = (List<Map<String,Object>>) params.get("product");
        for(int i = 0 ; i < product.size() ; i ++){
            List<Map<String,Object>> detail = restMapper.getDetailedForPlan(product.get(i));
            product.get(i).put("ctrlRate",detail.get(0).get("ctrlRate"));
            product.get(i).put("prdNo",detail.get(0).get("prdNo"));
            product.get(i).put("prdDtlIptPrice",detail.get(0).get("iptPrice"));
            if(null != product.get(i).get("salePrice")){
                double num = Double.parseDouble(product.get(i).get("num").toString());
                double price = Double.parseDouble(product.get(i).get("salePrice").toString());
                price = calculateUtils.mul(price,num);
                product.get(i).put("price", price);
            }
        }
        Map<String,Object> result = new HashMap<>();
        result.put("product",product);
        return result;
    }

    @Override
    public List<Map<String,Object>> getPrdDetailed(Map<String,Object> params){
        return restMapper.getPrdDetailed(params);
    }

    @Override
    public void addCount(Map<String, Object> params) throws Exception{
        List<Map<String,Object>> wareInfo = restMapper.getWare(params);
        Map<String,Object> ware = wareInfo.get(0);
        int wareCount = (int)ware.get("wCount");
        int backCount = Integer.parseInt(params.get("prdNum").toString());
        wareCount += backCount;
        ware.put("wCount",wareCount);
        ware.put("prdDtlNo",params.get("prdDtlNo"));
        ware.put("id",wareInfo.get(0).get("id"));
        restMapper.backCount(ware);
    }

    @Override
    @Transactional
    public void completePay(Map<String,Object> params) throws Exception{
        String order = (String)params.get("order");
        List<Map<String,Object>> orderlist = (List<Map<String,Object>>)JSONArray.parse(order);
        System.out.println("orderlist-----------"+orderlist);
        for(int i = 0 ; i < orderlist.size() ; i++){
            List<Map<String,Object>> wareComplete = restMapper.getWareForComplete(orderlist.get(i));
            if(!CollectionUtils.isEmpty(wareComplete)){
                int wCount = 0 ;
                int wOccupyCount = 0 ;
                int occCount = Integer.parseInt(orderlist.get(i).get("occCount").toString());
                for(int j = 0 ; j < wareComplete.size() ; j ++){
                    System.out.println("wareComplete::::::"+wareComplete);
                    Map<String,Object> wareNum = wareComplete.get(j);
                    wCount += (int)wareNum.get("wCount");
                    if(null != wareNum.get("wOccupyCount")){
                        wOccupyCount += (int)wareNum.get("wOccupyCount");
                    }
                }
                System.out.println("wCount:::"+wCount);
                System.out.println("wOccupyCoutn:::::"+wOccupyCount);
                System.out.println("occCount::::"+occCount);
                if(occCount<=wCount && occCount<=wOccupyCount){
                    for(int j = 0 ; j < wareComplete.size() ; j ++){
                        Map<String,Object> wareNum = wareComplete.get(j);
                        wCount = (int)wareNum.get("wCount");
                        wOccupyCount = 0 ;
                        if(null != wareNum.get("wOccupyCount")){
                            wOccupyCount = (int)wareNum.get("wOccupyCount");
                        }
                        if(wCount <= occCount && wOccupyCount <= occCount){
                            orderlist.get(i).put("num",wCount);
                            restMapper.completePay(orderlist.get(i));
                            occCount -= wCount;
                        }else{
                            orderlist.get(i).put("num",occCount);
                            restMapper.completePay(orderlist.get(i));
                            break;
                        }
                    }
                }else {
                    throw new Exception();
                }
            }
        }
    }

    @Override
    public List<Map<String,Object>> getWare(Map<String,Object> params){
        return restMapper.getWare(params);
    }

    @Override
    public void cancelPayWCount(Map<String,Object> params) throws Exception{
        restMapper.cancelPayWCount(params);
    }

    @Override
    public List<Map<String,Object>> getPrdForPrdDtl(Map<String,Object> params){
        return restMapper.getPrdForPrdDtl(params);
    }


    /**
     * 获取评论数据
     * @param params
     * @return
     */
    @Override
    public List<Map<String, Object>> getPrdComContent(Map<String,Object> params) {
        return restMapper.getPrdComContent(params);
    }

    /**
     * 评论分页
     * @param params
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> findAllComPageList(Map<String, Object> params) {
        Integer currentPage = params.get("currentPage") == null ? 1:Integer.parseInt((String)params.get("currentPage"));
        PageHelper.startPage(currentPage, 5);
        List<Map<String,Object>> list = restMapper.getPrdComContent(params);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> getPrdNoPrdDtlName(Map<String, Object> params) {
        return restMapper.getPrdNoPrdDtlName(params);
    }

    @Override
    public Map<String, Object> findShopCar(Map<String, Object> params) {
        return restMapper.findShopCar(params);
    }

    @Override
    public void updateShopCar(Map<String, Object> params) {
        restMapper.updateShopCar(params);
    }

    @Override
    public void addShopCar(Map<String, Object> params) {
        restMapper.addShopCar(params);
    }

    @Override
    public List<Map<String, Object>> getAllShop(Map<String, Object> params) {
        return restMapper.getAllShop(params);
    }

    @Override
    public void deleteShopCar(Map<String, Object> params) {
        restMapper.deleteShopCar(params);
    }

    @Override
    public void deleteAllShopCar(Map<String, Object> params) {
        restMapper.deleteAllShopCar(params);
    }
}
