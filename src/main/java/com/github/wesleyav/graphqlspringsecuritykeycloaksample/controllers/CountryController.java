package com.github.wesleyav.graphqlspringsecuritykeycloaksample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.wesleyav.graphqlspringsecuritykeycloaksample.entities.Country;
import com.github.wesleyav.graphqlspringsecuritykeycloaksample.services.CountryService;

@RestController
@RequestMapping(value = "/api/v1")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> findAll() {
		List<Country> countryFindAll = countryService.findAll();
		return ResponseEntity.ok().body(countryFindAll);
	}

	@RequestMapping(value = "/country/{country_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> countryFindById(@PathVariable Long country_id) {
		Country countryFindById = countryService.findById(country_id);
		return ResponseEntity.ok().body(countryFindById);
	}

}
