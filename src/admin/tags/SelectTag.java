package admin.tags;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import lombok.Getter;
import lombok.Setter;

import com.google.common.base.Strings;

/**
 * 自定义下拉列表标�?:绑定枚举
 * @author songhailiang
 *
 */
public class SelectTag extends SimpleTagSupport {

	/**
	 * 下拉列表name和id
	 */
	private String name;
	/**
	 * 下拉列表默认选中�?
	 */
	private String value;
	/**
	 * 对应的枚举�??
	 */
	private String enumName;
	/**
	 * 样式�?
	 */
	private String cssClass;
	
	/**
	 * 是否必填
	 */
	@Getter
	@Setter
	private Boolean required;
	
	/**
	 * 下拉列表onchange事件
	 */
	@Getter
	@Setter
	private String onChange;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doTag() throws JspException, IOException {
		
		try {

			Class<?> enumClass = Class.forName("ec.model."+enumName);
			Method method = enumClass.getMethod("all");
			Map<Integer, String> map = (Map<Integer, String>)method.invoke(enumClass);
			JspWriter out = getJspContext().getOut();  
	        
	        StringBuffer attrBuffer = new StringBuffer();
			attrBuffer.append(" name=").append(name);
			attrBuffer.append(" id=").append(name);
			if (!Strings.isNullOrEmpty(cssClass))
				attrBuffer.append(" class=").append(cssClass);
			if (required != null && required)
				attrBuffer.append(" data-parsley-required");
			if(!Strings.isNullOrEmpty(onChange))
				attrBuffer.append(" onchange=\"").append(onChange).append("\"");

			out.print("<select" + attrBuffer.toString() + ">");
			out.print("<option value=''>--请选择--</option>");
	        
	        for (int key : map.keySet()) {
				Object v = map.get(key);
				//Field display = item.getField("display");
				String selected="";
				if(value!= null && value.length()>0 && value.equalsIgnoreCase(""+ key)){
					selected = "selected";
				}
				out.print("<option value=" + key + " "+selected+">" + v + "</option>");
			}
	        
	        out.print("</select>");  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.doTag();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	/**
	 * @param className the className to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	public String getCssClass(){
		return this.cssClass;
	}
}
