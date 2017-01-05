package com.charlesbishop.webrest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vacant")
public class Vacant extends ObjModel {
	@Id
	@Column(name="ReferenceID", unique=true)
	private String referenceID;
	
	@Column(name="Block")
	private String block;
	
	@Column(name="Lot")
	private String lot;
	
	@Column(name="BuildingAddress")
	private String buildingAddress;
	
	@Column(name="NoticeDate")
	private Date noticeDate;
	
	@Column(name="Neighborhood")
	private String neighborhood;
	
	@Column(name="PoliceDistrict")
	private String policeDistrict;
	
	@Column(name="CouncilDistrict")
	private int councilDistrict;
	
	@Column(name="Location")
	private String location;
	
	public String getReferenceID() {
		return referenceID;
	}
	
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
	
	public String getBlock() {
		return block;
	}
	
	public void setBlock(String block) {
		this.block = block;
	}
	
	public String getLot() {
		return lot;
	}
	
	public void setLot(String lot) {
		this.lot = lot;
	}
	
	public String getBuildingAddress() {
		return buildingAddress;
	}
	
	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}
	
	public Date getNoticeDate() {
		return noticeDate;
	}
	
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	
	public String getNeighborhood() {
		return neighborhood;
	}
	
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	public String getPoliceDistrict() {
		return policeDistrict;
	}
	
	public void setPoliceDistrict(String policeDistrict) {
		this.policeDistrict = policeDistrict;
	}
	
	public int getCouncilDistrict() {
		return councilDistrict;
	}
	
	public void setCouncilDistrict(int councilDistrict) {
		this.councilDistrict = councilDistrict;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "{\"referenceID\": \"" + referenceID + "\","
		+"\"block\": \"" + block + "\","
		+"\"lot\": \"" + lot + "\","
		+"\"buildingAddress\": \"" + buildingAddress + "\","		
		+"\"noticeDate\": \"" + noticeDate + "\","
		+"\"neighborhood\": \"" + neighborhood + "\","
		+"\"policeDistrict\": \"" + policeDistrict + "\","
		+"\"councilDistrict\": " + councilDistrict + ","
		+"\"location\": \"" + location + "\"}";
	}
	
}
