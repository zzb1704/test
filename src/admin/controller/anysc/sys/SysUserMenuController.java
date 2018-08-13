package admin.controller.anysc.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.common.AppPermission;
import admin.controller.BaseController;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

import ec.common.Permission;
import ec.model.JsonResult;
import ec.model.Response;
import ec.service.sys.SysUserMenuService;

/**
 * 用户菜单关联
 * @author DSH
 */
@Controller
@RequestMapping("json/userMenu/")
@Slf4j
public class SysUserMenuController extends BaseController {

	@Resource
	private SysUserMenuService sysUserMenuService;

	/**
	 * 获取用户已关联的菜单
	 */
	@Permission(AppPermission.SysUser_Ref_Menu_Search)
	@RequestMapping("list")
	@ResponseBody
	private Map<String, Object> findUserMenu(Long user_id) {
		JsonResult jsonResult = new JsonResult();
		List<Map<String, Object>> list = sysUserMenuService.findUserMenu(user_id);
		jsonResult.setData(list);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 添加用户-菜单关联
	 */
	@Permission(AppPermission.SysUser_Ref_Menu_Add)
	@RequestMapping("addsubmit")
	@ResponseBody
	public Map<String, Object> submitAddUserMenu(Integer user_id, Integer[] menus) {
		JsonResult jsonResult = new JsonResult();
		try {
			Preconditions.checkNotNull(user_id, "用户ID不能为空");

			Response<Boolean> response;
			for (Integer menu : menus) {
				response = sysUserMenuService.insertUserMenu(user_id, menu);
				if (!response.isSuccess()) {
					return jsonResult.errorMapper(response.getError());
				}
			}

		} catch (NullPointerException e) {
			log.error("null pointer exception when insert user role with user:{} and menu:{},error:{}", user_id, menus,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper(e.getMessage());
		} catch (Exception e) {
			log.error("fail to insert user menu with user:{} and menu:{},error:{}", user_id, menus,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper("添加用户菜单失败");
		}

		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 删除用户-菜单关联
	 */
	@Permission(AppPermission.SysUser_Ref_Menu_Del)
	@RequestMapping("deletesubmit")
	@ResponseBody
	public Map<String, Object> submitDeleteUserMenu(Integer user_id, Integer[] menus) {
		JsonResult jsonResult = new JsonResult();

		try {
			Preconditions.checkNotNull(user_id, "用户ID不能为空");

			Response<Boolean> response;
			for (Integer menu : menus) {
				response = sysUserMenuService.deleteUserMenu(user_id, menu);
				if (!response.isSuccess()) {
					return jsonResult.errorMapper(response.getError());
				}
			}
		} catch (NullPointerException e) {
			log.error("null pointer exception when delete user menu with user:{} and menu:{},error:{}", user_id, menus,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper(e.getMessage());
		} catch (Exception e) {
			log.error("fail to delete user menu with user:{} and menu:{},error:{}", user_id, menus,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper("删除用户菜单失败！");
		}

		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

}
