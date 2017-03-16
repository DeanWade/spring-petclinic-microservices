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
package org.springframework.samples.petclinic.customers.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;

/**
 * @author Maciej Szarlinski
 */
@Data
public class OwnerDetails implements Serializable{

	private static final long serialVersionUID = 8863223762467223672L;

	private final int id;

    private final String firstName;

    private final String lastName;

    private final String address;

    private final String city;

    private final String telephone;

    private final List<PetDetails> pets = new ArrayList<>();

    @JsonIgnore
    public List<Integer> getPetIds() {
        return pets.stream()
            .map(PetDetails::getId)
            .collect(toList());
    }

	public void addPet(PetDetails pet) {
		this.pets.add(pet);
	}
	
	public void addPets(List<PetDetails> pets) {
		this.pets.addAll(pets);
	}
}
