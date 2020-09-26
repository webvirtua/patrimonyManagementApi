package com.webvirtua.patrimony.app.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webvirtua.patrimony.app.contracts.service.IService;
import com.webvirtua.patrimony.app.core.excepions.RunTimeException;
import com.webvirtua.patrimony.app.core.utils.ReturnRequest;
import com.webvirtua.patrimony.app.core.utils.Status;
import com.webvirtua.patrimony.app.dto.BrandDTO;
import com.webvirtua.patrimony.app.model.Brand;
import com.webvirtua.patrimony.app.repository.BrandRepository;

@Service
public class BrandService implements IService<BrandDTO>
{
	private ModelMapper modelMapper;
	
	public BrandService(ModelMapper modelMapper) 
	{
		this.modelMapper = modelMapper;
	}

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private Status status;
	
	public ReturnRequest findAll() 
	{
		List<Brand> brands = brandRepository.findAll();

		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(brands.size())
				.resultsPerPage(0)
				.totalPages(0)
				.page(0)
				.successMessage("Resultados Obtidos")
				.data(brands)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest findOne(Long id) 
	{
		Optional<Brand> brand = brandRepository.findById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1)
				.successMessage("Resultados Obtidos")
				.data(brand)
				.build();
		
		return resultRequest;
	}
	
	public ReturnRequest insert(BrandDTO brand) 
	{
		Brand brandExist = brandRepository.findByName(brand.getName());
		
		if (brandExist != null && !brandExist.equals(brand)) {
			throw new RunTimeException("Marca já existe na base de dados.");
		}
		
		Brand entity = this.modelMapper.map(brand, Brand.class);
		
		Brand brandAdded = brandRepository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode201())
				.totalResults(1)
				.successMessage("Marca inserida com sucesso")
				.data(brandAdded)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest update(Long id, BrandDTO brand) 
	{
		if (!brandRepository.existsById(id)) {
			throw new RunTimeException("Marca não existe na base de dados.");
		}
		
		Brand brandExist = brandRepository.findByName(brand.getName());
		
		if (brandExist != null && (id != brandExist.getId())) {
			throw new RunTimeException("Marca está cadastrada em outro registro na base de dados.");
		}
		
		Brand entity = this.modelMapper.map(brand, Brand.class);
		entity.setId(id);

		Brand brandAdded = brandRepository.save(entity);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.totalResults(1)
				.successMessage("Marca alterada com sucesso")
				.data(brandAdded)
				.build();
		
		ResponseEntity.ok();
		
		return resultRequest;
	}
	
	public ReturnRequest delete(Long id) 
	{
		if (!brandRepository.existsById(id)) {
			throw new RunTimeException("Marca não existe na base de dados.");
		}
		
		brandRepository.deleteById(id);
		
		ReturnRequest resultRequest = ReturnRequest.builder()
				.success(1)
				.status(status.getCode200())
				.successMessage("Marca excluída com sucesso")
				.build();
		
		return resultRequest;
	}
}
