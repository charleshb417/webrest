package com.charlesbishop.webrest.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vacant")
public class Vacant {
	@Id
	@Column(name="ReferenceID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private String referenceID;
	private String block;
	private String lot;
	private String buildingAddress;
	private Date noticeDate;
	private String neighborhood;
	private String policeDistrict;
	private int councilDistrict;
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
		return "Vacant [referenceID=" + referenceID + ", block=" + block
				+ ", lot=" + lot + ", buildingAddress=" + buildingAddress
				+ ", noticeDate=" + noticeDate + ", neighborhood="
				+ neighborhood + ", policeDistrict=" + policeDistrict
				+ ", councilDistrict=" + councilDistrict + ", location="
				+ location + "]";
	}
	
}
