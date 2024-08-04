package com.timothypolke.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timothypolke.entity.Size;

@Repository
public class SizeDAO implements ISizeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createorupdate(Size size) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(size);
	}
	
	@Override
	public void delete(Size size) {
		Session session=sessionFactory.getCurrentSession();
		session.remove(size);
	}

	@Override
	public Size read(UUID id) {
		Session session=sessionFactory.getCurrentSession();
		return session.get(Size.class, id);
	}

	@Override
	public List<Size> readAll() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Size").getResultList();
	}
	
}