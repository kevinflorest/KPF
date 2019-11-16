package com.administrativo.app.service;

import java.util.List;

import com.administrativo.app.models.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

	Mono<Client> getClient(String codClient);
	
	Flux<Client> getAllClients();
	
	Mono<Client> registerClient(Client client);
	
	Flux<Client> registerMass(List<Client> clients);
	
	Mono<Client> updateClient(Client client);
	
}
