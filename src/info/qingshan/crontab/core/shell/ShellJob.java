package info.qingshan.crontab.core.shell;


import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

public class ShellJob implements Job {

	private static final Logger log = LoggerFactory .getLogger(ShellJob.class);
	
	/*参数化命令行, 并有需要将标准流重定向到文件中*/
	private static final String CMD_ARGS = "cmd_args";
	/*只执行命令行*/
	private static final String CMD = "cmd";
	
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap map = context.getJobDetail().getJobDataMap();
		String type = map.getString("type");
		try {
			if(CMD.equals(type)){
				String cmd = map.getString("cmd");
				Shell.cmd(cmd);
			}else if(CMD_ARGS.equals(type)){
				JSONArray cmd =  (JSONArray) map.get("cmd");
				Shell.execute(cmd.toArray(new String[cmd.size()]));
			}
		} catch (Exception e) {
			log.error("run shell => {}", map, e);
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Shell.execute("B:/产品/zren/tools/search.bat");
	}
}
