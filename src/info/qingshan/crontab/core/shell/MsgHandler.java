package info.qingshan.crontab.core.shell;


/**
 * 对shell输出信息进行集中处理
 * The class <code>MsgHandler</code> Description 
 *
 * @version %I%, %G%
 * @see    relevant class name
 * @since
 */
public interface MsgHandler {

	/**
	 * 处理标准流
	 * @Description: 
	 * @param msg 标准流信息(逐行)
	 * @param sg  处理输出流对象
	 */
	public void info(String msg, StreamHandler sg);

	/**
	 * 处理错误流
	 * @Description: 
	 * @param msg 标准流信息(逐行)
	 * @param sg  处理输出流对象
	 */
	public void error(String msg, StreamHandler sg);
	
	/**
	 * 处理输出流
	 * @Description: 
	 * @param msg 标准流信息(逐行)
	 * @param sg  处理输出流对象
	 */
	public void all(String msg, StreamHandler sg);
}
