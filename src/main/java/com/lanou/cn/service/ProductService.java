package com.lanou.cn.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by landfash on 2017/7/17.
 */
public interface ProductService {

    /**
     * 添加新商品
     * @param params
     */
    void addProduct(Map<String,Object> params) throws Exception;

    /**
     * 获得商品类别和供应商
     * @return
     */
    Map<String,List<Map<String,Object>>> getTypeAndSup();

    PageInfo<Map<String,Object>> getAllProductPageList(Map<String,Object> params);

    Map<String,Object> getModifyProduct(Map<String,Object> params);

    List<Map<String,Object>> getWare(Map<String,Object> params);

    /**
     * 添加商品明细和仓储
     * @param params
     */
    void addDetailAndWare(Map<String,Object> params,String[] wNo) throws Exception;

    /**
     * 明细分页
     * @return
     */
    PageInfo<Map<String,Object>> getAllDetailedPageList(Map<String,Object> params);

    /**
     * 获得明细可修改信息
     * @param params
     * @return
     */
    List<Map<String,Object>> getModifyDetailed(Map<String,Object> params);

    /**
     * 保存明细修改
     * @param params
     */
    void saveModifyDetailed(Map<String,Object> params) throws Exception;

    /**
     * 提供接口查询明细
     * @param params
     * @return
     */
    List<Map<String,Object>> getDetailedProduct(Map<String,Object> params);
}
