package admin.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ec.common.AppConfig;

/**
 * 页面初始化时的加载管理，初始化常量
 * @author yongtao
 *
 */
public class ContextInitListener implements ServletContextListener {
	
	public ContextInitListener() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		InputStream inputStream = null;
		try {

			inputStream = arg0.getServletContext().getResourceAsStream("/WEB-INF/properties/app.properties");

			props.load(inputStream);
			AppConfig.UPLOAD_URL = (String) props.getProperty("upload.url");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
