package com.timothypolke.service;

import java.util.UUID;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timothypolke.dao.ISizeDAO;
import com.timothypolke.entity.Size;

@Service
@Transactional
public class SizeService implements ISizeService {

	@Autowired
	ISizeDAO sizeDAO;
	
	@Override
	public void createorupdate(Size size) {
		sizeDAO.createorupdate(size);
	}

	@Override
	public void delete(Size size) {
		sizeDAO.delete(size);
	}
	
	@Override
	public Size read(UUID id) {
		return sizeDAO.read(id);
	}	
	
	@Override
	public List<Size> readAll() {
		return sizeDAO.readAll();
	}
	
}