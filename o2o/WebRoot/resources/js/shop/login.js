$(function(){
	//alert("diaoyong ");

	$('#login').click(function() {
		var userName = document.getElementById("userName").value;
		//alert(userName.value);
		if(!userName){
			alert("用户名不能为空");
			return;
		};
		var passwd = document.getElementById("passwd").value;
		if(!passwd){
			alert("密码不能为空");
			return;
		};
		$.ajax( {
			url : "/o2o/shop/getUserByName?t="+new Date().getTime()+"&userName="+userName+"&passwd="+passwd,
			type : "POST",
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href = '/o2o/shopadmin/shoplist';
				}
				if (!data.success) {
					alert("用户名或者密码错误！");
					return;
				}

			},
			error : function(data, error) {
			}
		});
	});

});