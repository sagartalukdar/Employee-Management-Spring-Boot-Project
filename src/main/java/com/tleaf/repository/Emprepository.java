package com.tleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tleaf.entity.Employee;

public interface Emprepository extends JpaRepository<Employee, Integer>{

}
