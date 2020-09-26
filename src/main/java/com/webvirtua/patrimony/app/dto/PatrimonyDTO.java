package com.webvirtua.patrimony.app.dto;

import com.webvirtua.patrimony.app.model.Brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrimonyDTO 
{
	private String name;
	private String description;
	private Brand brand;
}
