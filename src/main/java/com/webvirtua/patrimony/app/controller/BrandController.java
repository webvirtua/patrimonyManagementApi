package com.webvirtua.patrimony.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webvirtua.patrimony.app.core.excepions.RunTimeException;
import com.webvirtua.patrimony.app.core.utils.ReturnRequest;
import com.webvirtua.patrimony.app.core.utils.Status;
import com.webvirtua.patrimony.app.dto.BrandDTO;
import com.webvirtua.patrimony.app.service.BrandService;

@RestController
@RequestMapping("/v1/brands")
public class BrandController 
{
	@Autowired 
	private BrandService brandService;
	
	@Autowired
	private Status status;
	
	@GetMapping({"/", ""})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest findAll() 
	{
		try {
			ReturnRequest result = brandService.findAll();
			ResponseEntity.ok(result.getData());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			
			return resultRequest;
		}
	}
	
	@GetMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest findOne(@PathVariable Long id) 
	{
		try {
			ReturnRequest result = brandService.findOne(id);
			ResponseEntity.ok(result.getData());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			
			return resultRequest;
		}
	}

	@PostMapping({"/", ""})
	@ResponseStatus(HttpStatus.CREATED)
	public ReturnRequest insert(@Valid @RequestBody BrandDTO brand)
	{
		try {
			this.verifyName(brand.getName());
			
			ReturnRequest result = brandService.insert(brand);
			ResponseEntity.ok(result.getData());
		
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			
			return resultRequest;
		}
	}
	
	@PutMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest update(@Valid @PathVariable Long id, @RequestBody BrandDTO brand) 
	{
		try {
			this.verifyName(brand.getName());
			
			ReturnRequest result = brandService.update(id, brand);
			ResponseEntity.ok(result.getData());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			
			return resultRequest;
		}
	}
	
	@DeleteMapping({"/{id}/", "/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest delete(@PathVariable Long id) 
	{
		try {
			ReturnRequest result = brandService.delete(id);
			ResponseEntity.ok();
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			
			return resultRequest;
		}
	}
	
	private void verifyName(String name) {
		if (name == null) {
			throw new RunTimeException("É obrigatório enviar o nome da marca no corpo da requisição.");
		}
	}
}
