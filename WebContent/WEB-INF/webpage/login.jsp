<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="html" uri="/htmltaglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/css.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery1.42.min.js" ></script>
	</head>
	<body>
		<div class="warp">
			<div class="warp_box">
				<div class="load">
					<h2>后台监控管理系统</h2>
					<div class="load_box">
						<div class="bd">
							<input type="text"  id="name" placeholder="请输入用户名"/>
							<input type="password" id="password" placeholder="请输入密码"/>
						</div>
						<div class="pass"><p>记住密码</p><span id="pass"></span></div>
						<a class="load_bottom" onclick="login()">登陆</a>
						<h3><a href="">忘记密码?</a></h3>
					</div>
				</div>
				<p class="bottom">Cpopyriht @2017 捷思敏研科技有限公司 版权所有</p>
			</div>
			
		</div>
	</body>
	<script src="${pageContext.request.contextPath}/assets/js/js.js"></script>
	<script type="text/javascript">
	function login(){
			var name = $.trim($("#name").val());
			if(!(name != null && name != "" )){
				alert('请填写用户名 ！');
				return;
			}
			
			var password = $.trim($("#password").val());
			if(!(password != null && password != "" )){
				alert('请填写密码！');
				return;
			}
			
			var pass = 0;
			if($("#pass").hasClass('on')){
				pass = 1
			}
			
			$.post("${pageContext.request.contextPath}/anysc/user/dologin.json",	{
				name :name,
				password : password,
				pass : pass
			},function(result){
				if (result.resultCode != 1000) {
					alert(result.error);
				} else {
					//window.location.href="${pageContext.request.contextPath}/"+result.data;
					alert("登录成功");
				}
			});
	};
	</script>
</html>
