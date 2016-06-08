package com.example.web;

import com.example.domain.City;
import com.example.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//See https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-jpa


// view hal browser at http://localhost:9191/browser/index.html#/

@RequestMapping("/city")
@RestController
public class CityController {

	private final CityService cityService;

	private final CityResourceAssembler cityResourceAssembler;

	@Autowired
	public CityController(CityService cityService, CityResourceAssembler cityResourceAssembler) {
		this.cityService = cityService;
		this.cityResourceAssembler = cityResourceAssembler;
	}


	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional(readOnly = true)
	public Resource<City> helloWorld() {
		City city = this.cityService.getCity("Bath", "UK");
		return cityResourceAssembler.toResource(city);
	}

	//e.g. curl -v localhost:9191/city/Bath/UK
	@RequestMapping(value="/{city}/{country}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional(readOnly = true)
	public Resource<City> getCity(@PathVariable("city") String city, @PathVariable("country") String country) {
		City loadedCity = this.cityService.getCity(city, country);
		return cityResourceAssembler.toResource(loadedCity);
	}

	//e.g. curl -v localhost:9191/city/Bath/UK
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional(readOnly = true)
	public Resource<City> id(@PathVariable("id") Long id) {
		City city = this.cityService.getCityById(id);
		return cityResourceAssembler.toResource(city);
	}


	/**
	 * Pages and sorts
	 * See http://docs.spring.io/spring-data/rest/docs/1.1.x/reference/html/paging-chapter.html
	 *
	 * sort by city name, descending
	 * http://localhost:9191/city/all?page=0&size=5&sort=name,desc
	 *
	 * A Pageable will consist of a page, size and sort property.  Put simpler, if you have a query string
	 * that looks like ?page=0&size=20&sort=type,desc, this will be converted to a Pageable with page 0, 20
	 * records per page, and sorting on the type property in descending order.
	 *
	 *  TODO - add links to each City in the response
	 *  https://patrickgrimard.io/2014/05/16/pagination-with-spring-data-and-hateoas-in-an-angularjs-app/
	 * @param pageable
	 * @param assembler
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional(readOnly = true)
	public PagedResources<City> getCity(Pageable pageable, PagedResourcesAssembler assembler) {
		Page<City> cities = this.cityService.all(pageable);
		return assembler.toResource(cities, cityResourceAssembler);
	}


}