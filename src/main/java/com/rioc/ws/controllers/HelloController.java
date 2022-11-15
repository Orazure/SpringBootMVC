package com.rioc.ws.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rioc.ws.services.hello.IHelloService;

@RestController
public class HelloController {
	
	private IHelloService service;
	
	public HelloController(IHelloService service) {
		this.service = service;
	}
	
	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}
	
	@GetMapping("/hello/name")
	public String getHelloFornName(@RequestParam("name") String name ) {
		return service.getHelloForName(name);
	}

}
