package com.administrativo.app.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrativo.app.dao.ClientDAO;
import com.administrativo.app.models.Client;
import com.administrativo.app.service.ClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO cdao;
	
	
	@Override
	public Mono<Client> getClient(String codClient) {
		return cdao.findBycodClient(codClient);
	}
	
	@Override
	public Flux<Client> getAllClients() {
		return cdao.findAll();
	}

	@Override
	public Mono<Client> registerClient(Client client) {
		
		Mono<Client> findClient = getClient(client.getCodClient());
		
		return Mono.just(client)
				.doOnNext(valid -> {valid.setDateCreate(new Date());})
				.flatMap(cli -> findClient.hasElement()
						.flatMap(exist -> exist ? Mono.empty() : cdao.save(cli)));
	}

	@Override
	public Flux<Client> registerMass(List<Client> clients) {
		
		Flux<Client> foundClients = findClients(getListClients(clients));
		
		return Flux.fromIterable(clients).doOnNext(cli -> {
			cli.setDateCreate(new Date());
		}).collectList()
				.flatMapMany(list -> Flux.fromIterable(list)
						.flatMap(cli2 -> foundClients
								.all(foundClient -> !foundClient.getCodClient().equals(cli2.getCodClient()))
								.flatMap(exists -> exists ? cdao.save(cli2) : Mono.empty())));
	}
	
	
	private String[] getListClients(List<Client> clients)
	{
		return clients
				.stream()
				.map(Client::getCodClient)
				.distinct()
				.toArray(String[]::new);
	}
	
	private Flux<Client> findClients(String[] clients)
	{
		return cdao.findAllBycodClients(clients);
	}

	@Override
	public Mono<Client> updateClient(Client client) {
		
		return cdao.findBycodClient(client.getCodClient()).flatMap(cli -> {
			cli.setCodClient(client.getCodClient());
			cli.setClient(client.getClient());
			cli.setTypeDocument(client.getTypeDocument());
			cli.setNumberDocument(client.getNumberDocument());
			cli.setAddress(client.getAddress());
			cli.setDistrict(client.getDistrict());
			cli.setProvence(client.getProvence());
			cli.setDepartament(client.getDepartament());
			cli.setDateCreate(new Date());
			return cdao.save(cli);
		});
	}
	



}
