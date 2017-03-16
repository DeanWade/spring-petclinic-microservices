package org.springframework.samples.petclinic.vets.service;

import java.io.Serializable;

import lombok.Data;

@Data
public class SpecialtyDetails implements Serializable{

	private static final long serialVersionUID = 4814709305907646213L;

    private int id;

    private String name;

}
