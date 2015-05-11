package cn.edu.nju.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.base.DaoSupportImpl;
import cn.edu.nju.oa.domain.Privilege;
import cn.edu.nju.oa.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements
		PrivilegeService {

	@Override
	public List<Privilege> getTopPrivilegeList() {
		return getSession().createQuery(
				"From Privilege p WHERE p.parent IS NULL").list();
	}

}
