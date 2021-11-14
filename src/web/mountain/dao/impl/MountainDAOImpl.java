package web.mountain.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.mountain.dao.MountainDAO;
import web.mountain.entity.Mountain;

@Repository
public class MountainDAOImpl implements MountainDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addMoutain(Mountain mountain) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(mountain);
	}
	
	@Override
	public int insertMtnPic(byte[] file, Mountain mountain) {
		Session session = sessionFactory.getCurrentSession();
		mountain.setMountainPic(file);
		session.save(mountain);
		return 1;
	}

	@Override
	public Mountain updateMountain(Mountain mountain) {
		// TODO Auto-generated method stub
		return null;
	}

}
