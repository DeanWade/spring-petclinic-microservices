package org.springframework.samples.petclinic.vets.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.samples.petclinic.vets.dao.VetRepository;
import org.springframework.samples.petclinic.vets.model.Specialty;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.stereotype.Service;

@Service("vetService")
public class VetServiceImpl implements VetService{

	private final VetRepository vetRepository;

	public VetServiceImpl(@Lazy VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public List<VetDetails> findAll() {
		return convertVets(vetRepository.findAll());
	}

	private List<VetDetails> convertVets(List<Vet> vets) {
		List<VetDetails> vetDetailsList = new ArrayList<VetDetails>(vets.size());
		for(Vet vet : vets){
			VetDetails vetDetails = convert(vet);
			vetDetailsList.add(vetDetails);
		}
		return vetDetailsList;
	}

	private VetDetails convert(Vet vet) {
		VetDetails vetDetails = new VetDetails();
		vetDetails.setId(vet.getId());
		vetDetails.setFirstName(vet.getFirstName());
		vetDetails.setLastName(vet.getLastName());
		vetDetails.setSpecialties(convertSpecialties(vet.getSpecialties()));
		return vetDetails;
	}

	private List<SpecialtyDetails> convertSpecialties(List<Specialty> specialties) {
		List<SpecialtyDetails> specialtyDetailsList = new ArrayList<SpecialtyDetails>(specialties.size());
		for(Specialty specialty : specialties){
			SpecialtyDetails specialtyDetails = convert(specialty);
			specialtyDetailsList.add(specialtyDetails);
		}
		return specialtyDetailsList;
	}

	private SpecialtyDetails convert(Specialty specialty) {
		SpecialtyDetails specialtyDetails = new SpecialtyDetails();
		specialtyDetails.setId(specialty.getId());
		specialtyDetails.setName(specialty.getName());
		return specialtyDetails;
	}
	
	
}
