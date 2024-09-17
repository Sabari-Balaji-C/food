package com.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManagementRepository extends JpaRepository<Tasks,Integer>{

}
