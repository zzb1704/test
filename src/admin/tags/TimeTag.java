package admin.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TimeTag extends SimpleTagSupport{
	@Override
	public void doTag() throws JspException, IOException {
		try {
			JspWriter out = getJspContext().getOut();
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd�?");//可以方便地修改日期格�?
			String hehe = dateFormat.format( now ); 
            String day=getWeekOfDate(now);
			out.print("<label>"+hehe+"&nbsp"+day+"</label>");
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.doTag();
	}
    public  String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期�?", "星期�?", "星期�?", "星期�?", "星期�?", "星期�?", "星期�?"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
