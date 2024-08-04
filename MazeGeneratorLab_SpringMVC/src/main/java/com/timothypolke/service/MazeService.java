package com.timothypolke.service;

import java.awt.Color;
import java.util.UUID;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timothypolke.dao.IMazeDAO;
import com.timothypolke.entity.Maze;
import com.timothypolke.entity.Size;
import com.timothypolke.entity.Theme;
import com.timothypolke.puzzle.Puzzle;

@Service
@Transactional
public class MazeService implements IMazeService {
	
	@Autowired
	ISizeService sizeService;
	@Autowired
	IThemeService themeService;
	
	@Autowired
	IMazeDAO mazeDAO;
	
	@Override
	public void createorupdate(Maze maze) {
		
		List<Size> sizes = sizeService.readAll();
		for (int i = 0; i < sizes.size(); i++) {
			if (sizes.get(i).getSizeID().equals(maze.getSize().getSizeID())) {
				maze.setSize(sizes.get(i));
			}
		}
		List<Theme> themes = themeService.readAll();
		for (int i = 0; i < themes.size(); i++) {
			if (themes.get(i).getThemeID().equals(maze.getTheme().getThemeID())) {
				maze.setTheme(themes.get(i));
			}
		}
		
		Puzzle puzzle = new Puzzle(Integer.valueOf(maze.getSize().getColumnCount()), Integer.valueOf(maze.getSize().getRowCount()), Integer.valueOf(maze.getSize().getWallSize()), Integer.valueOf(maze.getSize().getCellSize()), new Color(Integer.valueOf(maze.getTheme().getForeground_red()), Integer.valueOf(maze.getTheme().getForeground_green()), Integer.valueOf(maze.getTheme().getForeground_blue())), new Color(Integer.valueOf(maze.getTheme().getBackground_red()), Integer.valueOf(maze.getTheme().getBackground_green()), Integer.valueOf(maze.getTheme().getBackground_blue())), new Color(Integer.valueOf(maze.getTheme().getHighlight_red()), Integer.valueOf(maze.getTheme().getHighlight_green()), Integer.valueOf(maze.getTheme().getHighlight_blue())));
		
		maze.setSolvedImage(puzzle.getSolved());
		maze.setUnsolvedImage(puzzle.getUnsolved());
		
		mazeDAO.createorupdate(maze);
	}

	@Override
	public void delete(Maze maze) {
		mazeDAO.delete(maze);
	}
	
	@Override
	public Maze read(UUID id) {
		return mazeDAO.read(id);
	}	
	
	@Override
	public List<Maze> readAll() {
		return mazeDAO.readAll();
	}
	
}