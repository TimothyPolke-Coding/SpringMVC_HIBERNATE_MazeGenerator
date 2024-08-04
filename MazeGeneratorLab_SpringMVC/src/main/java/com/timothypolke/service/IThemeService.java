package com.timothypolke.service;

import java.util.UUID;
import java.util.List;

import com.timothypolke.entity.Theme;

public interface IThemeService {
	public void createorupdate(Theme theme);
	public void delete(Theme theme);
	public Theme read(UUID id);
	public List<Theme> readAll();
}