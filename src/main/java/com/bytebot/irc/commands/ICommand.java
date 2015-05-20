package com.bytebot.irc.commands;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by AEnterprise
 */
public interface ICommand {

	String getName();

	void handle(MessageEvent event, String[] args);

	String getShortDescription();

	boolean canExecute(User sender, Channel channel);

	int getNumArgs();

}
