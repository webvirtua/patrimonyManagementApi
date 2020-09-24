package com.webvirtua.patrimony.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webvirtua.patrimony.app.contracts.service.IService;
import com.webvirtua.patrimony.app.core.excepions.RunTimeException;
import com.webvirtua.patrimony.app.core.utils.ReturnRequest;
import com.webvirtua.patrimony.app.core.utils.Status;
import com.webvirtua.patrimony.app.dto.PatrimonyDTO;
import com.webvirtua.patrimony.app.model.Brand;
import com.webvirtua.patrimony.app.model.Patrimony;
import com.webvirtua.patrimony.app.repository.BrandRepository;
import com.webvirtua.patrimony.app.repository.PatrimonyRepository;

@Service
public class PatrimonyService implements IService<PatrimonyDTO>
{
	private ModelMapper modelMapper;
	
	public PatrimonyService(ModelMapper modelMapper) 
	{
		this.modelMapper = modelMapper;
	}

	@Autowired
	private PatrimonyRepository patrimonyRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private Status status;
	
	@PersistenceContext
	private EntityManager con;
	
	public ReturnRequest findAll() 
	{
		List<Patrimony> patrimony = patrimonyRepository.findAll();

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(patrimony.size())
				.resultsPerPage(0)
				.totalPages(0)
				.page(0)
				.successMessage("Resultados Obtidos")
				.data(patrimony)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest findOne(Long id) 
	{
		Optional<Patrimony> patrimony = patrimonyRepository.findById(id);

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1)
				.successMessage("Resultados Obtidos")
				.data(patrimony)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest insert(PatrimonyDTO patrimony) 
	{
		Patrimony entity = this.modelMapper.map(patrimony, Patrimony.class);

		Patrimony patrimonyAdded = patrimonyRepository.save(entity);
		
		Optional<Brand> brand = brandRepository.findById(patrimonyAdded.getBrand().getId());
		patrimonyAdded.setBrand(brand.get());
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode201())
				.totalResults(1)
				.successMessage("Patrimônio inserido com sucesso")
				.data(patrimonyAdded)
				.build();

		return resultRequest;
	}
	
	public ReturnRequest update(Long id, PatrimonyDTO patrimony) 
	{
		patrimony.setId(id);
		
		Patrimony entity = this.modelMapper.map(patrimony, Patrimony.class);

		Patrimony patrimonyUpdated = patrimonyRepository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1)
				.successMessage("Patrimonio alterado com sucesso")
				.data(patrimonyUpdated)
				.build();

		return resultRequest;
	}
	
	public ReturnRequest delete(Long id) 
	{
		if (!patrimonyRepository.existsById(id)) {
			throw new RunTimeException("Patrimonio não existe na base de dados.");
		}
		
		patrimonyRepository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.successMessage("Patrimonio excluído com sucesso")
				.build();
		
		return resultRequest;
	}
}
