package com.webvirtua.patrimony.app.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webvirtua.patrimony.app.core.excepions.RunTimeException;
import com.webvirtua.patrimony.app.core.utils.ReturnRequest;
import com.webvirtua.patrimony.app.dto.UserDTO;
import com.webvirtua.patrimony.app.model.User;
import com.webvirtua.patrimony.app.repository.UserRepository;

import com.webvirtua.patrimony.app.core.utils.Status;

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
	
	@Autowired
	private Status status;
	
	public ReturnRequest findAll() 
	{
		List<User> users = userRepository.findAll();

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(users.size())
				.resultsPerPage(0)
				.totalPages(0)
				.page(0)
				.successMessage("Resultados Obtidos")
				.data(users)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest findOne(Long id) 
	{
		Optional<User> user = userRepository.findById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1)
				.successMessage("Resultados Obtidos")
				.data(user)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest insert(UserDTO user) 
	{
		User emailExist = userRepository.findByEmail(user.getEmail());
		
		if (emailExist != null && !emailExist.equals(user)) {
			throw new RunTimeException("E-mail já existe na base de dados.");
		}

		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		
		User entity = this.modelMapper.map(user, User.class);
		
		User userAdded = userRepository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode201())
				.totalResults(1)
				.successMessage("Usuário inserido com sucesso")
				.data(userAdded)
				.build();

		return resultRequest;
	}
	
	public ReturnRequest update(Long id, UserDTO user) 
	{
		if (!userRepository.existsById(id)) {
			throw new RunTimeException("Usuário não existe na base de dados.");
		}
		
		User emailExist = userRepository.findByEmail(user.getEmail());
		
		if (emailExist != null && (id != emailExist.getId())) {
			throw new RunTimeException("E-mail pertence a outro usuário na base de dados.");
		}
		
		user.setId(id);
		
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		
		User entity = this.modelMapper.map(user, User.class);
		
		User userUpdated = userRepository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1)
				.successMessage("Usuário alterado com sucesso")
				.data(userUpdated)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest delete(Long id) 
	{	
		if (!userRepository.existsById(id)) {
			throw new RunTimeException("Usuário não existe na base de dados.");
		}
		
		userRepository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.successMessage("Usuário excluído com sucesso")
				.build();
		
		return resultRequest;
	}
}
