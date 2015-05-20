package com.bytebot.irc.commands;


import com.bytebot.irc.Bot;
import com.bytebot.irc.utils.Utils;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by AEnterprise
 */
public class CommandStop implements ICommand {


	@Override
	public String getName() {
		return "stop";
	}

	@Override
	public void handle(MessageEvent event, String[] args) {
		event.getChannel().send().message("ByteBot going offline!");
		Bot.terminate();
	}

	@Override
	public String getShortDescription() {
		return "Please don't terminate me!";
	}

	@Override
	public boolean canExecute(User sender, Channel channel) {
		return Utils.isAdmin(sender.getNick());
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}
