package me.repeat.ruFix;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class ruFixHandler {
	public static void handlerChange() throws NoSuchAlgorithmException, IOException {

		Logger globalLogger = Logger.getLogger("Minecraft");
		Handler[] handlers = globalLogger.getHandlers();

		for (Handler handler : handlers)
        {
			if (ruFix.ruFixDebug) {
				globalLogger.info("[ruFixDebug]:"+ handler.toString() + " - ���� ���������: "+handler.getEncoding());
			}
			//globalLogger.removeHandler(handler);
			if (handler instanceof ConsoleHandler) {
				try {
					//console
					if (ruFix.parseConsole) {
						handler.setEncoding(ruFix.ruFixConsole);
						globalLogger.info(ruFix.prefix + " ruFixConsole: " + ruFix.ruFixConsole);
					}
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
					if (ruFix.parseLogFile) {
						handler.setEncoding(ruFix.ruFixLogFile);
						globalLogger.info(ruFix.prefix + " ruFixLogFile: " + ruFix.ruFixLogFile);
					}
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ruFix.ruFixDebug) {
				globalLogger.info("[ruFixDebug]:"+ handler.toString() + " - ����� ���������: " + handler.getEncoding());
			}
		}
	}
}
