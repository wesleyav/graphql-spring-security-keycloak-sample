package com.github.wesleyav.graphqlspringsecuritykeycloaksample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.wesleyav.graphqlspringsecuritykeycloaksample.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
