package com.webvirtua.patrimony.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;
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
@Table(name = "api_patrimony",  uniqueConstraints=@UniqueConstraint(columnNames="id", name="PK_ID_PATRIMONY"))
public class Patrimony 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id_patrimony")
	private Long id;

	@ManyToOne
	@JoinColumn(name="api_brand", referencedColumnName="id", nullable=false, foreignKey=@ForeignKey(name = "FK_TB_BRAND_TB_PATRIMONY"))
	private Brand brand;
	
	@NotBlank
	@Size(max = 100)
	private String name;
	
	@Column(updatable=false)
	private Long tumble;

	@Size(max = 255)
	private String description;
}
