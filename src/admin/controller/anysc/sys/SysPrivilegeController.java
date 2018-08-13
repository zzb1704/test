package admin.controller.anysc.sys;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Throwables;

import ec.common.Permission;
import ec.dto.sys.SysPrivilegeDTO;
import ec.model.JsonResult;
import ec.model.Pager;
import ec.model.Response;
import ec.model.sys.SysPrivilege;
import ec.service.sys.SysPrivilegeService;
import admin.common.AppPermission;
import admin.controller.BaseController;

@Controller
@RequestMapping("json/privilege")
@Slf4j
public class SysPrivilegeController extends BaseController {

	@Resource
	private SysPrivilegeService privilegeService;

	/**
	 * 系统特权列表
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.Privilege_Search)
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> getlist(SysPrivilegeDTO dto, Integer page) {
		JsonResult jsonResult = new JsonResult();
		Pager<Map<String, Object>> pager = new Pager<>();
		if (page == null)
			page = 1;
		pager.setPageNo(page);
		pager.setPageSize(8);
		dto.setPager(pager);
		List<Map<String, Object>> list = privilegeService.search(dto);
		pager.setResults(list);
		jsonResult.setData(list);
		jsonResult.setPager(pager);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setNavigatePageNumbers(pager.getNavigatePageNumbers("").get("navigatePageNumbers").toString());
		return jsonResult.toMapper();
	}

	/**
	 * 全部系统特权
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.Privilege_Search)
	@RequestMapping("getAll")
	@ResponseBody
	public Map<String, Object> getlist(SysPrivilegeDTO dto) {
		JsonResult jsonResult = new JsonResult();
		List<Map<String, Object>> list = privilegeService.search(dto);
		jsonResult.setData(list);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 新增系统特权
	 * 
	 * @return
	 * @throws IOException
	 */
	@Permission(AppPermission.Privilege_Add)
	@RequestMapping("doadd")
	@ResponseBody
	public Map<String, Object> doSysUserAdd(SysPrivilege sysPrivilege) throws IOException {
		JsonResult result = new JsonResult();
		try {
			if(sysPrivilege.getStatus() == null){
				sysPrivilege.setStatus(SysPrivilege.Status.VALID.value());
			}
			// 查询是否已存在该用户名
			SysPrivilege sr = privilegeService.loadByName(sysPrivilege.getName());
			if (sr != null) {
				return result.errorMapper("角色名重复！");
			}
			Response<Boolean> response = privilegeService.insert(sysPrivilege);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add sysprivilege because error:{},data:{}", Throwables.getStackTraceAsString(e),
					sysPrivilege);
			return result.errorMapper("系统特权新增失败");
		}
		result.setData("privilege/list.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

	/**
	 * 根据特权ID查询对象
	 * 
	 * @return
	 */
	@Permission(AppPermission.Privilege_Search)
	@RequestMapping("loadById")
	@ResponseBody
	public Map<String, Object> loadById(Long id) {
		JsonResult jsonResult = new JsonResult();
		SysPrivilege su = privilegeService.loadById(id);
		jsonResult.setData(su);
		return jsonResult.toMapper();
	}

	/**
	 * 修改系统特权
	 * 
	 * @return
	 * @throws IOException
	 */
	@Permission(AppPermission.Privilege_Edit)
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(SysPrivilege sysPrivilege) throws IOException {
		JsonResult result = new JsonResult();
		try {
			if(sysPrivilege.getStatus() == null){
				sysPrivilege.setStatus(SysPrivilege.Status.VALID.value());
			}
			// 设置查询条件
			SysPrivilegeDTO dto = new SysPrivilegeDTO();
			dto.setId(sysPrivilege.getId());
			dto.setName(sysPrivilege.getName());
			SysPrivilege role = privilegeService.loadByIdAndName(dto);
			if (role == null) {
				// 查询是否已存在该用户名
				SysPrivilege sr = privilegeService.loadByName(sysPrivilege.getName());
				if (sr != null) {
					return result.errorMapper("角色名重复！");
				}
			}
			Response<Boolean> response = privilegeService.update(sysPrivilege);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to update sysprivilege because error:{},data:{}", Throwables.getStackTraceAsString(e),
					sysPrivilege);
			return result.errorMapper("系统特权修改失败");
		}
		result.setData("privilege/searchList.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

}
