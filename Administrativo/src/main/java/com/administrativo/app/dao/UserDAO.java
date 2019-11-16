package com.administrativo.app.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.administrativo.app.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserDAO extends ReactiveMongoRepository<User, String> {

	Mono<User> findBycodUser(String codUser);
	
	@Query("{'codUser' : {'$in' : ?0} }")
	Flux<User> findAllBycodUser(String[] codUser);
	
}
