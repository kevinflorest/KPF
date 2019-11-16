package com.administrativo.app.service;

import java.util.List;

import com.administrativo.app.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {

	Mono<User> getUser(String codUser);
	
	Flux<User> registerUsers(List<User> user);
	
	
}