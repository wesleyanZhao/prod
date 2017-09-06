package com.lanou.cn.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by landfash on 2017/7/17.
 */
public interface ProductMapper {

    /**
     * 添加商品
     * @param params
     */
    void addProduct(Map<String,Object> params);

    /**
     * 获得商品类别
     * @return
     */
    List<Map<String,Object>> getPrdType();

    /**
     * 获得供应商
     * @return
     */
    List<Map<String,Object>> getSupInfo();

    /**
     * 获得所有商品
     * @return
     */
    List<Map<String,Object>> getAllProduct(Map<String,Object> params);

    /**
     * 获得可用仓库
     * @return
     */
    List<Map<String,Object>> getWare(Map<String,Object> params);

    /**
     * 插入到商品明细表，并得到此次插入的数据的ID
     * @param params
     * @return
     */
    void addDetail(Map<String,Object> params);

    /**
     * 保存到明细-仓储表
     * @param params
     */
    void addWarePrdRel(Map<String,Object> params);

    /**
     * 查询明细商品分页
     * @param params
     * @return
     */
    List<Map<String,Object>> getDetailed(Map<String,Object> params);

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
    void saveModifyDetailed(Map<String,Object> params);

    /**
     * 保存明细仓储
     * @param params
     */
    void saveModifyWare(Map<String,Object> params);
}
