package com.webvirtua.patrimony.app.core.excepions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RunTimeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public RunTimeException(String message) 
	{
		super("Erro código: " + serialVersionUID + ". Mensagem: " + message);
	}	
}