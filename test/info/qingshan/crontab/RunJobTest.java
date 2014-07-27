package info.qingshan.crontab;

import info.qingshan.crontab.common.ClassPathUtil;
import info.qingshan.crontab.core.shell.Shell;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RunJobTest {
	private static final Logger log = LoggerFactory.getLogger(RunJobTest.class);

	@Test
	public void test() throws IOException, InterruptedException {
		String json = ClassPathUtil.getString("ini.json");
		log.info("读取配置: \n{}", json);
		JSONObject config = JSON.parseObject(json);
		JSONArray shell = config.getJSONArray("shell");
		for (int i = 0; i < shell.size(); i++) {
			JSONObject string = shell.getJSONObject(i);
			Map<String, ?> map = (Map<String, ?>) string.clone();
			String type = (String) map.get("type");
			if (type.equals("cmd_args")) {
				JSONArray cmd =  (JSONArray) map.get("cmd");
				Shell.execute(cmd.toArray(new String[cmd.size()]));
			}
		}
	}

}
