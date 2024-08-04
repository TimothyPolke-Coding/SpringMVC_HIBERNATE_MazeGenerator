package com.timothypolke.dao;

import java.util.UUID;
import java.util.List;

import com.timothypolke.entity.Maze;

public interface IMazeDAO {
	public void createorupdate(Maze maze);
	public void delete(Maze maze);
	public Maze read(UUID id);
	public List<Maze> readAll();
}