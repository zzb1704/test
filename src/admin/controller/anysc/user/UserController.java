package admin.controller.anysc.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.controller.BaseController;
import ec.model.JsonResult;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("anysc/user")
@Slf4j
public class UserController extends BaseController {
	
	//登录
	@RequestMapping("dologin")
	@ResponseBody
	public Map<String, Object> doLogin(String name, String password,Integer pass) {
		JsonResult result = new JsonResult();
		
		//根据用户名判断用户是否存在
		//对比密码是否一致
		//判断是否保存密码 暂定1为选中
		System.out.println(name +"=="+password +"===="+pass);
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

}
