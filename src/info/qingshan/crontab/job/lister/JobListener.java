package info.qingshan.crontab.job.lister;

import org.quartz.JobExecutionContext;
import org.quartz.listeners.JobListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobListener extends JobListenerSupport {

	private static final Logger log = LoggerFactory .getLogger(JobListener.class);
	
	@Override
	public String getName() {
		return "JobListener";
	}


	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		log.info("将执行:{}", context.getJobDetail().getKey().getName());
	}

}
