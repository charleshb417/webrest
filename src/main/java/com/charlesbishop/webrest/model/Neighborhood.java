package com.charlesbishop.webrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Neighborhood")
public class Neighborhood extends ObjModel {
	@Id
	@Column(name="neighborhood")
	private String neighborhood;

	@Column(name="district")
	private String district;
	
	@Column(name="numCrimes")
	private int numCrimes;
	
	@Column(name="numVacants")
	private int numVacants;

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	public int getNumCrimes() {
		return numCrimes;
	}

	public void setNumCrimes(int numCrimes) {
		this.numCrimes = numCrimes;
	}

	public int getNumVacants() {
		return numVacants;
	}

	public void setNumVacants(int numVacants) {
		this.numVacants = numVacants;
	}
	
	@Override
	public String toString() {
		return "{\"neighborhood\": \"" + neighborhood + "\","
				+"\"district\": \"" + district + "\","
				+"\"numCrimes\": " + numCrimes + ","
				+"\"numVacants\": " + numVacants + "}";
	}
}
