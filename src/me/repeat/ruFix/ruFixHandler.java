package me.repeat.ruFix;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class ruFixHandler {
	public static void handlerChange() throws NoSuchAlgorithmException, IOException {

		Logger globalLogger = Logger.getLogger("Minecraft");
		Handler[] handlers = globalLogger.getHandlers();

		String ruFixStreamHandler = System.getProperty("ruFixConsole");
		if (ruFixStreamHandler == null)
			ruFixStreamHandler = "UTF-8";

		String ruFixFileHandler = System.getProperty("ruFixLogFile");
		if (ruFixFileHandler == null)
			ruFixFileHandler = "UTF-8";

		for (Handler handler : handlers)
        {
//			globalLogger.info("!!!: "+handler.getEncoding());
			//globalLogger.removeHandler(handler);
			if (handler instanceof StreamHandler) {
				try {
					//console
					handler.setEncoding(ruFixStreamHandler);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (handler instanceof FileHandler) {
				try {
					// logfile
					handler.setEncoding(ruFixFileHandler);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			globalLogger.info("@@@: "+handler.getEncoding());
		}
		globalLogger.info(ruFix.prefix + " ruFixConsole: " + ruFixStreamHandler);
		globalLogger.info(ruFix.prefix + " ruFixLogFile: " + ruFixFileHandler);
	}
}
