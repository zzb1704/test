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
<link rel="stylesheet" href="css/css.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>

</head>
<style>
#preview1Img {alpha (opacity=0);
	-moz-opacity: 0;
	opacity: 0;
}
</style>
<body>
	<div class="top">
		<div class="logo">
			<a href=""><img src="images/page1_03.jpg"></a>
		</div>
		<ul class="top_right">
			<li><a href="">退出</a></li>
			<li><a href="">修改密码</a></li>
			<li><a href="">欢迎</a></li>
		</ul>
	</div>
	<div class="main">
		<div class="main_box">
			<div class="sidebar">
				<h2>项目管理</h2>
				<ul>
					<li class="on"><a href="">创建项目</a></li>
					<li><a href="">在做项目</a></li>
					<li><a href="">已完成项目</a></li>
					<li><a href="">回收站</a></li>
				</ul>
				<h2>用户管理</h2>
				<ul>
					<li><a href="">用户列表</a></li>
					<li><a href="">用户群</a></li>
				</ul>
				<h2>发布管理</h2>
				<ul>
					<li><a href="">发布任务</a></li>
					<li><a href="">消息推送</a></li>
				</ul>
				<h2>权限设置</h2>
				<ul>
					<li><a href="">审核员权限管理</a></li>
					<li><a href="">客户权限</a></li>
				</ul>
			</div>
			<div class="main_right">
				<div class="tit">创建项目</div>
				<div class="main_layer1">
					<div class="layer1_box">
						<div class="left">请选择类型</div>
						<div class="right">检测类型</div>
					</div>
				</div>
				<div class="main_layer2">
					<div class="main_logo">
						<div id="preview1">
							<img id="imghead1" border="0" src="images/LOGO_03.jpg" width="60"
								height="60" onClick="$('#preview1Img').click();">
							<p>60*60</p>
						</div>

						<input type="file" onChange="preview1Image(this)" id="preview1Img">
					</div>
					<div class="main_logoTxt">LOGO</div>
					<div class="layer2_box">
						<div class="left">项目名称</div>
						<div class="right">
							<input type="text" />
						</div>
					</div>
					<div class="clear"></div>
					<div class="layer2_box">
						<div class="left">项目编号</div>
						<div class="right">
							<input type="text" />
						</div>
					</div>
					<div class="clear"></div>
					<div class="layer2_box">
						<div class="left">开始时间</div>
						<div class="right">
							<input type="text" />
						</div>
					</div>
					<div class="clear"></div>
					<div class="layer2_box">
						<div class="left">项目简介</div>
						<div class="right">
							<textarea></textarea>
						</div>
					</div>
					<div class="clear"></div>
					<div class="layer2_box">
						<div class="left">背景图片上传</div>
						<div class="right">
							<div id="preview1"
								style="float: left; width: 148px; height: 148px; margin: 0;">
								<img id="imghead1" border="0" src="images/LOGO_03.jpg"
									style="width: 148px; height: 148px;"
									onClick="$('#preview1Img').click();">
							</div>

							<input type="file" onChange="preview1Image(this)"
								id="preview1Img">
						</div>
					</div>
					<div class="clear"></div>
					<div class="layer2_box">
						<div class="left">
							<span>设置不合<br />格原因
							</span>
						</div>
						<div class="right">
							<div class="sel">
								<p>是否开启判断合格不合格</p>
								<ul>
									<li>是</li>
									<li>否</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="main_layer3">
					<div class="layer3_tit">
						<div class="left">属性设置</div>
						<ul class="right">
							<li><a href="">添加属性</a></li>
							<li><a href="">添加题目</a></li>
							<li><a href="">添加价格</a></li>
						</ul>
					</div>
					<div class="tab">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th><span></span></th>
								<th>属性分类</th>
								<th>属性名称</th>
								<th>操作</th>
							</tr>
							<tr>
								<td><span></span></td>
								<td>任务编号</td>
								<td>
									<ul>
										<li>001</li>
										<li>002</li>
										<li>003</li>
										<li>004</li>
										<li>005</li>
										<li>006</li>
										<li>007</li>
										<li>008</li>
										<li>009</li>
									</ul>
								</td>
								<td><a href="">查看</a> <a href="">编辑</a></td>
							</tr>
							<tr>
								<td><span></span></td>
								<td>任务编号</td>
								<td>
									<ul>
										<li>001</li>
										<li>002</li>
										<li>003</li>
										<li>004</li>
										<li>005</li>
										<li>006</li>
										<li>007</li>
										<li>008</li>
										<li>009</li>
									</ul>
								</td>
								<td><a href="">查看</a> <a href="">编辑</a></td>
							</tr>
							<tr>
								<td><span></span></td>
								<td>任务编号</td>
								<td>
									<ul>
										<li>001</li>
										<li>002</li>
										<li>003</li>
										<li>004</li>
										<li>005</li>
										<li>006</li>
										<li>007</li>
										<li>008</li>
										<li>009</li>
									</ul>
								</td>
								<td><a href="">查看</a> <a href="">编辑</a></td>
							</tr>
							<tr>
								<td><span></span></td>
								<td>任务编号</td>
								<td>
									<ul>
										<li>001</li>
										<li>002</li>
										<li>003</li>
										<li>004</li>
										<li>005</li>
										<li>006</li>
										<li>007</li>
										<li>008</li>
										<li>009</li>
									</ul>
								</td>
								<td><a href="">查看</a> <a href="">编辑</a></td>
							</tr>
						</table>
					</div>
					<div class="tab2">
						<span></span> 全选 <a href="">删除</a>
					</div>
					<div class="tab_bottom">
						<p>注：红点代表必填项，其他属性可以选填</p>
						<a href="">完成</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/file.js"></script>
<script type="text/javascript" src="js/js.js"></script>
</html>
