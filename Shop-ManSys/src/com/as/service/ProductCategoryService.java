package com.as.service;

import java.util.List;

import com.as.dto.ProductCategoryExecution;
import com.as.entity.ProductCategory;
import com.as.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	
	//获取类别列表
	public List<ProductCategory> getProductCateList(ProductCategory productCategoryCondition);
	//删除类别
	public ProductCategoryExecution deleteProductCategoryById(long productCategoryId,long shopId);
	
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)throws ProductCategoryOperationException;
	
}
