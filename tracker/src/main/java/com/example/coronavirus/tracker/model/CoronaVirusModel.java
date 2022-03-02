package com.example.coronavirus.tracker.model;

public class CoronaVirusModel {

	private String state;

	private String country;

	public String getState() {
		return state;
	}

	public CoronaVirusModel() {
		super();
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getTotalnoofcases() {
		return totalnoofcases;
	}

	public void setTotalnoofcases(Integer totalnoofcases) {
		this.totalnoofcases = totalnoofcases;
	}

	private Integer totalnoofcases;

}
