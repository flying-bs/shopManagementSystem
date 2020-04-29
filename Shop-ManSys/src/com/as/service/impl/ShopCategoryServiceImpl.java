package com.as.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.dao.ShopCategoryDao;
import com.as.entity.ShopCategory;
import com.as.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Override
	public List<ShopCategory> getAllSecondLevelShopCategory()
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopCategory> getFirstLevelShopCategoryList()
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShopCategory getShopCategoryById(Long shopCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		// TODO Auto-generated method stub
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

	@Override
	public List<ShopCategory> getShopCategoryList(Long parentId)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

};
