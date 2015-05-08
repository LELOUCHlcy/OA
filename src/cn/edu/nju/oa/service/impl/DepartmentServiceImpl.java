package cn.edu.nju.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.dao.DepartmentDao;
import cn.edu.nju.oa.domain.Department;
import cn.edu.nju.oa.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource
	private DepartmentDao departmentDao;

	@Override
	public List<Department> getAll() {
		return departmentDao.getAll();
	}

	@Override
	public void delete(Long id) {
		departmentDao.delete(id);
	}

	@Override
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	public Department getById(Long id) {
		return departmentDao.getById(id);
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}

}
