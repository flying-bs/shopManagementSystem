package com.as.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.as.entity.Shop;

public interface ShopDao {
	
	/**
	 * 分页查询
	 */
	/**
	 * 分页查询店铺,可输入的条件有：店铺名（模糊），店铺状态，店铺Id,店铺类别,区域ID
	 * 
	 * @param shopCondition
	 * @param rowIndex 从第几行开始取
	 * @param pageSize 返回条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * 返回queryShopList总数
	 * 
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	
	/**
	 * 通过shopId查询店铺
	 */
	
	Shop queryByShopId(long shopId);
	/**
	 * insert
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	/**
	 * update
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
