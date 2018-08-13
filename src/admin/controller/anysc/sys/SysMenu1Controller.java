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
import ec.dto.sys.SysMenu1DTO;
import ec.model.JsonResult;
import ec.model.Pager;
import ec.model.Response;
import ec.model.sys.SysMenu1;
import ec.service.sys.SysMenu1Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 一级菜单管理
 * @author DSH
 *         
 */
@Controller
@RequestMapping("json/menu1/")
@Slf4j
public class SysMenu1Controller {
	@Resource
	private SysMenu1Service sysMenu1Service;

	/**
	 * 查询一级菜单
	 * 
	 * @return
	 */
	@Permission(AppPermission.Menu1_Search)
	@ResponseBody
	@RequestMapping("list")
	public Map<String, Object> getListMenu1(SysMenu1DTO dto, Integer page) {
		JsonResult jsonResult = new JsonResult();
		Pager<Map<String, Object>> pager = new Pager<>();
		if (page == null)
			page = 1;
		pager.setPageNo(page);
		pager.setPageSize(10);
		dto.setPager(pager);
		List<Map<String, Object>> list = sysMenu1Service.findMenu1(dto);
		pager.setResults(list);
		jsonResult.setData(list);
		jsonResult.setPager(pager);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setNavigatePageNumbers(pager.getNavigatePageNumbers("")
				.get("navigatePageNumbers").toString());
		return jsonResult.toMapper();
	}

	/**
	 * 增加商品一级分类
	 * 
	 * @param category1
	 * @return
	 * @throws IOException
	 */
	@Permission(AppPermission.Menu1_Add)
	@RequestMapping("insert")
	@ResponseBody
	public Map<String, Object> doCategory1Add(SysMenu1 sysMenu1)
			throws IOException {
		JsonResult result = new JsonResult();
		if (sysMenu1.getStatus() == null) {
			sysMenu1.setStatus(SysMenu1.Status.VALID.value());
		}
		try {
			Response<Boolean> response = sysMenu1Service.insert(sysMenu1);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add prodinfo because error:{},data:{}",
					Throwables.getStackTraceAsString(e), sysMenu1);
			return result.errorMapper("一级菜单添加失败");
		}
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

	/**
	 * 查询一级菜单根据id
	 * 
	 * @param id
	 * @return
	 */
	@Permission(AppPermission.Menu1_Search)
	@RequestMapping("getLoadById")
	@ResponseBody
	public Map<String, Object> getLoadById(Long id) {
		JsonResult jsonResult = new JsonResult();
		SysMenu1 sysMenu1 = sysMenu1Service.loadById(id);
		jsonResult.setData(sysMenu1);
		return jsonResult.toMapper();
	}

	/**
	 * 修改一级菜单
	 * 
	 * @param platformTopic
	 * @return
	 */
	@Permission(AppPermission.Menu1_Edit)
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> doUpdate(SysMenu1 sysMenu1) {
		JsonResult result = new JsonResult();
		try {
			if (sysMenu1.getStatus() == null) {
				sysMenu1.setStatus(SysMenu1.Status.VALID.value());
			}
			Response<Boolean> response = sysMenu1Service.update(sysMenu1);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to update prodinfo because error:{},data:{}",
					Throwables.getStackTraceAsString(e), sysMenu1);
			return result.errorMapper("修改一级菜单失败");
		}
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

}
