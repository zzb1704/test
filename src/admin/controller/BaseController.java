package admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import admin.editor.CommonsMultipartFileEditor;

/**
 * 
 * @author MYDREAM
 *
 */
public class BaseController {
	protected	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	protected	SimpleDateFormat sdf_data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@InitBinder
	public void initDataBinder(WebDataBinder binder, HttpServletRequest request) {
		
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		
		binder.registerCustomEditor(CommonsMultipartFile.class, new CommonsMultipartFileEditor());
	}
	
	/**
	 * 自动生成编号
	 * @param last 已存在的最大数
	 * @param non 最大长度的以0组合的字符串 例如:00000000
	 * @return 最大的编号
	 */
	public String autoBuildNo(Long last, String non){
		String no;
		if(last == null || last < 1){
			no = non.substring(0,non.length()-1) + "1";
		}else {
			last++;
			no = String.valueOf(last);
			no = non.substring(0,non.length()-no.length())+no;
		}
		
		return no;
	}
	
	/**
	 * 自动创建编号
	 * @param last 最后一条记录的编号 没有传null
	 * @param head 编号头固定的字符串
	 * @param non 编号的尾部初始字符串 默认用0
	 * @return
	 */
	public String autoBuildeNo(String last, String head, String non) {
		String no;
		int lastno; //最后一条编号的数字
		if(last == null){
			lastno = 0;
		}else {
			String tmpno = last.substring(head.length());
			lastno = Integer.valueOf(tmpno);
		}
		lastno++;
		no = head + non.substring(0, non.length()-String.valueOf(lastno).length()) + lastno;
		return no;
	}
}
