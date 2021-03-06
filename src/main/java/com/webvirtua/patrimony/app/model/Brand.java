package com.webvirtua.patrimony.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name = "api_brand",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_BRAND"))
public class Brand 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id_brand")
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String name;
}
