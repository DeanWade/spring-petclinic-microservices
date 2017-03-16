package org.springframework.samples.petclinic.customers.service;

import java.util.List;
import java.util.Optional;

public interface PetService {
	
    List<PetTypeDetails> findPetTypes();

    Optional<PetTypeDetails> findPetTypeById(int typeId);

    PetDetails findOne(int id);

	void save(PetDetails pet);
}
