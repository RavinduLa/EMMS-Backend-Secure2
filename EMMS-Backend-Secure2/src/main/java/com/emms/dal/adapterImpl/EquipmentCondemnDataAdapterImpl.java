package com.emms.dal.adapterImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emms.dal.adapter.EquipmentCondemnDataAdapter;
import com.emms.inventoryDao.CondemnedEquipmentRepository;
import com.emms.inventoryDao.PendingCondemnEquipmentRepo;
import com.emms.inventoryModel.CondemnedEquipment;
import com.emms.inventoryModel.PendingCondemnEquipment;

@Component
public class EquipmentCondemnDataAdapterImpl implements EquipmentCondemnDataAdapter {
	
	private CondemnedEquipmentRepository condemnedEquipmentRepository;
	private PendingCondemnEquipmentRepo pendingCondemnEquipmentRepo;
	
	@Autowired
	public EquipmentCondemnDataAdapterImpl(CondemnedEquipmentRepository condemnedEquipmentRepository,
			PendingCondemnEquipmentRepo pendingCondemnEquipmentRepo) {
		this.condemnedEquipmentRepository = condemnedEquipmentRepository;
		this.pendingCondemnEquipmentRepo = pendingCondemnEquipmentRepo;
	}

	@Override
	public PendingCondemnEquipment savePendingCondemnEquipment(PendingCondemnEquipment pendingCondemnEquipment) {
		 return pendingCondemnEquipmentRepo.save(pendingCondemnEquipment);
	}

	@Override
	public long deletePendingCondemnEquipment(long id) {
		pendingCondemnEquipmentRepo.deleteById(id);
		System.out.println("deleted pending condemn request with id : " + id);
		return id;
	}

	@Override
	public CondemnedEquipment saveCondemnedEquipment(CondemnedEquipment condemnedEquipment) {
		System.out.println("Saving condemned equipment with id : " + condemnedEquipment.getAssetId());
		return condemnedEquipmentRepository.save(condemnedEquipment);
	}

	@Override
	public List<PendingCondemnEquipment> getAllPendingCondemnEquipment() {
		return pendingCondemnEquipmentRepo.findAll();
	}

	@Override
	public List<CondemnedEquipment> getAllCondemnedEquipment() {
		return  condemnedEquipmentRepository.findAll();
	}

}
