package com.webvirtua.patrimony.app.core.utils;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class Status 
{
	private final int code200 = 200;
	private final int code201 = 201;
	private final int code202 = 202;
	private final int code203 = 203;
	private final int code204 = 204;
	
	private final int code301 = 301;
	
	private final int code400 = 400;
	private final int code401 = 401;
	private final int code403 = 403;
	private final int code404 = 404;
}
