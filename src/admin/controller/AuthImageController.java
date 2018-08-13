package admin.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.common.Keys;
import ec.common.RequestUtils;
import ec.common.VerifyCodeUtil;

/**
 * 验证码管理
 * @author yongtao
 *
 */
@Controller
public class AuthImageController extends HttpServlet implements Servlet {
	static final long serialVersionUID = 1L;
	/**
	 * 登录页验证码功能
	 */
	@RequestMapping("checkcode")
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		//生成随机字串
		String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
		//存入会话session
		//HttpSession session = request.getSession(true);
		//session.setAttribute("rand", verifyCode.toLowerCase());
		RequestUtils.setCookie(request, response, Keys.VERIFY_CODE, verifyCode,-1);
		//生成图片
		int w = 200, h = 80;
		VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);

	}
}
