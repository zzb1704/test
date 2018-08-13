package admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ec.common.FileUtil;
import ec.model.JsonResult;
import ec.model.Response;

/**
 * 文件操作控制器
 * 
 * @author yongtao
 * @create 2015 2015年5月2日 下午1:31:01
 *
 */
@RequestMapping("file")
@Controller
public class FileController extends BaseController {

	@Autowired
	HttpServletRequest request;
	
	
	@RequestMapping("/upload/photo")
	@ResponseBody
	public Map<String, Object> uploadPhoto(
			@RequestParam("photo") CommonsMultipartFile file) throws IOException {
		Map<String, Object>  map=new HashMap<String, Object>();
		JsonResult jsonResult = new JsonResult();
		if (file != null && !file.isEmpty()) {
			Response<String> response = FileUtil.uploadPhoto(file);
			if (!response.isSuccess())
				return jsonResult.errorMapper(response.getError());
			map.put("data", response.getResult());
			map.put("resultCode",1000);
			BufferedImage sourceImg = ImageIO.read(file.getInputStream());
			if(sourceImg!=null) {
				map.put("scene_image_width",sourceImg.getWidth()+"");
				map.put("scene_image_height",sourceImg.getHeight()+"");
			}
			else{
				return jsonResult.errorMapper("请选择图片");
			}
		}
		return map;
	}
	
	@RequestMapping("/upload/file")
	@ResponseBody
	public Map<String, Object> uploadFile(@RequestParam("file") CommonsMultipartFile file) throws IOException {
		Map<String, Object>  map=new HashMap<String, Object>();
		JsonResult jsonResult = new JsonResult();
		if (file != null && !file.isEmpty()) {
			Response<String> response = FileUtil.uploadFile(file);
			if (!response.isSuccess())
				return jsonResult.errorMapper(response.getError());
			map.put("data",response.getResult());
			map.put("resultCode",1000);
		}
		return map;
	}

}
