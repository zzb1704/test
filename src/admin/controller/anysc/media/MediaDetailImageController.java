package admin.controller.anysc.media;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Throwables;

import admin.common.AppPermission;
import admin.controller.BaseController;
import ec.common.Permission;
import ec.dto.media.MediaDetailImageDTO;
import ec.model.JsonResult;
import ec.model.Pager;
import ec.model.Response;
import ec.model.media.MediaDetailImage;
import ec.service.media.MediaDetailImageService;
import lombok.extern.slf4j.Slf4j;

/**
 * 图文管理
 * 
 * @author ZZB
 * @date 2016年7月22日
 */
@RequestMapping("anysc/media/detail")
@Controller
@Slf4j
public class MediaDetailImageController extends BaseController {
	@Resource
	private MediaDetailImageService mediaDetailImageService;

	/**
	 * 查询图文详情
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	@Permission(AppPermission.Media_Detail_Image_Search)
	public Map<String, Object> getMediaDetailImageList(MediaDetailImageDTO dto, Integer page) {
		JsonResult jsonResult = new JsonResult();
		Pager<Map<String, Object>> pager = new Pager<>();
		if (page == null)
			page = 1;
		pager.setPageNo(page);
		pager.setPageSize(8);
		dto.setPager(pager);
		List<Map<String, Object>> list = mediaDetailImageService.search(dto);
		pager.setResults(list);
		jsonResult.setData(list);
		jsonResult.setPager(pager);
		jsonResult.setResultCode(JsonResult.SUCCESS);
		jsonResult.setNavigatePageNumbers(pager.getNavigatePageNumbers("").get("navigatePageNumbers").toString());
		return jsonResult.toMapper();
	}

	/**
	 * 图文新增
	 * 
	 */
	@RequestMapping("add")
	@ResponseBody
	@Permission(AppPermission.Media_Detail_Image_Add)
	public Map<String, Object> doMediaDetailImageAdd(MediaDetailImage mediaDetailImage) throws IOException {
		JsonResult result = new JsonResult();
		if (mediaDetailImage.getStatus() == null) {
			mediaDetailImage.setStatus(MediaDetailImage.Status.VALID.value());
		}
		try {
			Response<Boolean> response = mediaDetailImageService.insert(mediaDetailImage);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add mediaDetailImage because error:{},data:{}", Throwables.getStackTraceAsString(e),
					mediaDetailImage);
			return result.errorMapper("新增失败");
		}
		result.setData("detail/list.html");
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

	/**
	 * 修改图文
	 */
	@RequestMapping("update")
	@ResponseBody
	@Permission(AppPermission.Media_Detail_Image_Edit)
	public Map<String, Object> doMediaDetailImageupdate(MediaDetailImage mediaDetailImage) throws IOException {
		JsonResult result = new JsonResult();
		try {
			Response<Boolean> response = mediaDetailImageService.update(mediaDetailImage);
			if (!response.isSuccess()) {
				return result.errorMapper(response.getError());
			}
		} catch (Exception e) {
			log.info("fail to add mediadetailimage because error:{},data:{}", Throwables.getStackTraceAsString(e),
					mediaDetailImage);
			return result.errorMapper("修改失败");
		}
		result.setResultCode(JsonResult.SUCCESS);
		return result.toMapper();
	}

}
