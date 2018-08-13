package admin.controller.anysc;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;

import admin.common.AppPermission;
import admin.common.Keys;
import ec.common.HttpUtils;
import ec.common.JSonUtils;
import ec.common.Permission;
import ec.common.RequestUtils;
import ec.dto.sys.UpdatePwdDTO;
import ec.model.JsonResult;
import ec.model.Response;
import ec.model.sys.SysUser;
import ec.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步处理系统信息控制器
 */
@Controller
@RequestMapping("json/sys")
@Slf4j
public class SysController {

	@Resource
	private SysUserService sysUserService;

	/**
	 * 用户登录
	 * 
	 * @param name
	 * @param password
	 * @param checkcode
	 * @param request
	 * @param httpresponse
	 * @return
	 */
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doLogin(String name, String password,
			String checkcode, HttpServletRequest request,
			HttpServletResponse httpresponse) {
		String code = RequestUtils.getCookieValue(request, Keys.VERIFY_CODE);
		JsonResult result = new JsonResult();
		try {
			Preconditions.checkNotNull(code, "验证码已失效");
		} catch (Exception e) {
			return result.errorMapper(e.getMessage());
		}
		if (!StringUtils.equalsIgnoreCase(checkcode, code)) {
			return result.errorMapper("验证码错误");
		}
		if (Strings.isNullOrEmpty(name)) {
			return result.errorMapper("请输入用户名");
		}
		if (Strings.isNullOrEmpty(password)) {
			return result.errorMapper("请输入密码");
		}
		//password = sysUserService.encryptPassword(password);
		Response<SysUser> response = sysUserService.loginWithVerifi(name,
				password, HttpUtils.getIp(request));
		if (!response.isSuccess()) {
			return result.errorMapper(response.getError());
		}

		result.setResultCode(JsonResult.SUCCESS);
		System.err.println("登录用户：" + response.getResult());

		RequestUtils.setCookie2(request, httpresponse, Keys.LOGIN_MOBILE,
				response.getResult().getName(), -1);

		RequestUtils.setCookie(request, httpresponse, Keys.LOGIN_USER,
				JSonUtils.toJSon(response.getResult()), -1);
		RequestUtils.setCookie(request, httpresponse, Keys.USER_PRIVILEGES,
				JSonUtils.toJSon(sysUserService.getUserPrivileges(response
						.getResult().getId())), -1);

		return result.toMapper();
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String, Object> delUserCookie(HttpServletRequest request,
			HttpServletResponse response) {
		String userString = RequestUtils.getCookieValue(request,
				Keys.LOGIN_USER);
		JsonResult jsonResult = new JsonResult();
		// SysUser sysUserInfo = new SysUser();
		if (!Strings.isNullOrEmpty(userString)) {
			RequestUtils.deleteCookie(request, response, Keys.LOGIN_USER, true);
			String user = RequestUtils.getCookieValue(request, Keys.LOGIN_USER);
			SysUser user1 = JSonUtils.readValue(user, SysUser.class);
		}
		
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 提交用户修改
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("changepwd")
	@ResponseBody
	@Permission(AppPermission.SysUser_Edit)
	public Map<String, Object> doupdatePwd(HttpServletRequest request,
			UpdatePwdDTO updatePwdDTO) {
		JsonResult jsonResult = new JsonResult();
		try {
			String checkNotNull = Preconditions.checkNotNull(
					updatePwdDTO.getOldPwd(), "旧密码不能为空");
			String checkNotNull2 = Preconditions.checkNotNull(
					updatePwdDTO.getNewPwd(), "新密码不能为空");
			String checkNotNull3 = Preconditions.checkNotNull(
					updatePwdDTO.getSureNewPwd(), "确认新密码不能为空");
			if (checkNotNull != null && checkNotNull2 != null
					&& checkNotNull3 != null && checkNotNull != ""
					&& checkNotNull2 != "" && checkNotNull3 != "") {
				SysUser sysUserInfo = null;
				SysUser sysUserInfo1 = new SysUser();
				if (updatePwdDTO.getNewPwd().equals(
						updatePwdDTO.getSureNewPwd())) {
					String userString = RequestUtils.getCookieValue(request,
							Keys.LOGIN_USER);
					if (!Strings.isNullOrEmpty(userString)) {
						// cookie中的实体类
						sysUserInfo = JSonUtils.readValue(userString,
								SysUser.class);
						// 获取数据库值
						SysUser sysUserInfo2 = sysUserService
								.loadUserById(sysUserInfo.getId());
						String pwd = sysUserInfo2.getPassword();
						String oldPwd = updatePwdDTO.getOldPwd();
						boolean matchResult = sysUserService.passwordMatch(
								oldPwd, pwd);
						if (!matchResult) {
							return jsonResult.errorMapper("旧密码错误");
						}
						sysUserInfo1.setId(sysUserInfo.getId());
						sysUserInfo1.setPassword(sysUserService
								.encryptPassword(updatePwdDTO.getNewPwd()));
					}
				} else {
					return jsonResult.errorMapper("两次密码不一致");
				}
				Response<Boolean> response = sysUserService
						.updatePwd(sysUserInfo1);
				if (!response.isSuccess()) {
					return jsonResult.errorMapper(response.getError());
				}
				jsonResult.setResultCode(JsonResult.SUCCESS);
				// 修改后跳转到登录页面
				return jsonResult.toMapper();
			} else {
				return jsonResult.errorMapper("请输入所有信息");
			}
		} catch (NullPointerException e) {
			log.error(
					"null pointer exception when insert user with data:{},error:{}",
					sysUserService, Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper(e.getMessage());
		}
	}

	// *******************下面都是系统用户相关*********************************

}
