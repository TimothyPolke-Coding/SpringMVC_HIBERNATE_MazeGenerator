package com.timothypolke.service;

import java.util.UUID;
import java.util.List;

import com.timothypolke.entity.Size;

public interface ISizeService {
	public void createorupdate(Size size);
	public void delete(Size size);
	public Size read(UUID id);
	public List<Size> readAll();
}