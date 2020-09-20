package com.webvirtua.patrimony.app.dto;

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
public class UserDTO 
{
	private Long id;
	private String name;
	private String email;
	private String password;
	private String isbn;
}
