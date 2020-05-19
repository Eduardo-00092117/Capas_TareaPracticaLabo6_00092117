package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;

@Controller
public class MainController {
	
	@Autowired
	private EstudianteDAO estudianteDAO;
	
	@RequestMapping("/inicio")
	public ModelAndView inicio() {
		Estudiante estudiante = new Estudiante();
		ModelAndView mav = new ModelAndView();
		mav.addObject("estudiante", estudiante);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/guardarEstudiante")
	public ModelAndView insertar(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(!result.hasErrors()) {
			try {
				estudianteDAO.insert(estudiante);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			estudiantes = estudianteDAO.findeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		return mav;
	}
	
	@RequestMapping(value = "/borrarEstudiante", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value="codigo") int id) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			estudianteDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			estudiantes = estudianteDAO.findeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		return mav;
	}
}
