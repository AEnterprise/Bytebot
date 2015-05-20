package com.bytebot.tileEntities;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.common.util.ForgeDirection;

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
	@Override
	public double getIdlePowerUsage() {
		return 0;
	}

	@Override
	public EnumSet<GridFlags> getFlags() {
		EnumSet<GridFlags> flags = EnumSet.noneOf(GridFlags.class);
		flags.add(GridFlags.REQUIRE_CHANNEL);
		return flags;
	}

	@Override
	public boolean isWorldAccessible() {
		return false;
	}

	@Override
	public DimensionalCoord getLocation() {
		return null;
	}

	@Override
	public AEColor getGridColor() {
		return null;
	}

	@Override
	public void onGridNotification(GridNotification gridNotification) {

	}

	@Override
	public void setNetworkStatus(IGrid iGrid, int i) {

	}

	@Override
	public EnumSet<ForgeDirection> getConnectableSides() {
		return null;
	}

	@Override
	public IGridHost getMachine() {
		return null;
	}

	@Override
	public void gridChanged() {

	}

	@Override
	public ItemStack getMachineRepresentation() {
		return null;
	}

	@Override
	public IGridNode getGridNode(ForgeDirection forgeDirection) {
		return null;
	}

	@Override
	public AECableType getCableConnectionType(ForgeDirection forgeDirection) {
		return null;
	}

	@Override
	public void securityBreak() {

	}
}
