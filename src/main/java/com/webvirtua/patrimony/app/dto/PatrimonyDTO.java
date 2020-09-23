package com.webvirtua.patrimony.app.dto;

import com.webvirtua.patrimony.app.model.Brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrimonyDTO 
{
	private Long id;
	private String name;
	private String description;
	private Brand brand;
	private String tumble;
}
