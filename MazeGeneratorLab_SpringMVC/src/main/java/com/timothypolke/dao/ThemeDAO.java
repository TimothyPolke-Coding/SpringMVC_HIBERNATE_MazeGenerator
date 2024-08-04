package com.timothypolke.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timothypolke.entity.Theme;

@Repository
public class ThemeDAO implements IThemeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createorupdate(Theme theme) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(theme);
	}
	
	@Override
	public void delete(Theme theme) {
		Session session=sessionFactory.getCurrentSession();
		session.remove(theme);
	}

	@Override
	public Theme read(UUID id) {
		Session session=sessionFactory.getCurrentSession();
		return session.get(Theme.class, id);
	}

	@Override
	public List<Theme> readAll() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Theme").getResultList();
	}
	
}