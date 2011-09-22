package me.repeat.ruFix;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ruFixPlayerListener extends PlayerListener{

	public static ruFix plugin; public ruFixPlayerListener(ruFix instance) {
        plugin = instance;
    }
	
	public void onPlayerChat(PlayerChatEvent event){

    	byte[] message = event.getMessage().getBytes();
		String text = "";
        for (int n = 0; n < message.length; n++) {

            int c = (int)message[n] & 0xFF;
            int c1 = 0;
            if (c > 128 && (int)(n+1) <= (int)(message.length-1)) {
            	c1 = (int)message[n+1] & 0xFF;
//				System.out.print(c);
            	if (c == 195 && c1 >= 128 && c1 <= 191) { //195 -  первый байт кодировки
//					System.out.print(c1+"!");
            		c = c1+1040-128; //1040-128 // 1040 - начало русского текста, 128 - А
//					System.out.print(c+"@");
            		text += (char)(c); 
            		n++;
            	} else if (c == 194 && c1 == 184 ) { //194 184 fix 'ё'
            		text += (char)((int)1105);
            		n++;
                } else {
            		text += (char)c;
            	}
            } else {
            	text += (char)c;
            }
        }
        if (System.getProperty("ruFixDebug") != null)
        	System.out.print("[ruFixDebug]:" + event.getMessage() + ":");
        event.setMessage(text);
	}
}
