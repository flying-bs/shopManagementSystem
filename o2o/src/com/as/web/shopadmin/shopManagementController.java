package com.as.web.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.as.dto.ShopExecution;
import com.as.entity.Area;
import com.as.entity.PersonInfo;
import com.as.entity.Shop;
import com.as.entity.ShopCategory;
import com.as.enums.ShopStateEnum;
import com.as.service.AreaService;
import com.as.service.ShopCategoryService;
import com.as.service.ShopService;
import com.as.util.CodeUtil;
import com.as.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shopadmin")
public class shopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	/**
	 * 获取商铺管理中商铺信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getshopmanagementinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getshopmanagementinfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId =HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId<0) {
			Object currentObject =request.getSession().getAttribute("currentShop");
			if (currentObject == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shop/shopList");
			}else {
				Shop currentShop = (Shop)currentObject;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		}else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop",currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}
	/**
	 * 
	 * 获取商铺列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getShopList",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopList(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		PersonInfo user = new PersonInfo();
		user.setUserId(1L);
		user.setName("test");
		request.getSession().setAttribute("user",user);
		user = (PersonInfo)request.getSession().getAttribute("user");
	
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(user);
			ShopExecution se = shopService.getShopList(shopCondition, 0, 100);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errmsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 获取商铺
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getshopbyid",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopById(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId>-1) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areaList = areaService.getAreaList(); 
				
				modelMap.put("success", true);
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("shop", e.getMessage());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("shop", "Empty shop");
		}
		return modelMap;
	}
	/**
	 * 修改店铺信息内容
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success",false);
			modelMap.put("errmsg","您输入的验证码有误，请重新输入!");
			return modelMap;
		}
		//1.获取前端填写的相关信息，包括图片以及店铺信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			modelMap.put("success",false);
			modelMap.put("errmsg",e.getMessage());
			return modelMap;
		} 
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}
		//modify ShopInfo
		if (shop!=null && shop.getShopId()!=null) {
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1L);
			shop.setOwner(owner);
			ShopExecution se;
			try {
				if (shopImg == null) {
					 se= shopService.modifyShop(shop, null);
				}else {
					 se = shopService.modifyShop(shop, shopImg);
				}
				if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errmsg", se.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errmsg", e.getMessage());
			}
			return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errmsg", "请输入店铺Id");
			return modelMap;
		}
	}
	
	/**
	 * 获取数据库信息显示到店铺界面，为后续注册/更新做准备
	 * @return
	 */
	@RequestMapping(value = "/getShopInitInfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
//			shopCategoryList = shopCategoryService
//					.getAllSecondLevelShopCategory();
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			System.out.println(shopCategoryList.get(0).getShopCategoryName()+"--------");
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
	
	/**
	 * 注册店铺
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success",false);
			modelMap.put("errmsg","您输入的验证码有误，请重新输入,,,");
			return modelMap;
		}
		//1.获取前端填写的相关信息，包括图片以及店铺信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			modelMap.put("success",false);
			modelMap.put("errmsg",e.getMessage());
			return modelMap;
		} 
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}else {
			modelMap.put("success",false);
			modelMap.put("errmsg","upload File can't be Empty!");
			return modelMap;
		}
		// To register shop
		if (shop!=null && shopImg !=null) {
			//PersonInfo owner = new PersonInfo();
			PersonInfo owner = (PersonInfo)request.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution se;
			try {
				se= shopService.addShop(shop, shopImg);
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					//The user can operate shop
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>)request.getSession().getAttribute("shopList");
					if (shopList == null ||shopList.size() == 0) {
						shopList = new ArrayList<Shop>();
					}
					shopList.add(se.getShop());
					request.setAttribute("shopList", shopList);
				}else {
					modelMap.put("success", false);
					modelMap.put("errmsg", se.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errmsg", e.getMessage());
			}
			return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errmsg", "商铺信息有误");
			return modelMap;
		}
	}
}
