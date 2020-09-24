package com.webvirtua.patrimony.app.contracts.controller;

import com.webvirtua.patrimony.app.core.utils.ReturnRequest;

public interface IController<C> 
{
	public ReturnRequest findAll();
	
	public ReturnRequest findOne(Long id);

	public ReturnRequest insert(C requestBody);
	
	public ReturnRequest update(Long id, C requestBody);
	
	public ReturnRequest delete(Long id);
}
