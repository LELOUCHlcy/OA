package cn.edu.nju.oa.service;

import java.util.List;

import cn.edu.nju.oa.base.DaoSupport;
import cn.edu.nju.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department>  {

	List<Department> getAll();

	void delete(Long id);

	void save(Department department);

	Department getById(Long id);

	void update(Department department);

	List<Department> getTopList();

	List<Department> getChildren(Long parentId);

}
