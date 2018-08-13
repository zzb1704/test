package admin.controller.anysc.sys;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Throwables;

import admin.common.AppPermission;
import ec.common.Permission;
import ec.dto.sys.SysMenu2DTO;
import ec.model.JsonResult;
import ec.model.Pager;
import ec.model.Response;
import ec.model.sys.SysMenu2;
import ec.service.sys.SysMenu2Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 二级菜单管理
 * 
 * @author DSH
 * 
 */
@Controller
@RequestMapping("json/menu2/")
@Slf4j
public class SysMenu2Controller {
	@Resource
	private SysMenu2Service sysMenu2Service;

	/**
	 * 查询二级菜单
	 * 
	 * @return
	 */
	@Permission(AppPermission.Menu2_Search)
	@ResponseBody
	@RequestMapping("list")
	public Map<String, Object> getListMenu2(SysMenu2DTO dto, Integer page) {
		JsonResult jsonResult = new JsonResult();
		Pager<Map<String, Object>> pager = new Pager<>();
		if (page == null)
			page = 1;
		pager.setPageNo(page);
		pager.setPageSize(10);
		dto.setPager(pager);
		List<Map<String, Object>> list = sysMenu2Service.list(dto);
		System.out.println("list.size()  " + list.size());
		pager.setResults(list);
		jsonResult.setData(list);
		jsonResult.setPager(pager);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setNavigatePageNumbers(pager.getNavigatePageNumbers("").get("navigatePageNumbers").toString());
		return jsonResult.toMapper();
	}

	/**
	 * 增加二级分类菜单
	 * 
	 * @param sysMenu2
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("doadd")
	@Permission(AppPermission.Menu2_Add)
	@ResponseBody
	public Map<String, Object> doCategory2Add(SysMenu2 sysMenu2) throws IOException {
		JsonResult result = new JsonResult();
		if (sysMenu2.getStatus() == null) {
			sysMenu2.setStatus(SysMenu2.Status.VALID.value());
		}
		try {
			Response<Boolean> response = sysMenu2Service.insert(sysMenu2);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add prodinfo because error:{},data:{}", Throwables.getStackTraceAsString(e), sysMenu2);
			return result.errorMapper("二级分类菜单添加失败");
		}
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

	/**
	 * 查询三级级菜单根据id
	 * 
	 * @param id
	 * @return
	 */
	@Permission(AppPermission.Menu2_Search)
	@RequestMapping("getLoadById")
	@ResponseBody
	public Map<String, Object> getLoadById(Long id) {
		JsonResult jsonResult = new JsonResult();
		SysMenu2 sysMenu2 = sysMenu2Service.loadById(id);
		jsonResult.setData(sysMenu2);
		return jsonResult.toMapper();
	}

	/**
	 * 修改三级菜单
	 * 
	 * @param sysMenu3
	 * @return
	 */
	@Permission(AppPermission.Menu2_Edit)
	@RequestMapping("toupdate")
	@ResponseBody
	public Map<String, Object> doUpdate(SysMenu2 sysMenu2) {
		JsonResult result = new JsonResult();
		try {
			if (sysMenu2.getStatus() == null) {
				sysMenu2.setStatus(SysMenu2.Status.VALID.value());
			}
			Response<Boolean> response = sysMenu2Service.update(sysMenu2);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to update prodinfo because error:{},data:{}", Throwables.getStackTraceAsString(e), sysMenu2);
			return result.errorMapper("修改二级菜单失败");
		}
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

}
