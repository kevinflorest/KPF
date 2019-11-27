package com.administrativo.app.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.administrativo.app.models.Client;
import com.administrativo.app.service.ClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("${application.config.path.client}")
public class ClientController {

	@Autowired
	private ClientService service;
	
	
	@GetMapping
	Flux<Client> getClients()
	{
		return service.getAllClients();
	}
	
	@GetMapping("{numberDocument}")
	Mono<Client> getClient(@PathVariable String numberDocument)
	{
		return service.getClient(numberDocument);
	}
	
	@PostMapping
	Mono<Client> registerClient(@Valid @RequestBody Client client)
	{
		return service.registerClient(client);
	}
	
	
	@PostMapping("/all")
	Flux<Client> registerMass(@NotEmpty(message = "The list not be a empty") @RequestBody List<Client> clients)
	{
		return service.registerMass(clients);
	}
	
	@PutMapping("{codClient}")
	Mono<Client> updateMass(@Valid @RequestBody Client client)
	{
		return service.updateClient(client);
	}
}
