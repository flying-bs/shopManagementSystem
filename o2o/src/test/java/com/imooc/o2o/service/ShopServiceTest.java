package test.java.com.imooc.o2o.service;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.java.com.imooc.o2o.BaseTest;

import com.as.dto.ShopExecution;
import com.as.entity.Area;
import com.as.entity.PersonInfo;
import com.as.entity.Shop;
import com.as.entity.ShopCategory;
import com.as.enums.ShopStateEnum;
import com.as.service.ShopService;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void getshopList(){
		Shop shop = new Shop();
		shop.setOwnerId(1L);
		ShopExecution shopExecution = shopService.getShopList(shop,1,4);
		//System.out.println("第一家名称："+shopExecution.getShopList().get(0).getShopName()+"第二家名称："+shopExecution.getShopList().get(1).getShopName());
		System.out.println("店铺列表个数："+shopExecution.getShopList().size());
		System.out.println("店铺总数："+shopExecution.getCount());
	}
	@Test
	@Ignore
	public void modifyShop() throws RuntimeException,FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(4L);
		shop.setShopName("修改后的Shop");
		shop.setShopImg("D:/bs/04延时记录/素材/微信公号素材/哲理图片/芒噶orphoto.jpg");
		ShopExecution se = shopService.modifyShop(shop, null);
	}
	@Test
	@Ignore
	public void testAddShop() throws Exception {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		Area area = new Area();
		area.setAreaId(7L);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(sc);
		shop.setShopName("mytest1");
		shop.setShopDesc("mytest1");
		shop.setShopAddr("testaddr1");
		shop.setPhone("13810524526");
		shop.setShopImg("test1");
		shop.setLongitude(1D);
		shop.setLatitude(1D);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("SHing");
		shop.setArea(area);
		shop.setShopCategory(sc);
		ShopExecution se = shopService.addShop(shop,null);
		Assert.assertEquals("mytest1", se.getShop().getShopName());
	}


//	@Test
//	public void testGetByEmployeeId() throws Exception {
//		long employeeId = 1;
//		ShopExecution shopExecution = shopService.getByEmployeeId(employeeId);
//		List<Shop> shopList = shopExecution.getShopList();
//		for (Shop shop : shopList) {
//			System.out.println(shop);
//		}
//	}
//
//	@Ignore
//	@Test
//	public void testGetByShopId() throws Exception {
//		long shopId = 1;
//		Shop shop = shopService.getByShopId(shopId);
//		System.out.println(shop);
//	}

}
