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

import com.webvirtua.patrimony.app.dto.BrandDTO;
import com.webvirtua.patrimony.app.dto.UserDTO;
import com.webvirtua.patrimony.app.resources.utils.ReturnRequest;
import com.webvirtua.patrimony.app.service.BrandService;
import com.webvirtua.patrimony.app.service.UserService;

@RestController
@RequestMapping("/v1/brands")
public class BrandController 
{
	@Autowired 
	private BrandService brandService;
	
	@GetMapping({"/", ""})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest findAll() 
	{
		ReturnRequest result = brandService.findAll();
		return result;
	}
	
	@GetMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest findOne(@PathVariable Long id) 
	{
		ReturnRequest result = brandService.findOne(id);
		return result;
	}

	@PostMapping({"/", ""})
	@ResponseStatus(HttpStatus.CREATED)
	public ReturnRequest insert(@Valid @RequestBody BrandDTO brand) // @Valid não está pegando, tá tentando validar mas dá erro 500
	{
		ReturnRequest result = brandService.insert(brand);
		return result;
	}
	
	@PutMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest update(@Valid @PathVariable Long id, @RequestBody BrandDTO brand) 
	{
		ReturnRequest result = brandService.update(id, brand);
		return result;
	}
	
	@DeleteMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest delete(@PathVariable Long id) 
	{
		ReturnRequest result = brandService.delete(id);
		return result;
	}
}
