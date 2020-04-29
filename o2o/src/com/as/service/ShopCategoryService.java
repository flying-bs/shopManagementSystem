package com.as.service;

import java.io.IOException;
import java.util.List;

import com.as.entity.ShopCategory;

public interface ShopCategoryService {
	
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) ;
	/**
	 * 
	 * 
	 * @param long shopId
	 * @return List<ProductCategory>
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	List<ShopCategory> getFirstLevelShopCategoryList() throws IOException;

	/**
	 * 
	 * @param parentId
	 * @return
	 * @throws IOException
	 */
	List<ShopCategory> getShopCategoryList(Long parentId) throws IOException;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	List<ShopCategory> getAllSecondLevelShopCategory() throws IOException;

	/**
	 * 
	 * @param shopCategory
	 * @return
	 */
	ShopCategory getShopCategoryById(Long shopCategoryId);

	/**
	 * 
	 * @param shopCategory
	 * @param thumbnail
	 * @return
	 */
//	ShopCategoryExecution addShopCategory(ShopCategory shopCategory,
//			CommonsMultipartFile thumbnail);
//
//	/**
//	 * 
//	 * @param shopCategory
//	 * @param thumbnail
//	 * @param thumbnailChange
//	 * @return
//	 */
//	ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory,
//			CommonsMultipartFile thumbnail, boolean thumbnailChange);
//
//	/**
//	 * 
//	 * @param shopCategoryId
//	 * @return
//	 */
//	ShopCategoryExecution removeShopCategory(long shopCategoryId);
//
//	/**
//	 * 
//	 * @param shopCategoryIdList
//	 * @return
//	 */
//	ShopCategoryExecution removeShopCategoryList(List<Long> shopCategoryIdList);

}
