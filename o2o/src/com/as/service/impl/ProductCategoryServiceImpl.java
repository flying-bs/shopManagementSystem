package com.as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.as.dao.ProductCategoryDao;
import com.as.dto.ProductCategoryExecution;
import com.as.entity.ProductCategory;
import com.as.enums.ProductCategoryStateEnum;
import com.as.exceptions.ProductCategoryOperationException;
import com.as.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCateList(
			ProductCategory productCategoryCondition) {
		// TODO Auto-generated method stub
		return productCategoryDao.queryProductCategory(productCategoryCondition);
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategoryById(long productCategoryId,long shopId)throws ProductCategoryOperationException {
		try {
			int effectNum = productCategoryDao.deleteProductCategoryById(productCategoryId,shopId);
			if (effectNum<-0) {
				throw new ProductCategoryOperationException("商品类别删除失败！");
			}else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductcategory error"+e.getMessage());
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException{
		if (productCategoryList!= null && productCategoryList.size()>0) {
			try {
				int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectNum<=0) {
					return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
				}else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("Add error"+e.getMessage());
			}
		}else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

}
