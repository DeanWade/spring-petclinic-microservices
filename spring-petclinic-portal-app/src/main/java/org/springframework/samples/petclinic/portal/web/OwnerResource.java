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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.customers.service.OwnerDetails;
import org.springframework.samples.petclinic.customers.service.OwnerService;
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
 * @author Michael Isvy
 * @author Maciej Szarlinski
 */
@RestController
@RequestMapping("/api/customer/owners")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
class OwnerResource {

	private final OwnerService ownerService;
    
    /**
     * Create Owner
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOwner(@Valid @RequestBody OwnerDetails owner) {
    	ownerService.save(owner);
    }

    /**
     * Read single Owner
     */
    @GetMapping(value = "/{ownerId}")
    public OwnerDetails findOwner(@PathVariable("ownerId") int ownerId) {
        return ownerService.findOne(ownerId);
    }

    /**
     * Read List of Owners
     */
    @GetMapping
    public List<OwnerDetails> findAll() {
        return ownerService.findAll();
    }

    /**
     * Update Owner
     */
    @PutMapping(value = "/{ownerId}")
    public OwnerDetails updateOwner(@PathVariable("ownerId") int ownerId, @Valid @RequestBody OwnerDetails ownerRequest) {
//        final OwnerDetails ownerModel = ownerService.findOne(ownerId);
//        // This is done by hand for simplicity purpose. In a real life use-case we should consider using MapStruct.
//        ownerModel.setFirstName(ownerRequest.getFirstName());
//        ownerModel.setLastName(ownerRequest.getLastName());
//        ownerModel.setCity(ownerRequest.getCity());
//        ownerModel.setAddress(ownerRequest.getAddress());
//        ownerModel.setTelephone(ownerRequest.getTelephone());
//        log.info("Saving owner {}", ownerModel);
        return ownerService.save(ownerRequest);
    }
    
   
}
