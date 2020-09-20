package com.webvirtua.patrimony.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webvirtua.patrimony.app.dto.PatrimonyDTO;
import com.webvirtua.patrimony.app.resources.utils.ReturnRequest;
import com.webvirtua.patrimony.app.service.PatrimonyService;

@RestController
@RequestMapping("/v1/patrimony")
public class PatrimonyController 
{
	@Autowired 
	private PatrimonyService patrimonyService;
	
	@GetMapping({"/", ""})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest findAll() 
	{
		ReturnRequest result = patrimonyService.findAll();
		return result;
	}
	
	@GetMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest findOne(@PathVariable Long id) 
	{
		ReturnRequest result = patrimonyService.findOne(id);
		return result;
	}

	@PostMapping({"/", ""})
	@ResponseStatus(HttpStatus.CREATED)
	public ReturnRequest insert(@Valid @RequestBody PatrimonyDTO patrimony)
	{
		ReturnRequest result = patrimonyService.insert(patrimony);
		return result;
	}
	
	@PutMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest update(@Valid @PathVariable Long id, @RequestBody PatrimonyDTO patrimony) 
	{
		ReturnRequest result = patrimonyService.update(id, patrimony);
		return result;
	}
	
	@DeleteMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest delete(@PathVariable Long id) 
	{
		ReturnRequest result = patrimonyService.delete(id);
		return result;
	}
}
