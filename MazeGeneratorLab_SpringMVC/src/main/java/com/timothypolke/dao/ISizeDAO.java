package com.timothypolke.dao;

import java.util.UUID;
import java.util.List;

import com.timothypolke.entity.Size;

public interface ISizeDAO {
	public void createorupdate(Size size);
	public void delete(Size size);
	public Size read(UUID id);
	public List<Size> readAll();
}