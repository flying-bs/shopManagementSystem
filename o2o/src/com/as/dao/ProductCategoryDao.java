package com.as.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.as.entity.ProductCategory;


public interface ProductCategoryDao {
	/**
	 * 商品类别列表
	 * @param ProductCategory
	 * @return
	 */
	 List<ProductCategory> queryProductCategory(@Param("productCategoryCondition")ProductCategory productCategoryCondition);
	
	/**
	 * 删除商品类别根据商品类别id和商铺ID(保证此商品类别是此店铺的而非其他店铺)
	 * 	 * @param productCategoryId
	 * @return
	 */
	public int deleteProductCategoryById(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
	
	/**
	 * 新增商品类别
	 */
	public int batchInsertProductCategory(List<ProductCategory> productCategories);
}
