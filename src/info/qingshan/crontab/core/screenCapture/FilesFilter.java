package info.qingshan.crontab.core.screenCapture;


import java.io.*;
import java.util.regex.*;

/**
 * 过滤文件使用
 * @author qingshan
 * 
 */
public class FilesFilter
		implements FileFilter {

	public FilesFilter() {}

	/**
	 * 构造器:初始化过滤文件与不过滤
	 * @param includingFiles 不过滤文件:正则表达式.*
	 * @param excludingFiles 滤过文件:默认正则表达式--^.*\\.(exe|bat|sh)$
	 */
	public FilesFilter(String includingFiles, String excludingFiles) {
		this.includingFiles = includingFiles;
		this.excludingFiles = excludingFiles;
	}

	/** 不过滤文件:正则表达式. **/
	private String includingFiles = ".*";
	/** 滤过文件:默认正则表达式--^.*\\.(exe|bat|sh)$ */
	private String excludingFiles = "^.*\\.(exe|bat|sh)$";

	public String getIncludingFiles() {
		return includingFiles;
	}

	public void setIncludingFiles(String includingFiles) {
		this.includingFiles = includingFiles;
	}

	public String getExcludingFiles() {
		return excludingFiles;
	}

	public void setExcludingFiles(String excludingFiles) {
		this.excludingFiles = excludingFiles;
	}

	@Override
	public boolean accept(File file) {
		if (file == null) {
			return false;
		}
		// log.debug(file.toPath().toString());
		// 过滤目录
		if (file.isDirectory()) {
			return false;
		}
		// 过滤文件
		if (file.isFile()) {
			String fileName = file.getName();
			if (fileName == null) {
				return false;
			} else if (fileName.trim().length() > 0) {
				boolean include = getMatchResult(includingFiles, fileName);
				boolean exclude = getMatchResult(excludingFiles, fileName);
				// 过滤文件后缀名
				if (include && !exclude) {
//					System.out.println(file.getPath().toString());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param pattern 所需匹配的正则表达式
	 * @param deal 被匹配字符串
	 * @return 匹配后结果:boolean
	 */
	public boolean getMatchResult(String pattern, String deal) {
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(deal);
		boolean b = m.matches();
		return b;
		// int index = 0;
		// int length = deal.length();
		// boolean result =true;
		// while (result) {
		// // m.region(index, length);
		// result = m.find(index);
		// if(m.hitEnd()){
		// break;
		// }
		// System.out.println(m.start());
		// index = m.start()+1;
		//
		// }
		// m.find();
	}

	public static void main(String[] args) {
		// File file = new File("c:/");
		// file.listFiles(new FilesFilter());
		// String excludingFiles=".*\\.[exe|bat|sh|ima]";
		// String excludingFiles="^.*[\\d]+.*";
		System.out.println("aaa2sa3a".replaceAll("[\\d]", "-"));

		// System.out.println(new FilesFilter()
		// .getMatchResult("[\\d]", "aaa2sa3a"));
	}
}
