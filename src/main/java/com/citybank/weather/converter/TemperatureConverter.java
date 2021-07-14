package com.citybank.weather.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class TemperatureConverter {
	
	public double fromCelsiusToFarenheit(Double celsius) {
		return new BigDecimal(((celsius*9)/5)+ 32)
						.setScale(2, RoundingMode.UP)
						.doubleValue() ; 
	}

}
