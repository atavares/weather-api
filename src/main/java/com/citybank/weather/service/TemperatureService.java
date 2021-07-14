package com.citybank.weather.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citybank.weather.converter.TemperatureConverter;
import com.citybank.weather.dto.CityDTO;
import com.citybank.weather.dto.TemperatureDTO;
import com.citybank.weather.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class TemperatureService {
	
	@Value("${uri.search.city}")
	private String uriSearchCity;

	@Value("${uri.search.woeid}")
	private String uriSearchWoeid;

	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;
	private TemperatureConverter temperatureConverter;
	
	@Autowired
	public TemperatureService(RestTemplate restTemplate,
			                  ObjectMapper objetMapper,
			                  TemperatureConverter temperatureConverter) {
		
		this.restTemplate = restTemplate;
		this.objectMapper = objetMapper;
		this.temperatureConverter = temperatureConverter;
	}
	
	public TemperatureDTO getTemperatureFrom(String city) {
		CityDTO[] citys = restTemplate.getForObject(uriSearchCity+city, CityDTO[].class);
		
		if(citys.length>0) {
			return searchTemperatureFromWoeid(citys[0].getWoeid());
		}else {
			throw new ResourceNotFoundException();
		}

	}
	
	private TemperatureDTO searchTemperatureFromWoeid(Long woeid)  {
		ObjectNode resource = restTemplate.getForObject(uriSearchWoeid+woeid, ObjectNode.class);
		
		List<TemperatureDTO> temperatures;
		try {
			temperatures = Arrays.asList(objectMapper.readValue(resource.get("consolidated_weather").toString(), TemperatureDTO[].class));

			if(!temperatures.isEmpty()) {
				TemperatureDTO temperatureDTO = temperatures.stream()
								   .max(Comparator.comparing(TemperatureDTO::getCreated))
								   .get();
				
				temperatureDTO.setFarenheit(temperatureConverter.fromCelsiusToFarenheit(temperatureDTO.getCelsius()));
				
				return temperatureDTO;
				
			}else {
				throw new ResourceNotFoundException();
			}
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}
	
	
}
