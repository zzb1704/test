package admin.controller.anysc;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.common.AppPermission;
import ec.common.Permission;
import ec.dto.sys.SysMenu1DTO;
import ec.model.JsonResult;
import ec.model.sys.SysMenu1;
import ec.service.sys.SysMenu1Service;
import ec.service.sys.SysMenu2Service;
import ec.service.sys.SysMenu3Service;

/**
 * 菜单显示管理
 */
@Controller
@RequestMapping("json/menu/")
public class MenuController {
	@Resource
	private SysMenu1Service sysMenu1Service;
	
	@Resource
	private SysMenu2Service sysMenu2Service;
	
	@Resource
	private SysMenu3Service sysMenu3Service;

	/**
	 * 查询一级菜单
	 * 
	 * @return
	 */
	@Permission(AppPermission.Menu_Search)
	@ResponseBody
	@RequestMapping("menu1/list")
	public Map<String, Object> getListMenu1(SysMenu1DTO dto) {
		JsonResult jsonResult = new JsonResult();
		dto.setStatus(SysMenu1.Status.VALID.value());
		List<Map<String, Object>> list = sysMenu1Service.findMenu1(dto);
		jsonResult.setData(list);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}

	/**
	 * 查询三级菜单
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.Menu_Search)
	@ResponseBody
	@RequestMapping("menu3/list")
	public Map<String, Object> getListMenu3(Long id) {
		JsonResult jsonResult = new JsonResult();
		List<Map<String, Object>> list = sysMenu3Service.findMenu3ByMenu2Id(id);
		jsonResult.setData(list);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}
	
	/**
	 * 查询二级菜单
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.Menu_Search)
	@ResponseBody
	@RequestMapping("menu2/list")
	public Map<String, Object> getListMenu2(Long id) {
		JsonResult jsonResult = new JsonResult();
		List<Map<String, Object>> sysMenu2 = sysMenu2Service.searchMenu2ByMenu1(id);
		jsonResult.setData(sysMenu2);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		return jsonResult.toMapper();
	}
	

}
