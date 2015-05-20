package com.bytebot.irc.commands;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by AEnterprise
 */
public class CommandBasicReply implements ICommand {
	private final String command, reply;

	public CommandBasicReply(String command, String reply){
		this.command = command;
		this.reply = reply;
	}

	@Override
	public String getName() {
		return command;
	}

	@Override
	public void handle(MessageEvent event, String[] args) {
		event.getChannel().send().message(reply);
	}

	@Override
	public String getShortDescription() {
		return "Basic reply (needs to be command dependant later)";
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
