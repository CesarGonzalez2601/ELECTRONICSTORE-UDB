package com.app.web.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Clients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "id" )
	private Integer idClient;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "dateofbirth")
	private LocalDate dateOfBirth;

	@Column( name = "isactive")
	private Boolean isActive;

}
