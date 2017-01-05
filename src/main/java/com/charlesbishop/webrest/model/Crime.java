package com.charlesbishop.webrest.model;

import java.util.Date;
import java.util.regex.Matcher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Crime")
public class Crime extends ObjModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CrimeID")
	private int crimeID;
	
	@Column(name="CrimeDate")
	private Date crimeDate;
	
	@Column(name="CrimeCode")
	private String crimeCode;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Weapon")
	private String weapon;
	
	@Column(name="District")
	private String district;
	
	@Column(name="Neighborhood")
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
		return "{\"crimeID\": " + crimeID + ","
				+"\"crimeDate\": \"" + crimeDate + "\","
				+"\"crimeCode\": \"" + crimeCode + "\","
				+"\"location\": \"" + location + "\","
				
				//It is possible that a quotation can be in the description, so escape all double quotes
				+"\"description\": \"" + description.replaceAll("\"", Matcher.quoteReplacement("\\\"")) + "\","
				
				+"\"weapon\": \"" + weapon + "\","
				+"\"district\": \"" + district + "\","
				+"\"neighborhood\": \"" + neighborhood + "\"}";
	}

}
