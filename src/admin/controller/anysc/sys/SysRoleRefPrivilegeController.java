package admin.controller.anysc.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

import ec.common.Permission;
import ec.dto.sys.SysRoleRefPrivilegeDTO;
import ec.model.JsonResult;
import ec.model.Response;
import ec.service.sys.SysRoleRefPrivilegeService;
import admin.common.AppPermission;
import admin.controller.BaseController;

@Controller
@RequestMapping("json/refprivilege/")
@Slf4j
public class SysRoleRefPrivilegeController extends BaseController {

	@Resource
	private SysRoleRefPrivilegeService refPrivilegeService;

	/**
	 * 获取系统角色已关联的权限
	 * @return
	 */
	@Permission(AppPermission.Role_Ref_Privilege_Search)
	@RequestMapping("getAll")
	@ResponseBody
	private Map<String, Object> getlist(SysRoleRefPrivilegeDTO dto) {
		JsonResult jsonResult = new JsonResult();
		List<Map<String, Object>> list = refPrivilegeService.getRoleRefPrivilegeList(dto);
		jsonResult.setData(list);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 添加角色权限关联
	 */
	@Permission(AppPermission.Role_Ref_Privilege_Add)
	@RequestMapping("addsubmit")
	@ResponseBody
	public Map<String, Object> submitAddRolePrivilege(Long role_id, Long[] privileges) {
		JsonResult jsonResult = new JsonResult();
		try {
			Preconditions.checkNotNull(role_id, "角色ID不能为空");

			Response<Boolean> response;
			for (Long privilege_id : privileges) {
				response = refPrivilegeService.insertUserRefRole(privilege_id, role_id);
				if (!response.isSuccess()) {
					return jsonResult.errorMapper(response.getError());
				}
			}
		} catch (NullPointerException e) {
			log.error("null pointer exception when insert role privilege with privilege:{} and role:{},error:{}",
					privileges, role_id, Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper(e.getMessage());
		} catch (Exception e) {
			log.error("fail to insert role privilege with privileges :{} and roleId :{},error:{}", privileges, role_id,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper("添加角色权限失败");
		}

		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 删除角色权限关联
	 */
	@Permission(AppPermission.Role_Ref_Privilege_Del)
	@RequestMapping("deletesubmit")
	@ResponseBody
	public Map<String, Object> submitDeleteRolePrivilege(Long role_id, Long[] privileges) {
		JsonResult jsonResult = new JsonResult();

		try {
			Preconditions.checkNotNull(role_id, "权限ID不能为空");

			Response<Boolean> response;
			for (Long privilege_id : privileges) {
				response = refPrivilegeService.deleteUserRefRole(privilege_id, role_id);
				if (!response.isSuccess()) {
					return jsonResult.errorMapper(response.getError());
				}
			}
		} catch (NullPointerException e) {
			log.error("null pointer exception when delete role privilege with role:{} and privilege:{},error:{}",
					role_id, privileges, Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper(e.getMessage());
		} catch (Exception e) {
			log.error("fail to delete role pribilege with role:{} and privilege:{},error:{}", role_id, privileges,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper("删除角色权限失败");
		}

		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

}
