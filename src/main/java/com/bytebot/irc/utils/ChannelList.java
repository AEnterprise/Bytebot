package com.bytebot.irc.utils;

import java.util.ArrayList;
import java.util.List;

import com.bytebot.irc.Bot;
import com.bytebot.irc.data.IDataHolder;
import com.google.common.collect.ImmutableList;

import org.pircbotx.Channel;

/**
 * Created by AEnterprise
 */
public class ChannelList implements IDataHolder {
	private List<String> channels = new ArrayList<String>();


	public void add(String channel) {
		if (!channels.contains(channel)) {
			channels.add(channel);
		}
		Bot.dataManager.save(getName());
	}

	public void remove(Channel channel) {
		channels.remove(channel.getName());
		channel.send().part();
		Bot.dataManager.save(getName());
	}

	public List<String> getChannels() {
		return ImmutableList.copyOf(channels);
	}

	@Override
	public void setDefaults() {
		channels.add("#acetest");
	}

	@Override
	public String getName() {
		return "channels";
	}
}
