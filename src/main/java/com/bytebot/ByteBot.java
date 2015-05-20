package com.bytebot;

import com.bytebot.blocks.BlockBotLink;
import com.bytebot.irc.Bot;
import com.bytebot.tileEntities.TileEntityBotLink;

import net.minecraft.block.Block;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "bytebot", version = "@MODVERSION@")
public class ByteBot {
	@Mod.Instance("bytebot")
	public static ByteBot INSTANCE;


	public static Block blockBotlink = new BlockBotLink();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerBlock(blockBotlink, "blockBotlink");
		GameRegistry.registerTileEntity(TileEntityBotLink.class, "botlink");

	}
    
    @EventHandler
	public void init(FMLInitializationEvent event) {
		Bot.start();
	}
}
