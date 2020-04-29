package com.as.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.as.dto.ShopExecution;
import com.as.entity.Shop;

public interface ShopService {//CommonsMultipartFile
	/**
	 * 添加商铺
	 */
	ShopExecution addShop(Shop shop,CommonsMultipartFile shopImg);
	
	/**
	 * 根据shop分页返回
	 * @param shopConditon
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	
	/**
	 * 查询指定店铺信息
	 * 
	 * @param long
	 *            shopId
	 * @return Shop shop
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 更新店铺信息（从店家角度）
	 * 
	 * @param areaId
	 * @param shopAddr
	 * @param phone
	 * @param shopImg
	 * @param shopDesc
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;
}
