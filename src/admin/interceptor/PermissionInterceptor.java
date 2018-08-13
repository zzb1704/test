package admin.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.base.Strings;

import admin.common.AppPermission;
import admin.common.Keys;
import ec.common.JSonUtils;
import ec.common.Permission;
import ec.common.RequestUtils;
import ec.model.JsonResult;
import ec.model.sys.SysUser;

@Slf4j
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 处理Permission Annotation，实现方法级权限控制
		HandlerMethod method = (HandlerMethod) handler;
		Permission permission = method.getMethodAnnotation(Permission.class);

		// 如果为空在表示该方法不需要进行权限验证
		if (permission == null) {
			return true;
		}

		// HttpSession session = request.getSession();

		// User user = (User) session.getAttribute(Keys.LOGIN_USER);

		SysUser user = null;
		String userString = RequestUtils.getCookieValue(request,
				Keys.LOGIN_USER);
		if (!Strings.isNullOrEmpty(userString)) {
			user = JSonUtils.readValue(userString, SysUser.class);
		}

		// 验证是否具有权限
		if (!hasPermission(request, permission.value())) {
			

			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equals("XMLHttpRequest")) {
				// ajax请求

				JsonResult jsonResult = new JsonResult();
				Map<String, Object> errorMap = jsonResult.errorMapper("权限不足");
				String message = JSonUtils.toJSon(errorMap);

				PrintWriter out = response.getWriter();
				out.print(message);
				out.close();

			} else {
				response.sendRedirect(request.getContextPath()
						+ "/nopermission.html");
			}

			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private boolean hasPermission(HttpServletRequest request, int permission) {

		// Object obj = session.getAttribute(Keys.USER_PRIVILEGES);
		String obj = RequestUtils.getCookieValue(request, Keys.USER_PRIVILEGES);

		if (Strings.isNullOrEmpty(obj))
			return false;

		List<Integer> hasList = JSonUtils.readValue(obj, ArrayList.class);
		if (hasList.contains(AppPermission.Adminsuper))
			return true;
		if (hasList.contains(permission))
			return true;

		return false;
	}

}
