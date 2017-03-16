package org.springframework.samples.petclinic.customers.service.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.samples.petclinic.customers.dao.OwnerRepository;
import org.springframework.samples.petclinic.customers.model.Owner;
import org.springframework.samples.petclinic.customers.service.OwnerDetails;
import org.springframework.samples.petclinic.customers.service.OwnerService;
import org.springframework.samples.petclinic.customers.utils.Convertor;
import org.springframework.stereotype.Service;

@Service("ownerService")
public class OwnerServiceImpl implements OwnerService{

	final OwnerRepository ownerRepository;
	
	public OwnerServiceImpl(@Lazy OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public OwnerDetails findOne(int ownerId) {
		Owner owner = ownerRepository.findOne(ownerId);
		return Convertor.convertOwner(owner);
	}

	@Override
	public OwnerDetails save(OwnerDetails ownerDetails) {
		Owner owner = Convertor.convertOwnerDetails(ownerDetails);
		owner = ownerRepository.save(owner);
		return Convertor.convertOwner(owner);
	}

	@Override
	public List<OwnerDetails> findAll() {
		List<Owner> owners = ownerRepository.findAll();
		return Convertor.convertOwnerList(owners);
	}

}
