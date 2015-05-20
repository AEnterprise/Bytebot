package com.bytebot;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "bytebot", version = "@MODVERSION@")
public class ByteBot {
	@Mod.Instance("bytebot")
	public static ByteBot INSTANCE;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}
