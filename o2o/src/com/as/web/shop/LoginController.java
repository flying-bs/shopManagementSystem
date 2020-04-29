package com.as.web.shop;

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
import com.as.service.PersonInfoService;
import com.as.service.ShopCategoryService;
import com.as.service.ShopService;
import com.as.util.CodeUtil;
import com.as.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shop")
public class LoginController {
	@Autowired
	private PersonInfoService infoService;
	/**
	 * 验证用户是否存在
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getUserByName",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> getUserByName(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
	//	long shopId = HttpServletRequestUtil.getLong(request, "userName");
		String userName = request.getParameter("userName");
		PersonInfo personInfo = infoService.getUserByName(userName); 
		if (personInfo == null) {
			System.out.println("不存在此用户！");
			modelMap.put("success", false);
			return modelMap;
		}
		System.out.println(personInfo.getGender());
		String passwd  = request.getParameter("passwd");
		if(personInfo.getGender().equals( passwd)){
			modelMap.put("success", true);
			modelMap.put("personInfo", personInfo);
		}
		return modelMap;
	}
	
}
