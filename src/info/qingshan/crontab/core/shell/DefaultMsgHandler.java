package info.qingshan.crontab.core.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认实现 shell msg处理
 * The class <code>DefaultMsgHandler</code> Description 
 *
 * @version %I%, %G%
 * @see    relevant class name
 * @since
 */
public class DefaultMsgHandler implements MsgHandler {

	private static final Logger log = LoggerFactory
			.getLogger(DefaultMsgHandler.class);

	public void info(String msg, StreamHandler sg) {
		log.info("info msg: {}", msg);
	}

	public void error(String msg, StreamHandler sg) {
		log.info("error msg: {}", msg);
	}

	@Override
	public void all(String msg, StreamHandler sg) {
//		log.info("all msg: {}", msg);
	}
}
