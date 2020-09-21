package com.webvirtua.patrimony.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webvirtua.patrimony.app.dto.UserDTO;
import com.webvirtua.patrimony.app.model.User;
import com.webvirtua.patrimony.app.repository.UserRepository;
import com.webvirtua.patrimony.app.resources.utils.ReturnRequest;

@Service
public class UserService
{
	private ModelMapper modelMapper;
	
	public UserService(ModelMapper mapper) 
	{
		this.modelMapper = mapper;
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ReturnRequest findAll() 
	{
		List<User> users = userRepository.findAll();

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(200)
				.totalResults(users.size())
				.resultsPerPage(0)
				.totalPages(0)
				.page(0)
				.successMessage("Resultados Obtidos")
				.data(users)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest findOne(Long id) 
	{
		Optional<User> user = userRepository.findById(id);
		
		if (user.equals(user)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.successMessage("Resultados Obtidos")
					.data(Arrays.asList(user))
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(0)
				.status(400)
				.totalResults(0)
				.errorMessage("Sem Resultados")
				.build();
		
		ResponseEntity.notFound().build();

		return resultRequest;
	}
	
	public ReturnRequest insert(UserDTO user) 
	{
		if (user.getName() == null) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.errorMessage("É obrigatório enviar o nome do cliente no corpo da requisição.")
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}
		
		if (user.getEmail() == null) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.errorMessage("É obrigatório enviar o e-mail do cliente no corpo da requisição.")
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}
		
		if (user.getPassword() == null) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.errorMessage("É obrigatório enviar a senha do cliente no corpo da requisição.")
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}
		
		User emailExist = userRepository.findByEmail(user.getEmail());
		
		if (emailExist != null && !emailExist.equals(user)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("E-mail já existe na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}
		
		//String passwordHash = passwordEncoder.encode(user.getPassword());
		//user.setPassword(passwordHash);
		
		User entity = this.modelMapper.map(user, User.class);
		
		User userAdded = userRepository.save(entity);
		
		if (userAdded.equals(userAdded)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(201)
					.totalResults(1)
					.successMessage("Usuário inserido com sucesso")
					.data(Arrays.asList(userAdded))
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(0)
				.status(400)
				.totalResults(1)
				.errorMessage("Ocorreu um erro")
				.build();
		
		ResponseEntity.notFound().build();
		
		return resultRequest;
	}
	
	public ReturnRequest update(Long id, UserDTO user) 
	{
		user.setId(id);
		
		User entity = this.modelMapper.map(user, User.class);
		
		if (!userRepository.existsById(id)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("Usuário não existe na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}
		
		User emailExist = userRepository.findByEmail(user.getEmail());
		
		if (emailExist != null && (user.getId() != emailExist.getId())) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("E-mail pertence a outro usuário na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}
		
		User userUpdated = userRepository.save(entity);
		
		if (userUpdated.equals(userUpdated)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.successMessage("Usuário alterado com sucesso")
					.data(Arrays.asList(userUpdated))
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(0)
				.status(400)
				.totalResults(0)
				.errorMessage("Ocorreu um erro")
				.build();
		
		ResponseEntity.notFound().build();
		
		return resultRequest;
	}
	
	public ReturnRequest delete(Long id) 
	{
		if (!userRepository.existsById(id)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("Usuário não existe na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}
		
		userRepository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(200)
				.totalResults(1)
				.successMessage("Usuário excluído com sucesso")
				.errorMessage("")
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
}
