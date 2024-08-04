package com.timothypolke.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timothypolke.entity.Maze;
import com.timothypolke.entity.Size;
import com.timothypolke.entity.Theme;
import com.timothypolke.service.IMazeService;
import com.timothypolke.service.ISizeService;
import com.timothypolke.service.IThemeService;

import com.timothypolke.puzzle.Puzzle;

@Controller
@RequestMapping("mazes")
public class MazeController{
	@Autowired
	IMazeService mazeservice;
	@Autowired
	ISizeService sizeservice;
	@Autowired
	IThemeService themeservice;
	@RequestMapping(value="add")
	public ModelAndView add(Model model){
		Map<String, Object> mapModel = new HashMap<String, Object>();
		List<Size> sizes = sizeservice.readAll();
		List<Theme> themes = themeservice.readAll();
		mapModel.put("sizes", sizes);
		mapModel.put("themes", themes);
		mapModel.put("maze", new Maze());
		return new ModelAndView("registermaze", mapModel);
	}
	@RequestMapping(value="save",method=RequestMethod.POST)
	public ModelAndView save(Model model,@ModelAttribute("maze") Maze maze){
		mazeservice.createorupdate(maze);
		return new ModelAndView("redirect:/mazes/");
	}
	@RequestMapping(value="/")
	public ModelAndView list(Model model){
		model.addAttribute("lstmazes",mazeservice.readAll());
		return new ModelAndView("listmazes","",model);
	}
	
	@RequestMapping(value="edit/{id}")
	public ModelAndView edit(@PathVariable("id") UUID id, Model model){
		Map<String, Object> mapModel = new HashMap<String, Object>();
		List<Size> sizes = sizeservice.readAll();
		List<Theme> themes = themeservice.readAll();
		mapModel.put("sizes", sizes);
		mapModel.put("themes", themes);
		mapModel.put("maze",mazeservice.read(id));
		return new ModelAndView("registermaze", mapModel);
	}
	
	@RequestMapping(value="delete/{id}")
	public ModelAndView delete(@PathVariable("id") UUID id, Model model){
		mazeservice.delete(mazeservice.read(id));
		return new ModelAndView("redirect:/mazes/");
	}
	
}