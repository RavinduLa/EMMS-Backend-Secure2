package com.emms.inventoryDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.CondemnedEquipment;

public interface CondemnedEquipmentRepository extends JpaRepository<CondemnedEquipment, Long> {
	
	CondemnedEquipment findByAssetId(long assetId);

}
