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

import com.timothypolke.entity.Theme;
import com.timothypolke.service.IThemeService;

@Controller
@RequestMapping("themes")
public class ThemeController{
	@Autowired
	IThemeService themeservice;
	@RequestMapping(value="add")
	public ModelAndView add(Model model){
		model.addAttribute("theme",new Theme());
		return new ModelAndView("registertheme","",model);
	}
	@RequestMapping(value="save",method=RequestMethod.POST)
	public ModelAndView save(Model model,@ModelAttribute("theme") Theme theme){
		themeservice.createorupdate(theme);
		model.addAttribute("theme",theme);
		return new ModelAndView("redirect:/themes/");
	}
	@RequestMapping(value="/")
	public ModelAndView list(Model model){
		model.addAttribute("lstthemes",themeservice.readAll());
		return new ModelAndView("listthemes","",model);
	}
	
	@RequestMapping(value="edit/{id}")
	public ModelAndView edit(@PathVariable("id") UUID id, Model model){
		model.addAttribute("theme",themeservice.read(id));
		return new ModelAndView("registertheme","",model);
	}
	
	@RequestMapping(value="delete/{id}")
	public ModelAndView delete(@PathVariable("id") UUID id, Model model){
		themeservice.delete(themeservice.read(id));
		return new ModelAndView("redirect:/themes/");
	}

}