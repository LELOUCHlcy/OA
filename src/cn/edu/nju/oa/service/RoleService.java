package cn.edu.nju.oa.service;

import java.util.List;

import cn.edu.nju.oa.domain.Role;

public interface RoleService {

	List<Role> getAll();

	void delete(Long id);

	void save(Role role);

	Role getById(Long id);

	void update(Role role);

}
