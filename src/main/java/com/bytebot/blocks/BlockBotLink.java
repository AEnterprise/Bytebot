package com.bytebot.blocks;

import com.bytebot.tileEntities.TileEntityBotLink;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Copyright (c) 2014-2015, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of GNU GPL v3.0
 * Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class BlockBotLink extends BlockContainer {

	public BlockBotLink() {
		super(Material.iron);
		setBlockName("blockBotlink");
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		world.markBlockForUpdate(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBotLink();
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		super.onBlockPreDestroy(world, x, y, z, meta);
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity instanceof TileEntityBotLink) {
			((TileEntityBotLink) entity).blockBreak();
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity instanceof TileEntityBotLink) {
			((TileEntityBotLink) entity).printNetwork();
		}
		return true;
	}
}
