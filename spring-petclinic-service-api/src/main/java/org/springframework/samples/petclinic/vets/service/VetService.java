package org.springframework.samples.petclinic.vets.service;

import java.util.List;

public interface VetService {

	List<VetDetails> findAll();
}
