package admin.controller.view.media;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.model.media.MediaDetailImage;
import ec.service.media.MediaDetailImageService;

@Controller
@RequestMapping("layer/media")
public class MediaDetailImageControllerView {
	@Resource
	private MediaDetailImageService mediaDetailImageService;
	
	@RequestMapping("update")
	public String viewArticleDEtailImsgeUpdate(ModelMap map,Long id){
		MediaDetailImage mediaDetailImage= mediaDetailImageService.loadById(id);
		map.put("detail", mediaDetailImage);	
		return "article/detail/detail_update";
	}

}
