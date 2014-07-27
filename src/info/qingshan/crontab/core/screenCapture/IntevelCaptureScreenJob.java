package info.qingshan.crontab.core.screenCapture;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 定时调度执行捕获屏幕图片
 * </pre>
 * <hr Color="green" >
 * @author Topin
 * @since JDK 1.5
 * @date 2014-3-12
 */
public class IntevelCaptureScreenJob implements Job {
	
	private static final Logger log = LoggerFactory .getLogger(IntevelCaptureScreenJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			JobDataMap map = context.getJobDetail().getJobDataMap();
			String storeDir = map.getString("dir");
			String photoSuffix = map.getString("photoSuffix");
			String date = String.format("%tF_%1$tT", new Date());
			String subDirectory = String.format("%tY/%1$tm/%1$td", new Date());
			String filePath = storeDir + "/"+subDirectory+"/" + date.replace(':', '-') + "." + photoSuffix;
			ScreentScreen.snatchScreen(filePath, photoSuffix);
			log.info("print screent => {}", filePath);
		} catch (Exception e) {
			log.error("print screent error", e);
		}
	}

}
