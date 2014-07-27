package info.qingshan.utils.shell;

import static org.junit.Assert.assertTrue;
import info.qingshan.crontab.core.shell.MsgHandler;
import info.qingshan.crontab.core.shell.Shell;
import info.qingshan.crontab.core.shell.StreamHandler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class ShellTest {

	@Test
	public void testExecuteStringArray() throws IOException,
			InterruptedException {
		File file = new File("result_test.txt");
		Shell.execute("cmd", "/c", "dir > ", file.getAbsolutePath());
		assertTrue(file.exists());
		file.delete();
	}

	@Test
	public void testExecuteListOfStringMsgHandler() throws IOException,
			InterruptedException {
		Shell.execute(Arrays.asList("cmd", "/c", "dir"), new MsgHandler() {

			@Override
			public void info(String msg, StreamHandler sg) {
				if (msg.contains("DIR")) {
					System.err.println("ok");
				}
			}

			@Override
			public void error(String msg, StreamHandler sg) {
			}

			@Override
			public void all(String msg, StreamHandler sg) {
			}
		});
	}

	@Test
	public void testCmd() throws IOException, InterruptedException {
		// 执行一个可执行文件
		File file = new File("test/dir.bat");
		Shell.cmd(file.getAbsolutePath());
		// 无法返回
		Shell.cmd("cmd /c dir > result.tmp.log");
	}

	@Test
	public void testCmdStringMsgHandler() throws IOException, InterruptedException {
		Shell.cmd("cmd /c dir /b", new MsgHandler() {

			@Override
			public void info(String msg, StreamHandler sg) {
				if (msg.contains("DIR")) {
					System.err.println("ok");
				}
			}

			@Override
			public void error(String msg, StreamHandler sg) {
			}

			@Override
			public void all(String msg, StreamHandler sg) {
			}
		});
	}
}
