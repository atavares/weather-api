package com.citybank.weather.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class TemperatureDTO {
	
	@JsonAlias("the_temp")
	private Double celsius;
	
	private Double farenheit;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private LocalDateTime created;
	
	
	public void setCelsius(Double celsius) {
		this.celsius = celsius;
	}
	
	public Double getCelsius() {
		return celsius;
	}

	public Double getFarenheit() {
		return farenheit;
	}

	public void setFarenheit(Double farenheit) {
		this.farenheit = farenheit;
	}

	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
}
