package com.webvirtua.patrimony.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webvirtua.patrimony.app.model.Patrimony;

public interface PatrimonyRepository extends JpaRepository<Patrimony, Long> 
{

}
