package admin.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import com.google.common.base.Strings;

import admin.common.Keys;
import ec.common.JSonUtils;
import ec.common.RequestUtils;
import ec.model.JsonResult;
import ec.model.sys.SysUser;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// String url = request.getRequestURI();
		// HttpSession session = request.getSession(true);
		// 从session 里面获取用户名的信息
		// Object obj = session.getAttribute(Keys.LOGIN_USER);

		String userString = RequestUtils.getCookieValue(request,
				Keys.LOGIN_USER);

		// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
		if (Strings.isNullOrEmpty(userString)) {
			return authFailed(request, response);
		}

		SysUser user = JSonUtils.readValue(userString, SysUser.class);
		if (user == null)
			return authFailed(request, response);

		return true;
	}

	private boolean authFailed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			// ajax请求
			JsonResult jsonResult = new JsonResult();
			Map<String, Object> errorMap = jsonResult.errorMapper("会话超时，请重新登录");
			String message = JSonUtils.toJSon(errorMap);

			PrintWriter out = response.getWriter();
			out.print(message);
			out.close();

		} else {
//			if(request.getContextPath().equals("http://103.240.247.20"))
//				response.sendRedirect("http://103.240.247.20:9082" + "/sys/login.html");
			response.sendRedirect(request.getContextPath() + "/sys/login.html");
		}

		return false;
	}
}
