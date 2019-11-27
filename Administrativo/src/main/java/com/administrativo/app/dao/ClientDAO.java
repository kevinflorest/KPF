package com.administrativo.app.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.administrativo.app.models.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientDAO extends ReactiveMongoRepository<Client, String>{
	
	@Query("{'codClient' : { '$in' :?0}}")
	Flux<Client> findAllBycodClients(String[] codClients);
	
	Mono<Client> findBynumberDocument(String numberDocument);

}
