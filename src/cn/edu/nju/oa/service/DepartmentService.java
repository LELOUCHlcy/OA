package cn.edu.nju.oa.service;

import java.util.List;

import cn.edu.nju.oa.domain.Department;

public interface DepartmentService {

	List<Department> getAll();

	void delete(Long id);

	void save(Department department);

	Department getById(Long id);

	void update(Department department);

}
