package com.webvirtua.patrimony.app.core.utils;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class Status 
{
	private int code200;
	private int code201;
	private int code202;
	private int code203;
	private int code204;
	
	private int code301;
	
	private int code400;
	private int code401;
	private int code403;
	private int code404;
	
	public Status() 
	{
		this.code200 = 200;
		this.code201 = 201;
		this.code202 = 202;
		this.code203 = 203;
		this.code204 = 204;
		
		this.code301 = 301;
		
		this.code400 = 400;
		this.code401 = 401;
		this.code403 = 403;
		this.code404 = 404;
	}
}
