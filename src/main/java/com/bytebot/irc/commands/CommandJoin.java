package com.bytebot.irc.commands;


import com.bytebot.irc.Bot;
import com.bytebot.irc.utils.Utils;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by AEnterprise
 */
public class CommandJoin implements ICommand {

	@Override
	public String getName() {
		return "join";
	}

	@Override
	public void handle(MessageEvent event, String[] args) {
		Bot.bot.sendIRC().joinChannel(args[0]);
		Bot.channelList.add(args[0]);
	}

	@Override
	public String getShortDescription() {
		return "Join a channel";
	}

	@Override
	public boolean canExecute(User sender, Channel channel) {
		return Utils.isAdmin(sender.getNick());
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
