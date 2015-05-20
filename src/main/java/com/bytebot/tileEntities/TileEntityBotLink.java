package com.bytebot.tileEntities;

import java.util.EnumSet;

import com.bytebot.ByteBot;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.common.util.ForgeDirection;

import appeng.api.AEApi;
import appeng.api.networking.GridFlags;
import appeng.api.networking.GridNotification;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridBlock;
import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.util.AECableType;
import appeng.api.util.AEColor;
import appeng.api.util.DimensionalCoord;

/**
 * Copyright (c) 2014-2015, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of GNU GPL v3.0
 * Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class TileEntityBotLink extends TileEntity implements IGridBlock, IGridHost {
	private IGridNode node;

	public void blockBreak() {
		if (node != null)
			node.destroy();
	}

	@Override
	public double getIdlePowerUsage() {
		return 2;
	}

	@Override
	public EnumSet<GridFlags> getFlags() {
		EnumSet<GridFlags> flags = EnumSet.noneOf(GridFlags.class);
		flags.add(GridFlags.REQUIRE_CHANNEL);
		return flags;
	}

	@Override
	public boolean isWorldAccessible() {
		return true;
	}

	@Override
	public DimensionalCoord getLocation() {
		return new DimensionalCoord(this);
	}

	@Override
	public AEColor getGridColor() {
		return AEColor.Transparent;
	}

	@Override
	public void onGridNotification(GridNotification gridNotification) {

	}

	@Override
	public void setNetworkStatus(IGrid iGrid, int i) {

	}

	@Override
	public EnumSet<ForgeDirection> getConnectableSides() {
		return EnumSet.allOf(ForgeDirection.class);
	}

	@Override
	public IGridHost getMachine() {
		return this;
	}

	@Override
	public void gridChanged() {

	}

	@Override
	public ItemStack getMachineRepresentation() {
		return new ItemStack(ByteBot.blockBotlink);
	}

	@Override
	public IGridNode getGridNode(ForgeDirection forgeDirection) {
		if (node == null) {
			node = AEApi.instance().createGridNode(this);
		}
		return node;
	}

	@Override
	public AECableType getCableConnectionType(ForgeDirection forgeDirection) {
		return AECableType.COVERED;
	}

	@Override
	public void securityBreak() {

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if (node != null) {
			node = AEApi.instance().createGridNode(this);
			node.loadFromNBT("node", tag);
			node.updateState();
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if (node != null)
			node.saveToNBT("node", tag);
	}
}
