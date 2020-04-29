package test.java.com.imooc.o2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.java.com.imooc.o2o.BaseTest;

import com.as.dao.ShopDao;
import com.as.entity.Area;
import com.as.entity.PersonInfo;
import com.as.entity.Shop;
import com.as.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testqueryShopList(){
		Shop shop = new Shop();
		//shop.setOwnerId(1L);
		List<Shop> list = shopDao.queryShopList(shop, 0, 4);
		System.out.println("每页的条数："+list.size());
		System.out.println("店铺总数："+shopDao.queryShopCount(shop));
	}
	@Test
	@Ignore
	public void testQueryShopById(){
		long shopId = 7L;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId:"+shop.getArea().getAreaId());
		System.out.println("areaName:"+shop.getArea().getAreaName());
	}
	@Test
	@Ignore
	public void insertShopTest(){
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		Area area = new Area();
		
		owner.setUserId(1L);
		shopCategory.setShopCategoryId(1L);
		area.setAreaId(8L);
		
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setArea(area);
		shop.setShopName("test1");
		shop.setPhone("test");
		shop.setAdvice("test1");
		shop.setShopAddr("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		int effectRows = shopDao.insertShop(shop);
		Assert.assertEquals(1,effectRows);
	}
	@Test
	@Ignore 
	public void updateShopTest(){
		Shop shop = new Shop();
		shop.setShopId(4L);
		shop.setShopName("testPhone");
		shop.setPhone("testPhone");
		shop.setShopAddr("test");
		shop.setLastEditTime(new Date());
		int effectRows = shopDao.updateShop(shop);
		Assert.assertEquals(1,effectRows);
	}

}
