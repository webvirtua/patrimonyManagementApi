package com.webvirtua.patrimony.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	
	public PatrimonyService(ModelMapper modelMapper) 
	{
		this.modelMapper = modelMapper;
	}

	@Autowired
	private PatrimonyRepository patrimonyRepository;
	
	@Autowired
	private BrandRepository brand;
	
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
		Patrimony entity = this.modelMapper.map(patrimony, Patrimony.class);

		//Optional<Brand> findBrand = brand.findById(patrimony.getBrand().getId());
//		EntityManager ent = null;
//		List<Patrimony> result = ent.createQuery("SELECT p FROM Passenger p ORDER BY p.id DESC", Patrimony.class).setMaxResults(1).getResultList();
		
		Patrimony patrimonyAdded = patrimonyRepository.save(entity);
		
		if (patrimonyAdded.equals(patrimonyAdded)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
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