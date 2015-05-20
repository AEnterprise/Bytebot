package com.bytebot.irc;

import com.bytebot.irc.data.DataManager;
import com.bytebot.irc.utils.ChannelList;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 * Created by AEnterprise
 */
public class Bot {
	public static String nickPass;

	public static Listener listener = new Listener();
	public static DataManager dataManager = new DataManager();
	public static PircBotX bot;
	public static ChannelList channelList;

	public static void terminate() {
		dataManager.saveAll();
		bot.stopBotReconnect();
		bot.sendIRC().quitServer();
	}

	public static void start() {
		new ByteBot().start();
	}

	public static class ByteBot extends Thread {

		@Override
		public void run() {
			listener.init();
			channelList = dataManager.getData(ChannelList.class);
			Configuration<PircBotX> configuration = new Configuration.Builder().setName("ByteBot").setServerHostname("irc.esper.net").addListener(listener).setMessageDelay(300L).buildConfiguration();
			bot = new PircBotX(configuration);
			try {
				bot.startBot();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}
}
