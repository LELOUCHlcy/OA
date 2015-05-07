package cn.edu.nju.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.domain.User;

@Service
public class TestService {
	
	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void save() {
		Session session = sessionFactory.getCurrentSession();
		session.save(new User());
		session.save(new User());
	}
}
