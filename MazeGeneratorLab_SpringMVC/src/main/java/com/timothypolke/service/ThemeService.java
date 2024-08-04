package com.timothypolke.service;

import java.util.UUID;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timothypolke.dao.IThemeDAO;
import com.timothypolke.entity.Theme;

@Service
@Transactional
public class ThemeService implements IThemeService {

	@Autowired
	IThemeDAO themeDAO;
	
	@Override
	public void createorupdate(Theme theme) {
		themeDAO.createorupdate(theme);
	}

	@Override
	public void delete(Theme theme) {
		themeDAO.delete(theme);
	}
	
	@Override
	public Theme read(UUID id) {
		return themeDAO.read(id);
	}	
	
	@Override
	public List<Theme> readAll() {
		return themeDAO.readAll();
	}
	
}