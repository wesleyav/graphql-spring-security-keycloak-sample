package com.github.wesleyav.graphqlspringsecuritykeycloaksample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wesleyav.graphqlspringsecuritykeycloaksample.entities.Country;
import com.github.wesleyav.graphqlspringsecuritykeycloaksample.repositories.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	public Country findById(Long country_id) {
		return countryRepository.findById(country_id).orElse(null);
	}

}
