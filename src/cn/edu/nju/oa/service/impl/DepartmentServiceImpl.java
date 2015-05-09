package cn.edu.nju.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.base.DaoSupportImpl;
import cn.edu.nju.oa.domain.Department;
import cn.edu.nju.oa.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements
		DepartmentService {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Department> getTopList() {
		return sessionFactory.getCurrentSession()
				.createQuery("From Department d WHERE d.parent IS NULL").list();
	}

	@Override
	public List<Department> getChildren(Long parentId) {
		return sessionFactory.getCurrentSession()
				.createQuery("From Department d WHERE d.parent.id = ?")
				.setParameter(0, parentId).list();
	}	
}
