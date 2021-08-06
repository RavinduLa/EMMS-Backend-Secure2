package com.emms.dal.adapter;

import java.util.List;

import com.emms.inventoryModel.CondemnedEquipment;
import com.emms.inventoryModel.PendingCondemnEquipment;

public interface EquipmentCondemnDataAdapter {
	
	public PendingCondemnEquipment savePendingCondemnEquipment(PendingCondemnEquipment pendingCondemnEquipment);
	public long deletePendingCondemnEquipment(long id);
	public CondemnedEquipment saveCondemnedEquipment(CondemnedEquipment condemnedEquipment);
	public List<PendingCondemnEquipment> getAllPendingCondemnEquipment();
	public List<CondemnedEquipment> getAllCondemnedEquipment();

}
