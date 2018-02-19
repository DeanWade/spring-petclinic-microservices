package org.springframework.samples.petclinic.customers.service.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.samples.petclinic.customers.dao.PetRepository;
import org.springframework.samples.petclinic.customers.model.Pet;
import org.springframework.samples.petclinic.customers.service.PetDetails;
import org.springframework.samples.petclinic.customers.service.PetService;
import org.springframework.samples.petclinic.customers.service.PetTypeDetails;
import org.springframework.samples.petclinic.customers.utils.Convertor;
import org.springframework.stereotype.Service;

//import static org.springframework.samples.petclinic.customers.utils.Convertor.*;

@Service("petService")
public class PetServiceImpl implements PetService{
	
	private final PetRepository petRepository;
	
	public PetServiceImpl(@Lazy PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public List<PetTypeDetails> findPetTypes() {
		return Convertor.convertPetTypes(petRepository.findPetTypes());
	}

	@Override
	public PetTypeDetails findPetTypeById(int typeId) {
		return Convertor.convert(petRepository.findPetTypeById(typeId)).get();
	}

	@Override
	public PetDetails findOne(int id) {
		return Convertor.convert(petRepository.findOne(id));
	}

	@Override
	public void save(PetDetails petDetails) {
		Pet pet = Convertor.convert(petDetails);
		petRepository.save(pet);
	}

}
