package com.timothypolke.service;

import java.util.UUID;
import java.util.List;

import com.timothypolke.entity.Maze;

public interface IMazeService {
	public void createorupdate(Maze maze);
	public void delete(Maze maze);
	public Maze read(UUID id);
	public List<Maze> readAll();
}