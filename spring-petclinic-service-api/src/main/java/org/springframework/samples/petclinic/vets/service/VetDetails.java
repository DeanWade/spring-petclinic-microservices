package org.springframework.samples.petclinic.vets.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class VetDetails implements Serializable{

	private static final long serialVersionUID = 6644389480014739679L;
	
    private Integer id;

    private String firstName;

    private String lastName;
    
    private List<SpecialtyDetails> specialties = new ArrayList<SpecialtyDetails>();

}
