package com.citybank.weather.dto;

public class CityDTO {

	private Long woeid;

	public Long getWoeid() {
		return woeid;
	}
	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}
	
	@Override
	public String toString() {
		return "CityDTO [woeid=" + woeid + "]";
	}
	
	
}
