package com.administrativo.app.models;

import javax.validation.constraints.NotEmpty;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="user")
public class User {
	
	@Id
	private String id;
	
	@NotEmpty
	private String codUser;
	@NotEmpty
	private String user;
	@NotEmpty
	private String password;
	@NotEmpty
	private String firstNameUser;
	@NotEmpty
	private String secondNameUser;
	@NotEmpty
	private String surnamePaternal;
	@NotEmpty
	private String surnameMaternal;

}
