package info.qingshan.crontab.job.lister;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.listeners.TriggerListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriggerListener extends TriggerListenerSupport {

	
	private static final Logger log = LoggerFactory .getLogger(TriggerListener.class);
	
	@Override
	public String getName() {
		return "TriggerListener";
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		
	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		String date = String.format("%tF_%1$tT", new Date());
		log.info(">>　{}:  now【{}】  下一次将在 【{}】执行", context.getJobDetail().getKey().getName(), 
				date,String.format("%tF %1$tT", context.getNextFireTime()));
	}

}
