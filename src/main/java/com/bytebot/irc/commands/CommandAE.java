package com.bytebot.irc.commands;

import com.bytebot.utils.AELinks;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * Copyright (c) 2014-2015, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of GNU GPL v3.0
 * Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class CommandAE implements ICommand {
	@Override
	public String getName() {
		return "AE";
	}

	@Override
	public void handle(MessageEvent event, String[] args) {
		if (args[0].equals("list")) {
			for (ItemStack stack : AELinks.getList(Integer.valueOf(args[1]))) {
				event.getChannel().send().message(stack.stackSize + "x " + StatCollector.translateToLocal(stack.getUnlocalizedName() + ".name"));
			}
		}
	}

	@Override
	public String getShortDescription() {
		return "Base AE command";
	}

	@Override
	public boolean canExecute(User sender, Channel channel) {
		return true;
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
