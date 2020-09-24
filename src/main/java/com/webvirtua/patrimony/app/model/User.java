package com.webvirtua.patrimony.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "api_user",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_USER"))
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id_user")
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String name;
	
	@NotBlank
	@Email
	@Size(max = 120)
	private String email;
	
	@NotBlank
	@Size(max = 255)
	private String password;
}
