package com.rioc.ws.services.hello;

import org.springframework.stereotype.Service;

@Service
public class HelloServce implements IHelloService {
	
	public HelloServce() {}
	
	public String getHelloForName(String name) {
		return "Hello " + name + " : "+ getLenght(name);
	}
	
	private int getLenght(String name) {
		return name.length();
	}

}
