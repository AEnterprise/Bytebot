package com.bytebot.irc.commands;


import com.bytebot.irc.Listener;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by AEnterprise
 */
public class CommandHelp implements ICommand {

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public void handle(MessageEvent event, String[] args) {
		event.getChannel().send().message("Possible commands:");
		for (ICommand c: Listener.commands) {
			if (c.canExecute(event.getUser(), event.getChannel()))
				event.getChannel().send().message(c.getName() + ": " + c.getShortDescription());
		}
	}

	@Override
	public String getShortDescription() {
		return "Help command";
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
