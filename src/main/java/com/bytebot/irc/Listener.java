package com.bytebot.irc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bytebot.irc.commands.CommandAE;
import com.bytebot.irc.commands.CommandBasicReply;
import com.bytebot.irc.commands.CommandHello;
import com.bytebot.irc.commands.CommandHelp;
import com.bytebot.irc.commands.CommandJoin;
import com.bytebot.irc.commands.CommandLeave;
import com.bytebot.irc.commands.CommandStop;
import com.bytebot.irc.commands.ICommand;
import com.google.common.base.Strings;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by AEnterprise
 */
public class Listener extends ListenerAdapter {
	public static List<ICommand> commands = new ArrayList<ICommand>();

	public void init() {
		//add commands
		commands.add(new CommandHello());
		commands.add(new CommandHelp());
		commands.add(new CommandStop());
		commands.add(new CommandBasicReply("reply", "replying"));
		commands.add(new CommandJoin());
		commands.add(new CommandLeave());
		commands.add(new CommandAE());
	}

	@Override
	public void onMessage(MessageEvent event) throws Exception {
		String message = event.getMessage();
		if (!message.startsWith("!"))
			return;
		message = message.substring(1);
		for (ICommand command: commands) {
			if (message.startsWith(command.getName())) {
				if (command.canExecute(event.getUser(), event.getChannel())) {
					message = message.substring(command.getName().length());
					String[] temp = message.split(" ");
					String[] args = Arrays.asList(temp).subList(1, temp.length).toArray(new String[]{});
					if (args.length == command.getNumArgs()) {
						command.handle(event, args);
					} else {
						event.getChannel().send().message(String.format("Invalid amound of args, expected %s, received %s", command.getNumArgs(), args.length));
					}
				} else {
					event.respond("YOU ARE NOT AUTHORIZED TO DO THAT!");
				}
			}
		}
	}

	@Override
	public void onConnect(ConnectEvent event) throws Exception {
		if (!Strings.isNullOrEmpty(Bot.nickPass))
			Bot.bot.sendIRC().identify(Bot.nickPass);
		for (String channel : Bot.channelList.getChannels()) {
			Bot.bot.sendIRC().joinChannel(channel);
		}
	}
}
