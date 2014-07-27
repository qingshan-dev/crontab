package info.qingshan.crontab.core.screenCapture;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

/**
 * <pre>
 * 定时捕获电脑全屏图像
 * </pre>
 * <hr Color="green" >
 * </hr>
 */
public class ScreentScreen {

	// bmp/png/jpg/gif
	public static final double KB = 1024;
	public static final double MB = Math.pow(KB, 2);
	public static final double GB = Math.pow(KB, 3);
	public static final double[] FILE_LIMITS = { KB, MB, GB };
	public static final String[] FILE_PARTS = { "KB", "MB", " GB" };

	public static void snatchScreen(String path, String photoSuffix) {
		Robot robot;
		try {
			robot = new Robot();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle rect = new Rectangle(0, 0, d.width, d.height);
			BufferedImage image = robot.createScreenCapture(rect);
			checkPath(path);
			ImageIO.write(image, photoSuffix, new File(path));
		} catch (Exception e) {
			System.out.println("Failed to snatch screen ... ");
			e.printStackTrace();
		}
	}
	
	private static void checkPath(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			if (!file.exists()) {
				file.mkdirs();
			}
		} else {
			file.getParentFile().mkdirs();
		}
	}

	public static void clean(String path) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("文件不存在");
		} else {
			if (!file.isDirectory()) {
				System.out.println("文件夹不存在");
			} else {
				FilesFilter fileFilter = new FilesFilter();
				fileFilter.setIncludingFiles("^.*\\.(jpg|png|bmp|GIF)$");
				File[] files = file.listFiles(fileFilter);
				double length = 0;
				for (File f : files) {
					length += f.length();
					f.delete();
					System.out.println("正在删除: " + f.getName());
				}

				ChoiceFormat fileform = new ChoiceFormat(FILE_LIMITS, FILE_PARTS);
				String unit = fileform.format(length);
				length = length >= GB ? length /= GB
						: (length >= MB ? length /= MB
								: (length >= KB ? length /= KB : length));
				String size = new DecimalFormat("#,##0.0#").format(length);
				System.out.println("刪除完成, 共刪除文件【" + files.length + "】个\t大小为 " + size + unit);
			}
		}
	}
}
