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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.samples.petclinic.visits.service.VisitDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author mszarlinski@bravurasolutions.com on 2016-12-05.
 */
@Data
public class PetDetails implements Serializable{

	private static final long serialVersionUID = -2833253330149114940L;

	private int id;

    private String name;

    private String owner;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private PetTypeDetails type;
    
    private final List<VisitDetails> visits = new ArrayList<>();

    public PetDetails(){
    	
    }
}
