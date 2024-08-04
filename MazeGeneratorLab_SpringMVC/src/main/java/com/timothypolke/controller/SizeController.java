package com.timothypolke.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timothypolke.entity.Size;
import com.timothypolke.service.ISizeService;

@Controller
@RequestMapping("sizes")
public class SizeController{
	@Autowired
	ISizeService sizeservice;
	@RequestMapping(value="add")
	public ModelAndView add(Model model){
		model.addAttribute("size",new Size());
		return new ModelAndView("registersize","",model);
	}
	@RequestMapping(value="save",method=RequestMethod.POST)
	public ModelAndView save(Model model,@ModelAttribute("size") Size size){
		sizeservice.createorupdate(size);
		model.addAttribute("size",size);
		return new ModelAndView("redirect:/sizes/");
	}
	@RequestMapping(value="/")
	public ModelAndView list(Model model){
		model.addAttribute("lstsizes",sizeservice.readAll());
		return new ModelAndView("listsizes","",model);
	}
	
	@RequestMapping(value="edit/{id}")
	public ModelAndView edit(@PathVariable("id") UUID id, Model model){
		model.addAttribute("size",sizeservice.read(id));
		return new ModelAndView("registersize","",model);
	}
	
	@RequestMapping(value="delete/{id}")
	public ModelAndView delete(@PathVariable("id") UUID id, Model model){
		sizeservice.delete(sizeservice.read(id));
		return new ModelAndView("redirect:/sizes/");
	}

}