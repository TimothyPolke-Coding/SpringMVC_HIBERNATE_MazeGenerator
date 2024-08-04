package com.timothypolke.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timothypolke.entity.Maze;

@Repository
public class MazeDAO implements IMazeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createorupdate(Maze maze) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(maze);
	}
	
	@Override
	public void delete(Maze maze) {
		Session session=sessionFactory.getCurrentSession();
		session.remove(maze);
	}

	@Override
	public Maze read(UUID id) {
		Session session=sessionFactory.getCurrentSession();
		return session.get(Maze.class, id);
	}

	@Override
	public List<Maze> readAll() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Maze").getResultList();
	}
	
}