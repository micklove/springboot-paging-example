package com.example;

import com.example.service.CityService;
import com.example.web.CityController;
import com.example.web.CityResourceAssembler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class CityControllerTest {

	@Autowired
	private CityService cityService;

	@Autowired
	private CityResourceAssembler cityResourceAssembler;


	@Before
	public void setUp() throws Exception {

	}


	@Test
	public void helloWorld() throws Exception {
		new CityController(cityService, cityResourceAssembler).helloWorld();

	}

}