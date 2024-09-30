package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Cart> findByCusId(int cusId);
    
	void deleteByCusIdAndMenId(int cusId, int menId);


}