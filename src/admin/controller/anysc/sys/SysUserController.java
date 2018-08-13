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
import ec.common.Utils;
import ec.dto.sys.SysUserDTO;
import ec.model.JsonResult;
import ec.model.Pager;
import ec.model.Response;
import ec.model.sys.SysUser;
import ec.service.sys.SysUserService;
import admin.common.AppPermission;
import admin.controller.BaseController;

@Controller
@RequestMapping("json/sysuser/")
@Slf4j
public class SysUserController extends BaseController {

	@Resource
	private SysUserService sysUserService;

	/**
	 * 系统用户列表
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.SysUser_Search)
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> getProdist(SysUserDTO dto, Integer page) {
		JsonResult jsonResult = new JsonResult();
		Pager<Map<String, Object>> pager = new Pager<>();
		if (page == null)
			page = 1;
		pager.setPageNo(page);
		pager.setPageSize(4);
		dto.setPager(pager);
		List<Map<String, Object>> list = sysUserService.searchUsers(dto);
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
	 * @param sysUser
	 * @return
	 * @throws IOException
	 */
	@Permission(AppPermission.SysUser_Add)
	@RequestMapping("doadd")
	@ResponseBody
	public Map<String, Object> doSysUserAdd(SysUser sysUser) throws IOException {
		JsonResult result = new JsonResult();
		try {
			// 查询是否已存在该用户名
			SysUser su = sysUserService.loadUserByName(sysUser.getName());
			if (su != null) {
				return result.errorMapper("用户名重复！");
			}
			Response<Boolean> response = sysUserService.insertUser(sysUser);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add sysuser because error:{},data:{}", Throwables.getStackTraceAsString(e), sysUser);
			return result.errorMapper("系统用户新增失败");
		}
		result.setData("sysuser/list.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

	/**
	 * 根据系统用户ID查询对象
	 * 
	 * @return
	 */
	@Permission(AppPermission.SysUser_Search)
	@RequestMapping("loadUserById")
	@ResponseBody
	public Map<String, Object> loadUserById(Long id) {
		JsonResult jsonResult = new JsonResult();
		SysUser su = sysUserService.loadUserById(id);
		jsonResult.setData(su);
		return jsonResult.toMapper();
	}

	/**
	 * 修改系统用户
	 * 
	 * @return
	 */
	@Permission(AppPermission.SysUser_Edit)
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> updateSysUser(SysUser sysUser, SysUserDTO dto) {
		JsonResult jsonResult = new JsonResult();
		try {
			//设置查询条件
			dto.setId(sysUser.getId());
			dto.setName(sysUser.getName());
			SysUser user = sysUserService.loadByIdAndName(dto);
			if (user == null) {
				SysUser su = sysUserService.loadUserByName(sysUser.getName());
				if (su != null) {
					return jsonResult.errorMapper("系统用户名重复！");
				}
			}
			//如果密码不为空，则对密码进行加密
			if(!Utils.isNullOrEmpty(sysUser.getPassword())){
				sysUser.setPassword(sysUserService.encryptPassword(sysUser.getPassword()));
			}
			Response<Boolean> response = sysUserService.updateUser(sysUser);
			if (!response.isSuccess()) {
				return jsonResult.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to update sysuser because error:{},data:{}", Throwables.getStackTraceAsString(e), sysUser);
			return jsonResult.errorMapper("系统用户信息修改失败");
		}
		jsonResult.setData("sysuser/list.html");
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}
	
	
	
	
}
