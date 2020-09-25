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
import com.webvirtua.patrimony.app.dto.PatrimonyDTO;
import com.webvirtua.patrimony.app.service.PatrimonyService;

@RestController
@RequestMapping("/v1/patrimony")
public class PatrimonyController implements IController<PatrimonyDTO>
{
	@Autowired 
	private PatrimonyService patrimonyService;
	
	@Autowired
	private Status status;
	
	@Autowired
	private HttpServletResponse response;
	
	@GetMapping
	public ReturnRequest findAll() 
	{
		try {
			ReturnRequest result = patrimonyService.findAll();
			
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
			ReturnRequest result = patrimonyService.findOne(id);
			
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
	public ReturnRequest insert(@Valid @RequestBody PatrimonyDTO patrimony)
	{
		try {
			this.verifyIdBrand(patrimony.getBrand().getId());
			this.verifyName(patrimony.getName());
			
			ReturnRequest result = patrimonyService.insert(patrimony);
			
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
	public ReturnRequest update(@Valid @PathVariable Long id, @RequestBody PatrimonyDTO patrimony) 
	{
		try {
			this.verifyIdBrand(patrimony.getBrand().getId());
			this.verifyName(patrimony.getName());
			
			ReturnRequest result = patrimonyService.update(id, patrimony);
			
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
			ReturnRequest result = patrimonyService.delete(id);
			
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
	
	private void verifyIdBrand(Long idBrand) {
		if (idBrand == null) {
			throw new RunTimeException("É obrigatório enviar o ID da marca no corpo da requisição.");
		}
	}
	
	private void verifyName(String name) {
		if (name == null) {
			throw new RunTimeException("É obrigatório enviar o nome do patrimônio no corpo da requisição.");
		}
	}
}
