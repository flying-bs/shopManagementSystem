package test.java.com.imooc.o2o.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.java.com.imooc.o2o.BaseTest;

import com.as.dto.ProductCategoryExecution;
import com.as.entity.ProductCategory;
import com.as.service.ProductCategoryService;

public class ProductCategoryServiceTest extends BaseTest{

	@Autowired
	ProductCategoryService productCategoryService;
	@Test
	
	public void productCategoryServiceTest(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setShopId(9L);
		List<ProductCategory> list = productCategoryService.getProductCateList(productCategory);
		System.out.println(list.size());
		System.out.println("商店名称："+list.get(2).getProductCategoryName());
	}
	@Test
	@Ignore
	public void deleteProductCategoryById(){
		long productCategoryId = 10L;
		long shopId = 9L;
		ProductCategoryExecution pe = productCategoryService.deleteProductCategoryById(productCategoryId,shopId);
		System.out.println(pe.getState());
		
	}
}
