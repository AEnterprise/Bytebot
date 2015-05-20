package com.bytebot.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.bytebot.tileEntities.TileEntityBotLink;

import net.minecraft.item.ItemStack;

/**
 * Copyright (c) 2014-2015, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of GNU GPL v3.0
 * Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class AELinks {
	private static HashMap<Integer, List<ItemStack>> lists = new HashMap<Integer, List<ItemStack>>();
	private static HashMap<Integer, TileEntityBotLink> links = new HashMap<Integer, TileEntityBotLink>();
	private static int nextID;

	public static int getID() {
		return nextID++;
	}

	public static int idToSave() {
		return nextID;
	}

	public static void setNextID(int newID) {
		nextID = newID;
	}

	public static void setList(int id, List<ItemStack> list) {
		lists.put(id, list);
	}

	public static List<ItemStack> getList(int id) {
		TileEntityBotLink link = links.get(0);
		if (link == null) {
			return Collections.emptyList();
		}
		link.updateList();
		return lists.get(0);
	}

	public static void link(int id, TileEntityBotLink link) {
		links.put(id, link);
	}

	public static void unlink(int id) {
		links.remove(id);
		lists.remove(id);
	}

}
