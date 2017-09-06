package com.lanou.cn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cn.mapper.ProductMapper;
import com.lanou.cn.mapper.RESTMapper;
import com.lanou.cn.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by landfash on 2017/7/17.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private RESTMapper restMapper;

    @Override
    public void addProduct(Map<String, Object> params) throws Exception{
        productMapper.addProduct(params);
    }

    @Override
    public Map<String, List<Map<String, Object>>> getTypeAndSup() {
        List<Map<String,Object>> supInfo = productMapper.getSupInfo();
        List<Map<String,Object>> prdType = productMapper.getPrdType();
        Map<String,List<Map<String,Object>>> typeAndSup = new HashMap<>();
        typeAndSup.put("type",prdType);
        typeAndSup.put("sup",supInfo);
        return typeAndSup;
    }

    @Override
    public PageInfo<Map<String,Object>> getAllProductPageList(Map<String,Object> params){
        Integer currentPage = params.get("currentPage") == null ? 1 : Integer.parseInt((String)params.get("currentPage"));
        PageHelper.startPage(currentPage,5);
        List<Map<String,Object>> list = productMapper.getAllProduct(params);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Map<String,Object> getModifyProduct(Map<String,Object> params){
        List<Map<String,Object>> list = productMapper.getAllProduct(params);
        return list.get(0);
    }


    @Override
    public List<Map<String, Object>> getWare(Map<String,Object> params) {
        return productMapper.getWare(params);
    }

    @Override
    @Transactional
    public void addDetailAndWare(Map<String,Object> params,String[] wNo) throws Exception{
        productMapper.addDetail(params);
        for(int i = 0 ; i < wNo.length ; i ++ ){
            Map<String,Object> wareMap = new HashMap<>();
            wareMap.put("username",params.get("username"));
            wareMap.put("prdNo",params.get("prdNo"));
            wareMap.put("id",params.get("id"));
            wareMap.put("wNo",wNo[i]);
            wareMap.put("wCount",params.get("wCount"+wNo[i]));
            wareMap.put("isUsed",params.get("wIsUsed"+wNo[i]));
            productMapper.addWarePrdRel(wareMap);
        }
    }

    @Override
    public PageInfo<Map<String,Object>> getAllDetailedPageList(Map<String,Object> params){
        Integer currentPage = params.get("currentPage") == null ? 1 : Integer.parseInt((String)params.get("currentPage"));
        PageHelper.startPage(currentPage,5);
        List<Map<String,Object>> list = productMapper.getDetailed(params);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Map<String,Object>> getModifyDetailed(Map<String,Object> params){
        return productMapper.getModifyDetailed(params);
    }

    @Override
    @Transactional
    public void saveModifyDetailed(Map<String,Object> params) throws Exception{
        productMapper.saveModifyDetailed(params);
        productMapper.saveModifyWare(params);
//        for(int i = 0 ; i < wNo.length ; i ++ ){
//            Map<String,Object> map = new HashMap<>();
//            map.put("wNo",wNo[i]);
//            map.put("wCount",params.get("wCount"+wNo[i]));
//            productMapper.saveModifyWare(map);
//        }
    }

    public List<Map<String,Object>> getDetailedProduct(Map<String,Object> params){
//        List<Map<String,Object>> list = productMapper.getDetailed(params);

//        Map<String,Object> detailed = list.get(0);
//        for(int i = 1 ; i < list.size() ; i ++ ){
//            if(detailed.size()>0){
//                if(((Integer)list.get(i).get("id"))==((Integer)detailed.get("id"))){
//                    detailed.put("wCount",(Integer)detailed.get("wCount") + ((Integer)list.get(i).get("wCount")));
//                    continue;
//                }
//            }
//        }

//        List<Map<String,Object>> listProduct = new ArrayList<>();
//        int num = 0 ;
//        outer:for(int i = 0 ; i < list.size() ; i ++){
//            if(listProduct.size()>0){
//                for(int j = 0 ; j < listProduct.size() ; j ++){
//                    if(((Integer)listProduct.get(j).get("id"))==((Integer)list.get(i).get("id"))){
//                        num = (Integer) listProduct.get(j).get("wCount") + (Integer) list.get(i).get("id");
//                        listProduct.get(j).put("wCount",num);
//                        System.out.println(num);
//                        continue outer;
//                    }
//                }
//                num = 0 ;
//                listProduct.add(list.get(i));
//            }else{
//                listProduct.add(list.get(i));
//            }
//        }
        return restMapper.getDetailedProduct(params);
    }
}
