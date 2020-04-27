package com.uca.capas.modelo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/inicio")
	public String inicio() {
		return "inicio";
	}
	
	@RequestMapping("/validar")
	public ModelAndView inicio(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//Lista quemada para efectos de ejemplo...
		List<String> lista = new ArrayList<>();
		
		String nombre = request.getParameter("Nombre");
		String apellido = request.getParameter("Apellido");
		String fechanac = request.getParameter("Fecha");
		String lugar = request.getParameter("Lugar");
		String colegio = request.getParameter("Colegio");
		String telefono = request.getParameter("Telefono");
		String celular = request.getParameter("Celular");
		
		String fechaInicio = "2003-01-01";
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
		Date fechaInicioDate = date.parse(fechaInicio);
		Date fechaingresada = date.parse(fechanac);
		if(fechaInicioDate.after(fechaingresada)){
			 lista.add("La Fecha debe ser mayor a 01/01/2003 (debe ser mas reciente)");
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
		if(nombre.length() > 25 || nombre.length() < 1) {
			lista.add("El campo Nombre no puede ser menor a 1 caracter ni mayor a 25 caracteres");
		}
		if(apellido.length() > 25 || apellido.length() < 1) {
			lista.add("El campo Apellido no puede ser menor a 1 caracter ni mayor a 25 caracteres");
		}
		
		if(lugar.length() > 25 || lugar.length() < 1) {
			lista.add("El campo Lugar de nacimiento no puede ser menor a 1 caracter ni mayor a 25 caracteres");
		}
		if(colegio.length() > 25 || colegio.length() < 1) {
			lista.add("El campo Colegio no puede ser menor a 1 caracter ni mayor a 25 caracteres");
		}
		if(telefono.length() != 8) {
			lista.add("El campo Telefono debe contener exactamente 8 digitos");
		}
		if(celular.length() != 8) {
			lista.add("El campo Celular debe contener exactamente 8 digitos");
		}
		
		if(lista.isEmpty()) {
			mav.setViewName("aceptado");
		}else {
			mav.addObject("lista", lista);
			mav.setViewName("error");
			
		}
		
		return mav;
	}

}
