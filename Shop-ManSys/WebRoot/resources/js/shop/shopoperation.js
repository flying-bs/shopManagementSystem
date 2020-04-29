$(function() {
	var shopId = getQueryString("shopId");
	var isEdit = shopId ? true : false;
	var initUrl = '/o2o/shopadmin/getShopInitInfo';
	var registerShopUrl = '/o2o/shopadmin/registershop';
	var shopInfoUrl = '/o2o/shopadmin/getshopbyid?shopId=' + shopId;
	var editShopUrl = "/o2o/shopadmin/modifyshop";

	if (isEdit) {
		getInfo(shopId);
	} else {
		getShopInitInfo();
	}
	//修改时需要显示的店铺信息
	function getInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '" selected>'
						+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr('disabled', 'disabled');
				$('#area').html(tempAreaHtml);
				$('#area').attr('data-id', shop.areaId);
			}
		});
	}

	//alert("已调用JS..");
	//显示店铺初始信息
	//getShopInitInfo();
	function getShopInitInfo() {
		//alert("diaoyongshopiniit");
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				//alert("diaoyongshopiniit");
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					//alert("diaoyongshopiniit");
						tempHtml += '<option data-id="' + item.shopCategoryId
								+ '">' + item.shopCategoryName + '</option>';
					});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}
	$('#submit').click(function() {
		var shop = {};
		if (isEdit) {
			shop.shopId = shopId;
		}
		shop.shopName = $('#shop-name').val();
		if (!shop.shopName) {
			$.toast("店铺名称不能为空哦！");
			return;
		}
		shop.shopAddr = $('#shop-addr').val();
		if (!shop.shopAddr) {
			$.toast("店铺地址不能为空哦！");
			return;
		}
		shop.phone = $('#shop-phone').val();
		if (!shop.phone) {
			$.toast("店铺合法人电话信息不能为空哦！");
			return;
		}
		shop.shopDesc = $('#shop-desc').val();
		shop.shopCategory = {
			shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			areaId : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		var shopImg = $('#shop_img')[0].files[0];
		var formData = new FormData();
		//验证码判断
			var verifyCodeActual = $('#j_captcha').val();
			if (!verifyCodeActual) {
				$.toast("验证码不能为空哦！");
				return;
			}
			formData.append("shopImg", shopImg);
			formData.append("shopStr", JSON.stringify(shop));
			formData.append("verifyCodeActual", verifyCodeActual);
			$.ajax( {
				url : isEdit ? editShopUrl : registerShopUrl,
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					if (data.success) {
						$.toast("提交成功！");
					} else {
						$.toast("提交失败！");
					}
					$('captcha_img').click();
				}
			});

		});
});