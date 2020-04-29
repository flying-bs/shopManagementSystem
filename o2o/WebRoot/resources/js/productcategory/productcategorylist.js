$(function() {
	var ProductCategoryId = getQueryString("ProductCategoryId");
	if (ProductCategoryId == "" || ProductCategoryId < 0) {
		getlist();
	} else {
		removeProductCategoryById();
	}
	function getlist(e) {
		$.ajax( {
			url : "/o2o/productcategory/getproductCategorylist",
			type : "get",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					handleList(data.productCategorylist);
				}
			}
		});
	}
	function handleList(data) {
		var html = '';
		data.map(function(item, index) {
			html += '<div class="row row-shop"><div class="col-40">'
					+ item.productCategoryName + '</div><div class="col-40">'
					+ item.priority + '</div><div class="col-20">'
					+ removeProductCategory(item.productCategoryId)
					+ '</div></div>';

		});
		$('.category-wrap').html(html);
	}

	function removeProductCategory(id) {
		return '<a href="/o2o/shopadmin/productcategorymanagement?ProductCategoryId=' + id + '">删除</a>';
	}

	function removeProductCategoryById(e) {
		$.ajax( {
					url : "/o2o/productcategory/removeProductCategory?ProductCategoryId="
							+ ProductCategoryId,
					type : "POST",
					dataType : "json",
					success : function(data) {
						if (data.success) {
							$.toast('删除成功！');
							getlist();
						}else {
							$.toast('删除失败！');
						}
			}
				});
	}
	$('.category-wrap').on('click', '.row-product-category.temp .delete',
		function(e) {
			console.log($(this).parent().parent());
			$(this).parent().parent().remove();

	});
	
	$('#new').click(function() {
		var tempHtml = '<div class="row row-product-category temp">'
				+ '<div class="col-33"><input class="category-input category" type="text" placeholder="商品类名"></div>'
				+ '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
				+ '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
				+ '</div>';
		$('.category-wrap').append(tempHtml);
	});
	$('#submit').click(function() {
		var tempArr = $('.temp');
		var productCategoryList = [];
		tempArr.map(function(index, item) {
			var tempObj = {};
			tempObj.productCategoryName = $(item).find('.category').val();
			tempObj.priority = $(item).find('.priority').val();
			if (tempObj.productCategoryName && tempObj.priority) {
				productCategoryList.push(tempObj);
			}
		});
		$.ajax({
			url : "/o2o/productcategory/addproductcategorys",
			type : 'POST',
			data : JSON.stringify(productCategoryList),
			contentType : 'application/json',
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
					getlist();
				} else {
					$.toast('提交失败！');
				}
			}
		});
	});

});
