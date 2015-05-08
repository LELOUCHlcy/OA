package cn.edu.nju.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.dao.RoleDao;
import cn.edu.nju.oa.domain.Role;
import cn.edu.nju.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;

	public List<Role> getAll() {
		return roleDao.getAll();
	}

	@Override
	public void delete(Long id) {
		roleDao.delete(id);
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

}
