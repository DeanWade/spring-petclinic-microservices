package org.springframework.samples.petclinic.visits.service;

import java.util.List;


public interface VisitService {

	void save(VisitDetails visit);

	List<VisitDetails> findByPetId(int petId);

}
