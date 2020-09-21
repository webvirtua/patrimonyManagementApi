package com.webvirtua.patrimony.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webvirtua.patrimony.app.dto.PatrimonyDTO;
import com.webvirtua.patrimony.app.model.Brand;
import com.webvirtua.patrimony.app.model.Patrimony;
import com.webvirtua.patrimony.app.repository.BrandRepository;
import com.webvirtua.patrimony.app.repository.PatrimonyRepository;
import com.webvirtua.patrimony.app.resources.utils.ReturnRequest;

@Service
public class PatrimonyService 
{
	private ModelMapper modelMapper;
	
	@PersistenceContext
	private EntityManager con;
	
	public PatrimonyService(ModelMapper modelMapper) 
	{
		this.modelMapper = modelMapper;
	}

	@Autowired
	private PatrimonyRepository patrimonyRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	public ReturnRequest findAll() 
	{
		List<Patrimony> patrimony = patrimonyRepository.findAll();

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(200)
				.totalResults(patrimony.size())
				.resultsPerPage(0)
				.totalPages(0)
				.page(0)
				.successMessage("Resultados Obtidos")
				.data(patrimony)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest findOne(Long id) 
	{
		Optional<Patrimony> patrimony = patrimonyRepository.findById(id);
		
		if (patrimony.equals(patrimony)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.successMessage("Resultados Obtidos")
					.data(Arrays.asList(patrimony))
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
	
	public ReturnRequest insert(PatrimonyDTO patrimony) 
	{	
		List<?> lastPatrimony = con.createQuery("SELECT p.id FROM Patrimony p ORDER BY p.id DESC").setMaxResults(1).getResultList(); 
		
		if (lastPatrimony.size() > 0) {
			patrimony.setTumble((Long)lastPatrimony.get(0) + 1);
		} else {
			patrimony.setTumble(1L);
		}
		
		Patrimony entity = this.modelMapper.map(patrimony, Patrimony.class);

		if (!(patrimony.getBrand().getId() > 0)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.errorMessage("É obrigatório enviar o ID da marca no corpo da requisição.")
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}
		
		if (patrimony.getName() == null) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.errorMessage("É obrigatório enviar o nome do patrimônio no corpo da requisição.")
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}

		Patrimony patrimonyAdded = patrimonyRepository.save(entity);
		
		if (patrimonyAdded.equals(patrimonyAdded)) {
			Optional<Brand> brand = brandRepository.findById(patrimonyAdded.getBrand().getId());
			patrimonyAdded.setBrand(brand.get());
			
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(201)
					.totalResults(1)
					.successMessage("Patrimônio inserido com sucesso")
					.data(Arrays.asList(patrimonyAdded))
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
	
	public ReturnRequest update(Long id, PatrimonyDTO patrimony) 
	{
		patrimony.setId(id);
		
		Patrimony entity = this.modelMapper.map(patrimony, Patrimony.class);
		
		if (!patrimonyRepository.existsById(id)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("Patrimonio não existe na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}

		Patrimony patrimonyUpdated = patrimonyRepository.save(entity);
		
		if (patrimonyUpdated.equals(patrimonyUpdated)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.successMessage("Patrimonio alterado com sucesso")
					.data(Arrays.asList(patrimonyUpdated))
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
		if (!patrimonyRepository.existsById(id)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("Patrimonio não existe na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}
		
		patrimonyRepository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(200)
				.totalResults(1)
				.successMessage("Patrimonio excluído com sucesso")
				.errorMessage("")
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
}
