package admin.tags;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LabelTag extends SimpleTagSupport {
	private String enumName;
	private int key;

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		try {
			Class<?> enumClass = Class.forName("ec.model."+enumName);

			Method method = enumClass.getMethod("fromNumber", int.class);
			Object obj = method.invoke(enumClass, key);
			String name = obj==null?"":obj.toString();
			JspWriter out = getJspContext().getOut();
			out.print(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.doTag();
	}

	/**
	 * @param enumName
	 *            the enumName to set
	 */
	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(int key) {
		this.key = key;
	}
}
