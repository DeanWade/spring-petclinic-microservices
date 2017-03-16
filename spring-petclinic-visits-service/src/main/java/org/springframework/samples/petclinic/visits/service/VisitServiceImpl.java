package org.springframework.samples.petclinic.visits.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.samples.petclinic.visits.model.Visit;
import org.springframework.samples.petclinic.visits.model.VisitRepository;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("visitService")
@Slf4j
public class VisitServiceImpl implements VisitService{

	
	private final VisitRepository visitRepository;
	
	public VisitServiceImpl(@Lazy VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public void save(VisitDetails visitDetails) {
		Visit visit = convert(visitDetails);
		log.info("Saving visit {}", visit);
		visitRepository.save(visit);
	}

	@Override
	public List<VisitDetails> findByPetId(int petId) {
		log.info("findByPetId {}", petId);
		return convert(visitRepository.findByPetId(petId));
	}
	
	private List<VisitDetails> convert(List<Visit> visits) {
		List<VisitDetails> visitDetailsList  = new ArrayList<VisitDetails>(visits.size());
		for(Visit visit: visits){
			VisitDetails visitDetails = convert(visit);
			visitDetailsList.add(visitDetails);
		}
		return visitDetailsList;
	}

	private Visit convert(VisitDetails visitDetails) {
		Visit visit = new Visit();
		visit.setId(visitDetails.getId());
		visit.setPetId(visitDetails.getPetId());
		visit.setDate(visitDetails.getDate());
		visit.setDescription(visitDetails.getDescription());
		return visit;
	}
	
	private VisitDetails convert(Visit visit) {
		VisitDetails visitDetails = new VisitDetails();
		visitDetails.setId(visit.getId());
		visitDetails.setPetId(visit.getPetId());
		visitDetails.setDate(visit.getDate());
		visitDetails.setDescription(visit.getDescription());
		return visitDetails;
	}

}
