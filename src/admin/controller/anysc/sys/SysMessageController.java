package admin.controller.anysc.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Throwables;
import ec.dto.sys.SysMessageDTO;
import ec.model.JsonResult;
import ec.model.Pager;
import ec.model.Response;
import ec.model.sys.SysMessage;
import ec.service.sys.SysMessageService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("anysc/sys/message")
@Slf4j
public class SysMessageController {

	@Resource
	private SysMessageService sysMessageService;

	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> search(SysMessageDTO sysMessageDTO, Integer page) {
		JsonResult jsonResult = new JsonResult();
		Pager<Map<String, Object>> pager = new Pager<>();
		if (page == null)
			page = 1;
		pager.setPageNo(page);
		pager.setPageSize(10);
		sysMessageDTO.setPager(pager);
		List<Map<String, Object>> list = sysMessageService.search(sysMessageDTO);
		pager.setResults(list);
		jsonResult.setData(list);
		jsonResult.setPager(pager);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setNavigatePageNumbers(pager.getNavigatePageNumbers("").get("navigatePageNumbers").toString());
		return jsonResult.toMapper();
	}

	// 增加方法
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> insert(SysMessage sysMessage) {
		JsonResult result = new JsonResult();
		try {
			Response<Boolean> response = sysMessageService.insert(sysMessage);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add sysMessage because error:{},data:{}", Throwables.getStackTraceAsString(e),sysMessage);
			return result.errorMapper("添加失败");
		}
		result.setData("sys/message/list.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

	// 修改方法
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(SysMessage sysMessage) {
		JsonResult result = new JsonResult();
		try {
			Response<Boolean> response = sysMessageService.update(sysMessage);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to update sysMessage because error:{},data:{}", Throwables.getStackTraceAsString(e),sysMessage);
			return result.errorMapper("修改失败");
		}
		result.setData("sys/message/list.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

}