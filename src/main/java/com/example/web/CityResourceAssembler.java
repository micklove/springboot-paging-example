package com.example.web;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.City;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CityResourceAssembler extends ResourceAssemblerSupport<City, Resource> {

	public CityResourceAssembler() {
		super(CityController.class, Resource.class);
	}

	@Override
	public List<Resource> toResources(Iterable<? extends City> cities) {
		List<Resource> resources = new ArrayList<>();
		for(City city : cities) {
			resources.add(toResource(city));
		}
		return resources;
	}

	@Override
	public Resource toResource(City city) {
		Resource resource = new Resource(city);
		resource.add(linkTo(CityController.class).slash(city.getId()).withSelfRel());
		resource.add(linkTo(CityController.class).slash("all").withRel("all"));
		return resource;
	}
}