package org.springframework.samples.petclinic.customers.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.customers.model.Owner;
import org.springframework.samples.petclinic.customers.model.Pet;
import org.springframework.samples.petclinic.customers.model.PetType;
import org.springframework.samples.petclinic.customers.service.OwnerDetails;
import org.springframework.samples.petclinic.customers.service.PetDetails;
import org.springframework.samples.petclinic.customers.service.PetTypeDetails;

public class Convertor {

	
	public static Optional<PetTypeDetails> convert(Optional<PetType> petType) {
		PetTypeDetails petTypeDetails = new PetTypeDetails();
		petTypeDetails.setId(petType.get().getId());
		petTypeDetails.setName(petType.get().getName());
		return Optional.of(petTypeDetails);
	}


	public static PetDetails convert(Pet pet) {
		PetDetails petDetails = new PetDetails();
		petDetails.setId(pet.getId());
		petDetails.setName(pet.getName());
		petDetails.setOwner(pet.getOwner().getFirstName() + " " + pet.getOwner().getLastName());
		petDetails.setBirthDate(pet.getBirthDate());
		
		PetTypeDetails petTypeDetails = new PetTypeDetails();
		petTypeDetails.setId(pet.getType().getId());
		petTypeDetails.setName(pet.getType().getName());
		petDetails.setType(petTypeDetails);
		return petDetails;
	}

	
	public static List<PetTypeDetails> convertPetTypes(List<PetType> petTypes) {
		List<PetTypeDetails> petTypeDetailsList = new ArrayList<PetTypeDetails>(petTypes.size());
		for(PetType petType : petTypes){
			PetTypeDetails petTypeDetails = new PetTypeDetails();
			petTypeDetails.setId(petType.getId());
			petTypeDetails.setName(petType.getName());
			petTypeDetailsList.add(petTypeDetails);
		}
		return petTypeDetailsList;
	}

	public static Pet convert(PetDetails petDetails) {
		Pet pet = new Pet();
		pet.setId(petDetails.getId());
		pet.setName(petDetails.getName());
//		pet.setOwner();
		pet.setBirthDate(pet.getBirthDate());
		
		PetType petType = new PetType();
		petType.setId(petDetails.getType().getId());
		petType.setName(petDetails.getType().getName());
		pet.setType(petType);
		return pet;
	}
	
	
	public static OwnerDetails convertOwner(Owner owner){
		OwnerDetails ownerDetails = new OwnerDetails(owner.getId(), owner.getFirstName(), owner.getLastName(), owner.getAddress(), owner.getCity(), owner.getTelephone());
		
		List<PetDetails> pets= convertPetDetailsList(owner.getPets());
		ownerDetails.addPets(pets);
		return ownerDetails;
	}
	
	private static List<PetDetails> convertPetDetailsList(List<Pet> pets) {
		List<PetDetails> petDetailsList = new ArrayList<PetDetails>(pets.size());
		for(Pet pet: pets){
			PetDetails petDetails = convert(pet);
			petDetailsList.add(petDetails);
		}
		return petDetailsList;
	}


	public static List<OwnerDetails> convertOwnerList(List<Owner> owners) {
		List<OwnerDetails> ownerDetailsList= new ArrayList<OwnerDetails>(owners.size());
		for(Owner owner: owners){
			OwnerDetails ownerDetails = convertOwner(owner);
			ownerDetailsList.add(ownerDetails);
		}
		return ownerDetailsList;
	}
	
	public static Owner convertOwnerDetails(OwnerDetails ownerDetails){
		Owner owner = new Owner(ownerDetails.getId(), ownerDetails.getFirstName(), ownerDetails.getLastName(), ownerDetails.getAddress(), ownerDetails.getCity(), ownerDetails.getTelephone());
		return owner;
	}
}
