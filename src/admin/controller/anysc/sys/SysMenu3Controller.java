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
import ec.dto.sys.SysMenu3DTO;
import ec.model.JsonResult;
import ec.model.Pager;
import ec.model.Response;
import ec.model.sys.SysMenu3;
import ec.service.sys.SysMenu3Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 三级菜单管理
 * 
 * @author DSH
 * 
 */
@Controller
@RequestMapping("json/menu3/")
@Slf4j
public class SysMenu3Controller {
	@Resource
	private SysMenu3Service sysMenu3Service;

	/**
	 * 查询三级菜单
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@Permission(AppPermission.Menu3_Search)
	@ResponseBody
	@RequestMapping("list")
	public Map<String, Object> getListMenu3(SysMenu3DTO dto, Integer page) {
		JsonResult jsonResult = new JsonResult();
		Pager<Map<String, Object>> pager = new Pager<>();
		if (page == null)
			page = 1;
		pager.setPageNo(page);
		pager.setPageSize(10);
		dto.setPager(pager);
		List<Map<String, Object>> list = sysMenu3Service.findMenu3(dto);
		pager.setResults(list);
		jsonResult.setData(list);
		jsonResult.setPager(pager);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setNavigatePageNumbers(pager.getNavigatePageNumbers("").get("navigatePageNumbers").toString());
		return jsonResult.toMapper();
	}

	/**
	 * 增加三级菜单
	 * 
	 * @param category1
	 * @return
	 * @throws IOException
	 */
	@Permission(AppPermission.Menu3_Add)
	@RequestMapping("insert")
	@ResponseBody
	public Map<String, Object> doMenu3Add(SysMenu3 sysMenu3) throws IOException {
		JsonResult result = new JsonResult();
		if (sysMenu3.getStatus() == null) {
			sysMenu3.setStatus(SysMenu3.Status.VALID.value());
		}
		try {
			Response<Boolean> response = sysMenu3Service.insert(sysMenu3);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add prodinfo because error:{},data:{}", Throwables.getStackTraceAsString(e), sysMenu3);
			return result.errorMapper("三级菜单添加失败");
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
	@Permission(AppPermission.Menu3_Search)
	@RequestMapping("getLoadById")
	@ResponseBody
	public Map<String, Object> getLoadById(Long id) {
		JsonResult jsonResult = new JsonResult();
		SysMenu3 sysMenu3 = sysMenu3Service.loadById(id);
		jsonResult.setData(sysMenu3);
		return jsonResult.toMapper();
	}

	/**
	 * 修改三级菜单
	 * 
	 * @param sysMenu3
	 * @return
	 */
	@Permission(AppPermission.Menu3_Edit)
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> doUpdate(SysMenu3 sysMenu3) {
		JsonResult result = new JsonResult();
		try {
			if (sysMenu3.getStatus() == null) {
				sysMenu3.setStatus(SysMenu3.Status.VALID.value());
			}
			Response<Boolean> response = sysMenu3Service.update(sysMenu3);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to update prodinfo because error:{},data:{}", Throwables.getStackTraceAsString(e), sysMenu3);
			return result.errorMapper("修改三级菜单失败");
		}
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

}
