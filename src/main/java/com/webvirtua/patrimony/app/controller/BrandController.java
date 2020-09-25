package com.webvirtua.patrimony.app.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webvirtua.patrimony.app.contracts.controller.IController;
import com.webvirtua.patrimony.app.core.excepions.RunTimeException;
import com.webvirtua.patrimony.app.core.utils.ReturnRequest;
import com.webvirtua.patrimony.app.core.utils.Status;
import com.webvirtua.patrimony.app.dto.BrandDTO;
import com.webvirtua.patrimony.app.service.BrandService;

@RestController
@RequestMapping("/v1/brands")
public class BrandController implements IController<BrandDTO>
{
	@Autowired 
	private BrandService brandService;
	
	@Autowired
	private Status status;
	
	@Autowired
	private HttpServletResponse response;
	
	@GetMapping
	public ReturnRequest findAll() 
	{
		try {
			ReturnRequest result = brandService.findAll();
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
	@GetMapping("/{id}")
	public ReturnRequest findOne(@PathVariable Long id) 
	{
		try {
			ReturnRequest result = brandService.findOne(id);
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}

	@PostMapping
	public ReturnRequest insert(@Valid @RequestBody BrandDTO brand)
	{
		try {
			this.verifyName(brand.getName());
			
			ReturnRequest result = brandService.insert(brand);
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
		
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
	@PutMapping("/{id}")
	public ReturnRequest update(@Valid @PathVariable Long id, @RequestBody BrandDTO brand) 
	{
		try {
			this.verifyName(brand.getName());
			
			ReturnRequest result = brandService.update(id, brand);
			
			ResponseEntity.ok(result.getData());
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
	@DeleteMapping("/{id}")
	public ReturnRequest delete(@PathVariable Long id) 
	{
		try {
			ReturnRequest result = brandService.delete(id);
			
			ResponseEntity.ok();
			response.setStatus(result.getStatus());
			
			return result;
		} catch (Exception e) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(status.getCode400())
					.errorMessage(e.getMessage())
					.build();
			
			ResponseEntity.badRequest().build();
			response.setStatus(status.getCode400());
			
			return resultRequest;
		}
	}
	
	private void verifyName(String name) {
		if (name == null) {
			throw new RunTimeException("É obrigatório enviar o nome da marca no corpo da requisição.");
		}
	}
}
