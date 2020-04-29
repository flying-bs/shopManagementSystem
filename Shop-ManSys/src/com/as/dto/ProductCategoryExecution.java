package com.as.dto;

import java.util.List;

import com.as.entity.ProductCategory;
import com.as.entity.Shop;
import com.as.enums.ProductCategoryStateEnum;
import com.as.enums.ShopStateEnum;


/**
 * dto类
 */
public class ProductCategoryExecution {
	//结果状态
	private int state;

	//状态标识内容
	private String stateInfo;

	private int count;

	private ProductCategory ProductCategory;

	private List<ProductCategory> ProductCategoryList;

	public ProductCategoryExecution() {
	}

	// 操作失败的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> ProductCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.ProductCategoryList = ProductCategoryList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ProductCategory getProductCategory() {
		return ProductCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		ProductCategory = productCategory;
	}

	public List<ProductCategory> getProductCategoryList() {
		return ProductCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		ProductCategoryList = productCategoryList;
	}


}