package com.administrativo.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administrativo.app.models.User;
import com.administrativo.app.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${application.config.path.user}")
public class UserController {

	@Autowired
	private UserService service;
	
	
	@GetMapping("{codUser}")
	public Mono<User> getUser(@PathVariable String codUser)
	{
		return service.getUser(codUser);
	}
	
	@PostMapping
	public Flux<User> registerUsers(@Valid @RequestBody List<User> users)
	{
		return service.registerUsers(users);
	}
	
	
}
