package com.lanou.cn.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 28/6/17.
 */
public interface RESTMapper {

	List<Map<String,Object>> getDetailedProduct(Map<String,Object> params);

	List<Map<String,Object>> getProduct(@Param("params") Map<String,Object> params);

	/**
	 * 获得商品类别
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getType(Map<String,Object> params);

	/**
	 * 给此事务上锁
	 * @param params
	 */
	void lock(Map<String,Object> params);

//	/**
//	 * 获得库存总数
//	 * @param params
//	 * @return
//	 */
//	List<Map<String,Object>> getWCountTotal(Map<String,Object> params);

	List<Map<String,Object>> getWare(Map<String,Object> params);

	void addOccupy(Map<String,Object> params);

	void updateWOccupyCount(Map<String,Object> map);

	/**
	 * 获得能作为赠品的商品
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getGifts(Map<String,Object> params);

	List<Map<String,Object>> getPrd(Map<String,Object> params);

	List<Map<String,Object>> getAllType();

	List<Map<String,Object>> getDetailedForPlan(Map<String,Object> params);

	/**
	 * 获得商品相关信息
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getPrdDetailed(Map<String,Object> params);

	/**
	 * 换货的商品的库存减少
	 * @param params
	 */
	void backCount(Map<String,Object> params);

	/**
	 * 支付完成，修改相应库存
	 * @param params
	 */
	void completePay(Map<String,Object> params);

	/**
	 * 取消订单恢复库存
	 * @param params
	 */
	void cancelPayWCount(Map<String,Object> params);

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
	 * 根据商品id获取商品编号
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getPrdNoPrdDtlName(Map<String,Object> params);

	/**
	 * 完成支付时的查询库存操作
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getWareForComplete(Map<String,Object> params);

	/**
	 * 查询购物车的商品明细和数量
	 * @param params
	 * @return
	 */
	Map<String,Object> findShopCar(@Param("params") Map<String, Object> params);

	/**
	 * 更新购物车数量
 	 * @param params
	 */
	void updateShopCar(@Param("params") Map<String, Object> params);

	/**
	 * 添加购物车
	 * @param params
	 */
	void addShopCar(@Param("params") Map<String, Object> params);

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
