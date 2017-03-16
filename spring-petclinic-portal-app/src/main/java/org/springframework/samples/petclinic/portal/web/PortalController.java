/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.portal.web;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.customers.service.OwnerDetails;
import org.springframework.samples.petclinic.customers.service.OwnerService;
import org.springframework.samples.petclinic.visits.service.VisitDetails;
import org.springframework.samples.petclinic.visits.service.VisitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author Maciej Szarlinski
 */
@RestController
@RequestMapping("/api/gateway")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PortalController {

    
    private final OwnerService ownerService;
    
    private final VisitService visitService;

    @GetMapping(value = "/owners/{ownerId}")
    public OwnerDetails getOwnerDetails(final @PathVariable int ownerId) {
        final OwnerDetails owner = ownerService.findOne(ownerId);
        supplyVisits(owner);
        return owner;
    }

//    private void supplyVisits(final OwnerDetails owner, final Map<Integer, List<VisitDetails>> visitsMapping) {
//        owner.getPets().forEach(pet ->
//            pet.getVisits().addAll(Optional.ofNullable(visitsMapping.get(pet.getId())).orElse(emptyList())));
//    }
//
//    private Map<Integer, List<VisitDetails>> getVisitsForPets(final List<Integer> petIds, final int ownerId) {
//        return petIds.parallelStream()
//            .flatMap(petId -> visitService.findByPetId(petId).stream())
//            .collect(groupingBy(VisitDetails::getPetId));
//    }
    
    private void supplyVisits(final OwnerDetails owner){
    	Map<Integer, List<VisitDetails>> visitsMapping = owner.getPetIds().parallelStream().flatMap(petId -> visitService.findByPetId(petId).stream())
    			.collect(groupingBy(VisitDetails::getPetId));
    	
    	owner.getPets().forEach(pet -> pet.getVisits().addAll(Optional.ofNullable(visitsMapping.get(pet.getId())).orElse(emptyList())));
    }

}
