package info.qingshan.crontab.core.shell;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调用命令
 * 		功能列表: 1.直接调用bat/sh文件 2.输出流可以自定义处理
 * 		参考:ProcessBuilder和Runtime.exec的区别联系(http://blog.sina.com.cn/s/blog_605f5b4f01019w2q.html)
 *   
 * @version %I%, %G%
 * @see    relevant class name
 * @since
 */
public class Shell {

	private static final Logger log = LoggerFactory.getLogger(Shell.class);
	
	/**
	 * (常用) 执行命令, 并使用默认输出流处理输出流信息
	 * @Description: 
	 * @param cmd
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void execute(String... cmd) throws IOException, InterruptedException {
		execute(Arrays.asList(cmd), new DefaultMsgHandler());
	}
	
	/**
	 * 可以对执行的命令行参数化("cmd", "/c", "dir"), 并让命令正确使用输出流(cat log.log | grep "keyword" > error.log)
	 * @Description: 
	 * @param cmds 命令集合
	 * @param handler 输出流的信息
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void execute(List<String> cmds, MsgHandler handler) throws IOException, InterruptedException {
		log.info("execute cmd: {}, handler: {}", cmds, handler);
		ProcessBuilder pb = new ProcessBuilder(cmds);  
		Process proc = pb.start();
		processCmd(handler, proc); 
	}


	/**
	 * (常用)输出命令即可运行, 相当于windows下的cmd中运行命令
	 * @Description: 
	 * @param cmd 命令
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void cmd(String cmd) throws IOException, InterruptedException {
		log.info("run cmd: [{}]", cmd);
		Process proc = Runtime.getRuntime().exec(cmd);
		processCmd(new DefaultMsgHandler(), proc);
	}
	
	/**
	 * 输出命令即可运行, 相当于windows下的cmd中运行命令
	 * @Description: 
	 * @param cmd 命令
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void cmd(String cmd, MsgHandler handler) throws IOException, InterruptedException {
		log.info("run cmd: {}", cmd);
		Process proc = Runtime.getRuntime().exec(cmd);
		processCmd(handler, proc);
	}
	
	/**
	 * 为防止输出流堵死执行命令的线程, 特别对输出流进行处理
	 * @Description: 
	 * @param handler
	 * @param proc
	 * @throws InterruptedException
	 */
	private static void processCmd(MsgHandler handler, Process proc)
			throws InterruptedException {
		StreamHandler errorGobbler = new StreamHandler(proc.getErrorStream(), "error", handler);
		StreamHandler outputGobbler = new StreamHandler(proc.getInputStream(), "info", handler);
		errorGobbler.start();
		outputGobbler.start();
		proc.waitFor();
		proc.destroy();
	}
}
