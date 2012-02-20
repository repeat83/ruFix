package me.repeat.ruFix;

import java.util.Arrays;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;

public class ruFixPlayerListener extends PlayerListener{

	public static ruFix plugin; public ruFixPlayerListener(ruFix instance) {
        plugin = instance;
    }
	
	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
		if (event.isCancelled()) return;
//		String fix = fixFromGame(event.getMessage());
		String fix = fixUseTable(event.getMessage());
		if (!event.getMessage().equals(fix)) {
			if (ruFix.ruFixDebug)
	        	System.out.print("[ruFixDebug]:" + event.getMessage() + ":");
			event.setMessage("/");
			event.setCancelled(true);
			event.getPlayer().chat(fix);
		}
} 
	
	@Override
	public void onPlayerChat(PlayerChatEvent event){
		if (ruFix.ruFixDebug)
        	System.out.print("[ruFixDebug]:" + event.getMessage() + ":");
		event.setMessage(fixUseTable(event.getMessage()));
//		event.setMessage(fixFromGame(event.getMessage()));
	}

/*
	public String newFix(String msg){
    	try {
			return new String(msg.getBytes("ISO-8859-1"),"CP1251");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
*/

	public String fixUseTable(String msg){
		for (int n = 0; n < msg.length(); n++) {
			char t = msg.charAt(n);
//System.out.println("char: " + t);
//System.out.println("int: " + (int)t);
			int idx = Arrays.binarySearch(ruFix.fromGame, t);
//System.out.println("idx: " + idx);
			if (idx > -1){
				msg = msg.replace(t, ruFix.toGame[idx]);
//System.out.println("charf: " + ruFix.toGame[idx]);
			}
		}
		return msg;
	}

/*	
	public String fixFromGame(String original_message){
    	byte[] message = original_message.getBytes();
		String text = "";
		for (int n = 0; n < message.length; n++) {

            int c = (int)message[n] & 0xFF;
            int c1 = 0;
            if (c >= 128 && (int)(n+1) <= (int)(message.length-1)) {
            	c1 = (int)message[n+1] & 0xFF;
            	if (c == 195 && c1 >= 128 && c1 <= 191) { //195 -  первый байт кодировки
            		c = c1+1040-128; //1040-128 // 1040 - начало русского текста, 128 - А
            		text += (char)(c); 
            		n++;
            	} else if (c == 194 && c1 == 184 ) { //194 184 fix 'ё'
            		text += (char)((int)1105);
            		n++;
                } else {
					text += new String(new byte[] {message[n], message[n+1]});
					n++;
            	}
            } else {
            	text += (char)c;
            }
        }
		return text;
	}
*/
}
