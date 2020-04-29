package com.as.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.as.dto.ProductCategoryExecution;
import com.as.entity.ProductCategory;
import com.as.entity.Shop;
import com.as.enums.ProductCategoryStateEnum;
import com.as.exceptions.ProductCategoryOperationException;
import com.as.service.ProductCategoryService;
import com.as.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/productcategory")
public class ProductCategoryManagementController {
	@Autowired
	ProductCategoryService productCategoryService;

	/**
	 * 获取该用户此商铺所有的类别信息
	 * 
	 * @param productCategoryCondition
	 * @return
	 */
	@RequestMapping(value = "/getproductCategorylist", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getproductCategorylist(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// shopMangage信息业中已经保存了此店铺的信息，所有request直接获取即可。
		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		ProductCategory productCategoryCondition = new ProductCategory();
		productCategoryCondition.setShopId(shop.getShopId());
		List<ProductCategory> productCategorylist = productCategoryService
				.getProductCateList(productCategoryCondition);
		if (productCategorylist != null && productCategorylist.size() > 0) {
			modelMap.put("productCategorylist", productCategorylist);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("erromsg", "商品类别为空");
		}
		return modelMap;
	}

	/**
	 * 根据类别Id删除此类别分类信息
	 * 
	 * @param productCategoryCondition
	 * @return
	 */
	@RequestMapping(value = "/removeProductCategory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeProductCategory(
			Long productCategoryId, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println("ID:"+request.getParameter("ProductCategoryId"));
		productCategoryId= HttpServletRequestUtil.getLong(request,
				"ProductCategoryId");//从前端Request中获取商品类别Id
		if (productCategoryId!=null && productCategoryId>0) {
			try {
				Shop currentShop =(Shop)request.getSession().getAttribute("currentShop");
				ProductCategoryExecution pe = productCategoryService
						.deleteProductCategoryById(productCategoryId,currentShop.getShopId());
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					//modelMap.put("ProductCategoryCondition", ProductCategoryCondition);
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("erromsg",pe.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("erromsg",e.toString());
				return modelMap;
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errroInfo", "请至少选择一个商品类别");
		}
		return modelMap;
	}

	/**
	 * 添加类别列表信息
	 * 
	 * @param productCategoryCondition
	 * @return
	 */
	@RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addproductcategorys(
			@RequestBody List<ProductCategory> productCategorylist,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 从前端session获取当前商店ID
		Shop currentShop = (Shop) request.getSession().getAttribute(
				"currentShop");
		for (ProductCategory pc : productCategorylist) {
			pc.setShopId(currentShop.getShopId());
		}

		if (productCategorylist != null && productCategorylist.size() > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService
						.batchAddProductCategory(productCategorylist);
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS
						.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errmsg", pe.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errmsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errmsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}
}
