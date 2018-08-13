package admin.tags;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;

import ec.common.JSonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * json格式数据输出标签
 * 
 * @author songhailiang
 * 
 */
@Slf4j
public class JsonTag extends SimpleTagSupport {

	/**
	 * json格式源数�?
	 */
	@Getter
	@Setter
	private String value;

	@SuppressWarnings("unchecked")
	@Override
	public void doTag() throws JspException, IOException {

		try {

			// 暂时只处理Map格式的json字符串，以后有需要可以再扩展属�??
			
			Map<String, String> map = (Map<String, String>) JSonUtils
					.readValue(value, LinkedHashMap.class);
			if (map != null) {
				String str = Joiner.on(",").join(map.values());
				JspWriter out = getJspContext().getOut();
				out.print(str);
			}

		} catch (Exception e) {
			log.error("fail to resolve json-tag with data:{},error:{}", value,
					Throwables.getStackTraceAsString(e));
		}

		super.doTag();
	}
}
