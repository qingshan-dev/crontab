#crontab

	* 支持cron表达(quartz)
	* 可直接调用sh/bat或shell/cmd, 并支持管道流以及命令中的重定向到文件
	* 配置方便(虽然windows有计划任务, linux有crontab, 但都属于系统, 加了还要删除, 这个就没有那么麻烦了), 跨平台使用
	* 带有定时截图功能(全屏), 支持多种后缀(PNG/BMP/JPG), 建议使用png后缀


##1.0使用
	* 支持cron表达(quartz)
	* 定时截图

##2.0使用
	* + shell/cmd参数
	* + 支持管道流以及命令中的重定向到文件
	* - 少许配置文件
###下载(win32 exe)
http://git.oschina.net/topin/crontab/tree/master/release/2.0

###配置:
* 示例(etc/ini.json):

    ```json
    {
        "shell": [
            {
                "type": "cmd",
                "cmd": "B:/project/crontab/test/dir.bat", 
                "cron": "0 21 0 * * ?"
            },
            {
            	"type": "cmd_args",
                "cmd": ["cmd", "/c", "dir >", "result.txt"], 
                "cron": "0 21 0 * * ?"
            }
        ], 
        "capture": [
    	    {
    	        "dir": "D:/tmp/", 
    	        "photoSuffix":"jpg",
    	        "cron": "0 0/2 * * * ?"
    	    },
    	    {
    	        "dir": "D:/tmp/", 
    	        "photoSuffix":"png",
    	        "cron": "0 0/2 * * * ?"
    	    }
        ]
    }
    ```
* 配置文件说明(http://git.oschina.net/topin/crontab/raw/master/res/crontab_api.html)
![API](http://git.oschina.net/topin/crontab/raw/master/res/crontab_api.png)
