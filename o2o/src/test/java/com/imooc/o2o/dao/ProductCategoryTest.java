package test.java.com.imooc.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.java.com.imooc.o2o.BaseTest;

import com.as.dao.ProductCategoryDao;
import com.as.entity.ProductCategory;

public class ProductCategoryTest extends BaseTest {

	@Autowired
	ProductCategoryDao productCategoryDao;
	
	@Test
	public void batchInsertProductCategoryTest(){
		ProductCategory category = new ProductCategory();
		category.setProductCategoryName("商品类别1");
		category.setProductCategoryDesc("Good");
		category.setPriority(3);
		category.setCreateTime(new Date());
		category.setShopId(9L);
		category.setLastEditTime(new Date());
		
		ProductCategory category2 = new ProductCategory();
		category2.setProductCategoryName("商品类别2");
		category2.setProductCategoryDesc("GREAT");
		category2.setPriority(3);
		category2.setCreateTime(new Date());
		category2.setShopId(9L);
		category2.setLastEditTime(new Date());
		
		List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
		productCategories.add(category);
		productCategories.add(category2);
		int effectNum = productCategoryDao.batchInsertProductCategory(productCategories);
		Assert.assertEquals(2, effectNum);
	}
	@Test
	@Ignore
	public void queryProductCategory(){
		ProductCategory productCategoryCondition = new ProductCategory();
		productCategoryCondition.setShopId(9L);
		List<ProductCategory> list = productCategoryDao.queryProductCategory(productCategoryCondition);
		System.out.println("商品类别总数："+list.size());
		System.out.println("商品类别名称："+list.get(2).getProductCategoryName());
	}
	
	@Test
	@Ignore
	public void deleteProductCategoryById(){
		ProductCategory productCategoryCondition = new ProductCategory();
		productCategoryCondition.setProductCategoryId(8L);
		long shopId = 9L;
		int effectNum = productCategoryDao.deleteProductCategoryById(productCategoryCondition.getProductCategoryId(),shopId);
		System.out.println("真or假："+effectNum);
	}
}
