package com.github.wesleyav.graphqlspringsecuritykeycloaksample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.wesleyav.graphqlspringsecuritykeycloaksample.entities.City;
import com.github.wesleyav.graphqlspringsecuritykeycloaksample.services.CityService;


@RestController
@RequestMapping(value = "/api/v1")
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> findAll() {
		List<City> cityFindAll = cityService.findAll();
		return ResponseEntity.ok().body(cityFindAll);
	}

	@RequestMapping(value = "/cities/{city_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> cityFindById(@PathVariable Long city_id) {
		City cityFindById = cityService.findById(city_id);
		return ResponseEntity.ok().body(cityFindById);
	}

}
