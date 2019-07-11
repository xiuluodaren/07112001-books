jQuery(document).ready(function() {
    /*
        Fullscreen background
    */
    $.backstretch("../assets/img/backgrounds/1.jpg");

    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });

    $('.login-form').on('submit', function(e) {

    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    });

    $("#login-button").click(function(){
		var username = $("#form-username").val();
		var password = $("#form-password").val();
		var code = $("#code").val();

		if(username.length == 0||password.length==0){
			alert("用户名和密码不能为空!");
			return;
		}
		if(code.length == 0){
			alert("验证码填写不正确!")
			return;
		}

		var params = {
			userName:username,
			password:password,
			code:code,
		};

		$.ajax({
			url:'/login',
			data:params,
			type:"post",
			success:function(data){
				if(data.success != true){
					alert(data.errMsg);
				}else{
					window.location.href = "/index";
				}
			}
		})
	})



	function timestampToTime(timestamp) {
		var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate()) + ' ';
		h = (date.getHours() < 10 ? '0'+(date.getHours()) : date.getHours()) + ':';
		m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
		s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
		return Y+M+D+h+m+s;
	}

	Date.prototype.format = function(fmt) {
		var o = {
			"M+" : this.getMonth()+1,                 //月份
			"d+" : this.getDate(),                    //日
			"h+" : this.getHours(),                   //小时
			"m+" : this.getMinutes(),                 //分
			"s+" : this.getSeconds(),                 //秒
			"q+" : Math.floor((this.getMonth()+3)/3), //季度
			"S"  : this.getMilliseconds()             //毫秒
		};
		if(/(y+)/.test(fmt)) {
			fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		}
		for(var k in o) {
			if(new RegExp("("+ k +")").test(fmt)){
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
			}
		}
		return fmt;
	}

});
