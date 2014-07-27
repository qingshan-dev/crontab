package info.qingshan.crontab.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <pre>
 * 从classpath中读取文件
 * </pre>
 * <hr Color="green" >
 * 
 * @author Topin
 * @since JDK 1.5
 * @date 2014-6-10
 */
public class ClassPathUtil {
	
	private static final Logger log = LoggerFactory
			.getLogger(ClassPathUtil.class);

	/** 定义classLoader */
	private static ClassLoader[]	loaders	= new ClassLoader[] {
			Thread.currentThread().getContextClassLoader(),
			ClassPathUtil.class.getClassLoader(),
			ClassLoader.getSystemClassLoader() };

	/**
	 * 在classpath中读取文件: 顺序是: jar同目录的下的文件 > jar中的文件
	 * 读取文件后转为stream转出
	 * @Description:
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	public static InputStream getStream(String resource) throws IOException {
		for (ClassLoader cl : loaders) {
			InputStream in = cl.getResourceAsStream("/" + resource);
			if (in == null) {
				in = cl.getResourceAsStream(resource);
				if (in != null) {
					log.debug("find config at classpath => out");
				}
			}
			if (in != null) {
				return in;
			}
		}
		throw new FileNotFoundException(resource);
	}
	
	/**
	 * 在classpath中读取文件: 顺序是: jar同目录的下的文件 > jar中的文件 
	 * 读取文件后转为string转出
	 * @Description: 
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	public static String getString(String resource) throws IOException {
		return toString(getStream(resource));
	}
	
	/**
	 * 流转为string
	 * @Description: 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String toString(InputStream in)
			throws IOException {
		StringBuilder sb = new StringBuilder(1024);
		byte[] bytes = new byte[1024];
		for (int i = in.read(bytes); i != -1; i = in.read(bytes)) {
			sb.append(new String(bytes, 0, i, Charset.forName("UTF-8")));
		}
		in.close();
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getString("ini.json"));
	}
}