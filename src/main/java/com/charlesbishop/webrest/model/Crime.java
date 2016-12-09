package com.charlesbishop.webrest.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Crime")
public class Crime {
	@Id
	@Column(name="CrimeID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int crimeID;
	private Date crimeDate;
	private String crimeCode;
	private String location;
	private String description;
	private String weapon;
	private String district;
	private String neighborhood;
	
	public int getCrimeID() {
		return crimeID;
	}
	
	public void setCrimeID(int crimeID) {
		this.crimeID = crimeID;
	}
	
	public Date getCrimeDate() {
		return crimeDate;
	}
	
	public void setCrimeDate(Date crimeDate) {
		this.crimeDate = crimeDate;
	}
	
	public String getCrimeCode() {
		return crimeCode;
	}
	
	public void setCrimeCode(String crimeCode) {
		this.crimeCode = crimeCode;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getWeapon() {
		return weapon;
	}
	
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getNeighborhood() {
		return neighborhood;
	}
	
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	@Override
	public String toString() {
		return "Crime [crimeID=" + crimeID + ", crimeDate=" + crimeDate
				+ ", crimeCode=" + crimeCode + ", location=" + location
				+ ", description=" + description + ", weapon=" + weapon
				+ ", district=" + district + ", neighborhood=" + neighborhood
				+ "]";
	}
}
