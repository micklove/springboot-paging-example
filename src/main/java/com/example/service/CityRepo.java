package com.example.service;


import com.example.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

interface CityRepo extends PagingAndSortingRepository<City, Long> {

	Page<City> findAll(Pageable pageable);
	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable);
	City findByNameAndCountryAllIgnoringCase(String name, String country);

}
