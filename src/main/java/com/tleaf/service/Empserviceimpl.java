package com.tleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tleaf.entity.Employee;
import com.tleaf.repository.Emprepository;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Service
public class Empserviceimpl implements Empservice{

	@Autowired
	private Emprepository emprepo;
	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
		Employee e=emprepo.save(emp);
		return e;
	}

	@Override
	public List<Employee> Emplist() {
		// TODO Auto-generated method stub
		return emprepo.findAll();
	}

	@Override
	public Employee getEmpbyId(int id) {
		// TODO Auto-generated method stub
		return emprepo.findById(id).get();
	}


	@Override
	public boolean deleteEmp(int id) {
		// TODO Auto-generated method stub
		Employee e=emprepo.findById(id).get();
		if(e!=null) {
			emprepo.delete(e);
			return true;
		}
		else return false;
	}

	public void removeMessage() {
	HttpSession hs=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes()))
			.getRequest().getSession();
	hs.removeAttribute("msg");
	}
}
