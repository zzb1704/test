package admin.controller.anysc.sys;

import java.io.IOException;
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
import ec.dto.sys.SysRoleDTO;
import ec.model.JsonResult;
import ec.model.Pager;
import ec.model.Response;
import ec.model.sys.SysRole;
import ec.service.sys.SysRoleService;
import ec.service.sys.SysUserRefRoleService;
import admin.common.AppPermission;
import admin.controller.BaseController;

/**
 * 系统角色
 * 
 * @author DSH
 *
 */
@Controller
@RequestMapping("json/role/")
@Slf4j
public class SysRoleController extends BaseController {

	@Resource
	private SysRoleService sysRoleService;

	@Resource
	private SysUserRefRoleService sysUserRefRoleService;

	/**
	 * 查询全部系统角色
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.Role_Search)
	@RequestMapping("list")
	@ResponseBody
	private Map<String, Object> getlist(SysRoleDTO dto) {
		JsonResult jsonResult = new JsonResult();
		List<Map<String, Object>> list = sysRoleService.search(dto);
		jsonResult.setData(list);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 添加用户-角色关联
	 * 
	 * @param userid
	 * @param roles
	 * @return
	 */
	@Permission(AppPermission.SysUser_Ref_Role_Add)
	@RequestMapping("addsubmit")
	@ResponseBody
	public Map<String, Object> submitAddRolePrivilege(Integer user_id, Integer[] roles) {
		JsonResult jsonResult = new JsonResult();
		try {
			Preconditions.checkNotNull(user_id, "角色ID不能为空");

			Response<Boolean> response;
			for (Integer role_id : roles) {
				response = sysUserRefRoleService.insertUserRefRole(user_id, role_id);
				if (!response.isSuccess()) {
					return jsonResult.errorMapper(response.getError());
				}
			}

		} catch (NullPointerException e) {
			log.error("null pointer exception when insert user role with userid:{} and role:{},error:{}", user_id,
					roles, Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper(e.getMessage());
		} catch (Exception e) {
			log.error("fail to insert role privilege with userid:{} and role:{},error:{}", user_id, roles,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper("添加角色权限失败");
		}

		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setData("sys/sysuser/refrole.html");
		return jsonResult.toMapper();
	}

	/**
	 * 删除用户-角色关联
	 * 
	 * @param userid
	 * @param roles
	 * @return
	 */
	@Permission(AppPermission.SysUser_Ref_Role_Del)
	@RequestMapping("deletesubmit")
	@ResponseBody
	public Map<String, Object> submitDeleteRolePrivilege(Integer user_id, Integer[] roles) {
		JsonResult jsonResult = new JsonResult();

		try {
			Preconditions.checkNotNull(user_id, "用户ID不能为空");

			Response<Boolean> response;
			for (Integer role_id : roles) {
				response = sysUserRefRoleService.deleteUserRefRole(user_id, role_id);
				if (!response.isSuccess()) {
					return jsonResult.errorMapper(response.getError());
				}
			}
		} catch (NullPointerException e) {
			log.error("null pointer exception when delete user role with user:{} and role:{},error:{}", user_id, roles,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper(e.getMessage());
		} catch (Exception e) {
			log.error("fail to delete user role with user:{} and role:{},error:{}", user_id, roles,
					Throwables.getStackTraceAsString(e));
			return jsonResult.errorMapper("删除角色权限失败");
		}

		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setData("sys/sysuser/refrole.html");
		return jsonResult.toMapper();
	}

	/**
	 * 系统用户列表
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.SysUser_Search)
	@RequestMapping("searchList")
	@ResponseBody
	public Map<String, Object> getProdist(SysRoleDTO dto, Integer page) {
		JsonResult jsonResult = new JsonResult();
		Pager<Map<String, Object>> pager = new Pager<>();
		if (page == null)
			page = 1;
		pager.setPageNo(page);
		pager.setPageSize(8);
		dto.setPager(pager);
		List<Map<String, Object>> list = sysRoleService.search(dto);
		pager.setResults(list);
		jsonResult.setData(list);
		jsonResult.setPager(pager);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setNavigatePageNumbers(pager.getNavigatePageNumbers("").get("navigatePageNumbers").toString());
		return jsonResult.toMapper();
	}

	/**
	 * 新增系统用户
	 * 
	 * @return
	 * @throws IOException
	 */
	@Permission(AppPermission.SysUser_Add)
	@RequestMapping("doadd")
	@ResponseBody
	public Map<String, Object> doSysUserAdd(SysRole sysRole) throws IOException {
		JsonResult result = new JsonResult();
		try {
			if(sysRole.getStatus() == null){
				sysRole.setStatus(SysRole.Status.VALID.value());
			}
			// 查询是否已存在该用户名
			SysRole sr = sysRoleService.loadByName(sysRole.getName());
			if (sr != null) {
				return result.errorMapper("角色名重复！");
			}
			Response<Boolean> response = sysRoleService.insert(sysRole);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add sysrole because error:{},data:{}", Throwables.getStackTraceAsString(e), sysRole);
			return result.errorMapper("系统角色新增失败");
		}
		result.setData("role/searchList.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

	/**
	 * 根据角色ID查询对象
	 * 
	 * @return
	 */
	@Permission(AppPermission.SysUser_Search)
	@RequestMapping("loadById")
	@ResponseBody
	public Map<String, Object> loadById(Long id) {
		JsonResult jsonResult = new JsonResult();
		SysRole su = sysRoleService.loadById(id);
		jsonResult.setData(su);
		return jsonResult.toMapper();
	}

	/**
	 * 修改系统用户
	 * 
	 * @return
	 * @throws IOException
	 */
	@Permission(AppPermission.SysUser_Edit)
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(SysRole sysRole) throws IOException {
		JsonResult result = new JsonResult();
		try {
			if(sysRole.getStatus() == null){
				sysRole.setStatus(SysRole.Status.VALID.value());
			}
			// 设置查询条件
			SysRoleDTO dto = new SysRoleDTO();
			dto.setId(sysRole.getId());
			dto.setName(sysRole.getName());
			SysRole role = sysRoleService.loadByIdAndName(dto);
			if (role == null) {
				// 查询是否已存在该用户名
				SysRole sr = sysRoleService.loadByName(sysRole.getName());
				if (sr != null) {
					return result.errorMapper("角色名重复！");
				}
			}
			Response<Boolean> response = sysRoleService.update(sysRole);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to update sysrole because error:{},data:{}", Throwables.getStackTraceAsString(e), sysRole);
			return result.errorMapper("系统角色修改失败");
		}
		result.setData("role/searchList.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

}