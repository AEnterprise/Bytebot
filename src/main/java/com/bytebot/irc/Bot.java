package com.bytebot.irc;

import com.bytebot.data.DataManager;
import com.bytebot.utils.ChannelList;

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


	public static void main (String[] args) throws Exception {
		if (args.length == 1) {
			nickPass = args[0];
		}
		listener.init();
		channelList = dataManager.getData(ChannelList.class);
		Configuration<PircBotX> configuration = new Configuration.Builder().setName("ByteBot").setServerHostname("irc.esper.net").addListener(listener).buildConfiguration();
		bot = new PircBotX(configuration);
		bot.startBot();
	}

	public static void terminate() {
		dataManager.saveAll();
		bot.stopBotReconnect();
		bot.sendIRC().quitServer();
	}
}