package com.bytebot.tileEntities;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.bytebot.ByteBot;
import com.bytebot.utils.AELinks;

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
import appeng.tile.inventory.AppEngInternalInventory;
import appeng.tile.storage.TileDrive;

/**
 * Copyright (c) 2014-2015, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of GNU GPL v3.0
 * Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class TileEntityBotLink extends TileEntity implements IGridBlock, IGridHost {
	public int ID = -2;
	private IGridNode node;

	public void blockBreak() {
		if (node != null)
			node.destroy();
	}

	@Override
	public void updateEntity() {
		return;
	}

	public void updateList() {
		if (ID == -2) {
			ID = AELinks.getID();
			AELinks.link(ID, this);
		}
		if (node != null) {
			node.updateState();
			List<ItemStack> list = new ArrayList<ItemStack>();
			for (IGridNode grindNode : node.getGrid().getNodes()) {
				IGridHost host = grindNode.getGridBlock().getMachine();
				if (host instanceof TileDrive) {
					TileDrive drive = (TileDrive) host;
					for (ItemStack stack : ((AppEngInternalInventory) drive.getInternalInventory())) {
						list.addAll(getStoredStacks(stack));
					}
				}
			}
			AELinks.setList(ID, list);
		}
	}

	private List<ItemStack> getStoredStacks(ItemStack stack) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("ic")) {
			for (int t = 0; t < stack.stackTagCompound.getInteger("ic"); t++) {
				ItemStack loaded = ItemStack.loadItemStackFromNBT(stack.stackTagCompound.getCompoundTag("#" + t));
				if (loaded != null) {
					loaded.stackSize = stack.stackTagCompound.getCompoundTag("#" + t).getInteger("Cnt");
					list.add(loaded);
				}
			}
		}
		return list;
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
			node.updateState();
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
		ID = tag.getInteger("ID");
		AELinks.link(ID, this);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if (node != null)
			node.saveToNBT("node", tag);
		tag.setInteger("ID", ID);
	}
}
