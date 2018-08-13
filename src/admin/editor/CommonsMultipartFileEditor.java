package admin.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;
/**
 * 
 * @author MYDREAM
 *
 */
public class CommonsMultipartFileEditor extends PropertiesEditor {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		if (text == null || text.equals("")) {
			text = null;
		}
		setValue(text);
	}

	@Override
	public String getAsText() {
		return getValue().toString();
	}
}
