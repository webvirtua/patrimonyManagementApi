package com.webvirtua.patrimony.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webvirtua.patrimony.app.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> 
{
	Brand findByName(String name);
}
