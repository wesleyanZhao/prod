package com.lanou.cn.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by landfash on 2017/7/21.
 */
public interface RESTService {

    Map<String,Object> getProduct(Map<String,Object> params);

    List<Map<String,Object>> getDetailedProduct(Map<String,Object> params);

    List<Map<String,Object>> produceOrder(Map<String,Object> params) throws Exception;

//    void updateWOccupyCount(Map<String,Object> map) throws Exception;

    /**
     * 获得能作为赠品的商品
     * @param params
     * @return
     */
    List<Map<String,Object>> getGifts(Map<String,Object> params);

    /**
     * 获得商品类别
     * @param params
     * @return
     */
    List<Map<String,Object>> getType(Map<String,Object> params);

    List<Map<String,Object>> getPrd(Map<String,Object> params);

    List<Map<String,Object>> getAllType();

    /**
     * 通过明细编号获得商品信息
     * @param params
     * @return
     */
    Map<String,Object> getDetailedForPlan(Map<String,Object> params);

    /**
     * 通过明细编号获得商品详细信息
     * @param params
     * @return
     */
    List<Map<String,Object>> getPrdDetailed(Map<String,Object> params);

    /**
     * 退货后返回库存
     * @param params
     */
    void addCount(Map<String,Object> params) throws Exception;

    /**
     * 换货的库存修改
     * @param params
     * @throws Exception
     */
    void barterGoods(Map<String,Object> params) throws Exception;

    /**
     * 完成支付，更改库存数及库存占用数
     * @param params
     * @throws Exception
     */
    void completePay(Map<String,Object> params) throws Exception;

    /**
     * 获得库存信息
     * @param params
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getWare(Map<String,Object> params);

    /**
     * 取消订单恢复库存
     * @param params
     * @throws Exception
     */
    void cancelPayWCount(Map<String,Object> params) throws Exception;

    /**
     * 根据明细获得此商品下的所有明细
     * @param params
     * @return
     */
    List<Map<String,Object>> getPrdForPrdDtl(Map<String,Object> params);

    /**
     * 获取评论内容
      * @return
     */
    List<Map<String,Object>> getPrdComContent(Map<String,Object> params);

    /**
     * 分页评论
     * @param params
     * @return
     */
    PageInfo<Map<String, Object>> findAllComPageList(Map<String, Object> params);

    /**
     *获取商品编号明细名称通过商品id
     * @return
     */
    List<Map<String,Object>> getPrdNoPrdDtlName(Map<String,Object> params);

    /**
     * 查询购物车的商品明细和数量
     * @param params
     * @return
     */
    Map<String,Object> findShopCar(Map<String, Object> params);

    /**
     * 更新购物车数量
     * @param params
     */
    void updateShopCar(Map<String, Object> params);

    /**
     * 添加购物车
     * @param params
     */
    void addShopCar(Map<String, Object> params);

    /**
     * 根据登陆的人的编号查询所有的购物车
     * @param params
     * @return
     */
    List<Map<String, Object>> getAllShop(Map<String,Object> params);

    /**
     * 根据id删除购物车数据
     * @param params
     */
    void deleteShopCar(Map<String,Object> params);

    /**
     * 根据vipNo删除购物车所有数据
     * @param params
     */
    void deleteAllShopCar(Map<String,Object> params);

}
