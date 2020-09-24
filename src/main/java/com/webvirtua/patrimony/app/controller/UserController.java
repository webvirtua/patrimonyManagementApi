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

import com.webvirtua.patrimony.app.contracts.controller.IController;
import com.webvirtua.patrimony.app.core.excepions.RunTimeException;
import com.webvirtua.patrimony.app.core.utils.ReturnRequest;
import com.webvirtua.patrimony.app.core.utils.Status;
import com.webvirtua.patrimony.app.dto.UserDTO;
import com.webvirtua.patrimony.app.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController implements IController<UserDTO>
{
	@Autowired 
	private UserService userService;
	
	@Autowired
	private Status status;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest findAll() 
	{
		try {
			ReturnRequest result = userService.findAll();
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
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest findOne(@PathVariable Long id) 
	{
		try {
			ReturnRequest result = userService.findOne(id);
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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ReturnRequest insert(@Valid @RequestBody UserDTO user)
	{
		try {
			this.verifyName(user.getName());
			this.verifyEmail(user.getName());
			this.verifyPassword(user.getName());
		
			ReturnRequest result = userService.insert(user);
			ResponseEntity.ok(result.getData());
			ResponseEntity.status(201);
			
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
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest update(@Valid @PathVariable Long id, @RequestBody UserDTO user) 
	{
		try {
			this.verifyName(user.getName());
			this.verifyEmail(user.getName());
			this.verifyPassword(user.getName());
			
			ReturnRequest result = userService.update(id, user);
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
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ReturnRequest delete(@PathVariable Long id) 
	{
		try {
			ReturnRequest result = userService.delete(id);
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
			throw new RunTimeException("É obrigatório enviar o nome do cliente no corpo da requisição.");
		}
	}
	
	private void verifyEmail(String email) {
		if (email == null) {
			throw new RunTimeException("É obrigatório enviar o e-mail do cliente no corpo da requisição.");
		}
	}
	
	private void verifyPassword(String password) {
		if (password == null) {
			throw new RunTimeException("É obrigatório enviar a senha do cliente no corpo da requisição.");
		}
	}
}
