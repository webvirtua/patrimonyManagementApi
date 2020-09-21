package com.webvirtua.patrimony.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webvirtua.patrimony.app.dto.BrandDTO;
import com.webvirtua.patrimony.app.model.Brand;
import com.webvirtua.patrimony.app.repository.BrandRepository;
import com.webvirtua.patrimony.app.resources.utils.ReturnRequest;

@Service
public class BrandService 
{
	private ModelMapper modelMapper;
	
	public BrandService(ModelMapper modelMapper) 
	{
		this.modelMapper = modelMapper;
	}

	@Autowired
	private BrandRepository brandRepository;
	
	public ReturnRequest findAll() 
	{
		List<Brand> brands = brandRepository.findAll();

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(200)
				.totalResults(brands.size())
				.resultsPerPage(0)
				.totalPages(0)
				.page(0)
				.successMessage("Resultados Obtidos")
				.data(brands)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest findOne(Long id) 
	{
		Optional<Brand> brand = brandRepository.findById(id);
		
		if (brand.equals(brand)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.successMessage("Resultados Obtidos")
					.data(Arrays.asList(brand))
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
	
	public ReturnRequest insert(BrandDTO brand) 
	{
		if (brand.getName() == null) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.errorMessage("É obrigatório enviar o nome da marca no corpo da requisição.")
					.build();
			
			ResponseEntity.ok();
			
			return resultRequest;
		}
		
		Brand brandExist = brandRepository.findByName(brand.getName());
		
		if (brandExist != null && !brandExist.equals(brand)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("Marca já existe na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}
		
		Brand entity = this.modelMapper.map(brand, Brand.class);
		
		Brand brandAdded = brandRepository.save(entity);
		
		if (brandAdded.equals(brandAdded)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(201)
					.totalResults(1)
					.successMessage("Marca inserida com sucesso")
					.data(Arrays.asList(brandAdded))
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
	
	public ReturnRequest update(Long id, BrandDTO brand) 
	{
		brand.setId(id);
		
		Brand entity = this.modelMapper.map(brand, Brand.class);
		
		if (!brandRepository.existsById(id)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("Marca não existe na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}
		
		Brand brandExist = brandRepository.findByName(brand.getName());
		
		if (brandExist != null && (brand.getId() != brandExist.getId())) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("Marca está cadastrada em outro registro na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}

		Brand userUpdated = brandRepository.save(entity);
		
		if (userUpdated.equals(userUpdated)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(1)
					.status(200)
					.totalResults(1)
					.successMessage("Marca alterada com sucesso")
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
		if (!brandRepository.existsById(id)) {
			ReturnRequest resultRequest = ReturnRequest.builder()
					.success(0)
					.status(200)
					.errorMessage("Marca não existe na base de dados")
					.build();
			
			ResponseEntity.notFound().build();
			
			return resultRequest;
		}
		
		brandRepository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(200)
				.totalResults(1)
				.successMessage("Marca excluída com sucesso")
				.errorMessage("")
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
}
