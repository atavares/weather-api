package com.citybank.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citybank.weather.dto.TemperatureDTO;
import com.citybank.weather.exception.ResourceNotFoundException;
import com.citybank.weather.service.TemperatureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {
	
	private TemperatureService temperatureService;
	
	@Autowired
	public TemperatureController(TemperatureService temperatureService) {
		this.temperatureService = temperatureService;
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Return temperature in celsius and farenheit"),
		    @ApiResponse(code = 404, message = "Not found temparature to city sent"),
		    @ApiResponse(code = 500, message = "An internal error occurred"),
		})
	@GetMapping(value= "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TemperatureDTO> getTemperature(@PathVariable String city) throws JsonMappingException, JsonProcessingException{
		try {
			return ResponseEntity.ok(temperatureService.getTemperatureFrom(city));
		
		}catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
