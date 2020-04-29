package com.as.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.as.dao.ShopDao;
import com.as.dto.ShopExecution;
import com.as.entity.Shop;
import com.as.enums.ShopStateEnum;
import com.as.service.ShopService;
import com.as.util.FileUtil;
import com.as.util.ImageUtil;
import com.as.util.PageCalculator;
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Override
	@Transactional

	public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg)throws RuntimeException {
		
		if (shop==null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//
			int effectNum = shopDao.insertShop(shop);
			if (effectNum<=0) {
				throw new RuntimeException("error");
			}else {
				if (shopImg != null) {
					try {
						//
						addShopImg(shop, shopImg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new RuntimeException("add shopIMG error:"+e.getMessage());
					}
					//
					effectNum = shopDao.updateShop(shop);
					if (effectNum<=0) {
						throw new RuntimeException("error");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("addshop error:"+ e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
		//获取图片路径
		String dest = FileUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}
	@Override
	public Shop getByShopId(long shopId) {
		// TODO Auto-generated method stub
		return shopDao.queryByShopId(shopId);
	}
	@Override
	@Transactional
	public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg)
			throws RuntimeException {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		} else {
			try {
				if (shopImg != null) {
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						FileUtil.deleteFile(tempShop.getShopImg());//delete original Files
					}
					addShopImg(shop, shopImg);
				}
				shop.setLastEditTime(new Date());
				int effectedNum = shopDao.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {// update success
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new RuntimeException("modifyShop error: "
						+ e.getMessage());
			}
		}
	}
	/**
	 * pageIndex：表示第几页
	 * pagesize：每页显示的条数
	 * rowIndex:从所有数据中的第几条开始显示-和数据库匹配
	 */
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex,
			int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if (shopList!=null) {
			se.setShopList(shopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
}
