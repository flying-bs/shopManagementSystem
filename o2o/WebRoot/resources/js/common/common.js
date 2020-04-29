function changeVerifyCode(img){
	//random()获取0-1的随机数，floor()取参数最小的整数。
	//Math.floor(Math.random()*100)是获取一个1-100的随机数，每次URL的更细会重新发起请求，用来避免浏览器缓存;
	img.src="../Kaptcha?"+Math.floor(Math.random()*100);
}
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}