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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.customers.service.OwnerDetails;
import org.springframework.samples.petclinic.customers.service.OwnerService;
import org.springframework.samples.petclinic.customers.service.PetDetails;
import org.springframework.samples.petclinic.customers.service.PetRequest;
import org.springframework.samples.petclinic.customers.service.PetService;
import org.springframework.samples.petclinic.customers.service.PetTypeDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Maciej Szarlinski
 */
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
class PetResource {

    private final PetService petService;

    private final OwnerService ownerService;

    @GetMapping("/petTypes")
    public List<PetTypeDetails> getPetTypes() {
        return petService.findPetTypes();
    }

    @PostMapping("/owners/{ownerId}/pets")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void processCreationForm(
        @RequestBody PetRequest petRequest,
        @PathVariable("ownerId") int ownerId) {

        final PetDetails pet = new PetDetails();
        final OwnerDetails owner = ownerService.findOne(ownerId);
        owner.addPet(pet);

        save(pet, petRequest);
    }

    @PutMapping("/owners/*/pets/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void processUpdateForm(@RequestBody PetRequest petRequest) {
    	final PetDetails pet = petService.findOne(petRequest.getId());
        save(pet, petRequest);
    }

    @GetMapping("/owners/*/pets/{petId}")
    public PetDetails findPet(@PathVariable("petId") int petId) {
        return petService.findOne(petId);
    }
    
    private void save(final PetDetails pet, final PetRequest petRequest) {
    	pet.setName(petRequest.getName());
    	pet.setBirthDate(petRequest.getBirthDate());
    	petService.findPetTypeById(petRequest.getTypeId()).ifPresent(pet::setType);
    	log.info("Saving pet {}", pet);
    	petService.save(pet);
    }

}
