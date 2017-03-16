package org.springframework.samples.petclinic.customers.service;

import java.util.List;

public interface OwnerService {

	OwnerDetails findOne(int ownerId);

	OwnerDetails save(OwnerDetails owner);

	List<OwnerDetails> findAll();
}
