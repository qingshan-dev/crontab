package info.qingshan.crontab.core.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 开启线程处理shell信息流 The class <code>StreamGobbler</code> Description
 *
 * @version %I%, %G%
 * @see relevant class name
 * @since
 */
public class StreamHandler extends Thread {
	private InputStream is;
	private String type;
	private MsgHandler handler;

	public StreamHandler(InputStream is, String type, MsgHandler handler) {
		this.is = is;
		this.type = type;
		this.handler = handler;
	}

	public void run() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		String line = null;
		try {
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				if (handler == null)
					continue;
				if ("error".equals(type)) {
					handler.error(line, this);
				} else if ("info".equals(type)) {
					handler.info(line, this);
				}
				handler.all(line, this);
				// log.info("type: {}, msg:{}", type, line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isr != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}