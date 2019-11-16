package com.administrativo.app.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.administrativo.app.dao.UserDAO;
import com.administrativo.app.models.User;
import com.administrativo.app.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserDAO udao;
	
	@Override
	public Mono<User> getUser(String codUser) {
		return udao.findBycodUser(codUser);
	}

	@Override
	public Flux<User> registerUsers(List<User> users) {
		
		Flux<User> foundUsers = findUsers(getCodesByList(users));
		
//		Flux<User> existingUsers = Flux.fromIterable(users)
//				.flatMap(user -> foundUsers.any(foundUser -> foundUser.getCodUser().equals(user.getCodUser()))
//						.flatMap(exists -> exists.booleanValue() ? Mono.just(user) : Mono.empty())
//				);
		
		 Flux<User> nonExistingUsers =  Flux.fromIterable(users)
				.flatMap(user -> foundUsers.all(foundUser -> !foundUser.getCodUser().equals(user.getCodUser()))
						.flatMap(exists -> exists.booleanValue() ? Mono.just(user) : Mono.empty())
				);
		 
		 return udao.saveAll(nonExistingUsers);
		 

	}
	
	private String[] getCodesByList(List<User> usuarios) {
		return usuarios.stream()
		.map(User::getCodUser)
		.distinct()
		.toArray(String[]::new);
	}
	
//	private String getCodesByList1(List<User> usuarios) {
//		return usuarios.stream()
//		.map(User::getCodUser)
//		.distinct()
//		.collect(Collectors.joining(","));
//	}
	
	private Flux<User> findUsers(String[] codUsers) {
		System.out.println(codUsers);
		return udao.findAllBycodUser(codUsers);
	}
	

}
