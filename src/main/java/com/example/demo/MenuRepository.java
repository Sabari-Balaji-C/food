package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByRestId(int restId);
    @Query("SELECT m.menPrice FROM Menu m WHERE m.menId = :menId")
    Double findMenuPriceById(@Param("menId") int menId);
 
    
}
