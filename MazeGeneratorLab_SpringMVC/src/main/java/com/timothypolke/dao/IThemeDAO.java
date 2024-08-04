package com.timothypolke.dao;

import java.util.UUID;
import java.util.List;

import com.timothypolke.entity.Theme;

public interface IThemeDAO {
	public void createorupdate(Theme theme);
	public void delete(Theme theme);
	public Theme read(UUID id);
	public List<Theme> readAll();
}