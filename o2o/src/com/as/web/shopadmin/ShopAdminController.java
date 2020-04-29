package com.as.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="shopadmin",method=RequestMethod.GET)
public class ShopAdminController {
	@RequestMapping(value="/shopoperation")
	public 	String shopoperation(){
		return"shop/shopregister";
		
	}
	@RequestMapping(value="/shoplist")
	public 	String shoplist(){
		return"shop/shoplist";	
	}
	@RequestMapping(value="/shopmanagement")
	public 	String shopmanagement(){
		return"shop/shopmanagement";	
	}
	@RequestMapping(value="/productcategorymanagement")
	public 	String productcategorylist(){
		return"productcategory/productcategorylist";	
	}
	
	@RequestMapping(value="/login")
	public 	String vertifyUser(){
		return"shop/login";	
	}
}
