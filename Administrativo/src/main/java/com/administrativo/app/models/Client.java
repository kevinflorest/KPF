package com.administrativo.app.models;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

	@Id
	private String id;
	
	@NotEmpty
	private String codClient;
	@NotEmpty
	private String client;
	@NotEmpty
	private String typeDocument;
	@NotEmpty
	private String numberDocument;
	@NotEmpty
	private String address;
	@NotEmpty
	private String district;
	@NotEmpty
	private String provence;
	@NotEmpty
	private String departament;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateCreate;
}
