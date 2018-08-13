package admin.controller.anysc.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ec.common.Permission;
import ec.dto.sys.SysUserRefRoleDTO;
import ec.model.JsonResult;
import ec.model.Response;
import ec.model.sys.SysUserRefRole;
import ec.service.sys.SysUserRefRoleService;
import admin.common.AppPermission;
import admin.controller.BaseController;

/**
 * 系统用户关联角色
 */
@Controller
@RequestMapping("json/refrole/")
public class SysUserRefRoleController extends BaseController {

	@Resource
	private SysUserRefRoleService sysUserRefRoleService;

	/**
	 * 获取系统用户已关联的角色
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.SysUser_Ref_Role_Search)
	@RequestMapping("list")
	@ResponseBody
	private Map<String, Object> getlist(SysUserRefRoleDTO dto) {
		JsonResult jsonResult = new JsonResult();
		List<Map<String, Object>> list = sysUserRefRoleService.getlist(dto);
		jsonResult.setData(list);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 新增系统用户关联角色
	 * 
	 * @return
	 */
	@Permission(AppPermission.SysUser_Ref_Role_Add)
	@RequestMapping("doadd")
	@ResponseBody
	private Map<String, Object> insert(SysUserRefRole sysUserRefRole, SysUserRefRoleDTO dto) {
		JsonResult result = new JsonResult();
		dto.setUser_id(sysUserRefRole.getUser_id());
		dto.setRole_id(sysUserRefRole.getRole_id());
		if (sysUserRefRoleService.getSysUserRefRole(dto) > 0) {
			return result.errorMapper("该关联信息已经存在！");
		}
		Response<Boolean> response = sysUserRefRoleService.insert(sysUserRefRole);
		if (!response.isSuccess()) {
			return result.errorMapper("新增系统用户关联角色失败！");
		}
		result.setData("json/refrole/list.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

	/**
	 * 按照ID查询一条记录
	 * 
	 * @param role_id
	 * @return
	 */
	@Permission(AppPermission.SysUser_Ref_Role_Search)
	@RequestMapping("loadById")
	@ResponseBody
	private Map<String, Object> loadById(Long role_id) {
		JsonResult jsonResult = new JsonResult();
		SysUserRefRole refRole = sysUserRefRoleService.loadById(role_id);
		jsonResult.setData(refRole);
		return jsonResult.toMapper();

	}

}
