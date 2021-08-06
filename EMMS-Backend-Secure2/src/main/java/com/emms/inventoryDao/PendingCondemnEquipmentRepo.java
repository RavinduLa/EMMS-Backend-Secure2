package com.emms.inventoryDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.PendingCondemnEquipment;

public interface PendingCondemnEquipmentRepo extends JpaRepository<PendingCondemnEquipment, Long> {
	
	PendingCondemnEquipment findByAssetId(long assetId);

}
