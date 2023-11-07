package com.tleaf.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tleaf.entity.Employee;
import com.tleaf.service.Empservice;

import jakarta.servlet.http.HttpSession;

@Controller
public class Homecontroller {

	@Autowired
	private Empservice es;
	@GetMapping("/")
	public String indexpage(Model m) {
		List<Employee> el=es.Emplist();
		m.addAttribute("emplist", el);
		return "index";
	}
	@GetMapping("editemp/{id}")
	public String editemployee(@PathVariable("id") int id,Model m) {
		Employee e=es.getEmpbyId(id);
		m.addAttribute("upemp", e);
		return "editemp";
	}
	@GetMapping("saveemp")
	public String saveemployee() {
		return "saveemp";
	}
	
	@PostMapping("newentry")
	public String newEntry(@ModelAttribute Employee emp,HttpSession hs) {
		emp.setCreatedAt(LocalDateTime.now().toString());
		Employee e=es.saveEmployee(emp);
		if(e!=null) {
			hs.setAttribute("msg", "Employee Added");
		}else {
			hs.setAttribute("msg", "Employee not Added yet!");
		}
		
		return "redirect:/saveemp";
	}
	@PostMapping("updateemp")
	public String update(@ModelAttribute Employee e,HttpSession hs) {
		e.setUpdatedAt(LocalDateTime.now().toString());
		Employee em=es.saveEmployee(e);
		if(em!=null) {
			hs.setAttribute("msg", "Employee Updated");
		}else {
			hs.setAttribute("msg", "not Updated yet!");
		}
		return "redirect:/";
	}
	@RequestMapping("deleteemp/{id}")
	public String delete(@PathVariable("id") int id,HttpSession hs) {
	  if(es.deleteEmp(id)) {
		  hs.setAttribute("msg", "employee deleted");
	  }else {
		  hs.setAttribute("msg", "not deleted !please do currectly");
	  }
	  return "redirect:/";
	}
}
