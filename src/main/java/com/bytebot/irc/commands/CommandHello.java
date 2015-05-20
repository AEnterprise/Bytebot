package com.bytebot.irc.commands;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by AEnterprise
 */
public class CommandHello implements ICommand {

	@Override
	public String getName() {
		return "hello";
	}

	@Override
	public void handle(MessageEvent event, String[] args) {
		event.getChannel().send().message("Hello " + event.getUser().getNick());
	}

	@Override
	public String getShortDescription() {
		return "Say hello";
	}

	@Override
	public boolean canExecute(User sender, Channel channel) {
		return true;
	}

	@Override
	public int getNumArgs() {
		return 0;
	}
}
