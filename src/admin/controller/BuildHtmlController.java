package admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author yongtao 根据传入的路径名扫描指定的路径
 *
 */
@Controller
@Slf4j
public class BuildHtmlController extends BaseController{
	
//	@RequestMapping("push")
//	public String viewPush(String mobile, String content, Integer type) {
//		if(!Strings.isNullOrEmpty(mobile)){
//			try {
//				if (type == 1) {
//					pushYunManager_User.pushSingleMessage(mobile, "一条新消息", content, 1, null,0);					
//				}else if(type == 2){
//					pushYunManager_Servant.pushSingleMessage(mobile, "一条客服消息", content, 1, null);
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return "push";
//	}
	
	@RequestMapping("login")
	public String login() {
		log.info("md5:{}", Hashing.md5().hashString("sdf", Charsets.UTF_8));
		return "login";
	}
	
	//登录成功后跳转到默认页
	@RequestMapping("default")
	public String todefault() {
		log.info("md5:{}", Hashing.md5().hashString("sdf", Charsets.UTF_8));
		return "default";
	}
	
	@RequestMapping("nopermission")
	public String noprivilege() {
		return "nopermission";
	}

	@RequestMapping("changepwd")
	public String changepwd() {
		log.info("md5:{}", Hashing.md5().hashString("sdf", Charsets.UTF_8));
		return "change_pwd";
	}
	
	@RequestMapping("{folder}/{path}/{action}")
	public String viewHtml(@PathVariable(value="folder")String folder,@PathVariable(value="path")String path, @PathVariable(value="action")String action){
		return folder + "/" + path + "/" + path + "_" + action;
	}
	
	@RequestMapping("layer/{folder}/{path}/{action}")
	public String viewLayerHtml(@PathVariable(value="folder")String folder,@PathVariable(value="path")String path, @PathVariable(value="action")String action, Long id){
		return folder + "/" + path + "/" + path + "_" + action;
	}
	
	@RequestMapping("{path}/{action}")
	public String viewPathHtml(@PathVariable(value="path")String path, @PathVariable(value="action")String action){
		return path + "/" + path + "_" + action;
	}
	
	@RequestMapping("layer/{path}/{action}")
	public String viewPathLayerHtml(@PathVariable(value="path")String path, @PathVariable(value="action")String action, Long id){
		return path + "/" + path + "_" + action;
	}
	
	@RequestMapping("{folder}/{folder2}/{path}/{action}")
	public String viewHtml2(@PathVariable(value="folder")String folder,@PathVariable(value="folder2")String folder2,@PathVariable(value="path")String path, @PathVariable(value="action")String action){
		//String[] array = folder.split("-");
//		StringBuilder sb = new StringBuilder();
//		for (String f : array) {
//			sb.append("f")
//		}
		return folder + "/" + folder2 + "/" + path + "/" + path + "_" + action;
	}
	
	@RequestMapping("layer/{folder}/{folder2}/{path}/{action}")
	public String viewLayerHtml2(@PathVariable(value="folder2")String folder2,@PathVariable(value="folder")String folder,@PathVariable(value="path")String path, @PathVariable(value="action")String action, Long id){
		//String[] array = folder.split("_");
		return folder + "/" + folder2 + "/" + path + "/" + path + "_" + action;
	}
	
}
