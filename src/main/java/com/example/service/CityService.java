package com.example.service;


import com.example.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

	Page<City> findCities(CitySearchCriteria criteria, Pageable pageable);
	City getCity(String name, String country);

	Page<City> all(Pageable pageable);

	City getCityById(Long id);

//	Page<HotelSummary> getHotels(City city, Pageable pageable);

}
