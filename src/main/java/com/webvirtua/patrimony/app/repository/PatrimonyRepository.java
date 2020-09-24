package com.webvirtua.patrimony.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webvirtua.patrimony.app.model.Patrimony;

public interface PatrimonyRepository extends JpaRepository<Patrimony, Long> 
{
	@Modifying
    @Query("UPDATE Patrimony p SET p.tumble=:tumble WHERE p.id=:id")
    public void updateTumble(@Param("id") Long id, @Param("tumble") Long tumble);
}
