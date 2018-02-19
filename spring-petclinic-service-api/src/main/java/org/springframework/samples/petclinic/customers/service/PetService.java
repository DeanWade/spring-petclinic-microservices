package org.springframework.samples.petclinic.customers.service;

import java.util.List;

public interface PetService {
	
    List<PetTypeDetails> findPetTypes();

    PetTypeDetails findPetTypeById(int typeId);

    PetDetails findOne(int id);

	void save(PetDetails pet);
}
