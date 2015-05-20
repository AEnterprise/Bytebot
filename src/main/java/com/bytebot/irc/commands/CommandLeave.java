package com.bytebot.irc.commands;


import com.bytebot.irc.Bot;
import com.bytebot.irc.utils.Utils;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by AEnterprise
 */
public class CommandLeave implements ICommand {
	@Override
	public String getName() {
		return "leave";
	}

	@Override
	public void handle(MessageEvent event, String[] args) {
		event.getChannel().send().part();
		Bot.channelList.remove(event.getChannel());
	}

	@Override
	public String getShortDescription() {
		return "Leave this channel";
	}

	@Override
	public boolean canExecute(User sender, Channel channel) {
		return Utils.isAdmin(sender.getNick()) || channel.isOp(sender);
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}
