package com.github.wesleyav.graphqlspringsecuritykeycloaksample.graphqls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.wesleyav.graphqlspringsecuritykeycloaksample.entities.Country;
import com.github.wesleyav.graphqlspringsecuritykeycloaksample.services.CountryService;

@Component
public class CountryGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

	@Autowired
	private CountryService countryService;

	public List<Country> countries() {
		return countryService.findAll();
	}

	public Country country(Long country_id) {
		return countryService.findById(country_id);
	}

}
