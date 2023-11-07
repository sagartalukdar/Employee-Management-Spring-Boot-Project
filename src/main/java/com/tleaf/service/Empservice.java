package com.tleaf.service;

import java.util.List;

import com.tleaf.entity.Employee;

public interface Empservice {

	public Employee saveEmployee(Employee emp) ;
	public List<Employee> Emplist() ;
	public Employee getEmpbyId(int id) ;
	
	public boolean deleteEmp(int id) ;
}
